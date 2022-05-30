package com.submarket.front.service.impl;

import com.submarket.front.service.ISellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class SellerService implements ISellerService {
    private final RestTemplate restTemplate;
    private final Environment env;
}
