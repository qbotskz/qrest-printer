package com.qbots.qrest.dto;

import lombok.Data;

import java.util.List;

@Data
public class HallDTO {

    private long     id;

    private String name;

    List<DeskDTO> desks;

}

