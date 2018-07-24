package br.puc.se.library;

import java.util.HashMap;

import br.puc.se.library.log.LogFormat;
import br.puc.se.library.log.LogLevel;
import br.puc.se.library.log.LogType;
import br.puc.se.library.output.LogOutput;

public class LoggerSettings {
	
	private static LoggerSettings instance;
	private HashMap<String, Object> current = new HashMap<String, Object>();
	
	private LoggerSettings() {
		reset();
	}
	
	public static synchronized LoggerSettings shared() {
		if (instance == null) instance = new LoggerSettings(); 
		return instance;
	}
	
	public void setLogType(LogType logType) {
		current.put("logType", logType);
		current.put("logOutput", logType.getOutput());
	}

	public void setLogLevel(LogLevel logLevel) {
		current.put("logLevel", logLevel);
	}

	public void setLogFormat(LogFormat logFormat) {
		current.put("logFormat", logFormat);
	}

	public void setDatabaseName(String name) {
		current.put("databaseName", name);
	}

	public void setFilePath(String path) {
		current.put("filePath", path);
	}

	public void setHttpUri(String uri) {
		current.put("httpUri", uri);
	}

	public LogType getLogType() {
		return (LogType) current.get("logType");
	}

	public LogLevel getLogLevel() {
		return (LogLevel) current.get("logLevel");
	}

	public LogFormat getLogFormat() {
		return (LogFormat) current.get("logFormat");
	}
	
	public LogOutput getLogOutput() {
		return (LogOutput) current.get("logOutput");
	}

	public String getDatabaseName() {
		return (String) current.get("databaseName");
	}

	public String getFilePath() {
		return (String) current.get("filePath");
	}

	public String getHttpUri() {
		return (String) current.get("httpUri");
	}
	
	public void reset() {
		setLogType(LogType.CONSOLE);
		setLogLevel(LogLevel.DEBUG);
		setLogFormat(LogFormat.DEFAULT);
		setDatabaseName("mysql2");
		setFilePath("./output.log");
		setHttpUri("http://localhost:8080");
	}
}
