package com.qbots.qrest.checks;

import com.qbots.qrest.dto.*;
import com.qbots.qrest.util.DateUtil;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;

public class PrintablePayment implements Printable {

    private String text;

    private int checkWidth = 30;

    private PrintPaymentDTO printPaymentDTO;

    public PrintablePayment(PrintPaymentDTO printPaymentDTO) {
        this.printPaymentDTO = printPaymentDTO;
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
            text = "**********************************************";
            setFontSize(8, graphics, pageFormat, y, text);
            y += 12;
            text = "Dermene";
            setFontSize(8, graphics, pageFormat, y, text);
            y += 12;
            text = "*********************************************";
            setFontSize(8, graphics, pageFormat, y, text);
            y += 14;

            text = "    Квитанция об оплате заказа    ";
            setFontSize(10, graphics, pageFormat, y, text, Font.CENTER_BASELINE);
            y += 16;

            text = "Зал: " +printPaymentDTO.getHallName() + " Стол: " + printPaymentDTO.getDeskNumber();
            setFontSize(9, graphics, pageFormat, y, text);
            y += 16;

            text = "Открыт:" + printPaymentDTO.getFoodOrderDTO().getCreatedDateInDate() + " Заказ №: " +printPaymentDTO.getFoodOrderDTO().getId();
            setFontSize(9, graphics, pageFormat, y, text);
            y += 16;

            // Печать каждой строки текста
            text = "Официант: " + printPaymentDTO.getWaiterName();
            setFontSize(8, graphics, pageFormat, y, text);
            y += 15;

            text = "-----------------------------------------";
            setFontSize(8, graphics, pageFormat, y, text);
            y += 15;

            text = "Наименование                 Кол-во Сумма";
            setFontSize(8, graphics, pageFormat, y, text);
            y += 15;

            text = "-----------------------------------------";
            setFontSize(8, graphics, pageFormat, y, text);
            y += 15;

            int maxWidth = 0;
            int maxWidth2 = 0;
            int guestNumber = 1;
            for (GuestDTO guestDTO : printPaymentDTO.getFoodOrderDTO().getGuests()){
                if (guestDTO.getOrderItems().size() ==0){
                    continue;
                }

                text = "Гость:"+ guestNumber++ ;
                setFontSize(8, graphics, pageFormat, y, text);
                y += 18;

                //setting price
                /////////////////////////////////////////////////////////////////////
                int temp = y;
                for (OrderItemDTO orderItemDTO : guestDTO.getOrderItems()){
                    int counter = 0;
                    int setPriceIdx = temp;
                    for (int i = 0; i < orderItemDTO.getFood().getName().length(); i++, counter++){
                        if (counter > 28){
                            counter = -1;
                            temp += 9;

                        }
                    }

                    text = String.valueOf(orderItemDTO.getPrice());
                    if (text.length() > maxWidth){
                        maxWidth = text.length();
                    }
                    while (text.length() < 41){
                        text = " " + text;
                    }
                    setFontSize(8, graphics, pageFormat, setPriceIdx, text);
                    temp += 17;
                }
                /////////////////////////////////////////////////////////////////////

                //setting quantity
                /////////////////////////////////////////////////////////////////////
                temp = y;
                for (OrderItemDTO orderItemDTO : guestDTO.getOrderItems()){
                    int counter = 0;
                    int setPriceIdx = temp;
                    for (int i = 0; i < orderItemDTO.getFood().getName().length(); i++, counter++){
                        if (counter > 28){
                            counter = -1;
                            temp += 9;

                        }
                    }

                    text = String.valueOf(orderItemDTO.getQuantity());

                    while (text.length() < 40 - maxWidth - 2){
                        text = " " + text;
                    }

                    int length = String.valueOf(orderItemDTO.getQuantity()).length();

                    if (length> maxWidth2){
                        maxWidth2 = length;
                    }

                    setFontSize(8, graphics, pageFormat, setPriceIdx, text);
                    temp += 17;
                }
                /////////////////////////////////////////////////////////////////////

                //setting food name
                for (OrderItemDTO orderItemDTO : guestDTO.getOrderItems()){
                    int counter = 0;
                    String inputText = "";
                    for (int i = 0; i < orderItemDTO.getFood().getName().length(); i++, counter++){
                        inputText += String.valueOf(orderItemDTO.getFood().getName().charAt(i));

                        if (counter > 34 - (maxWidth + maxWidth2) - 7){
                            counter = -1;
                            setFontSize(10, graphics, pageFormat, y, inputText);
                            y += 9;
                            inputText = "";
                        }
                    }
                    if (counter > 0) {
                        setFontSize(10, graphics, pageFormat, y, inputText);
                    }
                    y += 17;

                }


                text = "-----------------------------------------";
                setFontSize(8, graphics, pageFormat, y, text);
                y += 15;

            }


            text = "ИТОГО К ОПЛАТЕ:";
            setFontSize(8, graphics, pageFormat, y, text);

            text = new DecimalFormat( "###,###.##" ).format(printPaymentDTO.getFoodOrderDTO().getCheque().getForPayment());
            while (text.length() < 30){
                text = " " + text;
            }
            setFontSize(11, graphics, pageFormat, y, text);
            y += 15;

            text = "-----------------------------------------";
            setFontSize(8, graphics, pageFormat, y, text);
            y += 15;

            //setting prices
            int temp = y;
            maxWidth = 0;
            maxWidth2 = 0;
            for (PaymentDTO paymentDTO : printPaymentDTO.getFoodOrderDTO().getCheque().getPayments()) {

                int counter = 0;
                int setPriceIdx = temp;
                for (int i = 0; i < paymentDTO.getPaymentType().getName().length(); i++, counter++) {
                    if (counter > 28) {
                        counter = -1;
                        temp += 9;
                    }
                }
                text = String.valueOf(paymentDTO.getAmount());
                if (text.length() > maxWidth) {
                    maxWidth = text.length();
                }
                while (text.length() < 41) {
                    text = " " + text;
                }
                setFontSize(8, graphics, pageFormat, setPriceIdx, text);
                temp += 15;
            }

            //setting payment name
            for (PaymentDTO paymentDTO : printPaymentDTO.getFoodOrderDTO().getCheque().getPayments()) {
                int counter = 0;
                String inputText = "";
                for (int i = 0; i < paymentDTO.getPaymentType().getName().length(); i++, counter++){
                    inputText += String.valueOf(paymentDTO.getPaymentType().getName().charAt(i));
                    if (counter > 40 - (maxWidth + maxWidth2) - 7){
                        counter = -1;
                        setFontSize(8, graphics, pageFormat, y, inputText);
                        y += 9;
                        inputText = "";
                    }
                }
                if (counter > 0) {
                    setFontSize(8, graphics, pageFormat, y, inputText);
                }
                y += 15;
            }



            if (printPaymentDTO.getFoodOrderDTO().getCheque().getChange() > 0) {
                text = "Сдача:";
                setFontSize(8, graphics, pageFormat, y, text);

                text = new DecimalFormat("###,###.##").format(printPaymentDTO.getFoodOrderDTO().getCheque().getChange());
                while (text.length() < 41) {
                    text = " " + text;
                }
                setFontSize(8, graphics, pageFormat, y, text);
                y += 15;
            }


            text = "         Спасибо!Ждем Вас снова!         ";
            setFontSize(8, graphics, pageFormat, y, text);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return PAGE_EXISTS;
    }

    private String getFormatName(String name, int quantity, int price) {
        int counter = 0;
        String longName = "";
        for (int i = 0; i < name.length(); i++, counter++){
            longName += String.valueOf(name.charAt(i));
            if (counter > 28){
                longName += "\n";
                counter = -1;
            }
        }
        while (counter < 33){
            counter++;
            longName += " ";
        }

        longName += quantity + "   ";
        longName += price;
        return longName;
    }

    private Graphics setFontSize(int i, Graphics graphics, PageFormat pageFormat, int y, String text) {
        Font font = new Font("Courier New", Font.BOLD, i); // Шрифт печати
        graphics.setFont(font);
        graphics.drawString(text, (int) pageFormat.getImageableX(), y);
        return graphics;
    }

    private Graphics setFontSize(int i, Graphics graphics, PageFormat pageFormat, int y, String text,int  fontType) {
        Font font = new Font("Courier New", fontType, i); // Шрифт печати
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


}