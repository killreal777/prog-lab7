package data.management;

import data.xml.model.CollectionInfo;
import data.xml.model.DataRoot;
import io.Terminal;
import model.Organization;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.PriorityQueue;

public class DataManager {
    private final DataJaxbConverter jaxbConverter;
    private final IdGenerator idGenerator;
    private DataRoot dataRoot;

    public DataManager(Terminal terminal) {
        this.jaxbConverter = new DataJaxbConverter(terminal);
        this.idGenerator = new IdGenerator();
        loadData();
    }

    private void loadData() {
        try {
            this.dataRoot = jaxbConverter.readXml();
            idGenerator.loadIdInfo(dataRoot.getCollectionRoot().getCollection());
        } catch (IOException ignored) {
        }
    }

    public void saveData() throws FileNotFoundException {
        try {
            jaxbConverter.writeXml(dataRoot);
        } catch (jakarta.xml.bind.JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Provide data collection
     */
    public PriorityQueue<Organization> getCollection() {
        return dataRoot.getCollectionRoot().getCollection();
    }

    /**
     * Provide collection information
     */
    public CollectionInfo getCollectionInfo() {
        return dataRoot.getCollectionInfo();
    }

    /**
     * Provide IdGenerator
     */
    public IdGenerator getIdGenerator() {
        return idGenerator;
    }
}
