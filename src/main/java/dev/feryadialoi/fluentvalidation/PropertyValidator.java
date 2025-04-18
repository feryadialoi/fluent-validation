package dev.feryadialoi.fluentvalidation;

public interface PropertyValidator<T> {

    boolean validate(T propertyValue);

}
