package ru.itpark.planespotting.validation;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = AirportCodeValidator.class)
public @interface AirportCode {
    String message() default "api.validation.airport.code";
    Type type();

    enum Type {
        IATA,
        ICAO
    }
}
