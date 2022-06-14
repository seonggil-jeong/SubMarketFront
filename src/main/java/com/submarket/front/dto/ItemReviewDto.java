package com.submarket.front.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemReviewDto {
    private List<ItemReviewDto> response;
    private Integer reviewSeq;
    private String userId;
    private int reviewStar;
    private String reviewContents;
    private String reviewDate;
    private String userAge;
}
