package com.prueba.tecnica.mvp.context;


import com.prueba.tecnica.mvp.model.rules.DatosContables;
import com.prueba.tecnica.mvp.model.rules.EstadoLegal;
import com.prueba.tecnica.mvp.model.rules.HistorialPagos;
import com.prueba.tecnica.mvp.model.RiskEvaluationRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RiskEvaluationContext {
    private RiskEvaluationRequest request;
    private DatosContables datosContables;
    private HistorialPagos historialPagos;
    private EstadoLegal estadoLegal;
}
