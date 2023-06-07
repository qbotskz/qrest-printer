package com.qbots.qrest.dto;

import lombok.Getter;
import java.util.Date;

@Getter
public class PrintPrecheckDTO {
    String waiterName;
    Long deskNumber;
    String hallName;
    String precheckDate;
    FoodOrderDTO foodOrderDTO;
    String printerName;
}
