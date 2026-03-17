package com.prueba.tecnica.mvp.model.rules;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class EstadoLegal {

    private boolean juicioActivo;

    private LocalDate fechaConstitucion;
}
