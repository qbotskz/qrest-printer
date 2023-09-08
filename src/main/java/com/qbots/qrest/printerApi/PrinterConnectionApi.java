package com.qbots.qrest.printerApi;


import com.qbots.qrest.client.Endpoints;
import com.qbots.qrest.dto.OrderItemDeleteDTO;
import com.qbots.qrest.dto.PrintKitchenDTO;
import com.qbots.qrest.dto.PrintPaymentDTO;
import com.qbots.qrest.dto.PrintPrecheckDTO;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

//@Service
public class PrinterConnectionApi {

    static final String savePath = "C:/qrestoran/files/";


    private final RestTemplate restTemplate;

    public PrinterConnectionApi() {

        Duration duration = Duration.ofMillis(3*1000);

        this.restTemplate = new RestTemplateBuilder().setConnectTimeout(duration).setReadTimeout(duration)
                .build();


    }
    public List<PrintKitchenDTO> getAllPrintKitchens2() {
        ResponseEntity<List<PrintKitchenDTO>> response = restTemplate.exchange(
                Endpoints.getAllPrintKitchenEndpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PrintKitchenDTO>>() {}
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            // Обработка ошибок при неуспешном запросе
            throw new RuntimeException("Error occurred during HTTP request. Status code: " + response.getStatusCode());
        }
    }



    public  boolean updatePrintKitchen(PrintKitchenDTO kitchenDTO) {
        try {


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));


            org.springframework.http.HttpEntity<PrintKitchenDTO> entity = new org.springframework.http.HttpEntity<>(kitchenDTO, headers);

            restTemplate.postForObject(Endpoints.updateKitchenPrint, entity, Map.class);
           return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public List<OrderItemDeleteDTO> getAllOrderItemDeletes() {
        ResponseEntity<List<OrderItemDeleteDTO>> response = restTemplate.exchange(
                Endpoints.getAllCancelPrint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<OrderItemDeleteDTO>>() {}
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            // Обработка ошибок при неуспешном запросе
            throw new RuntimeException("Error occurred during HTTP request. Status code: " + response.getStatusCode());
        }
    }

    public boolean updateDeleteOrderItem(OrderItemDeleteDTO orderItemDeleteDTO) {
        try {


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));


            HttpEntity<OrderItemDeleteDTO> entity = new HttpEntity<>(orderItemDeleteDTO, headers);

            restTemplate.postForObject(Endpoints.updateCancelOrderItem, entity, Map.class);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public List<PrintPrecheckDTO> getAllPrintPrecheck() {
        ResponseEntity<List<PrintPrecheckDTO>> response = restTemplate.exchange(
                Endpoints.getAllPrecheck,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PrintPrecheckDTO>>() {}
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            // Обработка ошибок при неуспешном запросе
            throw new RuntimeException("Error occurred during HTTP request. Status code: " + response.getStatusCode());
        }
    }
    public List<PrintPaymentDTO> getAllPayments() {
        ResponseEntity<List<PrintPaymentDTO>> response = restTemplate.exchange(
                Endpoints.getPayments,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PrintPaymentDTO>>() {}
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            // Обработка ошибок при неуспешном запросе
            throw new RuntimeException("Error occurred during HTTP request. Status code: " + response.getStatusCode());
        }
    }
    public boolean updatePayment(PrintPaymentDTO printPaymentDTO) {
        try {


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));


            HttpEntity<PrintPaymentDTO> entity = new HttpEntity<>(printPaymentDTO, headers);

            restTemplate.postForObject(Endpoints.updatePayment, entity, Map.class);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    public boolean updatePrintPrecheck(PrintPrecheckDTO printPrecheckDTO) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));


            HttpEntity<PrintPrecheckDTO> entity = new HttpEntity<>(printPrecheckDTO, headers);

            restTemplate.postForObject(Endpoints.updatePrintPrecheck, entity, Map.class);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePrintedFile(String fileName) {
        try {
            fileName = fileName.substring(fileName.lastIndexOf('/') + 1);

            MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();

            // Добавляем параметры запроса
            requestParams.add("fileName", fileName);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            // Создаем объект HttpEntity с параметрами запроса и заголовками
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestParams, headers);

            // Отправляем POST-запрос с параметрами запроса и получаем ответ
            ResponseEntity<String> responseEntity = restTemplate.exchange(Endpoints.updateNotPrintedFile, HttpMethod.POST, requestEntity, String.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                String responseBody = responseEntity.getBody();
                System.out.println("Ответ сервера: " + responseBody);
                return true;
            } else {
                System.err.println("Ошибка при выполнении запроса. Код ответа: " + responseEntity.getStatusCodeValue());
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static String getNotPrintedFile() {
        try {


            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<byte[]> responseEntity = restTemplate.exchange(Endpoints.getNotPrintedFile, HttpMethod.GET, requestEntity, byte[].class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                byte[] fileBytes = responseEntity.getBody();

                String contentDispositionHeader = responseEntity.getHeaders().getFirst(HttpHeaders.CONTENT_DISPOSITION);
                String filename = extractFilenameFromContentDisposition(contentDispositionHeader);

                FileOutputStream fileOutputStream = new FileOutputStream(savePath + filename);
                StreamUtils.copy(fileBytes, fileOutputStream);
                fileOutputStream.close();
                return savePath + filename;
            }

//            else {
//                throw new IOException("Не удалось скачать файл. Код ответа: " + responseEntity.getStatusCodeValue());
//            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static String extractFilenameFromContentDisposition(String contentDisposition) {
        if (contentDisposition != null && contentDisposition.contains("filename=")) {
            int startIndex = contentDisposition.indexOf("filename=") + 9;
            int endIndex = contentDisposition.length();
            return contentDisposition.substring(startIndex, endIndex);
        }
        return null;
    }

}
