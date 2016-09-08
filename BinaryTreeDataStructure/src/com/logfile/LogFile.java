package com.logfile;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LogFile {
	
	private static String SPACE = ":  ";
	private static String NEW_LINE = "\n";
	private static final Logger logger = Logger.getLogger(LogFile.class.getName().toString());

	public static Logger getLogger() {
		return logger;
	}
	
	private static String format(Object object, String method, String message, Exception exception) {
		StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
		StringBuffer buffer = new StringBuffer();
		if(object == null){
			buffer.append(method + SPACE + message + SPACE);
		}
		else{
			buffer.append(object.getClass().getName() + SPACE + method + SPACE + message + SPACE);
		}
		if(exception != null){
			 //exception.printStackTrace(printWriter);
			 //printWriter.close();
		     buffer.append(stringWriter.toString());
		}
		return buffer.toString();
	}
	
	public static void warn(Object object, String method, String message, Exception exception) {
		String displayMessage = format(object, method, message, exception);
		getLogger().log(Level.WARN, displayMessage, exception);
	}
	
	public static void info(Object object, String method, String message, Exception exception) {
		String displayMessage = format(object, method, message, exception);
		getLogger().log(Level.INFO, displayMessage, exception);
	}

	public static void severe(Object object, String method, String message, Exception exception) {
		String displayMessage = format(object, method, message, exception);
		getLogger().log(Level.ERROR, displayMessage, exception);
	}
}
