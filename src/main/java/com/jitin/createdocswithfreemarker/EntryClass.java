package com.jitin.createdocswithfreemarker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.jitin.createdocswithfreemarker.model.Address;
import com.jitin.createdocswithfreemarker.model.DocumentRequest;
import com.jitin.createdocswithfreemarker.model.DocumentType;
import com.jitin.createdocswithfreemarker.model.Student;
import com.jitin.createdocswithfreemarker.model.TemplateEngine;
import com.jitin.createdocswithfreemarker.producer.DocumentGenerator;
import com.jitin.createdocswithfreemarker.utility.Constants;
import com.jitin.createdocswithfreemarker.utility.FileNameGenerator;

public class EntryClass {
public void foo() {
	System.out.println("i am foo");
}
	public static void main(String[] args) {
		
		Address addressDTO = new Address(23, "Test street", "Test city", "Test state");
		Student studentDTO = new Student("Test College of Technology", 12, "Test Student", 89.7, Boolean.TRUE,
				addressDTO);
		/*DocumentRequest documentRequestDTO = new DocumentRequest(TemplateEngine.VELOCITY, DocumentType.PDF,Constants.VELOCITY_TEMPLATE_DIRECTORY,
				"studentReport.vm");
		documentRequestDTO.setData(studentDTO);
		byte[] fileContent = DocumentGenerator.generateDocument(documentRequestDTO);
		String filename = FileNameGenerator.generateFileName(documentRequestDTO.getDocumentType());
		try {
			Files.write(new File(Constants.FILE_STORAGE_DIRECTORY + filename).toPath(), fileContent);
		} catch (IOException e) {
			System.out.println("Error occurred : " + e);
		}*/	
		
		DocumentRequest documentRequestDTO = new DocumentRequest(TemplateEngine.FREEMARKER, DocumentType.PDF,Constants.FREEMARKER_TEMPLATE_DIRECTORY,
				"studentReport.ftl");
		documentRequestDTO.setData(studentDTO);
		DocumentGenerator.generateDocument(Constants.FILE_STORAGE_DIRECTORY, "StudentResult", documentRequestDTO);
		
		/*String data = ""; 
	    try {
			data = new String(Files.readAllBytes(Paths.get(Constants.FREEMARKER_TEMPLATE_DIRECTORY+ "StudentReportTemplateText.txt")));
		} catch (IOException e) {
			System.out.println(e);
		} 
	    DocumentRequest documentRequestDTO = new DocumentRequest(data,TemplateEngine.FREEMARKER, DocumentType.PDF);
		documentRequestDTO.setData(studentDTO);
		DocumentGenerator.generateDocument(Constants.FILE_STORAGE_DIRECTORY, "StudentResult", documentRequestDTO);*/
		System.out.println("File has been successfully created!");
		
		/*String methodName = new Object() {}
	      .getClass()
	      .getEnclosingMethod()
	      .getName();
	      System.out.println(methodName);
	      
	      StackTraceElement[] stackTrace = new Throwable().getStackTrace();
	      System.out.println(stackTrace[0].getMethodName());
	      
	      StackTraceElement[] stackTrace1 = Thread.currentThread()
	    	      .getStackTrace();
	      System.out.println(stackTrace1[1].getMethodName());*/
		
	}

}
