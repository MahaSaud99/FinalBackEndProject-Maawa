package com.example.maawa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor @Data
public class GetItemForm {
    Integer userId;
    Integer storeId;
    Integer orderId;
}
