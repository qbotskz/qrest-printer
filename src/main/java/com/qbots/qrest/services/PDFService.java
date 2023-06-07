//package com.qbots.qrest.services;
//
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.PDPageContentStream;
//import org.apache.pdfbox.pdmodel.common.PDRectangle;
//import org.apache.pdfbox.pdmodel.font.PDType0Font;
//import org.apache.pdfbox.pdmodel.font.PDType1Font;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Date;
//
//public class PDFService {
//
//    public static PDDocument createPDF(String txt){
//        try {
//            // Создание нового документа
//            PDDocument document = new PDDocument();
//
//            PDRectangle pageSize = new PDRectangle(80, 160);
//
//            // Создание новой страницы
//            PDPage page = new PDPage(pageSize);
//            document.addPage(page);
//
//
//
//            // Получение объекта PDPageContentStream для рисования на странице
//            PDPageContentStream contentStream = new PDPageContentStream(document, page);
//
//            // Начало текстового блока
//            contentStream.beginText();
//
//            // Установка шрифта и размера шрифта
////            PDType0Font font = PDType0Font.load(document, Thread.currentThread().getContextClassLoader()
////                    .getResourceAsStream("src/main/resources/templates/fonts/times.ttf"));
//
//            PDType0Font font1 = PDType0Font.load(document, new File("src/main/resources/templates/fonts/helvetica_cyr_boldoblique.ttf"));
//
//
//            contentStream.setFont(font1, 12);
//
//            // Установка положения текста
//            contentStream.newLineAtOffset(25, 700);
//
//            // Вывод текста
//            contentStream.showText(txt);
//
//            // Завершение текстового блока
//            contentStream.endText();
//
//            // Закрытие PDPageContentStream
//            contentStream.close();
//
//            // Сохранение документа в файл
//            String fileName = new Date().getTime() +  ".pdf";
//            document.save(fileName);
//
//            // Закрытие документа
//            document.close();
//
//            System.out.println("PDF-документ успешно создан.");
//            return document;
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//}
