package dev.feryadialoi.fluentvalidation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class AbstractValidator<E> {

    private final List<Rule<Object, Object>> rules = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public <T> Rule<E, T> ruleFor(Function<E, T> ruleFor) {
        Rule<E, T> rule = new Rule<>();
        rule.setRuleFor(ruleFor);
        rules.add((Rule<Object, Object>) rule);
        return rule;
    }

    public ValidationResult validate(E entity) {
        var resultErrors = new ArrayList<ResultError>();
        for (var rule : rules) {
            for (var propertyRule : rule.getPropertyRules()) {
                if (!propertyRule.validate(rule.getPropertyValue(entity))) {
                    var resultError = new ResultError();
                    resultError.setPropertyName(rule.getPropertyName());
                    resultError.setErrorMessage(propertyRule.getErrorMessage());
                    resultErrors.add(resultError);
                }
            }
        }

        return new ValidationResult(resultErrors.isEmpty(), resultErrors);
    }
}
