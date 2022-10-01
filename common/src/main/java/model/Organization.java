package model;

import exceptions.FieldDefinitionException;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Organization implements Comparable<Organization>, Serializable {
    private Integer id; // not null, > 0, unique, autogenerate
    private String name; // not null, not empty String
    private Coordinates coordinates; // not null
    private LocalDateTime creationDate; // not null, autogenerate
    private String creationDateString; // this field is for XML serialization
    private Long annualTurnover; // not null, > 0
    private String fullName; // not null, unique
    private int employeesCount; // > 0
    private OrganizationType type; // not null
    private Address officialAddress; // not null
    private String ownerLogin;

    public Organization() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getCreationDateString() {
        return creationDateString;
    }

    public Long getAnnualTurnover() {
        return annualTurnover;
    }

    public String getFullName() {
        return fullName;
    }

    public int getEmployeesCount() {
        return employeesCount;
    }

    public OrganizationType getType() {
        return type;
    }

    public Address getOfficialAddress() {
        return officialAddress;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setId(Integer id) {
        if (id <= 0)
            throw new FieldDefinitionException("ID должен быть больше 0");
        this.id = id;
    }

    public void setName(String name) {
        if (name == null)
            throw new FieldDefinitionException("Название организации не может быть null");
        if (name.equals(""))
            throw new FieldDefinitionException("Название организации не может быть пустой строкой");
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDateString(String creationDateString) {
        this.creationDateString = creationDateString;
        this.creationDate = LocalDateTime.parse(creationDateString);
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        this.creationDateString = creationDate.toString();
    }

    public void setAnnualTurnover(Long annualTurnover) {
        if (annualTurnover <= 0)
            throw new FieldDefinitionException("Ежегодный товарооборот должен быть больше нуля");
        this.annualTurnover = annualTurnover;
    }

    public void setFullName(String fullName) {
        if (fullName == null)
            throw new FieldDefinitionException("Полное название организации не может быть null");
        this.fullName = fullName;
    }

    public void setEmployeesCount(int employeesCount) {
        if (employeesCount <= 0)
            throw new FieldDefinitionException("Чилсо сотрудников должно быть больше нуля");
        this.employeesCount = employeesCount;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public void setOfficialAddress(Address officialAddress) {
        this.officialAddress = officialAddress;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    @Override
    public String toString() {
        String[] dateTime = creationDateString.split("T");
        return "\033[1;97m" + String.format("ORGANIZATION id%s \n", id) + "\033[0m"
                + String.format("Name: \"%s\"\tFull name: \"%s\"\tType: \"%s\"\n", name, fullName, type)
                + String.format("Annual turnover: %s\tEmployees count: %s\n", annualTurnover, employeesCount)
                + String.format("ADDRESS %s\tCoordinates: %s\n", officialAddress, coordinates)
                + String.format("Creation date: %s %s\n", dateTime[0], dateTime[1])
                + String.format("Owner Login: %s", ownerLogin);
    }

    @Override
    public int compareTo(Organization o) {
        return annualTurnover.compareTo(o.getAnnualTurnover());
    }
}
