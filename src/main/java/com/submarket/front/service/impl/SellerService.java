package com.submarket.front.service.impl;

import com.submarket.front.dto.SellerDto;
import com.submarket.front.dto.UserDto;
import com.submarket.front.service.ISellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class SellerService implements ISellerService {
    private final RestTemplate restTemplate;
    private final Environment env;

    @Override
    public SellerDto getSellerInfo(String token) throws Exception {

        SellerDto sellerDto = new SellerDto();

        try {
            String url = env.getProperty("gateway.ip") + "/seller-service/seller";

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", token);

            HttpEntity<HttpHeaders> entity = new HttpEntity(headers);

            ResponseEntity<SellerDto> response = restTemplate.exchange(url, HttpMethod.GET, entity, SellerDto.class);

            sellerDto = response.getBody();
        } catch (HttpStatusCodeException httpStatusCodeException) {
            log.info("StatusCode : " + httpStatusCodeException.getRawStatusCode());

        } catch (Exception e) {
            log.info("Server Error");

        } finally {
            return sellerDto;
        }
    }
}
