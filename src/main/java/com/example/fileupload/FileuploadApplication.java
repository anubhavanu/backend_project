package com.example.fileupload;

import com.example.fileupload.Component.MessageReciever;
import com.example.fileupload.Component.MessageSender;
import com.example.fileupload.service.SchedulerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class FileuploadApplication extends Exception{

	public static void main(String[] args) {
		try {
			ApplicationContext context  =SpringApplication.run(FileuploadApplication.class, args);
			SchedulerService schedulerService = context.getBean(SchedulerService.class);
			schedulerService.StartSchedulerService();
//			schedulerService.schedule();

			MessageSender messageSender = context.getBean(MessageSender.class);
			messageSender.sendMessage();
			MessageReciever messageReciever=context.getBean(MessageReciever.class);
			String s=messageReciever.recieveMessage();
			System.out.println(s);
			System.out.println("**********HEHHEHEHEHEHEHEHEH*********");
		}catch(Exception e)
		{System.out.println(e.getMessage());



	}


		}

}
