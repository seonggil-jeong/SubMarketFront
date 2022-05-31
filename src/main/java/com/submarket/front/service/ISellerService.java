package com.submarket.front.service;

import com.submarket.front.dto.SellerDto;

public interface ISellerService {
    SellerDto getSellerInfo(String token) throws Exception;
}
