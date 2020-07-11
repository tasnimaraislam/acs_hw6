package com.example.getmesocialservice.validation;

        import javax.validation.Constraint;
        import javax.validation.Payload;
        import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidCreatedByNameValidator.class)
@Documented
public @interface ValidCreatedByName {

    String message() default "{Not a valid CreatedBy User name}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
