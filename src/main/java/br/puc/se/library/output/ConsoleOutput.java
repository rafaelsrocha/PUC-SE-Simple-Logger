package br.puc.se.library.output;

import br.puc.se.library.LoggerSettings;

public class ConsoleOutput implements LogOutput {

	@Override
	public String log(String message) {
		return LoggerSettings.shared().getLogFormat().format(message, "CONSOLE", this.getClass().getSimpleName());
	}

}
