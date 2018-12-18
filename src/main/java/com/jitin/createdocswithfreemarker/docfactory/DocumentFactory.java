package com.jitin.createdocswithfreemarker.docfactory;

import com.jitin.createdocswithfreemarker.exception.DocumentGeneratorException;
import com.jitin.createdocswithfreemarker.utility.DocumentType;

public class DocumentFactory {
	public static GenerateDocument getInstance(DocumentType documentType) {
		switch (documentType) {
		case PDF:
			return new GeneratePdfImpl();
		case EXCEL:
			return new GenerateExcelImpl();
		case CSV:
			return new GenerateCsvImpl();
		default:
			throw new DocumentGeneratorException("Error while returning instance!");
		}
	}
}
