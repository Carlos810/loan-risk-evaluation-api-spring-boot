package com.prueba.tecnica.mvp.model.rules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatosContables {
    private BigDecimal ventasPromedioMensual;
    private BigDecimal activos;
    private BigDecimal pasivos;
}
