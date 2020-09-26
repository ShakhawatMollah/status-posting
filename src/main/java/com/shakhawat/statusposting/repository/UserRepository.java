package com.shakhawat.statusposting.repository;

import com.shakhawat.statusposting.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
