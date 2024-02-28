package org.example.handler.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dao.UserDao;
import org.example.dto.LoginDto;
import org.example.dto.UserDto;
import org.example.entities.User;
import org.example.exceptions.UserNotFoundException;
import org.example.exceptions.ValidationException;
import org.example.handler.UserHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*In this class we add some methods related to user and some validation for that methods*/
@Component
public class UserHandlerImpl implements UserHandler {

    private static final Logger logger = LogManager.getLogger(UserHandlerImpl.class);

    @Autowired
    private UserDao userDao;

    /*In this method we authenticate the user
     * First we get the user by using email
     * Then check the correct is correct or not
     * If correct give login successfully else give Invalid Username / Password massage*/
    public UserDto login(LoginDto loginDto) {
        try {
            User user = userDao.getByEmail(loginDto.getEmail());
            if (user != null && user.getPassword().equals(loginDto.getPassword())) {
                UserDto userDto = new UserDto();
                BeanUtils.copyProperties(user, userDto);
                return userDto;
            } else {
                logger.warn("Invalid Username / Password");
                throw new UserNotFoundException("Invalid Username / Password");
            }
        } catch (UserNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error during login.", e);
        }
    }

    /*In this method we register user info
     * First get user and then save it*/
    @Override
    public String register(User user) {
        logger.info("END :: CLASS :: UserHandlerImpl :: METHOD :: register");
        try {
            logger.info("START :: CLASS :: UserHandlerImpl :: METHOD :: register");
            userDao.register(user);
            return "User registered successfully !";
        } catch (Exception e) {
            logger.error("An error occurred during user registration: " + e.getMessage());
            return "Error registering user.";
        }
    }

    @Override
    public void validateUser(User user) throws ValidationException {
        if (StringUtils.isBlank(user.getFirstName()) || StringUtils.isBlank(user.getLastName()) || StringUtils.isBlank(user.getEmail()) || StringUtils.isBlank(user.getPassword())) {
            throw new ValidationException("Invalid input. Please provide values for all user properties.");
        }
    }

    /*Hear we can show all the details of user and its dashboard
     *First check user valid or not
     *Get all the user info
     * Then map that info with userDto fields*/
    @Override
    public UserDto displayDashboard(int userId) {
        try {
            logger.info("START :: CLASS :: UserHandlerImpl :: METHOD :: displayDashboard :: USER_ID :: " + userId);
            User user = userDao.getUserById(userId);

            if (user != null) {
                logger.info("Got correct user");
                UserDto userDto = new UserDto();
                BeanUtils.copyProperties(user, userDto);
                logger.info("CLASS :: UserHandlerImpl :: METHOD :: displayDashboard :: USER_ID :: " + userId);
                return userDto;
            } else {
                logger.warn("User not found with ID: " + userId);
                throw new UserNotFoundException("User not found with ID: " + userId);
            }
        } catch (Exception e) {
            logger.error("An error occurred during displaying dashboard: " + e.getMessage());
            throw new RuntimeException("Error displaying dashboard.", e);
        } finally {
            logger.info("END :: CLASS :: UserHandlerImpl :: METHOD :: displayDashboard :: USER_ID :: " + userId);
        }
    }
}
