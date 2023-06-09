package com.qbots.qrest.client;

import com.qbots.qrest.PrinterService;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.*;
import java.awt.print.PrinterException;
import java.net.URI;
import java.nio.ByteBuffer;

/**
 * ChatServer Client
 *
 * @author Jiji_Sasidharan
 */
@ClientEndpoint
@Slf4j
public class CancelOrderItemClientEndpoint {

    Session userSession = null;
    private MessageHandler messageHandler;
    private URI uri;

    private String apiToken = "testToken";

    public static final int MAX_TEXT_MESSAGE_SIZE = 2048000; // 2 Megabytes.
    public static final int BUFFER_SIZE = MAX_TEXT_MESSAGE_SIZE * 5;

    WebSocketContainer container;

    public CancelOrderItemClientEndpoint(URI endpointURI) {
        this.uri = endpointURI;
        container = ContainerProvider.getWebSocketContainer();
        container.setDefaultMaxBinaryMessageBufferSize(BUFFER_SIZE);
        container.setDefaultMaxTextMessageBufferSize(BUFFER_SIZE);
        connect();
    }

    public void connect()   {
        log.info("Connecting to websocket ->" + uri);
        while (true){
            try {
//                if (userSession != null && userSession.isOpen()){
//                    return;
//                }
//                if (isCloseSession()){
                    container.connectToServer(this, uri);
                    sendMessage(apiToken);
//                }
                return;
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        }



    }

    private boolean isCloseSession() {
        try {
            sendMessage("test");
            return false;
        }catch (Exception e){
            return true;
        }
    }

    /**
     * Callback hook for Connection open events.
     *
     * @param userSession the userSession which is opened.
     */
    @OnOpen
    public void onOpen(Session userSession) {
        System.out.println("opening websocket");
        this.userSession = null;
        this.userSession = userSession;
    }

    /**
     * Callback hook for Connection close events.
     *
     * @param userSession the userSession which is getting closed.
     * @param reason the reason for connection close
     */
    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        System.out.println("closing websocket " + reason.getReasonPhrase());
        this.userSession = null;
        connect();
    }

    /**
     * Callback hook for Message Events. This method will be invoked when a client send a message.
     *
     * @param message The text message
     */
    @OnMessage
    public void onMessage(String message) {
        System.out.println(message);
        try {
            PrinterService.printCancelOrderItem(message);
            sendMessage(message);
        } catch (PrinterException e) {
            e.printStackTrace();
        }
//        sendMessage(message);
    //        if (this.messageHandler != null) {
    //            this.messageHandler.handleMessage(message);
    //        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("Произошла ошибка в WebSocket: " + throwable.getMessage());
        connect();
    }

    @OnMessage
    public void onMessage(ByteBuffer bytes) {
        System.out.println("Handle byte buffer");
    }

    /**
     * register message handler
     *
     * @param msgHandler
     */
    public void addMessageHandler(MessageHandler msgHandler) {
        this.messageHandler = msgHandler;
    }

    /**
     * Send a message.
     *
     * @param message
     */
    public void sendMessage(String message) {
        this.userSession.getAsyncRemote().sendText(message);
    }


    public static interface MessageHandler {

        public void handleMessage(String message);
    }
}