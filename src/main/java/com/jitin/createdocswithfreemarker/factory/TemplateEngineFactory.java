package com.jitin.createdocswithfreemarker.factory;

import com.jitin.createdocswithfreemarker.exception.DocumentGeneratorException;
import com.jitin.createdocswithfreemarker.model.TemplateEngine;
import com.jitin.createdocswithfreemarker.templateprocessor.FreemarkerTemplateProcessor;
import com.jitin.createdocswithfreemarker.templateprocessor.TemplateProcessor;
import com.jitin.createdocswithfreemarker.templateprocessor.VelocityTemplateProcessor;

public class TemplateEngineFactory {
	public static TemplateProcessor getInstance(TemplateEngine templateEngine) {
		switch (templateEngine) {
		case FREEMARKER:
			return new FreemarkerTemplateProcessor();
		case VELOCITY:
			return new VelocityTemplateProcessor();
		default:
			throw new DocumentGeneratorException("Error while returning instance!");
		}
	}
}
