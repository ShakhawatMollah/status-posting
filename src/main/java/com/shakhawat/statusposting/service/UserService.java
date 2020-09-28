package com.shakhawat.statusposting.service;

import com.shakhawat.statusposting.model.User;

public interface UserService {
	
    void save(User user);

    User findByUsername(String username);
}
