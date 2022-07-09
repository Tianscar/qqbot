package com.lrk.qqbot;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class RobotLogFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        return "[" + record.getLevel() + "] " + record.getMessage() + "\n";
    }

}