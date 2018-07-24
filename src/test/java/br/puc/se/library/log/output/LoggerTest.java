package br.puc.se.library.log.output;

import org.junit.Test;

import br.puc.se.library.Logger;
import br.puc.se.library.LoggerSettings;
import br.puc.se.library.log.LogFormat;
import br.puc.se.library.log.LogLevel;
import br.puc.se.library.log.LogListener;
import br.puc.se.library.log.LogType;
import br.puc.se.library.output.ConsoleOutput;
import br.puc.se.library.output.DatabaseOutput;
import br.puc.se.library.output.FileOuput;
import br.puc.se.library.output.HttpOutput;
import br.puc.se.library.output.MainOutput;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

public class LoggerTest {

	@Test
	public void defaultValuesTest() {
		LoggerSettings.shared().reset();
		
		Logger logger = new Logger().build();
		assertEquals(LogType.CONSOLE, logger.getLogType());
		assertEquals(logger.getLogLevel(), LogLevel.DEBUG);
		assertEquals(logger.getLogFormat(), LogFormat.DEFAULT);
	}
	
	@Test
	public void builderTest() {
		Logger logger = new Logger()
				.logType(LogType.DATABASE)
				.logLevel(LogLevel.ERROR)
				.logFormat(LogFormat.PRETTY_PRINTED)
				.build();
		assertEquals(logger.getLogType(), LogType.DATABASE);
		assertEquals(logger.getLogLevel(), LogLevel.ERROR);
		assertEquals(logger.getLogFormat(), LogFormat.PRETTY_PRINTED);
	}
	
	@Test
	public void fileTest() {
		LogListener listener = new LogListener();
		
		Logger logger = new Logger()
				.logType(LogType.FILE)
				.logLevel(LogLevel.FATAL)
				.logFormat(LogFormat.CLEAN)
				.build();
		
		logger.getMainOutput().addListener(listener);

		logger.log(LogLevel.INFO, "should not log");
		logger.log(LogLevel.WARNING, "should not log");
		logger.log(LogLevel.ERROR, "should not log");
		logger.log(LogLevel.FATAL, "should log");

		assertEquals(1, listener.getLogs().size());
	}
	
	@Test
	public void consoleTest() {
		LogListener listener = new LogListener();
		
		Logger logger = new Logger()
				.logType(LogType.FILE)
				.logLevel(LogLevel.ERROR)
				.logFormat(LogFormat.CLEAN)
				.build();

		logger.getMainOutput().addListener(listener);

		logger.log(LogLevel.INFO, "should not log");
		logger.log(LogLevel.WARNING, "should not log");
		logger.log(LogLevel.ERROR, "should log");
		logger.log(LogLevel.FATAL, "should log");

		assertEquals(2, listener.getLogs().size());
	}

	@Test
	public void httpTest() {
		LoggerSettings.shared().reset();
		
		LogListener listener = new LogListener();
		
		Logger logger = new Logger()
				.logType(LogType.FILE)
				.logFormat(LogFormat.CLEAN)
				.build();
		
		logger.getMainOutput().addListener(listener);

		logger.log(LogLevel.INFO, "should log");
		logger.log(LogLevel.WARNING, "should log");
		logger.log(LogLevel.ERROR, "should log");
		logger.log(LogLevel.FATAL, "should log");

		assertEquals(4, listener.getLogs().size());
	}

	@Test
	public void databaseTest() {
		LoggerSettings.shared().reset();
		
		LogListener listener = new LogListener();
		
		Logger logger = new Logger().logType(LogType.DATABASE).build();
		
		logger.getMainOutput().addListener(listener);

		logger.log(LogLevel.INFO, "should log");
		logger.log(LogLevel.WARNING, "should log");
		logger.log(LogLevel.ERROR, "should log");
		logger.log(LogLevel.FATAL, "should log");

		assertEquals(4, listener.getLogs().size());
	}

	@Test
	public void consoleMessageTest() {
		LogListener listener = new LogListener();
		
		Logger logger = new Logger()
				.logType(LogType.CONSOLE)
				.logLevel(LogLevel.ERROR)
				.logFormat(LogFormat.PRETTY_PRINTED)
				.build();

		logger.getMainOutput().addListener(listener);

		logger.log(LogLevel.ERROR, "should log");
		
		assertEquals(1, listener.getLogs().size());
		for (String message : listener.getLogs()) {
			assertTrue("should contains the message ['should log']", message.contains("should log"));
			assertTrue("should contains the logger name 'ConsoleOutput'", message.contains("ConsoleOutput"));
			assertTrue("should contains the log destination ': CONSOLE :'", message.contains(": CONSOLE :"));
		}
	}

	@Test
	public void fileMessageTest() {
		LoggerSettings.shared().reset();
		
		LogListener listener = new LogListener();
		String filePath = "./my-file-path";
		
		Logger logger = new Logger()
				.logType(LogType.FILE)
				.logLevel(LogLevel.ERROR)
				.filePath(filePath)
				.logFormat(LogFormat.PRETTY_PRINTED)
				.build();

		logger.getMainOutput().addListener(listener);

		logger.log(LogLevel.INFO, "should not log");
		logger.log(LogLevel.ERROR, "should log");

		assertEquals(1, listener.getLogs().size());
		for (String message : listener.getLogs()) {
			assertTrue("should contains the message ['should log']", message.contains("should log"));
			assertTrue("should contains the logger name 'FileOuput'", message.contains("FileOuput"));
			assertTrue("should contains the file path '" + filePath + "'", message.contains(filePath));
		}
	}

	@Test
	public void httpMessageTest() {
		LoggerSettings.shared().reset();
		
		LogListener listener = new LogListener();
		String httpUri = "ftp://xxx";
		
		Logger logger = new Logger()
				.logType(LogType.HTTP)
				.httpURI(httpUri)
				.build();

		logger.getMainOutput().addListener(listener);

		logger.log(LogLevel.DEBUG, "should log");

		assertEquals(1, listener.getLogs().size());
		for (String message : listener.getLogs()) {
			assertTrue("should contains the message ['should log']", message.contains("should log"));
			assertTrue("should contains the default logger level 'Debug'", message.contains("DEBUG"));
			assertTrue("should contains the HTTP URI '" + httpUri + "'", message.contains(httpUri));
		}
	}

	@Test
	public void databaseMessageTest() {
		LoggerSettings.shared().reset();
		
		LogListener listener = new LogListener();
		String databaseName = "puc-es";
		
		Logger logger = new Logger()
				.logType(LogType.DATABASE)
				.databaseName(databaseName)
				.logFormat(LogFormat.PRETTY_PRINTED)
				.build();

		logger.getMainOutput().addListener(listener);

		logger.log(LogLevel.DEBUG, "should log");

		assertEquals(1, listener.getLogs().size());
		for (String message : listener.getLogs()) {
			assertTrue("should contains the message ['should log']", message.contains("should log"));
			assertTrue("should contains the logger name 'DatabaseOutput'", message.contains("DatabaseOutput"));
			assertTrue("should contains the log level '[DEBUG]'", message.contains("[DEBUG]"));
			assertTrue("should contains the database name '" + databaseName + "'", message.contains(databaseName));
		}
	}
}
