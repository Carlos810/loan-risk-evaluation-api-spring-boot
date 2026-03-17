package com.prueba.tecnica.mvp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RiskEvaluationResult {

    private RiskLevel nivelRiesgo;
    private List<RuleResult> reglasEvaluadas;
}
