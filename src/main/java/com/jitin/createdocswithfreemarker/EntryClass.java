package com.jitin.createdocswithfreemarker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.jitin.createdocswithfreemarker.docfactory.DocumentFactory;
import com.jitin.createdocswithfreemarker.docfactory.GenerateDocument;
import com.jitin.createdocswithfreemarker.dto.AddressDTO;
import com.jitin.createdocswithfreemarker.dto.DocumentRequestDTO;
import com.jitin.createdocswithfreemarker.dto.StudentDTO;
import com.jitin.createdocswithfreemarker.utility.Constants;
import com.jitin.createdocswithfreemarker.utility.DocumentType;
import com.jitin.createdocswithfreemarker.utility.FileNameGenerator;

public class EntryClass {

	public static void main(String[] args) {
		AddressDTO addressDTO = new AddressDTO(23, "Test street", "Test city", "Test state");
		StudentDTO studentDTO = new StudentDTO("Test College of Technology", 12, "Test Student", 89.7, Boolean.TRUE,
				addressDTO);
		DocumentRequestDTO documentRequestDTO = new DocumentRequestDTO(DocumentType.PDF, "studentReport.ftl",
				"TEST COLLEGE", studentDTO);
		GenerateDocument generateDocument = DocumentFactory.getInstance(documentRequestDTO.getDocumentType());
		byte[] fileContent = generateDocument.createDocument(documentRequestDTO);
		String filename = FileNameGenerator.generateFileName(documentRequestDTO.getDocumentType());
		try {
			Files.write(new File(Constants.FILE_STORAGE_DIRECTORY + filename).toPath(), fileContent);
		} catch (IOException e) {
			System.out.println("Error occurred : " + e);
		}

		System.out.println(filename + " has been successfully created!");
	}

}
