package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Hello {
	
	private static final Logger logger = LoggerFactory.getLogger(Hello.class);
	
	@Autowired
	AppProperties properties;
	
	public String sayHello(String name) {
		String hello = properties.getValue() + ":" + name;
		logger.info(hello);
		return hello;

	}

}
