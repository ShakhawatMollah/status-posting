package com.shakhawat.statusposting.web;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shakhawat.statusposting.model.User;
import com.shakhawat.statusposting.model.UserStatus;
import com.shakhawat.statusposting.repository.LocationRepository;
import com.shakhawat.statusposting.repository.UserStatusRepository;
import com.shakhawat.statusposting.service.UserService;
import com.shakhawat.statusposting.validator.UserStatusValidator;


@Controller
public class StatusController {
	
	@Autowired
	UserStatusRepository statusRepository;
	
	@Autowired
	LocationRepository locationRepository;
	
	@Autowired
	UserService userService;
	
    @Autowired
    private UserStatusValidator userStatusValidator;
	
	@GetMapping("/statuspost/form")
	public String createOrUpdateUserStatus(@RequestParam(value = "id", required = false) UserStatus userStatus, Model m) {
		if (userStatus == null) {
			userStatus = new UserStatus();
		}
		m.addAttribute("userStatus", userStatus);
		m.addAttribute("locations", locationRepository.findAll());

		return "statusPosting/form";
	}

	@PostMapping("/statuspost/form")
	public String saveUserStatus(@ModelAttribute("userStatus") @Validated UserStatus userStatus, BindingResult bindingResult, SessionStatus sessionStatus, RedirectAttributes redirectAttributes, Model m) {
		
		userStatusValidator.validate(userStatus, bindingResult);
		
		if (bindingResult.hasErrors()) {
			m.addAttribute("userStatus", userStatus);
			m.addAttribute("locations", locationRepository.findAll());
			return "statusPosting/form";
		}
		
		/* Basic audit - Start */
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        
        User user = null;
        if(!StringUtils.isEmpty(userDetail.getUsername())) {
            User u = userService.findByUsername(userDetail.getUsername());
    		if(!StringUtils.isEmpty(u)) {
    			user = new User();
    			user.setId(u.getId());
    		}
        }

		if(StringUtils.isEmpty(userStatus.getId())) {
			userStatus.setCreatedAt(new Date());
			userStatus.setCreatedBy(user);
		} else {
			Optional<UserStatus> rs = statusRepository.findById(userStatus.getId());
			userStatus.setCreatedAt(rs.get().getCreatedAt());
			userStatus.setCreatedBy(rs.get().getCreatedBy());
			userStatus.setUpdatedAt(new Date());
			userStatus.setUpdatedBy(user);
		}
		/* Basic audit - End */
		
		statusRepository.save(userStatus);
		
		sessionStatus.setComplete();

		redirectAttributes.addFlashAttribute("message", "Data saved successfully!");
		
		return "redirect:/dashboard";

	}

}
