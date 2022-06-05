package com.submarket.front.service.impl;

import com.submarket.front.dto.ItemDto;
import com.submarket.front.dto.SellerDto;
import com.submarket.front.service.ISellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class SellerService implements ISellerService {
    private final RestTemplate restTemplate;
    private final Environment env;


    @Override
    public String addItem(String token, ItemDto itemDto) throws Exception {

        String url = env.getProperty("gateway.ip") + "/item-service/items";

        String rStr = "";

        try {
            ByteArrayResource mainImage = new ByteArrayResource(itemDto.getMainImage().getBytes()) { // 파일 정보
                @Override
                public String getFilename() {
                    return itemDto.getMainImage().getOriginalFilename(); //저장될 파일 이름
                }
            };
            ByteArrayResource subImage = new ByteArrayResource(itemDto.getSubImage().getBytes()) {
                @Override
                public String getFilename() {
                    return itemDto.getSubImage().getOriginalFilename();
                }
            };


            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", token);
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);


            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("itemTitle", itemDto.getItemTitle());
            body.add("itemContents", itemDto.getItemContents());
            body.add("itemPrice", itemDto.getItemPrice());
            body.add("itemCount", itemDto.getItemCount());
            body.add("categorySeq", itemDto.getCategorySeq());
            body.add("mainImage", mainImage);
            body.add("subImage", subImage);

            HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

            rStr = response.getBody();

        } catch (HttpStatusCodeException statusCodeException) {
            int code = statusCodeException.getRawStatusCode();
            log.info("code : " + code);
            log.info("Exception : " + statusCodeException);

            rStr = "상품이 저장되지 않았습니다";
        } catch (Exception e) {
            log.info("Exception : " + e);
            rStr = "상품이 저장되지 않았습니다";

        } finally {
            return rStr;
        }
    }

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
