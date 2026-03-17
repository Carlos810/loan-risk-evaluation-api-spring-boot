package com.prueba.tecnica.mvp.rules;

import com.prueba.tecnica.mvp.context.RiskEvaluationContext;
import com.prueba.tecnica.mvp.enumeraciones.NameOfRule;
import com.prueba.tecnica.mvp.enumeraciones.RiskLevel;
import com.prueba.tecnica.mvp.interfaces.global_operation.IRiskRule;
import com.prueba.tecnica.mvp.model.result.RuleResult;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AltaSolicitudVsVentasRule implements IRiskRule {


    @Override
    public RuleResult evaluate(RiskEvaluationContext context) {
        BigDecimal monto = context.getRequest().getMontoSolicitado();
        BigDecimal ventas = context.getDatosContables().getVentasPromedioMensual();

        if(monto.compareTo(ventas.multiply(new BigDecimal(8))) > 0){
            return new RuleResult(
                    NameOfRule.ALTA_SOLICITUD_VS_VENTA.toString(),
                    true,
                    RiskLevel.ALTO,
                    "Monto solicitado excede 8 veces las ventas promedio"
            );
        }

        return new RuleResult(
                NameOfRule.ALTA_SOLICITUD_VS_VENTA.toString(),
                true,
                RiskLevel.BAJO,
                "Monto dentro del rango permitido"
        );
    }


}
