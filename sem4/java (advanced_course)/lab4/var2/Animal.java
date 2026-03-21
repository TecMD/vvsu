package var2;

public class Animal implements Comparable<Animal> {

    private final String name;
    private final String typ;

    public Animal(String name, String typ) {
        this.name = name;
        this.typ = typ;
    }

    @Override
    public int compareTo(Animal o) {
        return this.typ.compareTo(o.typ);
    }

    @Override
    public String toString() {
        return typ + "(" + name + ")";
    }
}
