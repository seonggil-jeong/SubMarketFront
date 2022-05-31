package com.submarket.front.service;

import com.submarket.front.dto.UserDto;
import com.submarket.front.vo.RequestLogin;
import com.submarket.front.vo.ResponseUser;

public interface IUserService {
    ResponseUser getUserInfo(String token) throws Exception;

    String modifyUserInfo(UserDto userDto) throws Exception;
}
