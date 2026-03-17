package com.prueba.tecnica.mvp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RuleResult {

    private boolean applied;

    private RiskLevel riskLevel;

    private String message;

    public static RuleResult none(){
        return new RuleResult(false,null,null);
    }
}
