package io;

public enum Format {
    GRAY_ITALIC("\033[3;90m"), RED("\033[0;91m"), GREEN("\033[0;92m"), YELLOW("\033[1;93m"),

    WHITE("\033[0;97m"), BOLD("\033[1;99m"), ITALIC("\033[3;99m"), UNDERLINE("\033[4;99m");

    private final String tag;

    Format(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
