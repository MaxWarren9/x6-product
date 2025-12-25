package com.sds.x6_product.model;

import lombok.Data;

@Data
public class ApiError {
    final boolean success;
    final String message;
}
