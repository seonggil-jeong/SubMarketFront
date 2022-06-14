package com.submarket.front.service.impl;

import com.submarket.front.dto.CategoryDto;
import com.submarket.front.dto.ItemDto;
import com.submarket.front.dto.ItemReviewDto;
import com.submarket.front.service.IItemService;
import com.submarket.front.util.CmmUtil;
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

import javax.swing.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemService implements IItemService {
    private final Environment env;
    private final RestTemplate restTemplate;

    @Override
    public List<ItemReviewDto> findItemReviewByUserToken(String token) throws Exception {
        log.info(this.getClass().getName() + "findItemReviewByUserToken Start!");
        List<ItemReviewDto> itemReviewDtoList = new LinkedList<>();

        try {
            String url = env.getProperty("gateway.ip") + "/item-service/item/review";
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", CmmUtil.nvl(token));

            HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);
            ResponseEntity<ItemReviewDto> response = restTemplate.exchange(url, HttpMethod.GET, entity, ItemReviewDto.class);

            itemReviewDtoList = response.getBody().getResponse();

        } catch (HttpStatusCodeException httpStatusCodeException) {
            log.info("statusCode : " + httpStatusCodeException.getRawStatusCode());

            if (httpStatusCodeException.getRawStatusCode() == 401) {
                log.info("사용자 Token Error");
            }
            itemReviewDtoList = new LinkedList<>();

        } catch (Exception e) {
            log.info("Exception : " + e);
            itemReviewDtoList = new LinkedList<>();
        } finally {
            return itemReviewDtoList;
        }
    }

    @Override
    public String deleteItemReviewByReviewSeq(int reviewSeq, String token) throws Exception {
        log.info(this.getClass().getName() + ".deleteItemReviewByReviewSeq Start!");
        log.info("reviewSeq : " + reviewSeq);
        String rStr = null;
        try {
            String url = env.getProperty("gateway.ip") + "/item-service/item/review/" + reviewSeq + "/delete";
            log.info("url : " + url);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", token);

            HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            rStr = response.getBody();
        } catch (HttpStatusCodeException statusCodeException) {
            log.info("Status Exception");

            if (statusCodeException.getRawStatusCode() == 500) {
                rStr = "리뷰 정보를 찾을 수 없습니다";
            }

        } finally {
            return rStr;
        }
    }

    @Override
    public String modifyItemReviewByReviewSeq(ItemReviewDto itemReviewDto, String token) throws Exception {
        log.info(this.getClass().getName() + ".modifyItemReviewByReviewSeq Start!");
        String url = env.getProperty("gateway.ip") + "/item-service/item/review/" + itemReviewDto.getReviewSeq() + "/modify";
        String rStr = "";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", token);

            HttpEntity<ItemReviewDto> entity = new HttpEntity<>(itemReviewDto, headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            rStr = "리뷰 변경 성공";

        } catch (HttpStatusCodeException statusCodeException) {
            log.info("Exception : " + statusCodeException);
            int code = statusCodeException.getRawStatusCode();
            log.info("code : " + code);

            if (code == 401) {
                rStr = "인증 정보 오류";
            } else if (code == 500) {
                rStr = "리뷰 정보를 찾을 수 없습니다";
            }

        } finally {

            return rStr;
        }
    }


    @Override
    public List<ItemDto> getItemInfo() throws Exception {
        log.info(this.getClass().getName() + ".getItemInfo Start!");
        List<ItemDto> itemDtoList = new LinkedList<>();
        String url = env.getProperty("gateway.ip") + "/item-service/items";

        try {

            log.info("url : " + url);
            ResponseEntity<ItemDto> response = restTemplate.exchange(url, HttpMethod.GET, null, ItemDto.class);
            itemDtoList = response.getBody().getResponse();

        } catch (HttpStatusCodeException statusCodeException) {
            int code = statusCodeException.getRawStatusCode();
            log.info("code : " + code);

            if (code >= 500) {
                itemDtoList = new LinkedList<>();
            }
        }finally {
            return itemDtoList;
        }
    }

    @Override
    public CategoryDto getItemInfoByCategorySeq(int categorySeq) throws Exception {
        CategoryDto categoryDto = new CategoryDto();
        String url = env.getProperty("gateway.ip") + "/item-service/category/" + categorySeq;

        try {
            ResponseEntity<CategoryDto> response = restTemplate.exchange(url, HttpMethod.GET, null, CategoryDto.class);
            categoryDto = response.getBody();


        } catch (HttpStatusCodeException statusCodeException) {
            int code = statusCodeException.getRawStatusCode();
            log.info(code + " : " + statusCodeException.getMessage());

            categoryDto = new CategoryDto();

        } finally {
            log.info(this.getClass().getName() + ".getItemInfoByCategorySeq Start!");

            return categoryDto;

        }
    }

    @Override
    public ItemDto getItemInfoDetails(int itemSeq) throws Exception {
        log.info(this.getClass().getName() + ".getItemInfoDetails Start!");
        ItemDto itemDto = new ItemDto();
        String url = env.getProperty("gateway.ip") + "/item-service/items/" + itemSeq;
        log.info("url : " + url);
        try {
            ResponseEntity<ItemDto> response = restTemplate.getForEntity(url, ItemDto.class);
            itemDto = response.getBody();

        } catch (HttpStatusCodeException statusCodeException) {
            int code = statusCodeException.getRawStatusCode();
            log.info(code + "HttpStatusCodeException : " + statusCodeException);
            itemDto = new ItemDto();
        } catch (Exception exception) {
            log.info("Exception : " + exception);
            itemDto = new ItemDto();

        } finally {
            log.info(this.getClass().getName() + ".getItemInfoDetails End!");
            return itemDto;
        }
    }

    @Override
    public List<ItemReviewDto> findItemReviewByItemSeq(int itemSeq) throws Exception {
        List<ItemReviewDto> itemReviewDtoList = new LinkedList<>();
        String url = env.getProperty("gateway.ip") + "/item-service/item/" + itemSeq + "/review";
        log.info("url : " + url);

        ResponseEntity<ItemReviewDto> response = restTemplate.getForEntity(url, ItemReviewDto.class);

         itemReviewDtoList = response.getBody().getResponse();

        return itemReviewDtoList;
    }


    @Override
    public List<ItemDto> findItemRandomItem() throws Exception {
        log.info(this.getClass().getName() + ".getItemInfo Start!");
        List<ItemDto> itemDtoList = new LinkedList<>();
        String url = env.getProperty("gateway.ip") + "/item-service/items";


        try {
            log.info("url : " + url);
            ResponseEntity<ItemDto> response = restTemplate.exchange(url, HttpMethod.GET, null, ItemDto.class);
            itemDtoList = response.getBody().getResponse();

            Collections.shuffle(itemDtoList);

            if (itemDtoList.size() > 10) {
                itemDtoList = itemDtoList.subList(0, 10);
            }
        } catch (HttpStatusCodeException statusCodeException) {
            int code = statusCodeException.getRawStatusCode();
            log.info("code : " + code);

            if (code >= 500) {
                itemDtoList = new LinkedList<>();
            }
        }finally {
            return itemDtoList;
        }
    }
}
