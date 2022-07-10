package com.tianscar.qqbot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.logging.*;

public class RobotNotification {

	public static final String LOG_FILE_NAME = System.currentTimeMillis() + ".log";

	public enum Mode {
		QUIET,
		NORMAL,
		NONE
	}

	private static Mode mode;

	public static void setMode(Mode mode) {
		RobotNotification.mode = mode;
	}

	public static Mode getMode() {
		return mode;
	}

	private static final Logger logger = Logger.getLogger("BotLog");
	private static final ConsoleHandler consolehander;
	private static FileHandler filehander;
	private static final RobotLogFormatter formatter;
	private static final File logs;

	static {
		logs = new File(Data.dataDir.getAbsolutePath() + "/logs/" + LOG_FILE_NAME);
		File parentDir = logs.getParentFile();
		if (!parentDir.exists() && !parentDir.mkdirs()) {
			throw new IllegalStateException("Unable to create logs directory.");
		}
		mode = Mode.valueOf(Data.getProp("mode"));
		formatter = new RobotLogFormatter();
		consolehander = new ConsoleHandler();
		try {
			filehander = new FileHandler(logs.getAbsolutePath(),true);
		} catch(IOException ignored) {}
		consolehander.setFormatter(formatter);
		filehander.setFormatter(formatter);
		logger.addHandler(consolehander);
		logger.addHandler(filehander);
		try {
			Files.write(Paths.get(logs.getAbsolutePath()), ("---------------" + new Date() + "---------------\n").getBytes(), StandardOpenOption.APPEND);
		} catch(IOException ignored){}
	}
	public static void info(String message) {
		LogRecord logs = new LogRecord(Level.INFO, message);
		logger.setLevel(Level.INFO);
		consolehander.setLevel(Level.INFO);
		filehander.setLevel(Level.INFO);
		switch (mode) {
			case NORMAL:
				consolehander.publish(logs);
				filehander.publish(logs);
				break;
			case QUIET:
				filehander.publish(logs);
				break;
			case NONE:
				consolehander.publish(logs);
				break;
		}
	}
	public static void warning(String message) {
		LogRecord logs = new LogRecord(Level.WARNING, message);
		logger.setLevel(Level.WARNING);
		filehander.setLevel(Level.WARNING);
		consolehander.setLevel(Level.WARNING);
		consolehander.publish(logs);
		filehander.publish(logs);
	}

	public static void config(String message) {
		LogRecord logs = new LogRecord(Level.CONFIG, message);
		logger.setLevel(Level.CONFIG);
		filehander.setLevel(Level.CONFIG);
		consolehander.setLevel(Level.CONFIG);
		consolehander.publish(logs);
	}

}
