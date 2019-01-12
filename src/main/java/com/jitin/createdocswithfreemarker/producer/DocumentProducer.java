package com.jitin.createdocswithfreemarker.producer;

import com.jitin.createdocswithfreemarker.model.DocumentRequest;

public interface DocumentProducer {
	public byte[] generateDocumentFromProcessedText(DocumentRequest documentRequest);
}
