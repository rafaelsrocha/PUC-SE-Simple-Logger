package br.puc.se.library.output;

import java.util.HashSet;
import java.util.Set;

import br.puc.se.library.log.LogLevel;
import br.puc.se.library.log.LogListener;
import br.puc.se.library.LoggerSettings;
import br.puc.se.library.log.LogInterface;

public class MainOutput implements LogInterface {
	
	private Set<LogListener> listeners = new HashSet<>();

	@Override
	public void log(LogLevel messageLevel, String message) {
		LoggerSettings settings = LoggerSettings.shared();
		if (messageLevel.getPriority() <= settings.getLogLevel().getPriority()) {
			this.doLog(message);
		}
	}

	public void doLog(String message) {
		LogOutput output = LoggerSettings.shared().getLogOutput();
		
		// mock the log for console and send it to listeners
		for (LogListener listener : this.listeners) {
			listener.onLog(output.log(message));
		}
	}

	@Override
	public void addListener(LogListener listener) {
		this.listeners.add(listener);
	}

}
