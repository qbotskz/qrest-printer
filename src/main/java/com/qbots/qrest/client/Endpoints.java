package com.qbots.qrest.client;

public class Endpoints {
    public static final String port = "8052";
//    private static final String port = "8062";
    private static final String URL = "https://109.233.108.126:"+port + "/api/printer";

    public static final String getAllPrintKitchenEndpoint = URL + "/getAvailablePrintKitchens";
    public static final String updateKitchenPrint = URL + "/updatePrintKitchen";

    public static final String getAllCancelPrint = URL + "/getAllCancelPrint";
    public static final String updateCancelOrderItem = URL + "/updateCancelOrderItem";

    public static final String getAllPrecheck = URL + "/getAllPrecheck";
    public static final String updatePrintPrecheck = URL + "/updatePrintPrecheck";


    public static final String getPayments = URL + "/getPayments";
    public static final String updatePayment = URL + "/updatePayment";

    public static final String getNotPrintedFile = URL + "/getNotPrintedFile";
    public static final String updateNotPrintedFile = URL + "/updateNotPrintedFile";





}

