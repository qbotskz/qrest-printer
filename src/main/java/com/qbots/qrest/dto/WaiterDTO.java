package com.qbots.qrest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class WaiterDTO {

    private String name;
    private long chatId;
    private long id;
    private List<DeskDTO> desks;
    private Long code;
    private Integer closedOrdersCash;
    private long deskSize;

}
