package com.qbots.qrest.timerTasks;

import com.qbots.qrest.services.PrinterService;
import com.qbots.qrest.dto.OrderItemDeleteDTO;
import com.qbots.qrest.dto.PrintKitchenDTO;
import com.qbots.qrest.dto.PrintPaymentDTO;
import com.qbots.qrest.dto.PrintPrecheckDTO;
import com.qbots.qrest.printerApi.PrinterConnectionApi;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TimerTask;

@Service
public class WebsocketConnectionTask extends TimerTask {


    @Override
    public void run() {

        try {
//            log.info("Sending request!");
            PrinterConnectionApi printerConnectionApi = new PrinterConnectionApi();

            List<PrintKitchenDTO> printKitchenDTOS = printerConnectionApi.getAllPrintKitchens2();
            PrinterService.printKitchen(printKitchenDTOS);
            System.out.println(printKitchenDTOS);

            List<OrderItemDeleteDTO> orderItemDeleteDTOS = printerConnectionApi.getAllOrderItemDeletes();
            PrinterService.printCancelOrderItem(orderItemDeleteDTOS);

            List<PrintPrecheckDTO> printPrecheckDTOS = printerConnectionApi.getAllPrintPrecheck();
            PrinterService.printPrecheck(printPrecheckDTOS);

            List<PrintPaymentDTO> printPaymentDTOS = printerConnectionApi.getAllPayments();
            PrinterService.printPayment(printPaymentDTOS);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
