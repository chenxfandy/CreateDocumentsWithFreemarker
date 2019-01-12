package com.jitin.createdocswithfreemarker.producer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.commons.lang.StringUtils;

import com.jitin.createdocswithfreemarker.exception.DocumentGeneratorException;
import com.jitin.createdocswithfreemarker.factory.DocumentFactory;
import com.jitin.createdocswithfreemarker.factory.TemplateEngineFactory;
import com.jitin.createdocswithfreemarker.model.DocumentRequest;
import com.jitin.createdocswithfreemarker.templateprocessor.TemplateProcessor;
import com.jitin.createdocswithfreemarker.utility.Constants;
import com.jitin.createdocswithfreemarker.utility.FileNameGenerator;

public class DocumentGenerator {
	private DocumentGenerator() {

	}

	private static String getProcessedText(DocumentRequest documentRequest) {
		TemplateProcessor templateProcessor = TemplateEngineFactory.getInstance(documentRequest.getTemplateEngine());
		return templateProcessor.getProcessedText(documentRequest);
	}
	private static byte[] getDocument(DocumentRequest documentRequest) {
		DocumentProducer generateDocument = DocumentFactory.getInstance(documentRequest.getDocumentType());
		return generateDocument.generateDocumentFromProcessedText(getProcessedText(documentRequest), documentRequest.getWatermark());
	}

	public static byte[] generateDocument(DocumentRequest documentRequest) {
		return getDocument(documentRequest);
	}

	public static void generateDocument(String outputDirectory, String fileName, DocumentRequest documentRequest) {
		if(StringUtils.isBlank(fileName)) {
			fileName=FileNameGenerator.generateFileName(Constants.FILE_NAME_PREFIX,documentRequest.getDocumentType());
		}else {
			fileName=FileNameGenerator.generateFileName(fileName,documentRequest.getDocumentType());
		}
		if (StringUtils.isNotBlank(outputDirectory)) {
			byte[] document = getDocument(documentRequest);
			StringBuilder file = new StringBuilder(outputDirectory).append("/").append(fileName);
			try {
				Files.write(new File(file.toString()).toPath(), document);
			} catch (IOException e) {
				System.out.println("Error occurred : " + e);
			}
		}else {
			throw new DocumentGeneratorException("Output directory cannot be null or empty!");
		}
	}
}
