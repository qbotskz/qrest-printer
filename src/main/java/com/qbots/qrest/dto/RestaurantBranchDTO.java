package com.qbots.qrest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantBranchDTO {
    private int     id;

    private String branchName;

    private CityDTO city;

    public RestaurantBranchDTO() {
    }

    public RestaurantBranchDTO(int id, String branchName, CityDTO city) {
        this.id = id;
        this.branchName = branchName;
        this.city = city;
    }
}
