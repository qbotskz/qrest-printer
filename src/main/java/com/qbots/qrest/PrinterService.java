package com.qbots.qrest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qbots.qrest.checks.PrintableCancelOrderItem;
import com.qbots.qrest.checks.PrintableKitchen;
import com.qbots.qrest.checks.PrintablePayment;
import com.qbots.qrest.checks.PrintablePrecheck;
import com.qbots.qrest.dto.OrderItemDTO;
import com.qbots.qrest.dto.OrderItemDeleteDTO;
import com.qbots.qrest.dto.PrintKitchenDTO;
import com.qbots.qrest.dto.PrintPrecheckDTO;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.awt.print.*;
import java.util.ArrayList;
import java.util.HashSet;

public class PrinterService{

    public static void printKitchen(String msgJsonType) throws PrinterException {

        PrintKitchenDTO printKitchenDTO = new Gson().fromJson(msgJsonType, new TypeToken<PrintKitchenDTO>(){}.getType());

        for (PrintService printService : getPrintServices(printKitchenDTO)){

            PrinterJob printerJob = PrinterJob.getPrinterJob();
            printerJob.setPrintService(printService);

            PageFormat pageFormat = printerJob.defaultPage();
            Paper paper = pageFormat.getPaper();

            // Установите размер страницы печати в пикселях
            double paperWidth = 303; // ширина страницы в пикселях
            double paperHeight = 1800; // высота страницы в пикселях

            // Установите размер бумаги
            paper.setSize(paperWidth, paperHeight);

            // Установите позицию левого верхнего угла страницы
            double marginLeft = 20; // отступ слева в пикселях
            double marginTop = 0; // отступ сверху в пикселях
            paper.setImageableArea(marginLeft, marginTop, paperWidth - marginLeft * 2, paperHeight - marginTop * 2);

            // Установите измененную бумагу обратно в PageFormat
            pageFormat.setPaper(paper);
//////////////////////////////////////////////////////////////

// Подготовка документа для печати (например, создание экземпляра класса Printable)


            Printable printable = new PrintableKitchen(getDTOByPrinter(printService, printKitchenDTO));

            printerJob.setPrintable(printable, pageFormat); // Установка документа для печати
            printerJob.print(); // Печать документа
        }






    }

    private static PrintKitchenDTO getDTOByPrinter(PrintService printService, PrintKitchenDTO printKitchenDTO) {
        java.util.List<OrderItemDTO> items = new ArrayList<>();
        for (OrderItemDTO itemDTO : printKitchenDTO.getItems()){
            if (itemDTO.getFood().getKitchenName().equals(printService.getName())){
                items.add(itemDTO);
            }
        }
        PrintKitchenDTO printKitchenDTO1 = new PrintKitchenDTO(printKitchenDTO);
        printKitchenDTO1.setItems(items);
        return printKitchenDTO1;
    }

    public static java.util.Set<PrintService> getPrintServices(PrintKitchenDTO printKitchenDTO) {
        java.util.Set<PrintService> printers = new HashSet<>();
        for (OrderItemDTO orderItemDTO : printKitchenDTO.getItems()){
            PrintService defaultPrintService = getPrinterByName(orderItemDTO.getFood().getKitchenName());
            PrintService printService;
            if (defaultPrintService != null){
                printers.add(defaultPrintService);
            }
        }
        return printers;
    }

//    public static void printKitchen(String msg, String printerName){
//        print();
//    }
    private static PrintService getPrinterByName(String printerName) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
//        PrintService[] printServices = PrintServiceLookup.lookupPrintServices();
//        PrintServiceLookup.

        // Вывод списка доступных принтеров
        for (PrintService printService : printServices) {
            if (printService.getName().equals(printerName)){
                return printService;
            }
        }
        return null;
    }

    public static void printPrecheck(String msgJsonType) throws PrinterException {

        PrintPrecheckDTO printPrecheckDTO = new Gson().fromJson(msgJsonType, new TypeToken<PrintPrecheckDTO>() {
        }.getType());


        PrintService printService = getPrinterByName(printPrecheckDTO.getPrinterName());
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintService(printService);
        PageFormat pageFormat = printerJob.defaultPage();
        Paper paper = pageFormat.getPaper();
        // Установите размер страницы печати в пикселях
        double paperWidth = 303; // ширина страницы в пикселях
        double paperHeight = 1800; // высота страницы в пикселях
        // Установите размер бумаги
        paper.setSize(paperWidth, paperHeight);
        // Установите позицию левого верхнего угла страницы
        double marginLeft = 20; // отступ слева в пикселях
        double marginTop = 0; // отступ сверху в пикселях
        paper.setImageableArea(marginLeft, marginTop, paperWidth - marginLeft * 2, paperHeight - marginTop * 2);

        // Установите измененную бумагу обратно в PageFormat
        pageFormat.setPaper(paper);
//////////////////////////////////////////////////////////////

// Подготовка документа для печати (например, создание экземпляра класса Printable)


        Printable printable = new PrintablePrecheck(printPrecheckDTO);

        printerJob.setPrintable(printable, pageFormat); // Установка документа для печати
        printerJob.print(); // Печать документа


    }
    public static void printPayment(String msgJsonType) throws PrinterException {

        PrintPrecheckDTO printPrecheckDTO = new Gson().fromJson(msgJsonType, new TypeToken<PrintPrecheckDTO>() {
        }.getType());


        PrintService printService = getPrinterByName(printPrecheckDTO.getPrinterName());
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintService(printService);
        PageFormat pageFormat = printerJob.defaultPage();
        Paper paper = pageFormat.getPaper();
        // Установите размер страницы печати в пикселях
        double paperWidth = 303; // ширина страницы в пикселях
        double paperHeight = 1800; // высота страницы в пикселях
        // Установите размер бумаги
        paper.setSize(paperWidth, paperHeight);
        // Установите позицию левого верхнего угла страницы
        double marginLeft = 20; // отступ слева в пикселях
        double marginTop = 0; // отступ сверху в пикселях
        paper.setImageableArea(marginLeft, marginTop, paperWidth - marginLeft * 2, paperHeight - marginTop * 2);

        // Установите измененную бумагу обратно в PageFormat
        pageFormat.setPaper(paper);
//////////////////////////////////////////////////////////////

// Подготовка документа для печати (например, создание экземпляра класса Printable)


        Printable printable = new PrintablePayment(printPrecheckDTO);

        printerJob.setPrintable(printable, pageFormat); // Установка документа для печати
        printerJob.print(); // Печать документа


    }

    public static void printCancelOrderItem(String msgJsonType) throws PrinterException {

        OrderItemDeleteDTO orderItemDeleteDTO = new Gson().fromJson(msgJsonType, new TypeToken<OrderItemDeleteDTO>() {
        }.getType());


        PrintService printService = getPrinterByName(orderItemDeleteDTO.getOrderItemDTO().getFood().getKitchenName());
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintService(printService);
        PageFormat pageFormat = printerJob.defaultPage();
        Paper paper = pageFormat.getPaper();
        // Установите размер страницы печати в пикселях
        double paperWidth = 303; // ширина страницы в пикселях
        double paperHeight = 1800; // высота страницы в пикселях
        // Установите размер бумаги
        paper.setSize(paperWidth, paperHeight);
        // Установите позицию левого верхнего угла страницы
        double marginLeft = 20; // отступ слева в пикселях
        double marginTop = 0; // отступ сверху в пикселях
        paper.setImageableArea(marginLeft, marginTop, paperWidth - marginLeft * 2, paperHeight - marginTop * 2);

        // Установите измененную бумагу обратно в PageFormat
        pageFormat.setPaper(paper);
//////////////////////////////////////////////////////////////

// Подготовка документа для печати (например, создание экземпляра класса Printable)


        Printable printable = new PrintableCancelOrderItem(orderItemDeleteDTO);

        printerJob.setPrintable(printable, pageFormat); // Установка документа для печати
        printerJob.print(); // Печать документа


    }

}
