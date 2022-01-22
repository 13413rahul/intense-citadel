package com.rk.mtms.controller;


import com.rk.mtms.request.RegisterRequest;
import com.rk.mtms.response.UserResponse;
import com.rk.mtms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Map<String,Boolean>> registerMe(@RequestBody RegisterRequest registerRequest) {
        return userService.register(registerRequest);
    }

    @RequestMapping(value = "/validateUser", method = RequestMethod.POST)
    public ResponseEntity<List<Map<String,Boolean>>> getListOfRegisteredUserById(@RequestBody RegisterRequest registerRequest) {
        return userService.validate(registerRequest);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ResponseEntity<UserResponse> getRegisteredUserById(@RequestBody RegisterRequest registerRequest) {
        return  userService.profile(registerRequest);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<UserResponse> updateUser(@RequestBody RegisterRequest registerRequest) {
        return  userService.updateUser(registerRequest);
    }

}
