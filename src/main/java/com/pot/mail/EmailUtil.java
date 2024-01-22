package com.pot.mail;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

@Component
public class EmailUtil {
	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmail(String subject, List<String> mailIds, Map<String, Object> templateData)
			throws IOException, TemplateException {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject(subject);

		// Set recipient email address
		for (String mailId : mailIds) {
			message.setTo(mailId);
		}

		message.setText(generateEmailBody(templateData));

		javaMailSender.send(message);
	}

	private String generateEmailBody(Map<String, Object> templateData) throws IOException, TemplateException {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
		cfg.setClassForTemplateLoading(this.getClass(), "/");
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

		Template template = cfg.getTemplate("ExceptionMail.ftl");

		StringWriter out = new StringWriter();
		template.process(templateData, out);

		return out.toString();
	}
}
