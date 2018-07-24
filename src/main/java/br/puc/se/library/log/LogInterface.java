package br.puc.se.library.log;

public interface LogInterface {
	
	void log(LogLevel messageLevel, String message);
	
	void addListener(LogListener listener);
	
}
