package com.jitin.createdocswithfreemarker.utility;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jitin.createdocswithfreemarker.docfactory.GenerateCsvImpl;
import com.jitin.createdocswithfreemarker.dto.DocumentRequestDTO;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerTemplateProcessor {
	private static final Logger LOG = LoggerFactory.getLogger(FreemarkerTemplateProcessor.class);
	public static String processFreemarkerTemplateFromFile(DocumentRequestDTO documentRequestDTO) {
		String processedText = "";
		try {
			Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);
			configuration.setDirectoryForTemplateLoading(new File(Constants.TEMPLATE_DIRECTORY));
			processedText = processFreemarkerTemplate(configuration,documentRequestDTO);
		} catch (IOException e) {
			LOG.error("Error occurred : {}",e);
		}
		return processedText;
	}

	public static String processFreemarkerTemplateFromString(DocumentRequestDTO documentRequestDTO) {
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);
		StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
		stringTemplateLoader.putTemplate(documentRequestDTO.getTemplateName(), "templateContent"); //--Pass template content as String.
		configuration.setTemplateLoader(stringTemplateLoader);
		return processFreemarkerTemplate(configuration,documentRequestDTO);
	}

	private static String processFreemarkerTemplate(Configuration configuration,DocumentRequestDTO documentRequestDTO) {
		String processedText = "";
		Map<String, Object> root = new HashMap<String,Object>();
		root.put(Constants.GENERIC_DATA_MAP_KEY, documentRequestDTO.getData());
		configuration.setDefaultEncoding(Constants.DEFAULT_ENCODING);
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		configuration.setLogTemplateExceptions(false);
		try {
			Template template = configuration.getTemplate(documentRequestDTO.getTemplateName());
			Writer writer = new StringWriter();
			try {
				template.process(root, writer);
				processedText = processTemplateIntoString(template, root);
			} catch (TemplateException e) {
				LOG.error("Error occurred : {}",e);
			}
		} catch (IOException e) {
			LOG.error("Error occurred : {}",e);
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
