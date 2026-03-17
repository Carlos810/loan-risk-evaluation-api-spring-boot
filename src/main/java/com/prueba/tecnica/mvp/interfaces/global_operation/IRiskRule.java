package com.prueba.tecnica.mvp.interfaces.global_operation;

import com.prueba.tecnica.mvp.context.RiskEvaluationContext;
import com.prueba.tecnica.mvp.model.result.RuleResult;

public interface IRiskRule {
    RuleResult evaluate(RiskEvaluationContext context);
}
