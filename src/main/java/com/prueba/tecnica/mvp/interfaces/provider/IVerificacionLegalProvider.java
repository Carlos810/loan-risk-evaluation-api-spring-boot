package com.prueba.tecnica.mvp.interfaces.provider;

import com.prueba.tecnica.mvp.model.rules.EstadoLegal;

public interface IVerificacionLegalProvider {
    EstadoLegal obtenerEstado(String empresaId);
}
