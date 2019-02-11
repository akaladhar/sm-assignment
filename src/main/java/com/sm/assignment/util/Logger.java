package com.sm.assignment.util;

public class Logger {
    private static boolean loggingEnabled = true;
    public static void log(String message) {
        if (loggingEnabled) {
            System.out.println(message);
        }
    }
}
