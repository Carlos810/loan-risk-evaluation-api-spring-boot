package com.prueba.tecnica.mvp.providers.mock;

import com.prueba.tecnica.mvp.interfaces.IDatosContablesProvider;
import com.prueba.tecnica.mvp.model.DatosContables;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MockDatosContablesProvider implements IDatosContablesProvider {
    @Override
    public DatosContables obtenerDatos(String empresaId) {
        return new DatosContables(
                new BigDecimal(12000), //ventasPromedioMensual
                new BigDecimal(80000), //activos
                new BigDecimal(30000)  //pasivos
        );
    }
}
