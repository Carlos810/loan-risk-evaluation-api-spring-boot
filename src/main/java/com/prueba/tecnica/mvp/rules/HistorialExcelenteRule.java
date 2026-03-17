package com.prueba.tecnica.mvp.rules;


import com.prueba.tecnica.mvp.context.RiskEvaluationContext;
import com.prueba.tecnica.mvp.enumeraciones.NameOfRule;
import com.prueba.tecnica.mvp.enumeraciones.RiskLevel;
import com.prueba.tecnica.mvp.interfaces.global_operation.IRiskRule;
import com.prueba.tecnica.mvp.model.result.RuleResult;
import org.springframework.stereotype.Component;

@Component
public class HistorialExcelenteRule implements IRiskRule {

    @Override
    public RuleResult evaluate(RiskEvaluationContext context) {

        if(context.getHistorialPagos().isHistorialExcelente()){
            return new RuleResult(
                    NameOfRule.HISTORIAL_EXCELENTE.toString(),
                    true,
                    RiskLevel.BAJO,
                    "Historial excelente"
            );
        }

        return new RuleResult(
                NameOfRule.HISTORIAL_EXCELENTE.toString(),
                true,
                RiskLevel.ALTO,
                "Historial con Morosidad"
        );
    }
}
