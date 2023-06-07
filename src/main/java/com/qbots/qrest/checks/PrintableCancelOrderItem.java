package com.qbots.qrest.checks;

import com.qbots.qrest.dto.OrderItemDTO;
import com.qbots.qrest.dto.OrderItemDeleteDTO;
import com.qbots.qrest.dto.PrintKitchenDTO;
import com.qbots.qrest.util.DateUtil;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.Date;

public class PrintableCancelOrderItem implements Printable {

    private String text;

    private OrderItemDeleteDTO orderItemDeleteDTO;

    public PrintableCancelOrderItem(OrderItemDeleteDTO orderItemDeleteDTO) {
        this.orderItemDeleteDTO = orderItemDeleteDTO;
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
            text = DateUtil.getDateInRussian(new Date()) + " Заказ:" + orderItemDeleteDTO.getOrderId()+ " " +
                    "Оф:" + orderItemDeleteDTO.getWaiterName() ;
            setFontSize(8, graphics, pageFormat, y, text);
            y += 18;
            //*********************************************************************************
            text = "Стол:" + orderItemDeleteDTO.getDeskNumber() + " Зал:" + orderItemDeleteDTO.getHallName();
            setFontSize(12, graphics, pageFormat, y, text);
            y += 25;



            text = "Внимание!";
            setFontSize(12, graphics, pageFormat, y, text);
            y += 13;

            text = "Блюда удалены!";
            setFontSize(12, graphics, pageFormat, y, text);
            y += 13;

            text = getFormatReason("(" + orderItemDeleteDTO.getReason() + ")");
            for (String str : text.split("\n")) {
                y -= 2;
                setFontSize(10, graphics, pageFormat, y, str);
                y += 9; // Смещение по высоте для следующей строки
            }

            y += 5; // Смещение по высоте для следующей строки


            text = "НЕ ГОТОВИТЬ!";
            setFontSize(12, graphics, pageFormat, y, text);
            y += 18;


            //set quantity
            text = String.valueOf(orderItemDeleteDTO.getOrderItemDTO().getQuantity());
            while (text.length() < 28){
                text = " " + text;
            }
            setFontSize(12, graphics, pageFormat, y, text);

            //setting Name
            int counter = 0;
            String inputText = "";
            for (int i = 0; i < orderItemDeleteDTO.getOrderItemDTO().getFood().getName().length(); i++, counter++){
                inputText += String.valueOf(orderItemDeleteDTO.getOrderItemDTO().getFood().getName().charAt(i));

                if (counter > 15){
                    counter = -1;
                    setFontSize(12, graphics, pageFormat, y, inputText);
                    y += 9;
                    inputText = "";
                }
            }
            if (counter > 0) {
                setFontSize(12, graphics, pageFormat, y, inputText);
            }

            //set comment
            if (orderItemDeleteDTO.getOrderItemDTO().getComment() != null) {
                text = getFormatComment("(" + orderItemDeleteDTO.getOrderItemDTO().getComment() + ")");
                for (String str : text.split("\n")) {
                    y -= 2;
                    setFontSize(10, graphics, pageFormat, y, str);
                    y += 8; // Смещение по высоте для следующей строки
                }
            }



            //*********************************************************************************

            ////////////////////////////////////////////////////////////////////////




        } catch (Exception e) {
            e.printStackTrace();
        }

        return PAGE_EXISTS;
    }

    private String getFormatReason(String s) {
        int counter = 0;
        String longName = "";
        for (int i = 0; i < s.length(); i++, counter++){
            longName += String.valueOf(s.charAt(i));
            if (counter > 31){
                longName += "\n";
                counter = -1;
            }
        }
        while (counter < 31){
            counter++;
            longName += " ";
        }

        return longName;
    }

    private Graphics setFontSize(int i, Graphics graphics, PageFormat pageFormat, int y, String text) {
        Font font = new Font("Courier New", Font.BOLD, i); // Шрифт печати
        graphics.setFont(font);
        graphics.drawString(text, (int) pageFormat.getImageableX(), y);
        return graphics;
    }

    private static String getFormatNameOld(String name) {
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
        return longName;

    }
    private static String getFormatComment(String comment) {
        int counter = 0;
        String longName = "";
        for (int i = 0; i < comment.length(); i++, counter++){
            longName += String.valueOf(comment.charAt(i));
            if (counter > 20){
                longName += "\n";
                counter = -1;
            }
        }
        while (counter < 20){
            counter++;
            longName += " ";
        }

        return longName;

    }


}