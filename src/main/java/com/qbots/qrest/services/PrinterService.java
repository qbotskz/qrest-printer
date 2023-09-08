package com.qbots.qrest.services;

import com.qbots.qrest.checks.PrintableCancelOrderItem;
import com.qbots.qrest.checks.PrintableKitchen;
import com.qbots.qrest.checks.PrintablePayment;
import com.qbots.qrest.checks.PrintablePrecheck;
import com.qbots.qrest.dto.*;
import com.qbots.qrest.printerApi.PrinterConnectionApi;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.PrinterName;
import java.awt.print.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PrinterService{


    public static void printKitchen(List<PrintKitchenDTO> printKitchenDTOS) throws PrinterException {
        PrinterConnectionApi printerConnectionApi = new PrinterConnectionApi();

        for (PrintKitchenDTO printKitchenDTO : printKitchenDTOS) {
            System.out.println(printKitchenDTO);
            if (printerConnectionApi.updatePrintKitchen(printKitchenDTO)) {
                for (PrintService printService : getPrintServices(printKitchenDTO)) {
                    if(printService != null) {
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
            }

        }



    }

    private static PrintKitchenDTO getDTOByPrinter(PrintService printService, PrintKitchenDTO printKitchenDTO) {
        java.util.List<OrderItemDTO> items = new ArrayList<>();
        for (OrderItemDTO itemDTO : printKitchenDTO.getItems()){
            if (hasPrinter(itemDTO.getFood().getKitchens(), printService.getName())){
                items.add(itemDTO);
            }
        }
        PrintKitchenDTO printKitchenDTO1 = new PrintKitchenDTO(printKitchenDTO);
        printKitchenDTO1.setItems(items);
        return printKitchenDTO1;
    }

    private static boolean hasPrinter(List<KitchenDTO> kitchens, String name) {
        for (KitchenDTO kitchenDTO : kitchens){
            if (kitchenDTO.getPrinterName().equals(name)){
                return true;
            }
        }
        return false;
    }

    private static java.util.Set<PrintService> getPrintServices(PrintKitchenDTO printKitchenDTO) {
        java.util.Set<PrintService> printers = new HashSet<>();
        for (OrderItemDTO orderItemDTO : printKitchenDTO.getItems()) {
            List<PrintService> printerServices = getPrintersByKitchens(orderItemDTO.getFood().getKitchens());
            printers.addAll(printerServices);

        }
        return printers;
    }


    private static List<PrintService> getPrintersByKitchens(List<KitchenDTO> kitchenDTOS) {
        List<PrintService> printServices = new ArrayList<>();

        for (KitchenDTO kitchenDTO : kitchenDTOS){
            printServices.add(getPrinterByName(kitchenDTO.getPrinterName()));
        }
        return printServices;
    }

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

    public static void printPrecheck(List<PrintPrecheckDTO> printPrecheckDTOS) throws PrinterException {

        PrinterConnectionApi printerConnectionApi = new PrinterConnectionApi();

        for (PrintPrecheckDTO printPrecheckDTO : printPrecheckDTOS) {
            System.out.println(printPrecheckDTO);
            if (printerConnectionApi.updatePrintPrecheck(printPrecheckDTO)) {

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
        }


    }
    public static void printPayment(List<PrintPaymentDTO> printPaymentDTOS) throws PrinterException {
        PrinterConnectionApi printerConnectionApi = new PrinterConnectionApi();
        for (PrintPaymentDTO printPaymentDTO : printPaymentDTOS) {
            System.out.println(printPaymentDTO);
            if (printerConnectionApi.updatePayment(printPaymentDTO)) {

                PrintService printService = getPrinterByName(printPaymentDTO.getPrinterName());
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


                Printable printable = new PrintablePayment(printPaymentDTO);

                printerJob.setPrintable(printable, pageFormat); // Установка документа для печати
                printerJob.print(); // Печать документа
            }
        }

    }

    public static void printCancelOrderItem(List<OrderItemDeleteDTO> orderItemDeleteDTOS) throws PrinterException {

        PrinterConnectionApi printerConnectionApi = new PrinterConnectionApi();
        for (OrderItemDeleteDTO orderItemDeleteDTO : orderItemDeleteDTOS) {
            System.out.println(orderItemDeleteDTO);
            if (printerConnectionApi.updateDeleteOrderItem(orderItemDeleteDTO)) {

                List<PrintService> printerServices = getPrintersByKitchens(orderItemDeleteDTO.getOrderItem().getFood().getKitchens());
                for (PrintService printService : printerServices) {
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

// Подготовка документа для печати (например, создание экземпляра класса Printable)


                    Printable printable = new PrintableCancelOrderItem(orderItemDeleteDTO);

                    printerJob.setPrintable(printable, pageFormat); // Установка документа для печати
                    printerJob.print(); // Печать документа
                }
            }
        }

    }



    public static void printPDF(String fileName){
        String printerName = extractPrinterNameFromFileName(fileName);
        printPDF(fileName, printerName);
    }

    private static String extractPrinterNameFromFileName(String fileName) {
        return fileName.substring(fileName.indexOf("(") + 1, fileName.indexOf(")"));
    }

    private static void printPDF(String filePath, String printerName){
        try {


            PDDocument document = PDDocument.load(new File(filePath));
            PrinterJob printJob = PrinterJob.getPrinterJob();

            // Находим принтер по имени
            PrintService selectedPrintService = getPrinterByName(printerName);

            printJob.setPrintService(selectedPrintService);
            printJob.setPageable(new PDFPageable(document));
            printJob.print();


            document.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
