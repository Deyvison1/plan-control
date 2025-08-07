package com.plancontrol.api.file;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatDataUtils {

	public static String formatData(LocalDateTime data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		if (data != null) {
			return formatter.format(data);
		}
		return "";
	}

}
