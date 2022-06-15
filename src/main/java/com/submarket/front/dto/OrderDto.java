package com.submarket.front.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    List<OrderDto> response;
    private String orderId;
    private int itemSeq;
    private String userId;
    private String sellerId;

    private String userAddress;
    private String userAddress2;

    private String orderDateDetails;
    private String orderDate;


    private String itemTitle;
}
