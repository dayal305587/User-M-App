package com.BikkadIT.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BikkadIT.bindings.LoginForm;
import com.BikkadIT.entities.UserAccountEntity;
@Repository
public interface UserRepository extends JpaRepository<UserAccountEntity, Serializable> {
	
	public UserAccountEntity findByemailAndpassword(String email,String password);
	
	public UserAccountEntity findByMail(String email);

}
