package com.jitin.createdocswithfreemarker.docfactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.jitin.createdocswithfreemarker.dto.DocumentRequestDTO;
import com.jitin.createdocswithfreemarker.exception.DocumentGeneratorException;
import com.jitin.createdocswithfreemarker.utility.FreemarkerTemplateProcessor;

public class GeneratePdfImpl implements GenerateDocument{

	public byte[] createDocument(DocumentRequestDTO documentRequestDTO) {
		String processTemplateText = FreemarkerTemplateProcessor.processFreemarkerTemplateFromFile(documentRequestDTO);
		System.out.println("Processed text : "+processTemplateText);
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(processTemplateText);
		renderer.getDocument();
		renderer.layout();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			renderer.createPDF(byteArrayOutputStream);
			if (null!=documentRequestDTO.getWatermark() && documentRequestDTO.getWatermark()!="") {
				return applyWatermark(byteArrayOutputStream.toByteArray(), documentRequestDTO.getWatermark());
			} else {
				return byteArrayOutputStream.toByteArray();
			}
		} catch (Exception e) {
			System.out.println("Error occurred : "+e);
			throw new DocumentGeneratorException("Error while creating Pdf!");
		}
	}

	private byte[] applyWatermark(byte[] source, String watermarkValue) throws IOException, DocumentException {
		PdfReader reader = new PdfReader(source);
		int totalPages = reader.getNumberOfPages();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		PdfStamper stamper = new PdfStamper(reader, byteArrayOutputStream);
		Font font = new Font(FontFamily.HELVETICA, 80);
		Phrase phrase = new Phrase(watermarkValue, font);
		for (int i = 1; i <= totalPages; i++) {
			PdfContentByte over = stamper.getOverContent(i);
			over.saveState();
			PdfGState pdfGStae = new PdfGState();
			pdfGStae.setFillOpacity(0.1F);
			over.setGState(pdfGStae);
			ColumnText.showTextAligned(over, Element.ALIGN_CENTER, phrase, 297, 450, 45);
			over.restoreState();
		}
		stamper.close();
		reader.close();
		return byteArrayOutputStream.toByteArray();
	}

}
