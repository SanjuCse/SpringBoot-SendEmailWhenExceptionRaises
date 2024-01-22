package com.pot.aspect;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.pot.mail.EmailUtil;

import freemarker.template.TemplateException;

@Aspect
@Component
public class ExceptionAspect {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmailUtil emailUtil;

	@Value("#{'${email.ids}'.split(',')}")
	private List<String> mailIds;

	private String serviceName = "Digital Template";

	private String deployedServerName = "Prod";

	private String applicationStatus = "Down";

	private String methodName = "divide operation Method";

	private List<String> methodParams = List.of("param1", "param2");

	@Async
	@AfterThrowing(pointcut = "execution(* com.pot..*.*(..))", throwing = "ex")
	public void handleException(JoinPoint joinPoint, Throwable ex) throws IOException, TemplateException {
		// Log exception details with additional context
		log.error("Exception in {}.{}() with cause = '{}'", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), ex.getMessage());

		// Send detailed email with exception details
		emailUtil.sendEmail("Exception in Spring Boot App", mailIds, buildExceptionDetails(joinPoint, ex));
		log.info("Mail Sent");
	}

	private Map<String, Object> buildExceptionDetails(JoinPoint joinPoint, Throwable ex) {
		// Build a detailed message with method signature, arguments, and stack trace
		// Customize this based on your requirements
		// Example: "Exception in com.example.ServiceClass.methodName() with cause =
		// 'Invalid input'"
		// Include additional information if needed
		// ...
		Map<String, Object> exMap = new HashMap<>();
		exMap.put("serviceName", serviceName);
		exMap.put("deployedServerName", deployedServerName);
		exMap.put("applicationStatus", applicationStatus);
		exMap.put("methodName", methodName);
		exMap.put("methodParams", methodParams.toString());
		exMap.put("exceptionMsg", ex.getMessage());
		exMap.put("exceptionStackTrace", ex.getStackTrace().toString());

		return exMap;
	}
}
