package com.shakhawat.statusposting.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.shakhawat.statusposting.model.UserStatus;

@Component
public class UserStatusValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserStatus.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
    	UserStatus user = (UserStatus) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "This field is required");
        if (user.getStatus().length() > 255) {
            errors.rejectValue("status", null, "Status should be mamimum 255 characters.");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "location", "This field is required");
        if (user.getLocation() == null) {
            errors.rejectValue("location", null, "Location not found.");
        }
    }
}
