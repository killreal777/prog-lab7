package data.management;

import io.Format;
import io.Terminal;
import io.TextFormatter;
import data.xml.model.DataRoot;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import exceptions.FieldDefinitionException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class for conversation of collection of Organizations between the XML and java object data.model
 */

public class DataJaxbConverter {
    private final Terminal terminal;
    private final DataFile dataFile;
    private final DataSpecialValidator validator;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public DataJaxbConverter(Terminal terminal) {
        this.terminal = terminal;
        this.dataFile = new DataFile(terminal);
        this.validator = new DataSpecialValidator();
        initJaxbObjects();
    }

    private void initJaxbObjects() {
        try {
            JAXBContext context = JAXBContext.newInstance(DataRoot.class);
            this.marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            this.unmarshaller = context.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read XML form data file and convert it to the object data.model
     */
    public DataRoot readXml() throws IOException {
        try {
            DataRoot dataRoot = unmarshalDataRoot();
            validator.validateCollection(dataRoot.getCollectionRoot().getCollection());
            return dataRoot;
        } catch (JAXBException e) {
            return handleIncorrectDataException("некорректная структура XML");
        } catch (FieldDefinitionException e) {
            return handleIncorrectDataException(e.getMessage());
        }
    }

    private DataRoot unmarshalDataRoot() throws JAXBException, IOException {
        FileReader reader = dataFile.createReader();
        DataRoot dataRoot = (DataRoot) unmarshaller.unmarshal(reader);
        reader.close();
        return dataRoot;
    }

    private DataRoot handleIncorrectDataException(String message) throws IOException {
        message = "Данные в файле некорректны: " + message;
        String options = "Введите путь к другому файлу или пустую строку для создания коллекции в этом файле: ";
        String filePath = reenter(message, options);
        if (filePath.equals(""))
            return new DataRoot();
        dataFile.setFileByPath(filePath);
        return readXml();
    }

    public void writeXml(DataRoot dataRoot) throws JAXBException, FileNotFoundException {
        try {
            FileWriter writer = dataFile.createWriter();
            marshaller.marshal(dataRoot, writer);
            writer.close();
        } catch (IOException e) {
            handleSavingException(dataRoot);
        }
    }

    private void handleSavingException(DataRoot dataRoot) throws JAXBException, FileNotFoundException {
        String message = "Невозмножно сохранить коллекцию в файле: недостаточно прав ";
        String options = "Введите путь к другому файлу (для остановки этого процесса ввдеите путь к текущему файлу)"
                + "или пустую строку для отмены: ";
        String input = reenter(message, options);
        if (input.equals(""))
            throw new FileNotFoundException();
        dataFile.setFileByPath(input);
        writeXml(dataRoot);
    }

    private String reenter(String message, String options) {
        String text = message + "\n" + options;
        String invitationMessage = TextFormatter.format(text, Format.RED);
        return terminal.readLineEntire(invitationMessage);
    }
}
