package br.puc.se.library.output;

import br.puc.se.library.LoggerSettings;

public class DatabaseOutput implements LogOutput {

	@Override
	public String log(String message) {
		return LoggerSettings.shared().getLogFormat().format(message, LoggerSettings.shared().getDatabaseName(), this.getClass().getSimpleName());
	}

}
