package com.qbots.qrest.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentDTO {

    private long     id;

    private double amount;

    PaymentTypeDTO paymentType;


}
