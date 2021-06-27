package com.code9.usermicroservice.user.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateOfBirthValidation.class)
public @interface DateOfBirth {
    String message() default "Date of birth is not correct.";
    Class<?>[] groups() default {};
    public abstract Class<? extends Payload>[] payload() default {};

}
