package com.submarket.front.service;

import com.submarket.front.dto.CategoryDto;
import com.submarket.front.dto.ItemDto;
import com.submarket.front.dto.ItemReviewDto;

import java.util.List;

public interface IItemService {
    List<ItemReviewDto> findItemReviewByUserToken(String token) throws Exception;

    List<ItemDto> findItemRandomItem() throws Exception;

    List<ItemReviewDto> findItemReviewByItemSeq(int itemSeq) throws Exception;

    String deleteItemReviewByReviewSeq(int reviewSeq, String token) throws Exception;

    String modifyItemReviewByReviewSeq(ItemReviewDto itemReviewDto, String token) throws Exception;

    List<ItemDto> getItemInfo() throws Exception;

    CategoryDto getItemInfoByCategorySeq(int categorySeq) throws Exception;


    ItemDto getItemInfoDetails(int itemSeq) throws Exception;

    List<ItemDto> getItemInfoByAge(int age) throws Exception;


}
