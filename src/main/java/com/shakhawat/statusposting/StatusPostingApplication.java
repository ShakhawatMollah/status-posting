package com.shakhawat.statusposting;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.shakhawat.statusposting.model.Location;
import com.shakhawat.statusposting.model.Role;
import com.shakhawat.statusposting.repository.LocationRepository;
import com.shakhawat.statusposting.repository.RoleRepository;

@SpringBootApplication
public class StatusPostingApplication {
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(StatusPostingApplication.class, args);
	}
	
	@PostConstruct
	public void initData() {
		
		//Approach 1::Insert with constructor
		Location[] locData = {new Location(1L, "Sylhet"), 
				new Location(2L, "Bandarban"),
				new Location(3L, "Khulna")};
		List<Location> locList = Stream.of(locData).collect(Collectors.toList());
		locationRepository.saveAll(locList);
		
		//Approach 2::Insert with Object
		List<Role> roleList = new ArrayList<>();
		Role role = new Role();
		role.setId(1L);
		role.setName("ROLE_USER");
		roleList.add(role);
		
		Role role1 = new Role();
		role1.setId(2L);
		role1.setName("ROLE_STATUS");
		roleList.add(role1);
		
		roleRepository.saveAll(roleList);
	}

}
