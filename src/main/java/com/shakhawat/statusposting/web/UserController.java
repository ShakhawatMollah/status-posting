package com.shakhawat.statusposting.web;

import com.shakhawat.statusposting.model.User;
import com.shakhawat.statusposting.repository.UserStatusRepository;
import com.shakhawat.statusposting.service.SecurityService;
import com.shakhawat.statusposting.service.UserService;
import com.shakhawat.statusposting.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    
	@Autowired
	UserStatusRepository statusRepository;
    
    @GetMapping("/registration")
    public String registration(Model model) {
    	
        model.addAttribute("user", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User userForm, BindingResult bindingResult) {
    	
    	userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/dashboard";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
    	
        if (error != null) {
        	model.addAttribute("error", "Your username and password is invalid.");
        }

        if (logout != null) {
        	model.addAttribute("message", "You have been logged out successfully.");
        }
       
        return "login";
    }
    
    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping({"/dashboard"})
    public String welcome(Model model) {
    	model.addAttribute("statusList", statusRepository.findAll());
        return "dashboard";
    }

}
