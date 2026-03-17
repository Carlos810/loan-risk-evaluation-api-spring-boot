package com.prueba.tecnica.mvp.interfaces;

public interface IVerificacionLegalProvider {

    Boolean existsLegalProcess();
    Boolean existsDemanda();
    Boolean existEmbargo();
}
