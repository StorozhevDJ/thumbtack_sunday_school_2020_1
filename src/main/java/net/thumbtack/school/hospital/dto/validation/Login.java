package net.thumbtack.school.hospital.dto.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LoginValidator.class)
public @interface Login {
    String message() default "Incorrect username!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
