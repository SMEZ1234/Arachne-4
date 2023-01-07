package arachne4.lib.logging;

import java.io.PrintStream;

public final class ArachneLogger
{
	private static ArachneLogger instance;
	private final PrintStream infoStream, errorStream;
	
	public ArachneLogger(PrintStream stream) {
		this.infoStream = this.errorStream = stream;
	}
	
	public ArachneLogger(PrintStream infoStream, PrintStream errorStream) {
		this.infoStream = infoStream;
		this.errorStream = errorStream;
	}
	
	public static ArachneLogger getInstance() {
		if(instance == null) instance = new ArachneLogger(System.out, System.err);
		return instance;
	}
	
	public void info(String message) {
		infoStream.println("[INFO] " + message);
	}
	
	public void warn(String message) {
		infoStream.println("[WARN] " + message);
	}
	
	public void error(String message) {
		errorStream.println("[ERROR] " + message);
	}
	
	public void critical(String message) {
		errorStream.println("[CRITICAL] " + message);
	}
}
