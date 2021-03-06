package com.ecommerce.admin.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.admin.entity.User;
import com.ecommerce.admin.repository.AdminRepository;

/** 
 * Admin Service class
 * @author saipavan
 */
@Service
@Transactional
public class AdminService {

	@Autowired
	private AdminRepository repository;

	/** 
	 * This method will fetch credentials form DB And Verify credentials
	 * @param userNameOrmobileNumber
	 * @param password
	 * @return message
	 */
	public String login(String userNameOrmobileNumber, String password) {
		String role = "admin";
		String str = "";
		int count = 0;
		System.out.println(userNameOrmobileNumber);
		for (char ch : userNameOrmobileNumber.toCharArray()) {
			if (Character.isDigit(ch)) {
				count++;
			}
		}
		if (userNameOrmobileNumber.length() == 10 && count == 10) {
			User user = repository.findByMobileNumberAndRole(userNameOrmobileNumber, role);
			if (user.getMobileNumber().equals(userNameOrmobileNumber) && user.getPassword().equals(password)) {
				str = "Admin Successfully Logged In";
			} else {
				str = "Invalid username or password";
			}
		} else {
			User user = repository.findByUserNameAndRole(userNameOrmobileNumber, role);
			if (user.getUserName().equals(userNameOrmobileNumber) && user.getPassword().equals(password)) {
				str = "Admin Successfully Logged In";
			} else {
				str = "Invalid username or password";
			}
		}
		return str;
	}

	/** 
	 * This method will fetch credentials form DB And Verify Password If password is correct it updates password and save in DB
	 * @param userNameOrmobileNumber
	 * @param password
	 * @param confirmPassword
	 * @return message
	 */
	public String resetPassword(String userNameOrmobileNumber, String password, String confirmPassword) {
		String role = "admin";
		int count = 0;
		for (char ch : userNameOrmobileNumber.toCharArray()) {
			if (Character.isDigit(ch)) {
				count++;
			}
		}
		if (userNameOrmobileNumber.length() == 10 && count == 10) {
			User user = repository.findByMobileNumberAndRole(userNameOrmobileNumber, role);
			if (password.equals(confirmPassword)) {
				user.setPassword(confirmPassword);
				repository.save(user);
				return "sucessfully updated Password";
			} else {
				return "Password And confirm Password are not same";
			}
		} else {
			User user1 = repository.findByUserNameAndRole(userNameOrmobileNumber, role);
			if (password.equals(confirmPassword)) {
				user1.setPassword(confirmPassword);
				repository.save(user1);
				return "sucessfully updated Password";
			} else {
				return "Password And confirm Password are not same";
			}
		}
	}
	
	/** 
	 * This method will fetch credentials form DB by userName And updates All user details from user instance and save in DB 
	 * @param user
	 * @param userName
	 * @return User Instance
	 */
	public User update(User user, String userName) {
		String role = "admin";
		User newuser = repository.findByUserNameAndRole(userName, role);
		newuser.setFirstName(user.getFirstName());
		newuser.setLastName(user.getLastName());
		newuser.setAddress(user.getAddress());
		newuser.setMobileNumber(user.getMobileNumber());
		newuser.setEmailId(user.getEmailId());
		newuser.setPassword(user.getPassword());
		repository.save(newuser);
		return newuser;
	}
}
