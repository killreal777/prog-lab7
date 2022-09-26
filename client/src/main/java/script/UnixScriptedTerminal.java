package script;

import io.UnixTerminal;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public class UnixScriptedTerminal extends UnixTerminal implements ScriptReader {
    private final ScannerManager scannerManager;
    private final ScriptExecutionManager scriptExecutionManager;

    public UnixScriptedTerminal() {
        this.scannerManager = new ScannerManager();
        this.scriptExecutionManager = new ScriptExecutionManager(scannerManager);
    }

    @Override
    public String readLineEntire(String invitationMessage) {
        scriptExecutionManager.checkIfScriptIsEnded();
        if (scriptExecutionManager.isScriptNotExecuting())
            System.out.print(invitationMessage);
        return readLine(invitationMessage);
    }

    @Override
    public void readScript(String fileName) throws FileNotFoundException {
        scriptExecutionManager.createScriptScanner(fileName);
    }

    @Override
    protected String readLine(String invitationMessage) {
        try {
            return scannerManager.getCurrentScanner().nextLine().trim();
        } catch (NoSuchElementException e) {
            if (scriptExecutionManager.isScriptExecuting()) // means that script ended incorrectly
                return readLineEntire(invitationMessage);
            handleCtrlD();
            throw new RuntimeException(e);
        }
    }
}
