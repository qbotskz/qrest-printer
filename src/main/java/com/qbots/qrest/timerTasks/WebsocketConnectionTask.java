package com.qbots.qrest.timerTasks;

import com.qbots.qrest.client.*;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.TimerTask;

@Service
public class WebsocketConnectionTask extends TimerTask {

    static CancelOrderItemClientEndpoint cancelOrderItemClientEndpoint;
    static PaymentClientEndpoint paymentClientEndpoint;
    static PrecheckClientEndpoint precheckClientEndpoint;
    static WebsocketClientEndpoint clientEndPoint;



    static {
        try {
            System.out.println("Debaaaggggg!!!!!");
            cancelOrderItemClientEndpoint = new CancelOrderItemClientEndpoint(new URI(Endpoints.cancelOrderItemPrint));
            paymentClientEndpoint = new PaymentClientEndpoint(new URI(Endpoints.paymentApi));
            precheckClientEndpoint = new PrecheckClientEndpoint(new URI(Endpoints.precheckApi));
            clientEndPoint = new WebsocketClientEndpoint(new URI(Endpoints.kitchenApi));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            System.gc();
            method2();
            method3();
            method4();
            method5();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }


    private static void method5() throws URISyntaxException {
        cancelOrderItemClientEndpoint.connect();
//        clientEndPoint = new CancelOrderItemClientEndpoint(new URI(Endpoints.cancelOrderItemPrint));
//        clientEndPoint.addMessageHandler(new CancelOrderItemClientEndpoint.MessageHandler() {
//            public void handleMessage(String message) {
//                System.out.println(message);
//            }
//        });

    }

    private static void method4() throws URISyntaxException {
//        final PaymentClientEndpoint clientEndPoint = new PaymentClientEndpoint(new URI(Endpoints.paymentApi));
//        clientEndPoint.addMessageHandler(new PaymentClientEndpoint.MessageHandler() {
//            public void handleMessage(String message) {
//                System.out.println(message);
//            }
//        });

        cancelOrderItemClientEndpoint.connect();

    }

    private static void method3() throws URISyntaxException {
//        final PrecheckClientEndpoint clientEndPoint = new PrecheckClientEndpoint(new URI(Endpoints.precheckApi));
//        clientEndPoint.addMessageHandler(new PrecheckClientEndpoint.MessageHandler() {
//            public void handleMessage(String message) {
//                System.out.println(message);
//            }
//        });
        precheckClientEndpoint.connect();

    }

    private static void method2() throws URISyntaxException {
//        final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI(Endpoints.kitchenApi));
//
//        // add listener
//        clientEndPoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
//            public void handleMessage(String message) {
//                System.out.println(message);
//            }
//        });
        clientEndPoint.connect();

    }
}
