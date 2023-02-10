package objets;


public class GoldenRetriever extends Animal implements Chien { // extends only one class, but implements multiple interfaces

    private final String color;

    public GoldenRetriever(String name, int age, String color) {
        super(name, age, null, null);
        this.color = color;
    }

    public GoldenRetriever(String name, int age, Animal parent, Animal otherParent, String color) {
        super(name, age, parent, otherParent);
        this.color = color;
    }


    @Override
    public Animal croiser(Animal otherParent) {
        return new GoldenRetriever("",0,this,otherParent,color);
    }

    @Override
    public void sePresenter() {
        System.out.println("I'm a GoldenRetriever");
    }

    public String getColor() {
        return color;
    }
}
