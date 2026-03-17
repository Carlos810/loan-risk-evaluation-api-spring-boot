package com.prueba.tecnica.mvp.controller;

import com.prueba.tecnica.mvp.Validate.Validations;
import com.prueba.tecnica.mvp.context.RiskEvaluationContext;
import com.prueba.tecnica.mvp.interfaces.provider.IDatosContablesProvider;
import com.prueba.tecnica.mvp.model.rules.DatosContables;
import com.prueba.tecnica.mvp.model.rules.EstadoLegal;
import com.prueba.tecnica.mvp.model.rules.HistorialPagos;
import com.prueba.tecnica.mvp.model.RiskEvaluationRequest;
import com.prueba.tecnica.mvp.interfaces.provider.IHistorialPagosProvider;
import com.prueba.tecnica.mvp.interfaces.provider.IVerificacionLegalProvider;
import com.prueba.tecnica.mvp.engine.OrdenarisRiskEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/risk")
public class RiskController {
    private final Validations _validate;

    private final OrdenarisRiskEngine _engine;

    private final IHistorialPagosProvider _historialProvider;
    private final IDatosContablesProvider _datosProvider;
    private final IVerificacionLegalProvider _legalProvider;

    @PostMapping("/evaluate")
    public ResponseEntity analizateRiskCases(@RequestBody RiskEvaluationRequest request){
        String empresaId = request.getEmpresaId();
        DatosContables datos = _datosProvider.obtenerDatos(empresaId);
        HistorialPagos historial = _historialProvider.obtenerHistorial(empresaId);
        EstadoLegal legal = _legalProvider.obtenerEstado(empresaId);

        RiskEvaluationContext context = new RiskEvaluationContext(request,datos,historial,legal);
        return ResponseEntity.status(HttpStatus.OK).body(_engine.evaluate(context));
    }

}
