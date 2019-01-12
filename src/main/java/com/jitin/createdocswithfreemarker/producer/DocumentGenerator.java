package com.jitin.createdocswithfreemarker.producer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.jitin.createdocswithfreemarker.exception.DocumentGeneratorException;
import com.jitin.createdocswithfreemarker.factory.DocumentFactory;
import com.jitin.createdocswithfreemarker.factory.TemplateEngineFactory;
import com.jitin.createdocswithfreemarker.model.DocumentRequest;
import com.jitin.createdocswithfreemarker.templateprocessor.TemplateProcessor;
import com.jitin.createdocswithfreemarker.utility.DocumentGeneratorConstants;
import com.jitin.createdocswithfreemarker.utility.FileNameGenerator;

public class DocumentGenerator {
	private DocumentGenerator() {

	}

	private static byte[] getDocument(DocumentRequest documentRequest) {
		DocumentProducer generateDocument = DocumentFactory.getInstance(documentRequest.getDocumentType());
		return generateDocument.generateDocumentFromProcessedText(documentRequest);
	}

	public static byte[] generateDocument(DocumentRequest documentRequest) {
		if (null != documentRequest.getTemplateEngine()) {
			TemplateProcessor templateProcessor = TemplateEngineFactory
					.getInstance(documentRequest.getTemplateEngine().getEngine());
			String processedText = templateProcessor.getProcessedText(documentRequest.getTemplateEngine());
			documentRequest.setProcessedText(processedText);
			return getDocument(documentRequest);
		} else if (null != documentRequest.getProcessedText() && documentRequest.getProcessedText() != "") {
			return getDocument(documentRequest);
		} else {
			throw new DocumentGeneratorException("Some required properties were missing!");
		}
	}

	public static void generateDocument(String outputDirectory, String fileName, DocumentRequest documentRequest) {
		if (null != fileName && fileName != "") {
			fileName = FileNameGenerator.generateFileName(fileName, documentRequest.getDocumentType());
		} else {
			fileName = FileNameGenerator.generateFileName(DocumentGeneratorConstants.FILE_NAME_PREFIX,
					documentRequest.getDocumentType());
		}
		if (null != outputDirectory && outputDirectory != "") {
			byte[] document = generateDocument(documentRequest);
			StringBuilder file = new StringBuilder(outputDirectory).append("/").append(fileName);
			try {
				Files.write(new File(file.toString()).toPath(), document);
			} catch (IOException e) {
				System.out.println("Error occurred : " + e);
			}
		} else {
			throw new DocumentGeneratorException("Output directory cannot be null or empty!");
		}
	}
}
