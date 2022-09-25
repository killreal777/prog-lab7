package script;

import java.io.FileNotFoundException;

public interface ScriptReader {
    public void readScript(String fileName) throws FileNotFoundException;
}
