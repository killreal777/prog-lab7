package io;

/**
 * Class for text formatting
 */

public class TextFormatter {
    public static String format(String text, Format format) {
        return format.getTag() + text + "\033[0m";
    }
}
