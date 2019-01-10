package com.jitin.createdocswithfreemarker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.jitin.createdocswithfreemarker.docfactory.DocumentGenerator;
import com.jitin.createdocswithfreemarker.model.Address;
import com.jitin.createdocswithfreemarker.model.DocumentRequest;
import com.jitin.createdocswithfreemarker.model.DocumentType;
import com.jitin.createdocswithfreemarker.model.Student;
import com.jitin.createdocswithfreemarker.model.TemplateEngine;
import com.jitin.createdocswithfreemarker.utility.Constants;
import com.jitin.createdocswithfreemarker.utility.FileNameGenerator;

public class EntryClass {

	public static void main(String[] args) {
		Address addressDTO = new Address(23, "Test street", "Test city", "Test state");
		Student studentDTO = new Student("Test College of Technology", 12, "Test Student", 89.7, Boolean.TRUE,
				addressDTO);
		DocumentRequest documentRequestDTO = new DocumentRequest(TemplateEngine.VELOCITY, DocumentType.CSV,
				"studentReport.vm",studentDTO);
		byte[] fileContent = DocumentGenerator.generateDocument(documentRequestDTO);
		String filename = FileNameGenerator.generateFileName(documentRequestDTO.getDocumentType());
		try {
			Files.write(new File(Constants.FILE_STORAGE_DIRECTORY + filename).toPath(), fileContent);
		} catch (IOException e) {
			System.out.println("Error occurred : " + e);
		}

		System.out.println(filename + " has been successfully created!");
	}

}
