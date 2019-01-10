package com.jitin.createdocswithfreemarker.factory;

import com.jitin.createdocswithfreemarker.exception.DocumentGeneratorException;
import com.jitin.createdocswithfreemarker.model.DocumentType;
import com.jitin.createdocswithfreemarker.producer.CsvProducer;
import com.jitin.createdocswithfreemarker.producer.DocumentProducer;
import com.jitin.createdocswithfreemarker.producer.ExcelProducer;
import com.jitin.createdocswithfreemarker.producer.PdfProducer;

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
