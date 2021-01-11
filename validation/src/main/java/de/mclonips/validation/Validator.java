package de.mclonips.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import lombok.experimental.UtilityClass;

import java.util.Set;

/**
 * Utility to validate {@link jakarta.validation} annotations on objects.
 */
@UtilityClass
public class Validator {

    private final jakarta.validation.Validator internalValidator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        internalValidator = factory.getValidator();
    }

    public <T> void validate(T instance) {
        Set<ConstraintViolation<T>> validate = internalValidator.validate(instance);
        if (!validate.isEmpty()) {
            throw new ConstraintViolationException(validate);
        }
    }
}
