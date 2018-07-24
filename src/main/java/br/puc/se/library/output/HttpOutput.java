package br.puc.se.library.output;

import br.puc.se.library.LoggerSettings;

public class HttpOutput implements LogOutput {

	@Override
	public String log(String message) {
		return LoggerSettings.shared().getLogFormat().format(message, LoggerSettings.shared().getHttpUri(), this.getClass().getSimpleName());
	}

}
