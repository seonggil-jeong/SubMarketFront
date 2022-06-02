package com.submarket.front.service;

import com.submarket.front.dto.ItemReviewDto;

import java.util.List;

public interface IItemService {
    List<ItemReviewDto> findItemReviewByUserToken(String token) throws Exception;

    String deleteItemReviewByReviewSeq(int reviewSeq, String token) throws Exception;

    String modifyItemReviewByReviewSeq(ItemReviewDto itemReviewDto, String token) throws Exception;

}
