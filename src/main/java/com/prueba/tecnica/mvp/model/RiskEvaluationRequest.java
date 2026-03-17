package com.prueba.tecnica.mvp.model;

import com.prueba.tecnica.mvp.enumeraciones.ProductoFinanciero;
import jakarta.persistence.Entity;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Entity
public class RiskEvaluationRequest {
    private String empresaId ;

    private BigDecimal montoSolicitado;

    private LocalDateTime fechaSolicitud;

    private ProductoFinanciero productoFinanciero;
}


