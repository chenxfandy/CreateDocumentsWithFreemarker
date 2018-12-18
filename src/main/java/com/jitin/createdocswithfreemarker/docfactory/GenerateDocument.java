package com.jitin.createdocswithfreemarker.docfactory;

import com.jitin.createdocswithfreemarker.dto.DocumentRequestDTO;

public interface GenerateDocument {
	public byte[] createDocument(DocumentRequestDTO documentRequestDTO);
}
