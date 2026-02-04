package com.sds.x6_product.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
public record Product (
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id,
    String name,
    BigDecimal price,
    String description,
    int stock
    ) implements Serializable {}

