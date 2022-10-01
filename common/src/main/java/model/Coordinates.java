package model;

import exceptions.FieldDefinitionException;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private int x; // > -535
    private int y; // <= 630

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        if (x <= -535)
            throw new FieldDefinitionException("Значение координаты X организации должно быть больше -535");
        this.x = x;
    }

    public void setY(int y) {
        if (y > 630)
            throw new FieldDefinitionException("Значение координаты Y организации должно быть меньше или равно 630");
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("[%d; %d]", x, y);
    }
}
