package com.shakhawat.statusposting.validator;

import com.shakhawat.statusposting.model.User;
import com.shakhawat.statusposting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
	
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "This field is required");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 20) {
            errors.rejectValue("username", null, "Username should be between 6 and 20 characters.");
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", null, "The username already taken.");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "This field is required");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 12) {
            errors.rejectValue("password", null, "Password should be between 8 and 12 characters.");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", null, "These passwords don't match.");
        }
    }
}
