package com.jitin.createdocswithfreemarker.model;

public class DocumentRequest<T> {
	private TemplateEngine templateEngine;
	private DocumentType documentType;
	private String templateName;
	private String watermark;
	private T data;

	public DocumentRequest() {
	}

	public DocumentRequest(TemplateEngine templateEngine, DocumentType documentType, String templateName, T data) {
		super();
		this.templateEngine = templateEngine;
		this.documentType = documentType;
		this.templateName = templateName;
		this.data = data;
	}

	public DocumentRequest(TemplateEngine templateEngine, DocumentType documentType, String templateName,
			String watermark, T data) {
		super();
		this.templateEngine = templateEngine;
		this.documentType = documentType;
		this.templateName = templateName;
		this.watermark = watermark;
		this.data = data;
	}

	public TemplateEngine getTemplateEngine() {
		return templateEngine;
	}

	public void setTemplateEngine(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getWatermark() {
		return watermark;
	}

	public void setWatermark(String watermark) {
		this.watermark = watermark;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
