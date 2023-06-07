package com.qbots.qrest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
public enum OrderItemStatus {
    IN_CART(0), // в корзине, не готовится
    COOK(1), // уже готовится
    EAT(2); // кушают

    long id;
}
