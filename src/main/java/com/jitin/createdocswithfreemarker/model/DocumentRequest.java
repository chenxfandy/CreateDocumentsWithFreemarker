package com.jitin.createdocswithfreemarker.model;

public class DocumentRequest<T> {
	private TemplateEngine templateEngine;
	private DocumentType documentType;
	private String templateDirectory;
	private String templateName;
	private String watermark;
	private String templateText;
	private T data;

	public DocumentRequest(TemplateEngine templateEngine, DocumentType documentType, String templateDirectory, String templateName) {
		this.templateEngine = templateEngine;
		this.documentType = documentType;
		this.templateDirectory=templateDirectory;
		this.templateName = templateName;
	}

	public DocumentRequest(TemplateEngine templateEngine, DocumentType documentType, String templateDirectory, String templateName,
			String watermark) {
		this.templateEngine = templateEngine;
		this.documentType = documentType;
		this.templateDirectory=templateDirectory;
		this.templateName = templateName;
		this.watermark = watermark;
	}
	public DocumentRequest(String templateText,TemplateEngine templateEngine, DocumentType documentType) {
		this.templateEngine = templateEngine;
		this.documentType = documentType;
		this.templateText = templateText;
	}
	public DocumentRequest(String templateText,TemplateEngine templateEngine, DocumentType documentType, String watermark) {
		this.templateEngine = templateEngine;
		this.documentType = documentType;
		this.watermark = watermark;
		this.templateText = templateText;
	}

	public TemplateEngine getTemplateEngine() {
		return templateEngine;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public String getTemplateDirectory() {
		return templateDirectory;
	}

	public String getTemplateName() {
		return templateName;
	}

	public String getWatermark() {
		return watermark;
	}

	public String getTemplateText() {
		return templateText;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
