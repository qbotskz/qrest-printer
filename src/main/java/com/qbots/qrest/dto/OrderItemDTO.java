package com.qbots.qrest.dto;




import lombok.Getter;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

@Getter
public class OrderItemDTO  implements Serializable {

    private long id;

    private int quantity;

    private int price;

//    private Date createdDate;

    OrderItemStatus orderItemStatus;

    private FoodDTO food;

    private String comment;


}
