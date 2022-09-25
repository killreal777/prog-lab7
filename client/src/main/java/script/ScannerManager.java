package script;

import java.io.Serializable;
import java.util.Scanner;
import java.util.Stack;

/**
 * Class for managing Scanners for Terminal (it's necessary for script execution) Stores current Scanner and stack of
 * previous Scanners
 *
 * Default current Scanner is the Scanner with source in System.in
 */

public class ScannerManager {
    private final Stack<Scanner> scanners;

    public ScannerManager() {
        this.scanners = new Stack<>();
        scanners.push(new Scanner(System.in));
    }

    public Scanner getCurrentScanner() {
        return scanners.peek();
    }

    public void setCurrentScanner(Scanner scanner) {
        scanners.push(scanner);
    }

    public void returnPreviousScanner() {
        scanners.pop();
    }
}
