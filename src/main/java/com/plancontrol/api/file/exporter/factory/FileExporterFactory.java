package com.plancontrol.api.file.exporter.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.plancontrol.api.exception.UnsupportedFileException;
import com.plancontrol.api.file.exporter.MediaTypes;
import com.plancontrol.api.file.exporter.contract.FileExporter;
import com.plancontrol.api.file.exporter.impl.CsvExporter;
import com.plancontrol.api.file.exporter.impl.XlsxExporter;

@Component
public class FileExporterFactory {

	private Logger log = LoggerFactory.getLogger(FileExporterFactory.class);
	private final static String messageErro = "Invalid file format";
	
	@Autowired
	private ApplicationContext context;
	
	public FileExporter getImporter(String acceptHeader) throws Exception {
		if(acceptHeader.equalsIgnoreCase(MediaTypes.APPLICATION_XLSX_VALUE)) {
			return context.getBean(XlsxExporter.class);
		} else if(acceptHeader.equalsIgnoreCase(MediaTypes.APPLICATION_CSV_VALUE)) {
			return context.getBean(CsvExporter.class);
		} else {
			throw new UnsupportedFileException(messageErro);
		}
	}
}
