package abstractions.prototypes;

public abstract class CloneablePrototype implements Cloneable {

    @Override
    public CloneablePrototype clone() {
        try {
            return (CloneablePrototype) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
