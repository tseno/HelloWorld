package hello;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;


@Service
public class Hello {
	
	private static final Logger logger = LoggerFactory.getLogger(Hello.class);
	
	@Autowired
	AppProperties properties;
	
	public String sayHello(String name) {
		
		RetryTemplate retryTemplate = new RetryTemplate();
		 
		Map<Class<? extends Throwable>, Boolean> retryableExceptions = new HashMap<>();
		retryableExceptions.put(Exception.class, true);
		//retryableExceptions.put(UnretryableException.class, false);
		SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(5, retryableExceptions);
		retryTemplate.setRetryPolicy(retryPolicy);
		 
		ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
		exponentialBackOffPolicy.setInitialInterval(500);
		exponentialBackOffPolicy.setMultiplier(2);
		retryTemplate.setBackOffPolicy(exponentialBackOffPolicy);
		
		try {
			int resultSuccessAfterFailure = retryTemplate.execute(new RetryCallback<Integer, Exception>() {
			    
			 
			    @Override
			    public Integer doWithRetry(RetryContext context) throws Exception {
			    	logger.info("リトライ");
			        
			    	if (true) throw new Exception();
			 
			        return 42;
			    }
			});
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		String hello = properties.getValue() + ":" + name;
		logger.info(hello);
		return hello;

	}

}
