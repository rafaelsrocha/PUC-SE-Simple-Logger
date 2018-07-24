package br.puc.se.library.log;

import java.util.Date;
import java.util.Optional;

import br.puc.se.library.LoggerSettings;

public enum LogFormat {

	CLEAN ("TIMESTAMP - LEVEL - MESSAGE"),
	DEFAULT ("LEVEL : TIMESTAMP : DESTINATION : MESSAGE"),
	PRETTY_PRINTED ("[LEVEL] : TIMESTAMP : LOGGER_NAME : DESTINATION : MESSAGE");
	
	private String format;

	private LogFormat(String format) {
		this.format = format;
	}
	
	public String getFormat() {
		return format;
	}
	
	public String format(String message, String destination, String loggerName) {
		String formatted = getFormat();
		
		LoggerSettings settings = LoggerSettings.shared();
		LogLevel level = settings.getLogLevel();

		formatted = formatted.replace("LEVEL", String.format("%s", level))
				.replace("TIMESTAMP", String.format("%s", new Date()))
				.replace("LOGGER_NAME", Optional.ofNullable(loggerName).orElse(""))
				.replace("DESTINATION", Optional.ofNullable(destination).orElse(""))
				.replace("MESSAGE", message); 
		
		return formatted;
	}
}