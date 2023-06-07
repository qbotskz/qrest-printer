package com.qbots.qrest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DeskDTO {

    long id;
    long number;

    boolean available;

    OrderStatus foodOrderStatus;

    FoodOrderDTO order;

//    String currentWaiterFullName;

    HallDTO hall;


}
