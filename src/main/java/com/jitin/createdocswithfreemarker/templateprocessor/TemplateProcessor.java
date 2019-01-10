package com.jitin.createdocswithfreemarker.templateprocessor;

import com.jitin.createdocswithfreemarker.model.DocumentRequest;

public interface TemplateProcessor {
	public String getProcessedText(DocumentRequest documentRequestDTO);
}
