package com.prueba.tecnica.mvp.interfaces;

import com.prueba.tecnica.mvp.model.DatosContables;


public interface IDatosContablesProvider {

    DatosContables obtenerDatos(String empresaId);
}

