package com.submarket.front.service;

import com.submarket.front.dto.OrderDto;
import com.submarket.front.dto.SubDto;
import com.submarket.front.dto.UserDto;
import com.submarket.front.vo.RequestLogin;

import java.util.List;

public interface IUserService {
    UserDto getUserInfo(String token) throws Exception;

    String modifyUserInfo(UserDto userDto) throws Exception;

    List<SubDto> getSubList(String token) throws Exception;

    String saveSub(SubDto subDto, String token) throws Exception;
}
