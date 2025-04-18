package dev.feryadialoi.fluentvalidation.validator;

import dev.feryadialoi.fluentvalidation.AbstractValidator;
import dev.feryadialoi.fluentvalidation.model.CreateCustomerRequest;


public class CreateCustomerRequestValidator extends AbstractValidator<CreateCustomerRequest> {

    public CreateCustomerRequestValidator() {
        ruleFor(CreateCustomerRequest::getName)
                .withPropertyName("name")
                .notEmpty()
                .notEquals("admin");

        ruleFor(CreateCustomerRequest::getEmail)
                .notEmpty()
                .must(this::beAValidEmail, "invalid email")
                .withPropertyName("email");

        ruleFor(CreateCustomerRequest::getPhone)
                .notEmpty()
                .must(this::beAValidPhone, "invalid phone")
                .withPropertyName("phone");
    }

    public boolean beAValidEmail(String email) { return false; }

    private boolean beAValidPhone(String phone) { return false; }

}
