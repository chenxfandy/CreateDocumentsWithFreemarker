package com.jitin.createdocswithfreemarker.model;

public class TemplateEngine<T> {
	private Engine engine;
	private String templateDirectory;
	private String templateName;
	private String templateText;
	private String context;
	private T data;

	public TemplateEngine(Engine engine, String templateDirectory, String templateName, String context) {
		this.engine = engine;
		this.templateDirectory = templateDirectory;
		this.templateName = templateName;
		this.context = context;
	}

	public TemplateEngine(Engine engine, String templateText, String context) {
		this.engine = engine;
		this.templateText = templateText;
		this.context = context;
	}

	public Engine getEngine() {
		return engine;
	}

	public String getTemplateDirectory() {
		return templateDirectory;
	}

	public String getTemplateName() {
		return templateName;
	}

	public String getTemplateText() {
		return templateText;
	}

	public String getContext() {
		return context;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
