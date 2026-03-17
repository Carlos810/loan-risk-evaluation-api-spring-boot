package com.prueba.tecnica.mvp.model.result;

import com.prueba.tecnica.mvp.enumeraciones.RiskLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RuleResult {

    private String TypeRule;

    private boolean applied;

    private RiskLevel riskLevel;

    private String message;

    public static RuleResult none(){
        return new RuleResult(null,false,null,null);
    }
}
