package com.prueba.tecnica.mvp.rules;

import com.prueba.tecnica.mvp.engine.RiskEvaluationContext;
import com.prueba.tecnica.mvp.model.RiskLevel;
import com.prueba.tecnica.mvp.model.RuleResult;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AltaSolicitudVsVentasRule implements RiskRule {


    @Override
    public RuleResult evaluate(RiskEvaluationContext context) {
        BigDecimal monto = context.getRequest().getMontoSolicitado();
        BigDecimal ventas = context.getDatosContables().getVentasPromedioMensual();

        if(monto.compareTo(ventas.multiply(new BigDecimal(8))) > 0){
            return new RuleResult(
                    true,
                    RiskLevel.ALTO,
                    "Monto solicitado excede 8 veces las ventas promedio"
            );
        }

        return new RuleResult(
                true,
                RiskLevel.BAJO,
                "Monto dentro del rango permitido"
        );
    }


}
