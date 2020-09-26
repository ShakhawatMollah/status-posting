package com.shakhawat.statusposting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shakhawat.statusposting.model.User;
import com.shakhawat.statusposting.model.UserStatus;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Long>{
	
	List<UserStatus> findByIsPublicOrderByIsPinStatusDesc(Boolean isPublic);
	
	List<UserStatus>findByCreatedByOrderByIsPinStatusDesc(User createdBy);

}
