package com.code9.usermicroservice.user.validation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Component
public class DateOfBirthValidation implements ConstraintValidator<DateOfBirth, Date> {

    @Override
    public void initialize(DateOfBirth constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        Date nowDate = new Date();
        if(date.after(nowDate))
            return false;
        Period period = Period.between(
                date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                nowDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        );
        if(period.getYears() > 18)
            return false;
        return true;
    }
}
