package utils;


import PC.Connection;
import com.sun.istack.internal.NotNull;

public final class Logger {

    private static final String ESCAPE_CHAR = "\u001B";

    private static final String ANSI_RESET = "[0m";
    private static final String ANSI_BLACK = "[30m";
    private static final String ANSI_BRIGHT_RED = "[1;31m";
    private static final String ANSI_BLUE = "[34m";
    private static final String ANSI_BRIGHT_YELLOW = "[33m";
    private static final String ANSI_BRIGHT_GREEN = "[1;32m";

    enum LogTypes {
        ERROR,
        WARNING,
        INFO,
        DEBUG
    }

    private static void print(@NotNull LogTypes type, @NotNull String color, @NotNull String tag, @NotNull String message) {
        if (type.ordinal() <= Config.IMPORTANCE_TO_PRINT.ordinal()) {
            String toPrint =
                    ESCAPE_CHAR +
                            color +
                            type.name().toUpperCase() +
                            " : " +
                            tag +
                            " : " +
                            message +
                            ESCAPE_CHAR +
                            ANSI_RESET;

            if (Connection.runningOnEV3 && Config.USING_PC) {
                Connection.EV3.sendLogMessage(ESCAPE_CHAR +
                        ANSI_BRIGHT_GREEN +
                        "FROM EV3 : " +
                        toPrint);
            } else {
                System.out.println(toPrint);
            }
        }
    }

    public static void error(@NotNull String tag, @NotNull String message) {
        Logger.print(LogTypes.ERROR, ANSI_BRIGHT_RED, tag, message);
    }

    public static void warning(@NotNull String tag, @NotNull String message) {
        Logger.print(LogTypes.WARNING, ANSI_BRIGHT_YELLOW, tag, message);
    }

    public static void info(@NotNull String tag, @NotNull String message) {
        Logger.print(LogTypes.INFO, ANSI_BLUE, tag, message);
    }

    public static void debug(@NotNull String tag, @NotNull String message) {
        Logger.print(LogTypes.DEBUG, ANSI_BLACK, tag, message);
    }
}