package com.qbots.qrest;

import com.qbots.qrest.client.*;
import com.qbots.qrest.timerTasks.WebsocketConnectionTask;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Timer;

@SpringBootApplication
public class QrestApplication {

	String apiToken  = "deV@dev1d1f2gw3e8t!$667Bauka";

//	@Autowired
//	static WebsocketConnectionTask websocketConnectionTask;

	public static void main(String[] args) throws  URISyntaxException {
		SpringApplication.run(QrestApplication.class, args);

		initTimer();
		
	}

	private static void initTimer() {
		WebsocketConnectionTask websocketConnectionTask = new WebsocketConnectionTask();
		Timer timer = new Timer(true);
		int time = 10*60*1000;
		timer.scheduleAtFixedRate(websocketConnectionTask, time , time);
	}



}
