package com.submarket.front.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
public class CategoryDto {
    private Map<String, Map<String, Object>> response;
    private Map<String, Object> header;
    private Map<String, Object> body;
    private List<Map<String, Object>> categorys;
}
