package com.jitin.createdocswithfreemarker.templateprocessor;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.jitin.createdocswithfreemarker.exception.DocumentGeneratorException;
import com.jitin.createdocswithfreemarker.model.DocumentRequest;
import com.jitin.createdocswithfreemarker.utility.Constants;

import freemarker.cache.StringTemplateLoader;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

public class FreemarkerTemplateProcessor implements TemplateProcessor {

	public String getProcessedText(DocumentRequest documentRequest) {
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);
		configuration.setDefaultEncoding(Constants.DEFAULT_ENCODING);
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		configuration.setLogTemplateExceptions(false);
		String templateName = Constants.DEFAULT_TEMPLATE_NAME;
		if (StringUtils.isNotBlank(documentRequest.getTemplateText())) {
			this.processTemplateFromString(configuration, templateName, documentRequest.getTemplateText());
		} else if (StringUtils.isNotBlank(documentRequest.getTemplateDirectory())
				&& StringUtils.isNotBlank(documentRequest.getTemplateName())) {
			templateName = documentRequest.getTemplateName();
			this.processTemplateFromFile(configuration, documentRequest.getTemplateDirectory(), templateName);
		} else {
			throw new DocumentGeneratorException("Some required properties were null or empty!");
		}
		String processedText = "";
		Template template;
		try {
			template = configuration.getTemplate(templateName);
			processedText = processTemplate(template, documentRequest.getData());
		} catch (TemplateNotFoundException e) {
			System.out.println("Error occurred : " + e);
		} catch (MalformedTemplateNameException e) {
			System.out.println("Error occurred : " + e);
		} catch (ParseException e) {
			System.out.println("Error occurred : " + e);
		} catch (IOException e) {
			System.out.println("Error occurred : " + e);
		}
		return processedText;
	}

	private void processTemplateFromString(Configuration configuration, String templateName, String templateText) {
		StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
		stringTemplateLoader.putTemplate(templateName, templateText);
		configuration.setTemplateLoader(stringTemplateLoader);
	}

	private void processTemplateFromFile(Configuration configuration, String templateDirectory, String templateName) {
		try {
			configuration.setDirectoryForTemplateLoading(new File(templateDirectory + "/"));
		} catch (IOException e) {
			System.out.println("Error occurred : " + e);
		}
	}

	private String processTemplate(Template template, Object data) {
		String processedText = "";
		try {
			Map<String, Object> root = new HashMap<String, Object>();
			root.put(Constants.GENERIC_DATA_MAP_KEY, data);
			Writer writer = new StringWriter();
			try {
				template.process(root, writer);
				processedText = processTemplateIntoString(template, root);
			} catch (TemplateException e) {
				System.out.println("Error occurred : " + e);
			}
		} catch (IOException e) {
			System.out.println("Error occurred : " + e);
		}
		return processedText;
	}

	private static String processTemplateIntoString(Template template, Object model)
			throws IOException, TemplateException {
		StringWriter result = new StringWriter();
		template.process(model, result);
		return result.toString();
	}
}
