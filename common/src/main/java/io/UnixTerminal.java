package io;

import java.util.NoSuchElementException;

public class UnixTerminal extends Terminal {
    private Runnable preExitCall;

    public UnixTerminal() {
        this.preExitCall = () -> {};
    }

    public void setPreExitCall(Runnable preExitCall) {
        this.preExitCall = preExitCall;
    }

    @Override
    protected String readLine(String invitationMessage) {
        try {
            return super.readLine(invitationMessage);
        } catch (NoSuchElementException e) {
            handleCtrlD();
            throw new RuntimeException(e);
        }
    }

    protected void handleCtrlD() {
        System.out.println(TextFormatter.format("Ctrl+D", Format.RED));
        preExitCall.run();
        System.exit(0);
    }
}
