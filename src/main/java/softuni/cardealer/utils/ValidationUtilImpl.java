package softuni.cardealer.utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidationUtilImpl implements ValidationUtil {
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    public <T> boolean isValid(T entity) {
        return this.validator.validate(entity).size() == 0;
    }

    @Override
    public <T> Set<ConstraintViolation<T>> violations(T entity) {
        return this.validator.validate(entity);
    }
}
