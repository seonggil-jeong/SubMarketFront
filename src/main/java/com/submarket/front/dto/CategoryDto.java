package com.submarket.front.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
public class CategoryDto {
    private List<Map<String, Object>> response;
    private List<Map<String, Object>> body;
}
