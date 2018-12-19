package com.jitin.createdocswithfreemarker.dto;

import com.jitin.createdocswithfreemarker.utility.DocumentType;

public class DocumentRequestDTO<T> {
	private DocumentType documentType;
	private String templateName;
	private String watermark;
	private T data;

	public DocumentRequestDTO() {
	}

	public DocumentRequestDTO(DocumentType documentType, String templateName, T data) {
		super();
		this.documentType = documentType;
		this.templateName = templateName;
		this.data = data;
	}
	public DocumentRequestDTO(DocumentType documentType, String templateName, String watermark, T data) {
		super();
		this.documentType = documentType;
		this.templateName = templateName;
		this.watermark = watermark;
		this.data = data;
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
