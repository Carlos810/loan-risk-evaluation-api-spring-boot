package com.prueba.tecnica.mvp.rules;

import com.prueba.tecnica.mvp.context.RiskEvaluationContext;
import com.prueba.tecnica.mvp.enumeraciones.NameOfRule;
import com.prueba.tecnica.mvp.enumeraciones.RiskLevel;
import com.prueba.tecnica.mvp.interfaces.global_operation.IRiskRule;
import com.prueba.tecnica.mvp.model.result.RuleResult;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class EmpresaNuevaRule implements IRiskRule {
    @Override
    public RuleResult evaluate(RiskEvaluationContext context) {
        LocalDate fecha = context.getEstadoLegal().getFechaConstitucion();
        Long meses = ChronoUnit.MONTHS.between(fecha, LocalDateTime.now());

        if(meses < 18){
            return new RuleResult(
                    NameOfRule.EMPRESA_NUEVA.name(),
                    true,
                    RiskLevel.MEDIO,
                    "Empresa con menos de 18 meses"
            );
        }

        return new RuleResult(NameOfRule.EMPRESA_NUEVA.toString(),true,RiskLevel.BAJO,"Empresa tiene más de 1 año y medio operando");
    }
}
