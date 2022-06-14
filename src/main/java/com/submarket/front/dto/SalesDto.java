
package com.submarket.front.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesDto {
    private List<SalesDto> response;
    private int salesSeq;
    private String date;
    private int value;

}
