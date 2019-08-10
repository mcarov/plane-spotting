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
    public boolean isValid(String iataCode, ConstraintValidatorContext context) {
        if(iataCode == null || iataCode.equals("")) {
            return true;
        }
        if(!StringUtils.isAlpha(iataCode))
            return false;

        return iataCode.length() == size;
    }
}
