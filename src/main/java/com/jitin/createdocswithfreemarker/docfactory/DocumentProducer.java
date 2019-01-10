package com.jitin.createdocswithfreemarker.docfactory;

import com.jitin.createdocswithfreemarker.model.DocumentRequest;

public interface DocumentProducer {
	public byte[] generateDocumentFromProcessedText(String processedText,String watermark);
}
