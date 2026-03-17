package com.prueba.tecnica.mvp.rules;

import com.prueba.tecnica.mvp.context.RiskEvaluationContext;
import com.prueba.tecnica.mvp.enumeraciones.NameOfRule;
import com.prueba.tecnica.mvp.enumeraciones.RiskLevel;
import com.prueba.tecnica.mvp.interfaces.global_operation.IRiskRule;
import com.prueba.tecnica.mvp.model.result.RuleResult;
import org.springframework.stereotype.Component;

@Component
public class DemandaLegalRule implements IRiskRule {

    @Override
    public RuleResult evaluate(RiskEvaluationContext context) {

        if(context.getEstadoLegal().isJuicioActivo()){
            return new RuleResult(
                    NameOfRule.DEMANDA_LEGAL.toString(),
              true,
                    RiskLevel.ALTO,
                    "Empresa con proceso legal Activo"
            );
        }

        return new RuleResult(NameOfRule.DEMANDA_LEGAL.toString(),false,null,null);
    }
}
