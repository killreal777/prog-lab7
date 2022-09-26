package data.xml.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import model.Organization;

import java.util.PriorityQueue;

@XmlRootElement(name = "collection")
public class CollectionRoot {
    private PriorityQueue<Organization> collection = new PriorityQueue<>();

    public CollectionRoot() {
    }

    @XmlElement(name = "organization")
    public PriorityQueue<Organization> getCollection() {
        return collection;
    }

    public void setCollection(PriorityQueue<Organization> collection) {
        this.collection = collection;
    }
}
