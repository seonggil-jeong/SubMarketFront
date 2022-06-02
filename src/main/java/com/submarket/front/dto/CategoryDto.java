package com.submarket.front.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
public class CategoryDto {
    private String categorySeq;
    private String categoryName;
    private List<ItemDto> items;

}
