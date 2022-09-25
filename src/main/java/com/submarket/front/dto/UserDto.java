package com.submarket.front.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int userSeq;
    private String userId;
    private String userPassword;
    private String userEncPassword;
    private String userName;
    private String userEmail;
    private String userAge;
    private String userPn;
    private int userStatus;
    private String userAddress;
    private String userAddress2;
}

