package com.jitin.createdocswithfreemarker.factory;

import com.jitin.createdocswithfreemarker.docfactory.CsvProducer;
import com.jitin.createdocswithfreemarker.docfactory.DocumentProducer;
import com.jitin.createdocswithfreemarker.docfactory.ExcelProducer;
import com.jitin.createdocswithfreemarker.docfactory.PdfProducer;
import com.jitin.createdocswithfreemarker.exception.DocumentGeneratorException;
import com.jitin.createdocswithfreemarker.model.DocumentType;

public class DocumentFactory {
	public static DocumentProducer getInstance(DocumentType documentType) {
		switch (documentType) {
		case PDF:
			return new PdfProducer();
		case EXCEL:
			return new ExcelProducer();
		case CSV:
			return new CsvProducer();
		default:
			throw new DocumentGeneratorException("Error while returning instance!");
		}
	}
}
