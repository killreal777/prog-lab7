package io;

import java.util.Scanner;

public class Terminal {
    private final Scanner scanner;

    public Terminal() {
        this.scanner = new Scanner(System.in);
    }

    public String[] readLineSplit() {
        return readLineSplit(">>> ");
    }

    public String[] readLineSplit(String invitationMessage) {
        return readLineEntire(invitationMessage).split("\\s+");
    }

    public String readLineEntire() {
        return readLineEntire(">>> ");
    }

    public String readLineEntire(String invitationMessage) {
        System.out.print(invitationMessage);
        return readLine(invitationMessage);
    }

    protected String readLine(String invitationMessage) {
        return scanner.nextLine().trim();
    }

    public void print(String message) {
        System.out.print(message + "\n");
    }
}
