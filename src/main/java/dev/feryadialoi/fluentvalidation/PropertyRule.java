package dev.feryadialoi.fluentvalidation;

public class PropertyRule<T> {

    private String errorMessage;
    private PropertyValidator<T> propertyValidator;

    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

    public String getErrorMessage() { return errorMessage; }

    public void setPropertyValidator(PropertyValidator<T> propertyValidator) { this.propertyValidator = propertyValidator; }

    public boolean validate(T value) { return propertyValidator.validate(value); }
}
