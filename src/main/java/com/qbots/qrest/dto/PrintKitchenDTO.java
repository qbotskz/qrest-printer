package com.qbots.qrest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class PrintKitchenDTO {

    long id;
    List<OrderItemDTO> items;
    long orderId;
//    Date createdDate;
    String waiterName;
    String hallName;
    long deskNumber;

    public PrintKitchenDTO(PrintKitchenDTO printKitchenDTO) {
        this.id = printKitchenDTO.getId();
        this.items = printKitchenDTO.getItems();
        this.orderId = printKitchenDTO.getOrderId();
//        this.createdDate = printKitchenDTO.getCreatedDate();
        this.waiterName = printKitchenDTO.getWaiterName();
        this.hallName = printKitchenDTO.getHallName();
        this.deskNumber = printKitchenDTO.getDeskNumber();
    }
}
