package com.BikkadIT.service;

import java.util.Map;

import com.BikkadIT.bindings.LoginForm;
import com.BikkadIT.bindings.UnlockAccForm;
import com.BikkadIT.bindings.UserForm;

public interface UserServiceI {
	
	public String LoginCheck(LoginForm loginForm);
	
	public Map<Integer, String>getContries();
	
	public Map<Integer, String>getStates(Integer CountryId);
	
	public Map<Integer, String>getCities(Integer stateId);
	
	public boolean emailUnique(String mail);
	
	public boolean saveUser(UserForm userForm);
	
	public boolean unlockAccount(UnlockAccForm unlockAccForm);
	
	public String forgotPassword(String email);

}
