package com.qbots.qrest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class GuestDTO {

    private long id;

    private List<OrderItemDTO> orderItems;

//    private String createdDate;

}