package com.rk.mtms.request;

import lombok.Data;

@Data
public class RegisterRequest {
        private int id;
        private String firstName;
        private String lastName;
        private String userName;
        private String password;
        private String email;
        private String mobile;
        private Integer addressId;
        private boolean manager;
}

