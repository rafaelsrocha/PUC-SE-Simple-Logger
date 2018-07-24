package br.puc.se.library.log;

import br.puc.se.library.output.ConsoleOutput;
import br.puc.se.library.output.DatabaseOutput;
import br.puc.se.library.output.FileOuput;
import br.puc.se.library.output.HttpOutput;
import br.puc.se.library.output.LogOutput;

public enum LogType {

	DATABASE(3, new DatabaseOutput()),
	HTTP(2, new HttpOutput()),
	FILE(1, new FileOuput()),
	CONSOLE(0, new ConsoleOutput());

	private int type;
	
	private LogOutput output;

	private LogType(int type, LogOutput output) {
		this.type = type;
		this.output = output;
	}
	
	public int getPriority() {
		return type;
	}
	
	public LogOutput getOutput() {
		return output;
	}
	
}