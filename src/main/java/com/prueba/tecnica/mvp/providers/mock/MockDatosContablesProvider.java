package com.prueba.tecnica.mvp.providers.mock;

import com.prueba.tecnica.mvp.interfaces.provider.IDatosContablesProvider;
import com.prueba.tecnica.mvp.model.rules.DatosContables;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MockDatosContablesProvider implements IDatosContablesProvider {
    @Override
    public DatosContables obtenerDatos(String empresaId) {
        return new DatosContables(
                new BigDecimal(300000), //ventasPromedioMensual
                new BigDecimal(80000), //activos
                new BigDecimal(30000)  //pasivos
        );
    }
}
