package model;

import exceptions.FieldDefinitionException;

import java.io.Serializable;

public class Address implements Serializable {
    private String zipCode; // not null, length <= 16
    private Location town; // not null

    public Address(String zipCode, Long x, int y, float z, String name) {
        this.zipCode = zipCode;
        this.town = new Location(x, y, z, name);
    }

    public Address() {
    }

    public String getZipCode() {
        return zipCode;
    }

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
