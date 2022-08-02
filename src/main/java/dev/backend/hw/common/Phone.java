package dev.backend.hw.common;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {
    String message() default "유효하지 않은 전화번호입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}