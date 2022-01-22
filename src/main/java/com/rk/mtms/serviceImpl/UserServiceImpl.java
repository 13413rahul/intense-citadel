package com.rk.mtms.serviceImpl;

import com.rk.mtms.entity.Image;
import com.rk.mtms.entity.User;
import com.rk.mtms.repository.*;
import com.rk.mtms.request.RegisterRequest;
import com.rk.mtms.response.UserResponse;
import com.rk.mtms.service.UserService;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Component
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    RegisterRepository registerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ImageRepository imageRepository;


    @Override
    public ResponseEntity<Map<String,Boolean>> register(RegisterRequest registerRequest) {
        log.info("kjghdfsghj {} jkghffjh {}",registerRequest , 1);
        registerRepository.insertIntoUserTable(registerRequest.getFirstName(), registerRequest.getLastName(), registerRequest.getUserName(), registerRequest.getPassword(),registerRequest.getMobile(),registerRequest.getEmail());
        boolean isSuccessRegister = true;
        Map<String,Boolean> resultMap = new HashMap<String,Boolean>();
        resultMap.put("isSuccessRegister",isSuccessRegister);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<Map<String,Boolean>>> validate(RegisterRequest registerRequest) {
        boolean isValidUser = false;
        String userType = "Student";
        Map<String,Boolean> resultMap = new HashMap<>();
        List<Map<String, Boolean>> result = new ArrayList<>();

        if(registerRequest.isManager()) {
            userType = "manager";
        }
        User user = userRepository.getUserForUserName(registerRequest.getUserName(), userType);
        if(user == null) {
            resultMap.put("isValidUser",isValidUser);
            result.add(resultMap);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        String RequestedPassword = registerRequest.getPassword();
        String passwordDb = user.getPassword();

        if(passwordDb.equals(RequestedPassword)) {
            isValidUser = true;
        }

        resultMap.put("isValidUser",isValidUser);
        result.add(resultMap);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @Override
    public  ResponseEntity<UserResponse> profile(RegisterRequest registerRequest) {
        UserResponse userResponse = new UserResponse();
        String userType = "Student";
        if(registerRequest.isManager()) {
            userType = "manager";
        }

        User user = userRepository.getUserForUserName(registerRequest.getUserName(),userType);
        Image image = null;

        if(imageRepository.findByUserId(user.getId()) != null) {
            image = imageRepository.findByUserId(user.getId());
            userResponse.setUrl(image.getUrl());
        }

        userResponse.setMobileNo(user.getPhoneNo());
        userResponse.setEmail(user.getEmail());
        userResponse.setUserName(user.getUserName());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setUserType(user.getUserType());
        userResponse.setId(user.getId());

        return new ResponseEntity<>(userResponse,HttpStatus.OK);

    }

    @Override
    public ResponseEntity<UserResponse> updateUser(RegisterRequest registerRequest) {
        UserResponse userResponse = new UserResponse();
        userRepository.updateUserTable(registerRequest.getFirstName(),registerRequest.getLastName(),registerRequest.getUserName()
        ,registerRequest.getEmail(),registerRequest.getId());
        User user = userRepository.getUserForUserName(registerRequest.getUserName(),"Student");

        userResponse.setEmail(user.getEmail());
        userResponse.setUserName(user.getUserName());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setId(user.getId());

        return new ResponseEntity<>(userResponse,HttpStatus.OK);
    }


}
