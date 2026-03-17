package com.prueba.tecnica.mvp.interfaces.provider;

import com.prueba.tecnica.mvp.model.rules.DatosContables;


public interface IDatosContablesProvider {

    DatosContables obtenerDatos(String empresaId);
}

