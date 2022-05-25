package com.submarket.front.controller;

import com.submarket.front.dto.CategoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Slf4j
@RestController
public class TestController {
    @RequestMapping("/test")
    public Map<String, Object> test() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CategoryDto> response =  restTemplate.exchange("http://127.0.0.1:11000/category", HttpMethod.GET, null, CategoryDto.class);
        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setResponse(response.getBody().getResponse());
        categoryDto.setHeader(categoryDto.getResponse().get("header"));
        categoryDto.setBody(categoryDto.getResponse().get("body"));
        categoryDto.setCategorys((List<Map<String, Object>>) categoryDto.getBody().get("categorys"));


        return categoryDto.getCategorys().get(1);
    }
}
