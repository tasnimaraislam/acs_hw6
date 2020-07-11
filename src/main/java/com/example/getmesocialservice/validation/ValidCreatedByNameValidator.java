package com.example.getmesocialservice.validation;

        import javax.validation.ConstraintValidator;
        import javax.validation.ConstraintValidatorContext;

public class ValidCreatedByNameValidator implements ConstraintValidator<ValidCreatedByName, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s.matches("[a-zA-Z0-9]+")){
            return true;
        } else{
            return false;
        }
    }
}

