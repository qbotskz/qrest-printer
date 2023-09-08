package com.qbots.qrest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class OrderItemDeleteDTO implements Serializable {

    long id;
    private OrderItemDTO orderItem;

    private String reason;

    long orderId;
    String waiterName;
    String hallName;
    long deskNumber;

}
