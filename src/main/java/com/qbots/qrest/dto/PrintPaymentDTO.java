package com.qbots.qrest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrintPaymentDTO {
    long id;
    String waiterName;
    Long deskNumber;
    String hallName;
    String precheckDate;
    FoodOrderDTO foodOrderDTO;
    String printerName;
}
