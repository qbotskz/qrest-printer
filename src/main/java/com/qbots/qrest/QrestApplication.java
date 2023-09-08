package com.qbots.qrest;

import com.qbots.qrest.client.*;
import com.qbots.qrest.services.PrinterService;
import com.qbots.qrest.timerTasks.CheckFilesTask;
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



	public static void main(String[] args)   {
		SpringApplication.run(QrestApplication.class, args);

//		PrinterService.printPDF();

		initTimer();

		
	}

	private static void initTimer() {
		WebsocketConnectionTask websocketConnectionTask = new WebsocketConnectionTask();
		Timer timer = new Timer(true);
		int time = 1000;
		timer.scheduleAtFixedRate(websocketConnectionTask, time , time);


		CheckFilesTask checkFilesTask = new CheckFilesTask();
		Timer timer1 = new Timer(true);
		int time1 = 1000;
		timer1.scheduleAtFixedRate(checkFilesTask, time1 , time1);



	}



}
