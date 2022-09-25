package abstractions.prototypes;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class PrototypesManager<T extends CloneablePrototype> {
    private final HashMap<String, T> prototypesMap;

    protected PrototypesManager() {
        this.prototypesMap = new HashMap<>();
    }

    protected abstract void definePrototypes();

    protected void addPrototype(String name, T prototype) {
        prototypesMap.put(name, prototype);
    }

    public boolean contains(String prototypeName) {
        return prototypesMap.containsKey(prototypeName);
    }

    public T clonePrototype(String prototypeName) {
        return (T) prototypesMap.get(prototypeName).clone();
    }

    protected ArrayList<String> getPrototypesNameList() {
        return new ArrayList<>(prototypesMap.keySet());
    }
}
