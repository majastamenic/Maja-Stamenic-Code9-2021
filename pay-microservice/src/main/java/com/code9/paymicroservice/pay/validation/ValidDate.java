package com.code9.paymicroservice.pay.validation;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidDateValidation.class)
public @interface ValidDate {
    String message() default "ValidDate is not correct.";
    Class<?>[] groups() default {};
    public abstract Class<? extends Payload>[] payload() default {};

}
