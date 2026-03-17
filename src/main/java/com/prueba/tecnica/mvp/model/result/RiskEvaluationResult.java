package com.prueba.tecnica.mvp.model.result;

import com.prueba.tecnica.mvp.enumeraciones.RiskLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RiskEvaluationResult {
    private RiskLevel nivelRiesgo;
    private String motivoFinal;
    private List<RuleResult> reglasEvaluadas;
}
