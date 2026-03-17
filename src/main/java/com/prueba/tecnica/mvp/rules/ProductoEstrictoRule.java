package com.prueba.tecnica.mvp.rules;

import com.prueba.tecnica.mvp.context.RiskEvaluationContext;
import com.prueba.tecnica.mvp.enumeraciones.NameOfRule;
import com.prueba.tecnica.mvp.enumeraciones.ProductoFinanciero;
import com.prueba.tecnica.mvp.enumeraciones.RiskLevel;
import com.prueba.tecnica.mvp.interfaces.global_operation.IRiskRule;
import com.prueba.tecnica.mvp.model.result.RuleResult;
import org.springframework.stereotype.Component;

@Component
public class ProductoEstrictoRule implements IRiskRule {
    @Override
    public RuleResult evaluate(RiskEvaluationContext context) {
        String productoFinanciero = context.getRequest().getProductoFinanciero().toString().toUpperCase().trim();
        System.out.println("producto financiero parametro: "+productoFinanciero);
        if(productoFinanciero == ProductoFinanciero.ARRENDAMIENTO_FINANCIERO.toString().toUpperCase().trim()){
            return new RuleResult(
                    NameOfRule.PRODUCTO_ESTRICTO.toString(),
                    true,
                    RiskLevel.ALTO,
                    "Producto de Alto riesgo"
            );
        }

        return new RuleResult(NameOfRule.PRODUCTO_ESTRICTO.toString(),false,null,null);
    }
}
