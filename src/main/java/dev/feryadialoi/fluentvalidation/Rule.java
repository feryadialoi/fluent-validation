package dev.feryadialoi.fluentvalidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class Rule<E, T> {

    private String propertyName;
    private Function<E, T> ruleFor;

    private final List<PropertyRule<T>> propertyRules = new ArrayList<>();

    public List<PropertyRule<T>> getPropertyRules() {
        return propertyRules;
    }

    public void setRuleFor(Function<E, T> ruleFor) {
        this.ruleFor = ruleFor;
    }

    public T getPropertyValue(E entity) {
        return ruleFor.apply(entity);
    }

    public Rule<E, T> withPropertyName(String propertyName) {
        this.propertyName = propertyName;
        return this;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Rule<E, T> notEmpty() {
        var propertyRule = new PropertyRule<T>();
        propertyRule.setErrorMessage("should not empty");
        propertyRule.setPropertyValidator(propertyValue -> switch (propertyValue) {
            case String string -> !string.isEmpty();
            case Number number -> !number.equals(0);
            case null, default -> false;
        });
        propertyRules.add(propertyRule);
        return this;
    }

    public <V> Rule<E, T> notEquals(V value) {
        var propertyRule = new PropertyRule<T>();
        propertyRule.setErrorMessage("should not equals to " + value);
        propertyRule.setPropertyValidator(propertyValue -> !Objects.equals(value, propertyValue));
        propertyRules.add(propertyRule);
        return this;
    }

    public Rule<E, T> must(Function<T, Boolean> function, String errorMessage) {
        var propertyRule = new PropertyRule<T>();
        propertyRule.setErrorMessage(errorMessage);
        propertyRule.setPropertyValidator(function::apply);
        propertyRules.add(propertyRule);
        return this;
    }
}
