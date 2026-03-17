package com.prueba.tecnica.mvp.providers.mock;

import com.prueba.tecnica.mvp.model.rules.HistorialPagos;
import com.prueba.tecnica.mvp.interfaces.provider.IHistorialPagosProvider;
import org.springframework.stereotype.Component;

@Component
public class MockHistorialPagosProvider implements IHistorialPagosProvider {
    @Override
    public HistorialPagos obtenerHistorial(String empresaId) {
        return new HistorialPagos(
                false, // deudaMayor90Dias
                true   // historialExcelente
        );
    }
}
