package com.submarket.front.service;

import com.submarket.front.dto.ItemDto;
import com.submarket.front.dto.SellerDto;

public interface ISellerService {
    SellerDto getSellerInfo(String token) throws Exception;

    String addItem(String token, ItemDto itemDto) throws Exception;
}
