package com.jitin.createdocswithfreemarker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.jitin.createdocswithfreemarker.model.DocumentRequest;
import com.jitin.createdocswithfreemarker.model.DocumentType;
import com.jitin.createdocswithfreemarker.model.Engine;
import com.jitin.createdocswithfreemarker.model.TemplateEngine;
import com.jitin.createdocswithfreemarker.producer.DocumentGenerator;
import com.jitin.createdocswithfreemarker.utility.FileNameGenerator;

public class EntryClass {

	public static void main(String[] args) {

		Address addressDTO = new Address(23, "Test street", "Test city", "Test state");
		Student studentDTO = new Student("Test College of Technology", 12, "Test Student", 89.7, Boolean.TRUE,
				addressDTO);

		// ---TESTING FREEMARKER TEMPLATE---

		// --Write file you are passing template as a file.
		TemplateEngine freemarkerTemplateEngine1 = new TemplateEngine(Engine.FREEMARKER,
				Constants.FREEMARKER_TEMPLATE_DIRECTORY, "StudentReport.ftl", "data");
		freemarkerTemplateEngine1.setData(studentDTO);
		DocumentRequest documentRequest1 = new DocumentRequest(DocumentType.PDF, freemarkerTemplateEngine1);
		// --Call this method when you want to write file directly.
		DocumentGenerator.generateDocument(Constants.FILE_STORAGE_DIRECTORY, "StudentResult_1", documentRequest1);

		// --When you want to write file from byte[]
		byte[] fileContent1 = DocumentGenerator.generateDocument(documentRequest1);
		String filename1 = FileNameGenerator.generateFileName("StudentResult_2", documentRequest1.getDocumentType());
		try {
			Files.write(new File(Constants.FILE_STORAGE_DIRECTORY + filename1).toPath(), fileContent1);
		} catch (IOException e) {
			System.out.println("Error occurred : " + e);
		}

		// --Write file when you are passing template as a string.
		String text1 = "";
		try {
			text1 = new String(Files.readAllBytes(
					Paths.get(Constants.FREEMARKER_TEMPLATE_DIRECTORY + "StudentReportTemplateText.txt")));
		} catch (IOException e) {
			System.out.println(e);
		}
		TemplateEngine freemarkerTemplateEngine2 = new TemplateEngine(Engine.FREEMARKER, text1, "data");
		freemarkerTemplateEngine2.setData(studentDTO);
		DocumentRequest documentRequestDTO2 = new DocumentRequest(DocumentType.PDF, freemarkerTemplateEngine2);
		documentRequestDTO2.setWatermark("TEST COLLEGE");
		// --Call this method when you want to write file directly.
		DocumentGenerator.generateDocument(Constants.FILE_STORAGE_DIRECTORY, "StudentResult_3", documentRequestDTO2);
		// --When you want to write file from byte[]
		byte[] fileContent2 = DocumentGenerator.generateDocument(documentRequestDTO2);
		String filename2 = FileNameGenerator.generateFileName("StudentResult_4", documentRequestDTO2.getDocumentType());
		try {
			Files.write(new File(Constants.FILE_STORAGE_DIRECTORY + filename2).toPath(), fileContent2);
		} catch (IOException e) {
			System.out.println("Error occurred : " + e);
		}

		// ---TESTING VELOCITY TEMPLATE---

		// --Write file you are passing template as a file.
		TemplateEngine velocityTemplateEngine1 = new TemplateEngine(Engine.VELOCITY,
				Constants.VELOCITY_TEMPLATE_DIRECTORY, "StudentReport.vm", "data");
		velocityTemplateEngine1.setData(studentDTO);
		DocumentRequest documentRequest3 = new DocumentRequest(DocumentType.PDF, velocityTemplateEngine1);
		// --Call this method when you want to write file directly.
		DocumentGenerator.generateDocument(Constants.FILE_STORAGE_DIRECTORY, "StudentResult_5", documentRequest3);
		// --When you want to write file from byte[]
		byte[] fileContent3 = DocumentGenerator.generateDocument(documentRequest3);
		String filename3 = FileNameGenerator.generateFileName("StudentResult_6", documentRequest3.getDocumentType());
		try {
			Files.write(new File(Constants.FILE_STORAGE_DIRECTORY + filename3).toPath(), fileContent3);
		} catch (IOException e) {
			System.out.println("Error occurred : " + e);
		}

		// --Write file when you are passing template as a string.
		String text2 = "";
		try {
			text2 = new String(Files
					.readAllBytes(Paths.get(Constants.VELOCITY_TEMPLATE_DIRECTORY + "StudentReportTemplateText.txt")));
		} catch (IOException e) {
			System.out.println(e);
		}
		TemplateEngine velocityTemplateEngine2 = new TemplateEngine(Engine.VELOCITY, text2, "data");
		velocityTemplateEngine2.setData(studentDTO);
		DocumentRequest documentRequestDTO4 = new DocumentRequest(DocumentType.PDF, velocityTemplateEngine2);
		documentRequestDTO4.setWatermark("TEST COLLEGE");
		// --Call this method when you want to write file directly.
		DocumentGenerator.generateDocument(Constants.FILE_STORAGE_DIRECTORY, "StudentResult_7", documentRequestDTO4);
		// --When you want to write file from byte[]
		byte[] fileContent4 = DocumentGenerator.generateDocument(documentRequestDTO4);
		String filename4 = FileNameGenerator.generateFileName("StudentResult_8", documentRequestDTO4.getDocumentType());
		try {
			Files.write(new File(Constants.FILE_STORAGE_DIRECTORY + filename4).toPath(), fileContent4);
		} catch (IOException e) {
			System.out.println("Error occurred : " + e);
		}
		System.out.println("File(s) created!");
	}

}
