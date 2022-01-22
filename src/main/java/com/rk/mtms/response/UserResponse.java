package com.rk.mtms.response;

import lombok.Data;

@Data
public class UserResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String mobileNo;
    private String email;
    private String userName;
    private String userType;
    private String url;
}

