package com.BikkadIT.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BikkadIT.bindings.LoginForm;
import com.BikkadIT.bindings.UnlockAccForm;
import com.BikkadIT.bindings.UserForm;
import com.BikkadIT.entities.CountryMasterEntity;
import com.BikkadIT.entities.StateMasterEntity;
import com.BikkadIT.entities.UserAccountEntity;
import com.BikkadIT.repositories.CityRepository;
import com.BikkadIT.repositories.CountryRepository;
import com.BikkadIT.repositories.StateRepository;
import com.BikkadIT.repositories.UserRepository;
import com.BikkadIT.util.EmailUtils;

@Service
public class UserServiceImpl implements UserServiceI{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public String LoginCheck(LoginForm loginForm) {
		UserAccountEntity userAcc = userRepository.findByemailAndpassword(loginForm.getEmail(), loginForm.getPassword());
		if(userAcc!=null) {
			String status = userAcc.getAccStatus();
			if(status.equals("LOCK")) {
				return "Your Account is Lock";
			}else {
				return "Login Success";
			}
		}else {
			return "Invalid Creditionals";
		}
		
		
	}

	@Override
	public Map<Integer, String> getContries() {
		List<CountryMasterEntity> findAll = countryRepository.findAll();
		Map<Integer, String> countriesmap = new HashMap<>();
		findAll.forEach(country ->{
			countriesmap.put(country.getCountryId(), country.getCountryName());
		});
		return countriesmap;
	}

	@Override
	public Map<Integer, String> getStates(Integer CountryId) {
		List<StateMasterEntity> findByCountryId = stateRepository.findByCountryId(CountryId);
		Map<Integer, String> statemap = new HashMap<>();
		findByCountryId.forEach(state->{
			statemap.put(state.getStateId(), state.getStateName());
		});
		return null;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean emailUnique(String mail) {
		UserAccountEntity findByMail = userRepository.findByMail(mail);
		if(findByMail==null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean saveUser(UserForm userForm) {
		userForm.setAccStatus("LOCK");
		userForm.setPassword(generaterrandomPassword());
		
		UserAccountEntity userAccountEntity = new UserAccountEntity();
		BeanUtils.copyProperties(userForm, userAccountEntity);
		UserAccountEntity save = userRepository.save(userAccountEntity);
		String sub = "Your Registration Successful";
		String body=getUserEmailBody(userForm);
		if(save!=null) {
			try {
				emailUtils.sendmail(userForm.getEmail(), sub, body);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		
		return false;
	}
	
	public String generaterrandomPassword() {
		String randomAlphanumeric = RandomStringUtils.randomAlphanumeric(6);
		return randomAlphanumeric;
		
	}

	@Override
	public boolean unlockAccount(UnlockAccForm unlockAccForm) {
		String email = unlockAccForm.getEmail();
		String tempPwd = unlockAccForm.getTempPwd();
		UserAccountEntity userAccountEntity = userRepository.findByemailAndpassword(email, tempPwd);
		if(userAccountEntity !=null) {
			userAccountEntity.setAccStatus("UNLOCKED");
			userAccountEntity.setPassword(unlockAccForm.getNewPwd());
			userRepository.save(userAccountEntity);
		}
		return false;
	}

	@Override
	public String forgotPassword(String email) {
		UserAccountEntity userAccountEntity = userRepository.findByMail(email);
		if(userAccountEntity!=null) {
			String subject = "Forgot Password Sent";
			String body = "";
			emailUtils.sendmail(userAccountEntity.getEmail(), subject, body);
			return "SUCCESS";
		}
		return "FAILS";
	}
	
	private String getUserEmailBody(UserForm userForm) {
		
		
		
		return null;
		
	}

}
