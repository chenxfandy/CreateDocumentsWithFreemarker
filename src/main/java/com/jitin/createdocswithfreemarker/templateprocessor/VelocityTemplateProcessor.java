package com.jitin.createdocswithfreemarker.templateprocessor;

import java.io.StringReader;
import java.io.StringWriter;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.ParseException;
import org.apache.velocity.runtime.parser.node.SimpleNode;

import com.jitin.createdocswithfreemarker.exception.DocumentGeneratorException;
import com.jitin.createdocswithfreemarker.model.DocumentRequest;
import com.jitin.createdocswithfreemarker.utility.Constants;

public class VelocityTemplateProcessor implements TemplateProcessor {

	public String getProcessedText(DocumentRequest documentRequest){
		Template template;
		if (StringUtils.isNotBlank(documentRequest.getTemplateText())) {
			template = this.processTemplateFromString(documentRequest.getTemplateText());
		} else if (StringUtils.isNotBlank(documentRequest.getTemplateDirectory())
				&& StringUtils.isNotBlank(documentRequest.getTemplateName())) {
			template = this.processTemplateFromFile(documentRequest.getTemplateDirectory(),
					documentRequest.getTemplateName());
		} else {
			throw new DocumentGeneratorException("Some required properties were null or empty!");
		}
		return this.processTemplate(template, documentRequest.getData());
	}

	private Template processTemplateFromFile(String templateDirectory, String templateName) {
		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.init();
		StringBuilder velocityTemplate = new StringBuilder(templateDirectory).append(templateName);
		return velocityEngine.getTemplate(velocityTemplate.toString());
	}

	private Template processTemplateFromString(String templateText) {
		RuntimeServices runtimeServices = RuntimeSingleton.getRuntimeServices();
		StringReader stringReader = new StringReader(templateText);
		SimpleNode simpleNode;
		Template template = new Template();
		try {
			simpleNode = runtimeServices.parse(stringReader, Constants.DEFAULT_TEMPLATE_NAME);
			template.setRuntimeServices(runtimeServices);
			template.setData(simpleNode);
			template.initDocument();
		} catch (ParseException e) {
			System.out.println("Error occurred : " + e);
		}
		return template;
	}

	private String processTemplate(Template template, Object data) {
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put(Constants.GENERIC_DATA_MAP_KEY, data);

		StringWriter stringWriter = new StringWriter();
		template.merge(velocityContext, stringWriter);
		return stringWriter.toString();
	}
}
