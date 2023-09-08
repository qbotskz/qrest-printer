package com.qbots.qrest.timerTasks;

import com.qbots.qrest.dto.OrderItemDeleteDTO;
import com.qbots.qrest.dto.PrintKitchenDTO;
import com.qbots.qrest.dto.PrintPaymentDTO;
import com.qbots.qrest.dto.PrintPrecheckDTO;
import com.qbots.qrest.printerApi.PrinterConnectionApi;
import com.qbots.qrest.services.PrinterService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.SortedMap;
import java.util.TimerTask;

@Service
public class CheckFilesTask extends TimerTask {


    @Override
    public void run() {

        try {
//            log.info("Sending request!");
            PrinterConnectionApi printerConnectionApi = new PrinterConnectionApi();
            String fileName = printerConnectionApi.getNotPrintedFile();
            System.out.println(fileName);
            if (fileName != null){
                if (printerConnectionApi.updatePrintedFile(fileName)){
                    PrinterService.printPDF(fileName);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
