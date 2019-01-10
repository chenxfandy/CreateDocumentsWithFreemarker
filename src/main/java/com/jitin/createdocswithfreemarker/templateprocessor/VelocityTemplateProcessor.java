package com.jitin.createdocswithfreemarker.templateprocessor;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.jitin.createdocswithfreemarker.model.DocumentRequest;
import com.jitin.createdocswithfreemarker.utility.Constants;

public class VelocityTemplateProcessor implements TemplateProcessor {

	public String getProcessedText(DocumentRequest documentRequestDTO) {
		VelocityEngine ve = new VelocityEngine();
		ve.init();
		String velocityTemplate = Constants.VELOCITY_TEMPLATE_DIRECTORY + documentRequestDTO.getTemplateName();
		Template t = ve.getTemplate(velocityTemplate);
		VelocityContext context = new VelocityContext();
		context.put(Constants.GENERIC_DATA_MAP_KEY, documentRequestDTO.getData());
		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		return writer.toString();
	}
}
