package ru.flashvagon.RESTtemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.flashvagon.RESTtemplate.communication.Communication;

@SpringBootApplication
public class Pp316RestTemplateApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Pp316RestTemplateApplication.class, args);

		Communication communication = context.getBean("communication", Communication.class);
		// System.out.println(communication.getAllUsers());
		System.out.println("Answer: " + communication.getAnswer());
	}

}
