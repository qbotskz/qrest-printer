//package com.qbots.qrest.client;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.converter.StringMessageConverter;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.client.standard.StandardWebSocketClient;
//import org.springframework.web.socket.messaging.WebSocketStompClient;
//
//import javax.annotation.PostConstruct;
//
//@Component
//public class MyWebSocketClient {
//
//    private WebSocketStompClient stompClient;
//
////    @Autowired
////    private SimpMessagingTemplate messagingTemplate;
//
//    @PostConstruct
//    public void init() {
//        stompClient = new WebSocketStompClient(new StandardWebSocketClient());
//        stompClient.setMessageConverter(new StringMessageConverter());
//        stompClient.connect("ws://localhost:8080/my-websocket-endpoint", new StompSessionHandlerAdapter() {});
//    }
//
////    public void sendMessage(String message) {
////        messagingTemplate.convertAndSend("/topic/my-topic", message);
////    }
//}