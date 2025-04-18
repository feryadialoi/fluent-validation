package dev.feryadialoi.fluentvalidation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ValidationResult {

    private final List<ResultError> resultErrors;

    public ValidationResult(List<ResultError> resultErrors) {
        this.resultErrors = resultErrors;
    }

    public boolean isValid() { return resultErrors.isEmpty(); }

    public List<ResultError> getResultErrors() { return resultErrors; }

    public Map<String, List<String>> errorsAsMap() {
        return resultErrors.stream().collect(Collectors.groupingBy(
                ResultError::getPropertyName,
                Collectors.mapping(ResultError::getErrorMessage, Collectors.toList())));
    }
}
