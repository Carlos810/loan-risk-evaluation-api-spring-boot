package com.prueba.tecnica.mvp.model;

import com.prueba.tecnica.mvp.enumeraciones.ProductoFinanciero;
import jakarta.persistence.Entity;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Entity
public class RiskEvaluationRequest {
    private String empresaId ;

    private BigDecimal montoSolicitado;

    private LocalDate fechaSolicitud;

    private ProductoFinanciero productoFinanciero;
}


