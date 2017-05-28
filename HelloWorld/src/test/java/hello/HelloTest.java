package hello;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloTest {
	
	@Autowired
	Hello hello;
	@Autowired
	AppProperties properties;
	
	@Test
	public void testSayHello() {
		assertThat(hello.sayHello("Senou") , is(properties.getValue() + ":Senou"));
	}

}
