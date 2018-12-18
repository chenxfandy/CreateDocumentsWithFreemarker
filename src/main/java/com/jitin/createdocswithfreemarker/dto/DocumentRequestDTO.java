package com.jitin.createdocswithfreemarker.dto;

import com.jitin.createdocswithfreemarker.utility.DocumentType;

public class DocumentRequestDTO<T> {
	private String documentName;
	private DocumentType documentType;
	private String templateName;
	private String watermark;
	private T data;

	public DocumentRequestDTO() {
	}

	public DocumentRequestDTO(String documentName, DocumentType documentType, String templateName, T data) {
		super();
		this.documentName = documentName;
		this.documentType = documentType;
		this.templateName = templateName;
		this.data = data;
	}
	public DocumentRequestDTO(String documentName, DocumentType documentType, String templateName, String watermark, T data) {
		super();
		this.documentName = documentName;
		this.documentType = documentType;
		this.templateName = templateName;
		this.watermark = watermark;
		this.data = data;
	}
	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
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
