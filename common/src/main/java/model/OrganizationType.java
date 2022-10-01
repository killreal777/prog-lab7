package model;

import java.io.Serializable;

public enum OrganizationType implements Serializable {
    COMMERCIAL("Коммерческая организация"), TRUST("Траст"),
    PRIVATE_LIMITED_COMPANY("Общество с ограниченной ответственностью"),
    OPEN_JOINT_STOCK_COMPANY("Открытое акционерное общество");

    private final String name;

    OrganizationType(String name) {
        this.name = name;
    }

    public static OrganizationType getByID(int id) throws InvalidIDException {
        switch (id) {
        case 0:
            return COMMERCIAL;
        case 1:
            return TRUST;
        case 2:
            return PRIVATE_LIMITED_COMPANY;
        case 3:
            return OPEN_JOINT_STOCK_COMPANY;
        }
        throw new InvalidIDException();
    }

    @Override
    public String toString() {
        return name;
    }

    public static class InvalidIDException extends RuntimeException {
    }
}
