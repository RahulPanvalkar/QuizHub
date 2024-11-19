package com.services;

import com.dao.RoleDao;
import com.dao.UserDao;
import com.entities.Role;
import com.entities.User;
import com.utility.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class DefaultUserCreation {

	private BCryptPasswordEncoder passwordEncoder;
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;

	private static final Logger logger = LoggerUtil.getLogger(DefaultUserCreation.class);

	// Constructor Injection
	public DefaultUserCreation(UserDao userDao, BCryptPasswordEncoder passwordEncoder) {
		this.userDao = userDao;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
	public ResponseEntity<?> addDefaultUsers() {
		logger.info("addDefaultUsers is called...");
		Map<String, Object> returnMap = new HashMap<>();
		Map<String, Object> adminMap = createAdminUser();
		Map<String, Object> normalMap = createNormalUser();

		// Check for partial or complete failure
		boolean adminError = (Boolean)adminMap.get("error");
		boolean normalError = (Boolean)normalMap.get("error");
		if (adminError || normalError) {
			if (adminError && normalError){
				returnMap.put("message", adminMap.get("message") +" & "+ normalMap.get("message"));
				return ResponseEntity.status(HttpStatus.OK).body(returnMap);
			}
			else if (adminError) {
				returnMap.put("message", adminMap.get("message"));
			}
			else if (normalError) {
				returnMap.put("message", normalMap.get("message"));
			}
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(returnMap);
		}

		// Success: Return list of created users
		returnMap.put("admin",(User)adminMap.get("admin"));
		returnMap.put("normal",(User)normalMap.get("normal"));
		return ResponseEntity.status(HttpStatus.CREATED).body(returnMap);
	}

	@Transactional
	private Map<String, Object> createAdminUser() {
		logger.info("Inside method..");
		Map<String, Object> retMap = new HashMap<>();

		User user = new User();
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setUsername("johndoe");
		user.setPassword(passwordEncoder.encode("Admin123"));
		user.setEmailId("johndoe@gmail.com");
		user.setPhone("1234567890");

		// Set user roles
		Set<Role> roles = new HashSet<>();
		Role adminRole = roleDao.findByRoleId("ADM").orElse(new Role("ADM","ADMIN"));
		roles.add(adminRole);
		Role userRole = roleDao.findByRoleId("USR").orElse(new Role("USR","USER"));
		roles.add(userRole);
		user.setRoles(roles);

		try {
			// Check if user already exists by username or email
			if (userDao.findByUsername(user.getUsername()).isPresent() ||
					userDao.findByEmailId(user.getEmailId()).isPresent()) {
				logger.info("User already present: [{}]", user.getUsername());
				retMap.put("error",true);
				retMap.put("message","Admin user already present");
				return retMap;
			}

			// Save the user
			User savedUser = userDao.saveUser(user);
			logger.info("Default admin user created by username: [{}]", savedUser.getUsername());
			retMap.put("message","Admin user created");
			retMap.put("error",false);
			retMap.put("admin",savedUser);

		} catch (IllegalArgumentException  | DuplicateKeyException e) {
			logger.error("Error creating user", e);
			retMap.put("error",true);
			retMap.put("message", e);
		}
		return retMap;
	}


	@Transactional
	private Map<String, Object> createNormalUser() {
		logger.info("Inside method..");
		Map<String, Object> retMap = new HashMap<>();

		User user = new User();
		user.setFirstName("Emma");
		user.setLastName("Watson");
		user.setUsername("watsonemma12");
		user.setPassword(passwordEncoder.encode("Emma@123"));
		user.setEmailId("emma@gmail.com");
		user.setPhone("1287654321");

		// setting user roles
		Set<Role> roles = new HashSet<>();
		Role userRole = roleDao.findByRoleId("USR").orElse(new Role("USR","USER"));
		roles.add(userRole);
		user.setRoles(roles);

		try {
			// Check if user already exists by username or email
			if (userDao.findByUsername(user.getUsername()).isPresent() ||
					userDao.findByEmailId(user.getEmailId()).isPresent()) {
				logger.info("Normal user already present: [{}]", user.getUsername());
				retMap.put("error",true);
				retMap.put("message","Normal user already present");
				return retMap;
			}

			// Save the user
			User savedUser = userDao.saveUser(user);
			logger.info("Default normal user created by username: [{}]", savedUser.getUsername());
			retMap.put("message","Normal user created");
			retMap.put("error",false);
			retMap.put("normal",savedUser);

		} catch (Exception e) {
			logger.error("Error creating normal user", e);
			retMap.put("error",true);
			retMap.put("message", e);
		}
		return retMap;
	}
}
