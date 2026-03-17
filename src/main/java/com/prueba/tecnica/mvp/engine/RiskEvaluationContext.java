package com.prueba.tecnica.mvp.engine;


import com.prueba.tecnica.mvp.model.DatosContables;
import com.prueba.tecnica.mvp.model.RiskEvaluationRequest;
import com.prueba.tecnica.mvp.model.RiskEvaluationResult;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RiskEvaluationContext {

    private RiskEvaluationRequest request;

    private DatosContables datosContables;
}
