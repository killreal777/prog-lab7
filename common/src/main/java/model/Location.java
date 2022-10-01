package model;

import exceptions.FieldDefinitionException;

import java.io.Serializable;

public class Location implements Serializable {
    private Long x; // not null
    private int y;
    private float z;
    private String name; // not null

    public Location(Long x, int y, float z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public Location() {
    }

    public Long getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public String getName() {
        return name;
    }

    public void setX(Long x) {
        if (x == null)
            throw new FieldDefinitionException("Координата X локации не может быть null");
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void setName(String name) {
        if (name == null)
            throw new FieldDefinitionException("Название локации не может быть null");
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Location: \"%s\" [%d; %d; %.2f]", name, x, y, z);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Location loc))
            return false;
        return loc.name.equals(name) && loc.x.equals(x) && loc.y == y && loc.z == z;
    }
}
