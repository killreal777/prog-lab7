package data.xml.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.time.LocalDateTime;


@XmlRootElement(name = "collection_info")
@XmlType(propOrder = { "collectionType", "initializationDateString", "elementsAmount" })
public class CollectionInfo {
    private String collectionType = "PriorityQueue<Organization>";
    private LocalDateTime initializationDate = LocalDateTime.now();
    private String initializationDateString = initializationDate.toString(); // this field is for XML storing
    private int elementsAmount;

    public CollectionInfo() {
    }

    public void incrementElementsAmount() {
        elementsAmount++;
    }

    public void decrementElementsAmount() {
        elementsAmount--;
    }

    @XmlElement(name = "collection_type")
    public String getCollectionType() {
        return collectionType;
    }

    @XmlElement(name = "initialization_date")
    public String getInitializationDateString() {
        return initializationDateString;
    }

    @XmlElement(name = "elements_amount")
    public int getElementsAmount() {
        return elementsAmount;
    }

    public void setCollectionType(String collectionType) {
        this.collectionType = collectionType;
    }

    public void setInitializationDate(LocalDateTime initializationDate) {
        this.initializationDate = initializationDate;
        this.initializationDateString = initializationDate.toString();
    }

    public void setElementsAmount(int elementsAmount) {
        this.elementsAmount = elementsAmount;
    }

    @Override
    public String toString() {
        String[] dateTime = initializationDateString.split("T");
        String type = String.format("Collection type: %s\n", collectionType);
        String date = String.format("Initialization date: %s %s\n", dateTime[0], dateTime[1]);
        String amount = String.format("Elements amount: %s", elementsAmount);
        return type + date + amount;
    }
}
