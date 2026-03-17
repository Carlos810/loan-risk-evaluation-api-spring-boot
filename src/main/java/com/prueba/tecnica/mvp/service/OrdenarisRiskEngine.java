package com.prueba.tecnica.mvp.service;

import com.prueba.tecnica.mvp.engine.RiskEvaluationContext;
import com.prueba.tecnica.mvp.model.RiskEvaluationResult;
import com.prueba.tecnica.mvp.model.RiskLevel;
import com.prueba.tecnica.mvp.model.RuleResult;
import com.prueba.tecnica.mvp.rules.RiskRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdenarisRiskEngine {
    private final List<RiskRule> rules;

    public RiskEvaluationResult evaluate(RiskEvaluationContext context){
        Integer countRules = rules.size();
        System.out.println("loaded rules: " + countRules);

        if(countRules == 0) {
            throw new IllegalArgumentException("There is not rules loaded, please review '@Components as rules'");
        }

        RiskLevel risk = RiskLevel.BAJO;

        List<RuleResult> resultados = new ArrayList<>();

        for (RiskRule rule: rules){
            RuleResult result = rule.evaluate(context);
            resultados.add(result);

            if(result.getRiskLevel() != null){
                risk = result.getRiskLevel();
            }

        }
        return new RiskEvaluationResult(risk,resultados);
    }

}
