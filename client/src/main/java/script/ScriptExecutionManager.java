package script;

import exceptions.MessagedRuntimeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/**
 * Class for managing scripts execution
 */

public class ScriptExecutionManager {
    private final Stack<String> executingScripts;
    private final ScannerManager scannerManager;

    ScriptExecutionManager(ScannerManager scannerManager) {
        this.executingScripts = new Stack<>();
        this.scannerManager = scannerManager;
    }

    void createScriptScanner(String fileName) throws FileNotFoundException {
        File scriptFile = new File(fileName);
        validateScript(scriptFile);
        executingScripts.push(scriptFile.getAbsolutePath());
        scannerManager.setCurrentScanner(new Scanner(scriptFile));
    }

    private void validateScript(File scriptFile) throws MessagedRuntimeException, FileNotFoundException {
        if (!scriptFile.exists() || !scriptFile.isFile())
            throw new FileNotFoundException();
        if (!scriptFile.canRead())
            throw new MessagedRuntimeException("недостаточно прав");
        if (executingScripts.contains(scriptFile.getAbsolutePath())) {
            throw new MessagedRuntimeException("обнаружена рекурсия");
        }
    }

    void checkIfScriptIsEnded() {
        if (IsScriptEnded()) {
            executingScripts.pop();
            scannerManager.returnPreviousScanner();
        }
    }

    private boolean IsScriptEnded() {
        return isScriptExecuting() && !scannerManager.getCurrentScanner().hasNext();
    }

    boolean isScriptExecuting() {
        return !executingScripts.isEmpty();
    }

    boolean isScriptNotExecuting() {
        return executingScripts.isEmpty();
    }
}
