package com.code9.paymicroservice.pay.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.Date;

public class ValidDateValidation implements ConstraintValidator<ValidDate, String> {
    @Override
    public void initialize(ValidDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Date nowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);

        int mounth = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        int validMounth = Integer.parseInt(s.split("/")[0]);
        int validYear = Integer.parseInt(s.split("/")[1]);
        validYear = 2000 + validYear;

        if(year == validYear && validMounth<mounth)
            return false;

        return true;
    }
}
