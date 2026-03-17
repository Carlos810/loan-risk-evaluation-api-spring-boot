package com.prueba.tecnica.mvp.interfaces.provider;

import com.prueba.tecnica.mvp.model.rules.HistorialPagos;

public interface IHistorialPagosProvider {
    HistorialPagos obtenerHistorial(String empresaId);
}
