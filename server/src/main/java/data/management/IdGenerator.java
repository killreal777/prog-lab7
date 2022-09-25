package data.management;

import model.Organization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Class for ID generation for data collection objects
 */

public class IdGenerator {
    private final ArrayList<Integer> removedIds;
    private int currentHighestId;

    public IdGenerator() {
        removedIds = new ArrayList<>();
    }

    /**
     * Generate and register a new ID
     */
    public int generateId() {
        if (removedIds.isEmpty()) {
            currentHighestId++;
            return currentHighestId;
        } else
            return removedIds.remove(0);
    }

    /**
     * Mark ID as removed for issuing later
     */
    public void setToRemoved(int id) {
        removedIds.add(id);
    }

    /**
     * Load information about IDs form collection for correct ID generation
     */
    public void loadIdInfo(Collection<Organization> collection) {
        if (collection.isEmpty())
            return;
        ArrayList<Integer> idList = getIdList(collection);
        this.currentHighestId = Collections.max(idList);
        registerMissedIdsAsRemoved(idList);
    }

    private ArrayList<Integer> getIdList(Collection<Organization> collection) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Organization organization : collection)
            ids.add(organization.getId());
        return ids;
    }

    private void registerMissedIdsAsRemoved(ArrayList<Integer> idList) {
        for (int id = 1; id < currentHighestId; id++) {
            if (!idList.contains(id))
                removedIds.add(id);
        }
    }
}
