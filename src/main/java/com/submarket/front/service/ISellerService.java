package com.submarket.front.service;

import com.submarket.front.dto.ItemDto;
import com.submarket.front.dto.SalesDto;
import com.submarket.front.dto.SellerDto;

import java.util.List;

public interface ISellerService {
    SellerDto getSellerInfo(String token) throws Exception;

    String addItem(String token, ItemDto itemDto) throws Exception;

    List<ItemDto> findItemList(String token) throws Exception;

    int findTotalValue(String token, List<ItemDto> itemDtoList) throws Exception;

    List<SalesDto> findAllSalesDtoBySellerId(String token) throws Exception;
}
