package com.prueba.tecnica.mvp.enumeraciones;

public enum RiskLevel {
    BAJO,
    MEDIO,
    ALTO,
    RECHAZADO
    ;

    public static RiskLevel max(RiskLevel current, RiskLevel incoming){

        if(incoming == null) return current;

        if(current == null) return incoming;

        return incoming.ordinal() > current.ordinal() ? incoming : current;
    }
}
