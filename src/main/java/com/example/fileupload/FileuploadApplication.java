package com.example.fileupload;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FileuploadApplication extends Exception{

	public static void main(String[] args) {
		try {
			SpringApplication.run(FileuploadApplication.class, args);
		}catch(Exception e)
		{System.out.println(e.getMessage());


	}


		}

}
