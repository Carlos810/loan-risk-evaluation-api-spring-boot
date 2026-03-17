package com.prueba.tecnica.mvp.model.rules;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HistorialPagos {

    private boolean deudaMayor90Dias;
    private boolean historialExcelente;
}
