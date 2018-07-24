package br.puc.se.library.output;

import br.puc.se.library.LoggerSettings;

public class FileOuput implements LogOutput {

	@Override
	public String log(String message) {
		return LoggerSettings.shared().getLogFormat().format(message, LoggerSettings.shared().getFilePath(), this.getClass().getSimpleName());
	}

}
