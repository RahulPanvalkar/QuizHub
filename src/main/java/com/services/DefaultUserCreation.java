package com.services;

import com.dao.UserDao;
import com.entities.Role;
import com.entities.User;
import com.utility.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class DefaultUserCreation {

	private BCryptPasswordEncoder passwordEncoder;
	private UserDao userDao;

	private static final Logger logger = LoggerUtil.getLogger(DefaultUserCreation.class);

	// Constructor Injection
	public DefaultUserCreation(UserDao userDao, BCryptPasswordEncoder passwordEncoder) {
		this.userDao = userDao;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
    public void createAdminUser() {
		logger.info("Inside method..");
		User user = new User();
		user.setUserId(1);
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setUsername("johndoe");
		user.setPassword(passwordEncoder.encode("Admin123"));
		user.setEmailId("johndoe@gmail.com");
		user.setPhone("1234567890");

		// setting user roles
		Set<Role> roles = new HashSet<>();
		roles.add(new Role("ADM","ADMIN"));
		roles.add(new Role("USR","USER"));
		user.setRoles(roles);

		// Save the user to the database
		try {
			Optional<User> userOpt1 = userDao.findByUsername(user.getUsername());
			if (userOpt1.isEmpty()){
				userOpt1 = userDao.findByEmailId(user.getEmailId());
				if (userOpt1.isEmpty()){
					userDao.saveUser(user);
					logger.info("Default user created: [{}]", user.getUsername());
					return;
				}
			}
			logger.info("User already present >> [{}]",userOpt1.get());
		} catch (IllegalArgumentException e) {
			logger.error(e);
			e.printStackTrace();
		}

    }

	@Transactional
	public void createNormalUser() {
		logger.info("Inside method..");
		User user = new User();
		user.setFirstName("Emma");
		user.setLastName("Watson");
		user.setUsername("watsonemma12");
		user.setPassword(passwordEncoder.encode("Emma@123"));
		user.setEmailId("emma@gmail.com");
		user.setPhone("1287654321");

		// setting user roles
		Set<Role> roles = new HashSet<>();
		roles.add(new Role("USR","USER"));
		user.setRoles(roles);

		// Save the user to the database
		try {
			Optional<User> userOpt1 = userDao.findByUsername(user.getUsername());
			if (userOpt1.isEmpty()){
				userOpt1 = userDao.findByEmailId(user.getEmailId());
				if (userOpt1.isEmpty()){
					userDao.saveUser(user);
					logger.info("Default normal user created: [{}]", user.getUsername());
					return;
				}
			}
			logger.info("User already present >> [{}]",userOpt1.get());
		} catch (IllegalArgumentException e) {
			logger.error(e);
			e.printStackTrace();
		}

	}
}
