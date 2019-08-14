package ru.itpark.planespotting.validation;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AirportCodeValidator implements ConstraintValidator<AirportCode, String> {
    private int size;

    public void initialize(AirportCode constraint) {
        size = constraint.type() == AirportCode.Type.IATA ? 3 : 4;
    }

    @Override
    public boolean isValid(String code, ConstraintValidatorContext context) {
        if(code == null || code.equals("")) {
            return true;
        }
        if(!StringUtils.isAlpha(code))
            return false;

        return code.length() == size;
    }
}
