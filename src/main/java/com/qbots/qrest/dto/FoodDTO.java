package com.qbots.qrest.dto;



import lombok.Getter;
import lombok.Setter;

import java.util.List;


//@Data
@Getter
@Setter
public class FoodDTO {

    private long     id;

    private String name;

    private String description;
    private Integer price;
    private Long remains;
    private String lastChanged;
    private RestaurantBranchDTO branch;

    private String photo_url;

    private Boolean activated;
    private Integer specialOfferSum;
    private Integer cashBackPercentage;
    private List<KitchenDTO> kitchens;


}
