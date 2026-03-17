package com.prueba.tecnica.mvp.controller;

import com.prueba.tecnica.mvp.engine.RiskEvaluationContext;
import com.prueba.tecnica.mvp.interfaces.IDatosContablesProvider;
import com.prueba.tecnica.mvp.model.DatosContables;
import com.prueba.tecnica.mvp.model.RiskEvaluationRequest;
import com.prueba.tecnica.mvp.model.RiskEvaluationResult;
import com.prueba.tecnica.mvp.service.OrdenarisRiskEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/risk")
public class RiskController {
    private final IDatosContablesProvider datosProvider;
    private final OrdenarisRiskEngine engine;
    @PostMapping("/evaluate")
    public RiskEvaluationResult evaluar(@RequestBody RiskEvaluationRequest request){
        DatosContables datos = datosProvider.obtenerDatos(request.getEmpresaId());
        RiskEvaluationContext context = new RiskEvaluationContext(request,datos);
        return engine.evaluate(context);
    }

}
