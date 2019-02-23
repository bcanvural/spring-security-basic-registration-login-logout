package com.github.bcanvural.basicregistration.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.github.bcanvural.basicregistration.UserDto;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    public void initialize(PasswordMatches constraint) {
    }

    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        return validatePassword(obj);
    }

    private boolean validatePassword(final Object obj) {
        UserDto user = (UserDto) obj;
        return user.getMatchingPassword().equals(user.getPassword());
    }
}
