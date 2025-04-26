package dev.feryadialoi.fluentvalidation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record ValidationResult(Boolean isValid, List<ResultError> resultErrors) {

    public Map<String, List<String>> errorsAsMap() {
        return resultErrors.stream().collect(Collectors.groupingBy(
                ResultError::getPropertyName,
                Collectors.mapping(ResultError::getErrorMessage, Collectors.toList())));
    }
}
