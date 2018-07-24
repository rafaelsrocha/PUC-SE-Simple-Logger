package br.puc.se.library;

import br.puc.se.library.log.LogFormat;
import br.puc.se.library.log.LogLevel;
import br.puc.se.library.log.LogType;
import br.puc.se.library.output.MainOutput;

public class Logger {
	private MainOutput mainOutput;
	
	public Logger build() {
		mainOutput = new MainOutput();
		return this;
	}
	
	public Logger logLevel(LogLevel logLevel) {
		LoggerSettings.shared().setLogLevel(logLevel);
		return this;
	}

	public Logger logType(LogType logType) {
		LoggerSettings.shared().setLogType(logType);
		return this;
	}
	
	public Logger logFormat(LogFormat logFormat) {
		LoggerSettings.shared().setLogFormat(logFormat);
		return this;
	}
	
	public Logger databaseName(String name) {
		LoggerSettings.shared().setDatabaseName(name);
		return this;
	}
	
	public Logger filePath(String path) {
		LoggerSettings.shared().setFilePath(path);
		return this;
	}
	
	public Logger httpURI(String uri) {
		LoggerSettings.shared().setHttpUri(uri);
		return this;
	}
	
	public String getDatabaseName() {
		return LoggerSettings.shared().getDatabaseName();
	}

	public String getFilePath() {
		return LoggerSettings.shared().getFilePath();
	}
	
	public String getHttpUri() {
		return LoggerSettings.shared().getHttpUri();
	}

	public LogType getLogType() {
		return LoggerSettings.shared().getLogType();
	}

	public LogLevel getLogLevel() {
		return LoggerSettings.shared().getLogLevel();
	}
	
	public LogFormat getLogFormat() {
		return LoggerSettings.shared().getLogFormat();
	}
	
	public MainOutput getMainOutput() {
		return mainOutput;
	}
	
	public void log(LogLevel messageLevel, String message) {
		mainOutput.log(messageLevel, message);
	}

}
