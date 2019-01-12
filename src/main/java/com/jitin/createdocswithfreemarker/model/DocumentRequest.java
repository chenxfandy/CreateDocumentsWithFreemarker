package com.jitin.createdocswithfreemarker.model;

public class DocumentRequest {
	private DocumentType documentType;
	private TemplateEngine templateEngine;
	private String processedText;
	private String watermark;

	public DocumentRequest(DocumentType documentType, TemplateEngine templateEngine) {
		this.documentType = documentType;
		this.templateEngine = templateEngine;
	}

	public DocumentRequest(DocumentType documentType, String processedText) {
		this.documentType = documentType;
		this.processedText = processedText;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public TemplateEngine getTemplateEngine() {
		return templateEngine;
	}

	public String getProcessedText() {
		return processedText;
	}

	public void setProcessedText(String processedText) {
		this.processedText = processedText;
	}

	public String getWatermark() {
		return watermark;
	}

	public void setWatermark(String watermark) {
		this.watermark = watermark;
	}

}
