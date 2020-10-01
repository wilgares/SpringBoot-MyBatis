package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.controller.UserController;


@SpringBootApplication
@ComponentScan(basePackageClasses = UserController.class)
public class SampleMapperApplication implements CommandLineRunner {
    
    public static void main(String[] args) {
        SpringApplication.run(SampleMapperApplication.class, args);
        
    }

	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

    
}
