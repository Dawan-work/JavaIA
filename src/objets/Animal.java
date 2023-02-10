package objets;

public abstract class Animal { // Represent a concept
    private static int count = 0; // Shared by all instances
    static final String TYPE = "Animal"; // Constant

    // region attributes
    long id; // visibility : default (Class/Child/Package)
    private String name;
    public int age;
    protected final Animal[] parents;
    // endregion


    // region Constructor
    private Animal() { // Default constructor
        count++;
        id = count;
        parents = new Animal[2];
    }

    // Constructor initialize attributes
    public Animal(String name, int age, Animal parent, Animal otherParent) {
        this();
        this.name = name;
        this.age = age;
        parents[0] = parent;
        parents[1] = otherParent;
    }
    // endregion

    // region comportment

    public void vieillir() {
        age++;
    }

    public abstract Animal croiser(Animal otherParent);

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public Animal[] getParents() {
        return parents;
    }

    @Override
    public String toString() {
        return String.format("Nom : %s, Age : %d",name,age);
    }

    @Override
    public boolean equals(Object obj) {
        return id == ((Animal) obj).id;
    }
    //
}
