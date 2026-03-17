package com.prueba.tecnica.mvp.Validate;

import com.prueba.tecnica.mvp.enumeraciones.ProductoFinanciero;
import com.prueba.tecnica.mvp.rules.RiskRule;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Validations {

    public static ProductoFinanciero parseProducto(String producto) {

        try {
            return ProductoFinanciero.valueOf(producto.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Producto financiero inválido: " + producto
            );
        }

    }

    public static void readAndLoadProviders(List<RiskRule> rules){
        Integer countRules = rules.size();
        System.out.println("loaded rules: " + countRules);

        if(countRules == 0) {
            throw new IllegalArgumentException("There is not rules loaded, please review '@Components as rules'");
        }
    }
}
