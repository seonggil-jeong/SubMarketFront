package com.submarket.front.controller;

import com.submarket.front.dto.CategoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Slf4j
@Controller
public class TestController {
    @RequestMapping("/test")
    public String test() throws Exception {
        return "/user/sub-list";
    }
}
