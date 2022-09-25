package data.management;

import io.Format;
import io.Terminal;
import io.TextFormatter;

import java.io.*;

/**
 * Class for interaction with data file
 *
 * Checks file permissions Allows to change (reenter) data file Provides tools for reading and writing
 */

public class DataFile {
    private final Terminal terminal;
    private File file;

    public DataFile(Terminal terminal) {
        this.terminal = terminal;
        String filePath = getFilePathFromEnv();
        setFileByPath(filePath);
    }

    private String getFilePathFromEnv() {
        String filePath = System.getenv("LAB6");
        if (filePath != null)
            return filePath;
        String message = "Переменная окружения \"LAB6\" не найдена";
        String options = "Введите путь к другому файлу: ";
        return reenter(message, options);
    }

    /**
     * Check and set data file by file path
     */
    public void setFileByPath(String filePath) {
        this.file = new File(filePath);
        checkFile();
    }

    private void checkFile() {
        checkExisting();
        checkReadingPermission();
        checkWritingPermission();
    }

    private void checkExisting() {
        if (file.exists() && file.isFile())
            return;
        String message = "Файл с коллекцией не найден";
        String options = "Введите путь к другому файлу: ";
        String filePath = reenter(message, options);
        file = new File(filePath);
        checkExisting();
    }

    private void checkReadingPermission() {
        if (file.canRead())
            return;
        String message = "Файл с коллекцией не может быть прочитан: недостаточно прав";
        String options = "Введите путь к другому файлу: ";
        String filePath = reenter(message, options);
        file = new File(filePath);
        checkExisting();
        checkReadingPermission(); // in case of exception user must reset file path
    }

    private void checkWritingPermission() {
        if (file.canWrite())
            return;
        String message = "Вы не сможете сохранить коллекцию в указанном файле: недостаточно прав";
        String options = "Введите путь к другому файлу или пустую строку для продолжения в этом файле: ";
        String input = reenter(message, options);
        if (input.equals(""))
            return;
        setFileByPath(input); // resetting file path in case of not empty reenter
    }

    private String reenter(String message, String options) {
        String text = message + "\n" + options;
        String invitationMessage = TextFormatter.format(text, Format.RED);
        return terminal.readLineEntire(invitationMessage);
    }

    /**
     * Create FileReader for data file
     */
    public FileReader createReader() {
        try {
            return new FileReader(file);
        } catch (FileNotFoundException e) {
            checkFile();
            return createReader();
        }
    }

    /**
     * Create FileWriter for data file
     */
    public FileWriter createWriter() throws IOException {
        return new FileWriter(file);
    }
}
