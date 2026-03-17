package com.prueba.tecnica.mvp.engine;

import com.prueba.tecnica.mvp.Validate.Validations;
import com.prueba.tecnica.mvp.context.RiskEvaluationContext;
import com.prueba.tecnica.mvp.model.result.RiskEvaluationResult;
import com.prueba.tecnica.mvp.enumeraciones.RiskLevel;
import com.prueba.tecnica.mvp.model.result.RuleResult;
import com.prueba.tecnica.mvp.interfaces.global_operation.IRiskRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdenarisRiskEngine {
    private final List<IRiskRule> _rules;
    private final Validations _utils;

    public RiskEvaluationResult evaluate(RiskEvaluationContext context){
        _utils.readAndLoadProviders(_rules);
        RiskLevel risk = RiskLevel.BAJO;

        List<RuleResult> resultados = new ArrayList<>();

        for (IRiskRule rule: _rules){
            RuleResult result = rule.evaluate(context);
            resultados.add(result);

            /*if(result.getRiskLevel() != null){
                risk = result.getRiskLevel();
            }*/

            if(result.getRiskLevel() == RiskLevel.RECHAZADO){
                return new RiskEvaluationResult(RiskLevel.RECHAZADO,result.getMessage(), resultados);
            }
            risk = RiskLevel.max(risk, result.getRiskLevel());
        }


        String motivoFinal = resultados.stream()
                .filter(r -> r.getRiskLevel() != null)
                .max(Comparator.comparing(r -> r.getRiskLevel().ordinal()))
                .map(RuleResult::getMessage)
                .orElse("Evaluación sin hallazgos");

        return new RiskEvaluationResult(risk,motivoFinal,resultados);

    }

}
