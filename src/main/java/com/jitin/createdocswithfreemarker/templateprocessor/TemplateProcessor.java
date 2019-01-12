package com.jitin.createdocswithfreemarker.templateprocessor;

import com.jitin.createdocswithfreemarker.model.TemplateEngine;

public interface TemplateProcessor {
	public String getProcessedText(TemplateEngine templateEngine);
}
