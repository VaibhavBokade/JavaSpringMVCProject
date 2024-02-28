package org.example.controller;

import org.example.dto.UserDto;
import org.example.entities.User;
import org.example.exceptions.UserNotFoundException;
import org.example.exceptions.ValidationException;
import org.example.handler.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;

/*In this controller we write the controller related to user field and Autowired the user handler class */
@Controller
public class UserController {

    @Autowired
    private UserHandler userHandler;

    /*In this controller we register the user*/
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            userHandler.validateUser(user);
            userHandler.register(user);
            return ResponseEntity.ok("User registered successfully !");
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering user.");
        }
    }

    /*In this controller we see all the information of uer */
    @GetMapping(value = "/introTaskManager/dashboard")
    public ResponseEntity<UserDto> dashboard(@RequestHeader("userId") int userId) {
        try {
            UserDto userDto = userHandler.displayDashboard(userId);
            return ResponseEntity.ok(userDto);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /*In this api we take a session from request and terminate that session*/
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        try {
            request.getSession().invalidate();
            return ResponseEntity.ok("Logout successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during logout.");
        }
    }
}
