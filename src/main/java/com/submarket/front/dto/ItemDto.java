package com.submarket.front.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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

    private int readCount20;
    private int readCount30;
    private int readCount40;
    private int readCountOther;

    private int userAge;

    private String mainImagePath; // DB에 저장되어 있는 이미지 정보
    private String subImagePath;

    private MultipartFile mainImage; // Front 에서 넘어온 이미지
    private MultipartFile subImage; // Image 2


    // 상품 정보 조회
    private int itemTotalPrice;

    // TODO: 2022/05/11 FK 설정
}