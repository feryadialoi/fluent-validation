package dev.feryadialoi.fluentvalidation.validator;

import dev.feryadialoi.fluentvalidation.ResultError;
import dev.feryadialoi.fluentvalidation.model.CreateCustomerRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


class CreateCustomerRequestValidatorTest {

    CreateCustomerRequestValidator validator;

    @BeforeEach
    void setUp() {
        validator = new CreateCustomerRequestValidator();
    }

    @Test
    void test() {
        var validationResult = validator.validate(new CreateCustomerRequest());

        assertThat(validationResult.isValid()).isFalse();

        assertThat(validationResult.getResultErrors()).hasSize(5)
                .map(ResultError::getPropertyName)
                .containsExactlyInAnyOrder("name", "email", "email", "phone", "phone");

        assertThat(validationResult.getResultErrors()).hasSize(5)
                .map(ResultError::getErrorMessage)
                .containsExactlyInAnyOrder(
                        "should not empty",
                        "should not empty",
                        "invalid email",
                        "should not empty",
                        "invalid phone");

        assertThat(validationResult.errorsAsMap())
                .hasSize(3)
                .containsKeys("name", "email", "phone")
                .containsValues(
                        List.of("should not empty", "invalid email"),
                        List.of("should not empty"),
                        List.of("should not empty", "invalid phone")
                );
    }
}