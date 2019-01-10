package com.jitin.createdocswithfreemarker.docfactory;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jitin.createdocswithfreemarker.dto.DocumentRequestDTO;
import com.jitin.createdocswithfreemarker.exception.DocumentGeneratorException;
import com.jitin.createdocswithfreemarker.utility.Constants;
import com.jitin.createdocswithfreemarker.utility.FreemarkerTemplateProcessor;

public class GenerateCsvImpl implements GenerateDocument{
	private static final Logger LOG = LoggerFactory.getLogger(GenerateCsvImpl.class);
	public byte[] createDocument(DocumentRequestDTO documentRequestDTO) {
		String processedText = FreemarkerTemplateProcessor.processFreemarkerTemplateFromFile(documentRequestDTO);
		LOG.info("Processed text : {}",processedText);
		Document document = Jsoup.parse(processedText);
		Elements tables = document.getElementsByTag(Constants.HTML_TABLE);
		Writer writer = null;
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			writer = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream));
			for (Element table : tables) {
				Elements rows = table.select(Constants.HTML_TABLE_ROW);
				if (!rows.isEmpty()) {
					int i = 0;
					Elements tableHeders = rows.select(Constants.HTML_TABLE_HEADER);
					if (!tableHeders.isEmpty()) {
						writeData(writer, tableHeders);
						i = 1;
					}
					for (; i < rows.size(); i++) {
						Elements cells = rows.get(i).select(Constants.HTML_TABLE_DATA);
						writeData(writer, cells);
					}
				}
				writer.write("\n");
			}
			LOG.info("CSV file has been successfully written!");
		} catch (Exception e) {
			LOG.error("Error occurred : {}",e);
			throw new DocumentGeneratorException("Error while creating csv!");
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				LOG.error("Error occurred : {}",e);
			}
		}
		return byteArrayOutputStream.toByteArray();
	}
	private void writeData(Writer writer, Elements cells) {
		try {
			for (Element cell : cells) {
				writer.write(cell.text().concat(", "));
			}
			writer.write("\n");
		} catch (Exception e) {
			LOG.error("Error occurred : {}",e);
			throw new DocumentGeneratorException("Error while creating csv!");
		}
	}
}
