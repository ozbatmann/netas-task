package com.netas.project.conf;

import com.netas.project.model.Files;
import org.hibernate.SessionFactory;

import javax.validation.*;
import java.util.Set;

public class ValidationUtil {
    private  static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    public static void validate(Object o){
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(o);
        if(violations.size() > 0){
            StringBuilder stringBuilder = new StringBuilder();
            for( ConstraintViolation c: violations){
                stringBuilder.append(c.getMessage()+'\n');
            }
            throw new ValidationException(stringBuilder.toString());

        }
    }

}
