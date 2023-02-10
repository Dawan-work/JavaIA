package objets;


public class Malinois extends Animal implements Chien {

    public Malinois(String name, int age, Animal parent, Animal otherParent) {
        super(name, age, parent, otherParent);
    }

    public Malinois(String name, int age) {
        super(name, age, null, null);
    }

    @Override
    public Animal croiser(Animal otherParent) {
        // return otherParent.croiser(this); StackOverflowError
        return new Malinois("",0, this, otherParent);
    }

    @Override
    public void sePresenter() {
        System.out.println("I'm a Malinois");
    }
}
