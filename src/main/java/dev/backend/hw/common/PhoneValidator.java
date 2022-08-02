package dev.backend.hw.common;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

    @Override
    public void initialize(Phone phone) {
    }

    @Override
    public boolean isValid(String field, ConstraintValidatorContext context) {
        if (field != null && field.isEmpty()) {
            addConstraintViolation(context,"전화번호를 입력해주세요.");
            return false;
        }
        return field != null && field.matches("[0-9]+")
                && (field.length() < 20);
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String errorMessage) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessage)
                .addConstraintViolation();
    }
}
