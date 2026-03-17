package com.prueba.tecnica.mvp.rules;

import com.prueba.tecnica.mvp.engine.RiskEvaluationContext;
import com.prueba.tecnica.mvp.model.RuleResult;

public interface RiskRule {
    RuleResult evaluate(RiskEvaluationContext context);
}
