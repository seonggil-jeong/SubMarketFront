package com.submarket.front.service.impl;

import com.submarket.front.dto.ItemDto;
import com.submarket.front.dto.OrderDto;
import com.submarket.front.dto.SalesDto;
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

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class SellerService implements ISellerService {
    private final RestTemplate restTemplate;
    private final Environment env;
    private final ItemService itemService;


    @Override
    public String addItem(String token, ItemDto itemDto) throws Exception {

        String url = env.getProperty("gateway.ip") + "/item-service/items";

        String rStr = "";

        try {
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            ByteArrayResource mainImage = new ByteArrayResource(itemDto.getMainImage().getBytes()) { // 파일 정보
                @Override
                public String getFilename() {
                    return itemDto.getMainImage().getOriginalFilename(); //저장될 파일 이름
                }
            };
            body.add("mainImage", mainImage);

            if (itemDto.getSubImage().getSize() > 0) {
                ByteArrayResource subImage = new ByteArrayResource(itemDto.getSubImage().getBytes()) {
                    @Override
                    public String getFilename() {
                        return itemDto.getSubImage().getOriginalFilename();
                    }
                };
                body.add("subImage", subImage);
            }


            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", token);
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);


            body.add("itemTitle", itemDto.getItemTitle());
            body.add("itemContents", itemDto.getItemContents());
            body.add("itemPrice", itemDto.getItemPrice());
            body.add("itemCount", itemDto.getItemCount());
            body.add("categorySeq", itemDto.getCategorySeq());

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
            String url = env.getProperty("gateway.ip") + "/seller-service/sellers";

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

    @Override
    public List<ItemDto> findItemList(String token) throws Exception {
        String url = env.getProperty("gateway.ip") + "/item-service/item/seller";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        List<ItemDto> itemDtoList = new LinkedList<>();

        HttpEntity entity = new HttpEntity(headers);

        try {

            ResponseEntity<ItemDto> response = restTemplate.exchange(url, HttpMethod.GET, entity, ItemDto.class);

            itemDtoList = response.getBody().getResponse();

        } catch (Exception exception) {
            log.info("Exception : " + exception);
            itemDtoList = new LinkedList<>();

        } finally {
            return itemDtoList;
        }

    }

    @Override
    public int findTotalValue(String token, List<ItemDto> itemDtoList) throws Exception {
        // 매출액 조회
        log.info(this.getClass().getName() + ".findTotalValue Start!");
        int totalPrice = 0;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", token);

            for (ItemDto itemDto : itemDtoList) {
                int itemPrice = itemDto.getItemPrice();

                String url = env.getProperty("gateway.ip") + "/user-service/sellers/subs/" + itemDto.getItemSeq();

                HttpEntity<Map> entity = new HttpEntity<>(headers);
                ResponseEntity<Integer> response = restTemplate.exchange(url, HttpMethod.GET, entity, Integer.class);
                int subCount = response.getBody();

                totalPrice += itemPrice * subCount;
            }

        } catch (Exception exception) {
            log.info("Exception : " + exception);

        } finally {
            return totalPrice;
        }
    }

    @Override
    public List<ItemDto> findEachItemTotalPrice(String token, List<ItemDto> itemDtoList) throws Exception {
        // 매출액 조회
        log.info(this.getClass().getName() + ".findTotalValue Start!");

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", token);

            for (int i = 0; i < itemDtoList.size(); i++) {
                int itemPrice = itemDtoList.get(i).getItemPrice();
                String url = env.getProperty("gateway.ip") + "/user-service/sellers/subs/" + itemDtoList.get(i).getItemSeq();
                HttpEntity<Map> entity = new HttpEntity<>(headers);

                ResponseEntity<Integer> response = restTemplate.exchange(url, HttpMethod.GET, entity, Integer.class);
                int subCount = response.getBody();

                int price = itemPrice * subCount;

                itemDtoList.get(i).setItemTotalPrice(price);
            }

        } catch (Exception exception) {
            log.info("Exception : " + exception);

        } finally {
            return itemDtoList;
        }
    }

    @Override
    public List<SalesDto> findAllSalesDtoBySellerId(String token) throws Exception {
        log.info(this.getClass().getName() + ".findAllSalesDtoBySellerId Start!");

        String url = env.getProperty("gateway.ip") + "/seller-service/sellers/sales";
        List<SalesDto> salesDtoList = new ArrayList<>();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);

        HttpEntity entity = new HttpEntity(headers);

        try {
            ResponseEntity<SalesDto> response = restTemplate.exchange(url, HttpMethod.GET, entity, SalesDto.class);
            salesDtoList = (List<SalesDto>) response.getBody().getResponse();


        } catch (HttpStatusCodeException statusCodeException) {
            int code = statusCodeException.getRawStatusCode();
            log.info("HttpStatusCodeException : " + statusCodeException);

            salesDtoList = new LinkedList<>();

        } catch (Exception e) {
            log.info("Exception : " + e);
            salesDtoList = new LinkedList<>();

        } finally {
        log.info(this.getClass().getName() + ".findAllSalesDtoBySellerId End!");
            return salesDtoList;
        }
    }

    @Override
    public List<OrderDto> getOrderDtoList(String sellerId, String token) throws Exception {
        log.info(this.getClass().getName() + ".getOrderDtoList Start!");

        List<OrderDto> orderDtoList = new LinkedList<>();
        String url = env.getProperty("gateway.ip") + "/order-service/sellers/" + sellerId + "/orders";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", token);


            HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);

            ResponseEntity<OrderDto> response = restTemplate.exchange(url, HttpMethod.GET, entity, OrderDto.class);

            orderDtoList = response.getBody().getResponse();

            for (int i = 0; i < orderDtoList.size(); i++) {
                int itemSeq = orderDtoList.get(i).getItemSeq();

                ItemDto itemDto = itemService.getItemInfoDetails(itemSeq);
                orderDtoList.get(i).setItemTitle(itemDto.getItemTitle());
            }

        } catch (Exception exception) {
            log.info("Exception : " + exception);
            orderDtoList = new LinkedList<>();

        } finally {
            log.info(this.getClass().getName() + ".getOrderDtoList End!");
            return orderDtoList;
        }
    }
}
