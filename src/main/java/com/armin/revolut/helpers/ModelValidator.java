package com.armin.revolut.helpers;

import com.armin.revolut.exceptions.BadRequestException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ModelValidator {
    private static final Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public static <T> Set<ConstraintViolation<T>> getValidation(T model) {
        return validator.validate(model);
    }

    public static <T> void validate(T req) throws BadRequestException {
        if (null == req) throw new BadRequestException("You need to provide input");

        Set<ConstraintViolation<T>> constraints = getValidation(req);
        if (constraints.size() <= 0) return;

        List<String> errors = constraints.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        throw new BadRequestException(errors);
    }


}
