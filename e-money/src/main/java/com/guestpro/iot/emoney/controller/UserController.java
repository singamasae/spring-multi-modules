package com.guestpro.iot.emoney.controller;


import com.guestpro.iot.emoney.model.Role;
import com.guestpro.iot.emoney.model.User;
import com.guestpro.iot.emoney.pojo.GenericResponse;
import com.guestpro.iot.emoney.pojo.RegisterUser;
import com.guestpro.iot.emoney.repository.RoleRepository;
import com.guestpro.iot.emoney.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public ResponseEntity<String> currentUser(Principal principal) {
        return ResponseEntity.status(HttpStatus.OK).body("Hello, " + principal.getName());
    }

    // behavior:
    // register user
    @PostMapping("/register")
    public GenericResponse registerUser(@Valid @RequestBody RegisterUser user) {
        GenericResponse response = new GenericResponse();
        if (!user.getPassword().equals(user.getRepassword())) {
            response.setMessage("password not match");
            response.setSuccess(false);
            return response;
        }

        Role role = roleRepository.findById(user.getRoleId()).orElse(null);
        if (role == null) {
            response = new GenericResponse();
            response.setMessage("invalid role id");
            response.setSuccess(false);
            return response;
        }

        List<Role> roles = new ArrayList<>();
        roles.add(role);

        User registeredUser = userService.register(user, roles);
        if (registeredUser == null) {
            response = new GenericResponse();
            response.setMessage("unable to register new user");
            response.setSuccess(false);
            return response;
        }

        response = new GenericResponse();
        response.setSuccess(true);
        response.setMessage("register user successful");
        return response;
    }

    // unregister user
    @PutMapping("/unregister/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public GenericResponse unregisterUser(@PathVariable String id) {
        GenericResponse response;
        User user = userService.unRegisterUser(id);
        if (user == null) {
            response = new GenericResponse();
            response.setMessage("unable to unregister existing user");
            response.setSuccess(false);
            return response;
        }

        response = new GenericResponse();
        response.setSuccess(true);
        response.setMessage("unregister user successful");
        return response;
    }

}
