package com.example.fileupload;

import com.example.fileupload.service.SchedulerService;
import com.example.fileupload.service.TestRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
public class FileuploadApplication extends Exception{

	public static void main(String[] args) {
		try {
			ApplicationContext context  =SpringApplication.run(FileuploadApplication.class, args);
			TestRepo tr=context.getBean(TestRepo.class);

//			ExecutorService executor = Executors.newFixedThreadPool(5);
//			executor.submit(() -> {
//				try {
//					tr.startTxnQueuelistner();
//				} catch (JsonProcessingException e) {
//					throw new RuntimeException(e);
//				} catch (InterruptedException e) {
//					throw new RuntimeException(e);
//				}
//			});



			SchedulerService schedulerService = context.getBean(SchedulerService.class);
			schedulerService.StartSchedulerService();
//			schedulerService.schedule();

//			MessageSender messageSender = context.getBean(MessageSender.class);
//			//messageSender.sendMessage();
//			MessageReciever messageReciever=context.getBean(MessageReciever.class);
//			String s=messageReciever.recieveMessage();




//
//			System.out.println(s);



			System.out.println("**********HEHHEHEHEHEHEHEHEH*********");
		}catch(Exception e)
		{System.out.println(e.getMessage());




	}


		}

}
