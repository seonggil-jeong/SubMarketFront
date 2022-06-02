package com.submarket.front.vo;

import lombok.Data;

@Data
public class RequestChangePassword {
    private String userId;
    private String oldPassword;
    private String newPassword;
}
