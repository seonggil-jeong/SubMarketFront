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
    public List<Map<String, Object>> test() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CategoryDto> response =  restTemplate.exchange("http://127.0.0.1:55460/category", HttpMethod.GET, null, CategoryDto.class);
        CategoryDto categoryDto = response.getBody();

        // Back 에서 받을 값을 response : <List> 로 Map 타입으로 변환해서 전송, Front : DTO로 받아 이름으로 response 에 값 저장



       return response.getBody().getResponse();
    }
}
