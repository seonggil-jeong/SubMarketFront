package com.submarket.front.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubDto {
    private List<SubDto> response;
    private Integer subSeq;
    private int itemSeq;
    private String subDate;
    private int subCount;
    private String userId;

    private String itemTitle;
    private String mainImagePath; // DB에 저장되어 있는 이미지 정보
    private int itemPrice;

    // Kafka Setting
    private String sellerId;
    private String userAddress;
    private String userAddress2;
}
