package model;

import exceptions.FieldDefinitionException;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement(name = "official_address")
@XmlType(propOrder = { "zipCode", "town" })
public class Address implements Serializable {
    private String zipCode; // not null, length <= 16
    private Location town; // not null

    public Address(String zipCode, Long x, int y, float z, String name) {
        this.zipCode = zipCode;
        this.town = new Location(x, y, x, name);
    }

    public Address() {
    }

    @XmlElement(name = "zip_code")
    public String getZipCode() {
        return zipCode;
    }

    @XmlElement(name = "town")
    public Location getTown() {
        return town;
    }

    public void setZipCode(String zipCode) {
        if (zipCode.equals(""))
            throw new FieldDefinitionException("Zip Code не может быть пустой строкой");
        if (zipCode.length() > 16)
            throw new FieldDefinitionException("Zip Code не может длиннее 16 символов");
        this.zipCode = zipCode;
    }

    public void setTown(Location town) {
        this.town = town;
    }

    @Override
    public String toString() {
        return "ZipCode: " + zipCode + "\t" + town.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Address))
            return false;
        Address adr = (Address) obj;
        return adr.zipCode.equals(zipCode) && adr.town.equals(town);
    }
}
