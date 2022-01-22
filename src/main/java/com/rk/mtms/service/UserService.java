package com.rk.mtms.service;

import com.rk.mtms.request.RegisterRequest;
import com.rk.mtms.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface UserService {

    ResponseEntity<Map<String,Boolean>> register(RegisterRequest registerRequest);

    ResponseEntity<List<Map<String,Boolean>>> validate(RegisterRequest registerRequest);

    ResponseEntity<UserResponse> profile(RegisterRequest registerRequest);

    ResponseEntity<UserResponse> updateUser(RegisterRequest registerRequest);
}
