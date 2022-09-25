package data.management;

import exceptions.FieldDefinitionException;
import model.Organization;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Class for validation special requirements for fields of data model objects, which can't be validated by theirs
 * constructors (field uniqueness in collection)
 */

public class DataSpecialValidator {
    /**
     * Validates all special requirements
     */
    public void validateCollection(PriorityQueue<Organization> collection) throws FieldDefinitionException {
        checkIdUniqueness(collection);
        checkFullNameUniqueness(collection);
    }

    private void checkIdUniqueness(PriorityQueue<Organization> collection) throws FieldDefinitionException {
        ArrayList<Integer> idList = new ArrayList<>();
        Integer id;
        for (Organization org : collection) {
            id = org.getId();
            if (idList.contains(id))
                throw new FieldDefinitionException("Обнаружен неуникальный ID: " + id);
            idList.add(id);
        }
    }

    private void checkFullNameUniqueness(PriorityQueue<Organization> collection) throws FieldDefinitionException {
        ArrayList<String> fullNameList = new ArrayList<>();
        String fullName;
        for (Organization org : collection) {
            fullName = org.getFullName();
            if (fullNameList.contains(fullName))
                throw new FieldDefinitionException("Обнаружено неуникальное полное имя организации: " + fullName);
            fullNameList.add(fullName);
        }
    }
}
