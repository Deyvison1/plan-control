package com.plancontrol.api.file.imported.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.plancontrol.api.exception.UnsupportedFileException;
import com.plancontrol.api.file.imported.contract.FileImported;
import com.plancontrol.api.file.imported.impl.CsvImported;
import com.plancontrol.api.file.imported.impl.XlsxImporter;


@Component
public class FileImportedFactory {
	
	private Logger log = LoggerFactory.getLogger(FileImportedFactory.class);
	private final static String messageErro = "Invalid file format";
	
	@Autowired
	private ApplicationContext context;
	
	public FileImported getImporter(String fileName) throws Exception {
		if(fileName.endsWith(".xlsx")) {
			return context.getBean(XlsxImporter.class);
		} else if(fileName.endsWith(".csv")) {
			return context.getBean(CsvImported.class);
		} else {
			throw new UnsupportedFileException(messageErro);
		}
	}
}
