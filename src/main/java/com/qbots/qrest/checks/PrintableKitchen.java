package com.qbots.qrest.checks;

import com.qbots.qrest.dto.OrderItemDTO;
import com.qbots.qrest.dto.PrintKitchenDTO;
import com.qbots.qrest.util.DateUtil;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class PrintableKitchen implements Printable {

    private String text;

    private PrintKitchenDTO printKitchenDTO;

    public PrintableKitchen(PrintKitchenDTO printKitchenDTO) {
        this.printKitchenDTO = printKitchenDTO;
    }



    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex != 0) {
            return NO_SUCH_PAGE;
        }

        try {
            int y = (int) pageFormat.getImageableY() + 12; // Начальная позиция печати

            text = "***********QREST**********" ;
            setFontSize(12, graphics, pageFormat, y, text);
            y += 18;
            // Печать каждой строки текста
            ////////////////////////////////////////////////////////////////////////
            text = DateUtil.getDateInRussian(new Date()) + " Заказ:" + printKitchenDTO.getOrderId()+ " " +
                    "Оф:" + printKitchenDTO.getWaiterName() ;
            setFontSize(8, graphics, pageFormat, y, text);
            y += 18;
            //*********************************************************************************
            text = "Стол:" + printKitchenDTO.getDeskNumber() + " Зал:" + printKitchenDTO.getHallName();
            setFontSize(12, graphics, pageFormat, y, text);
            y += 25;


            for (OrderItemDTO itemDTO : printKitchenDTO.getItems()){
                text = getFormatNameOld(itemDTO.getFood().getName(), itemDTO.getQuantity());

                for (String str : text.split("\n")){
                    setFontSize(12, graphics, pageFormat, y, str);
                    y += 9; // Смещение по высоте для следующей строки
                }

                if (itemDTO.getComment() != null) {
                    text = getFormatComment("(" + itemDTO.getComment() + ")");
                    for (String str : text.split("\n")) {
                        y -= 2;
                        setFontSize(10, graphics, pageFormat, y, str);
                        y += 8; // Смещение по высоте для следующей строки
                    }
                }

//                graphics.drawString(text, (int) pageFormat.getImageableX(), y);
                y += 9; // Смещение по высоте для следующей строки
            }
            ////////////////////////////////////////////////////////////////////////




        } catch (Exception e) {
            e.printStackTrace();
        }

        return PAGE_EXISTS;
    }

    private Graphics setFontSize(int i, Graphics graphics, PageFormat pageFormat, int y, String text) {
        Font font = new Font("Courier New", Font.BOLD, i); // Шрифт печати
        graphics.setFont(font);
        graphics.drawString(text, (int) pageFormat.getImageableX(), y);
        return graphics;
    }

    private static String getFormatNameOld(String name, long quantity) {
        int counter = 0;
        String longName = "";
        for (int i = 0; i < name.length(); i++, counter++){
            longName += String.valueOf(name.charAt(i));
            if (counter > 20){
                longName += "\n";
                counter = -1;
            }
        }
        while (counter < 26){
            counter++;
            longName += " ";
        }

        longName += quantity;
        return longName;

    }
    private static String getFormatComment(String comment) {
        int counter = 0;
        String longName = "";
        for (int i = 0; i < comment.length(); i++, counter++){
            longName += String.valueOf(comment.charAt(i));
            if (counter > 30){
                longName += "\n";
                counter = -1;
            }
        }
        while (counter < 30){
            counter++;
            longName += " ";
        }

        return longName;

    }


}