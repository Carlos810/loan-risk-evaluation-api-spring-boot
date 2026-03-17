package com.prueba.tecnica.mvp.providers.mock;

import com.prueba.tecnica.mvp.model.rules.EstadoLegal;
import com.prueba.tecnica.mvp.interfaces.provider.IVerificacionLegalProvider;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MockVerificacionLegalProvider implements IVerificacionLegalProvider {

    @Override
    public EstadoLegal obtenerEstado(String empresaId) {
       return new EstadoLegal(
            false, // juicio activo
               LocalDate.of(2024,1,1) //empresa nueva
       );
    }
}
