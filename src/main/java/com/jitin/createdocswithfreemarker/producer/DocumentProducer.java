package com.jitin.createdocswithfreemarker.producer;

public interface DocumentProducer {
	public byte[] generateDocumentFromProcessedText(String processedText, String watermark);
	//public byte[] generateDocumentFromProcessedText(String processedText);
}
