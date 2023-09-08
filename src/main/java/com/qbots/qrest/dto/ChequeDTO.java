package com.qbots.qrest.dto;

import com.qbots.qrest.dto.PaymentDTO;
import lombok.Getter;
import lombok.Setter;


import java.util.List;
import java.util.Map;
import java.util.TreeMap;


@Getter
@Setter
public class ChequeDTO {


    private long id;
    private double total;

    private PaymentDTO prepayment;

    private double discount;
    private double service;

    private int deliveryPrice;

    private Boolean useCashback;

    private double usedCashback;

    private double cashbackPercentage;

    private double addedCashback;

    private double calculatedTotal;

    private Double forPayment;

    @Getter
    private Double change;


    private List<PaymentDTO> payments;


    public ChequeDTO() {

    }

}
