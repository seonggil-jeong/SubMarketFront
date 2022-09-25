package com.submarket.front.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubRequest {
    private int subSeq;
    private int itemSeq;
    private String subDate;
    private int subCount;
    private String userId;
}
