package com.netas.project.conf;

import com.netas.project.model.Files;
import org.hibernate.SessionFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ValidationUtil {
    private  static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    public static boolean validate(Object o){
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(o);
        if(violations.size() == 0) return true;

        return false;
    }

}
