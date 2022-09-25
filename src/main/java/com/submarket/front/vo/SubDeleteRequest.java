package com.submarket.front.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SubDeleteRequest {
    private int itemSeq;
    private int subSeq;
    private int userSeq;
}
