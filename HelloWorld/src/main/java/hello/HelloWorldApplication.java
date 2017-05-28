package hello;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HelloWorldApplication {

	@Autowired
	Hello hello;
	
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(HelloWorldApplication.class, args);
		HelloWorldApplication application = applicationContext.getBean(HelloWorldApplication.class);
		application.hello();
	}

	public void hello() {
		hello.sayHello("Makoto");
	}
	
}
	
