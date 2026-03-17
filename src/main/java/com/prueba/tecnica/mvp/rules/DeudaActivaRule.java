package com.prueba.tecnica.mvp.rules;

import com.prueba.tecnica.mvp.context.RiskEvaluationContext;
import com.prueba.tecnica.mvp.enumeraciones.NameOfRule;
import com.prueba.tecnica.mvp.enumeraciones.RiskLevel;
import com.prueba.tecnica.mvp.interfaces.global_operation.IRiskRule;
import com.prueba.tecnica.mvp.model.result.RuleResult;
import org.springframework.stereotype.Component;

@Component
public class DeudaActivaRule implements IRiskRule {
    @Override
    public RuleResult evaluate(RiskEvaluationContext context) {
        if(context.getHistorialPagos().isDeudaMayor90Dias()){

            return new RuleResult(
                    NameOfRule.DEUDA_ACTIVA.name(),
                    true,
                    RiskLevel.RECHAZADO,
                    "Deuda vencida mayor a 90 días"
            );
        }
        return new RuleResult(
                NameOfRule.DEUDA_ACTIVA.name(),
                false,
                RiskLevel.MEDIO,
                "Deuda anterior esta en el rango de 3 meses"
        );
    }
}
