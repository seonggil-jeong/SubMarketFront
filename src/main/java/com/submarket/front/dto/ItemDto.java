package com.submarket.front.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private List<ItemDto> response;
    private int itemSeq;
    private String sellerId;
    private String itemTitle;
    private String itemContents;

    private int itemPrice;
    private int itemCount; // 상품 수

    private int categorySeq;

    private int itemStatus; // 활성화
    private String imagePath;

    // TODO: 2022/05/11 FK 설정
}