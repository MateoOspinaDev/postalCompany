package com.workshop.postal.helpers;

public class EnsureHelper {
    public static void ensureNotNullOrEmpty(String value, String message) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void ensureNotNull(Long value, String message) {
        if (value == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void ensureNotNull(Object value, String message) {
        if (value == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void ensureNotZeroOrNegative(double value, String message) {
        if (value <= 0) {
            throw new IllegalArgumentException(message);
        }
    }
}
