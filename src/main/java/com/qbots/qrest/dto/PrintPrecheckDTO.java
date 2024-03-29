package com.qbots.qrest.dto;

import lombok.Getter;
import java.util.Date;

@Getter
public class PrintPrecheckDTO {
    long id;
    String waiterName;
    Long deskNumber;
    String hallName;
    String precheckDate;
    FoodOrderDTO foodOrder;
    String printerName;
    Date cancelPrecheckDate;
}
