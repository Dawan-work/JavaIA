package objets;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Jardin {
    public static void main(String[] args) {
        GoldenRetriever aGolden = new GoldenRetriever("Joe",3,"orange");
        GoldenRetriever anotherGolden = new GoldenRetriever("Jasmine", 5,
                null, null, "red");
        GoldenRetriever aGoldenWithParent = new GoldenRetriever("Maya", 1, aGolden, anotherGolden, "red");

        Malinois aMalinois = new Malinois("Rex", 5, null, null);
        Malinois anotherMalinois = new Malinois("Scooby", 4, null, null);

        Animal anAnimal = aGolden;
        Animal anotherAnimal = aMalinois;

        Chien aChien = anotherGolden;
        Chien anotherChien = anotherMalinois;

        aGolden.sePresenter(); // I'm a GoldenRetriever
        aMalinois.sePresenter(); // I'm a Malinois

        aChien.sePresenter(); // I'm a GoldenRetriever
        anotherChien.sePresenter(); // I'm a Malinois

        List<Chien> dogs = List.of(
                aChien, // Type Chien
                anotherChien, // Type Chien
                aGolden, // Type GoldenRetriever
                aMalinois // Type Malinois
        );

        for (int i = 0; i < dogs.size(); i++) {
            dogs.get(i).sePresenter();
        }
        for (Chien dog : dogs) {
            dog.sePresenter();
        }

        dogs.forEach(dog -> dog.sePresenter());

        System.out.println("Golden color : " + aGolden.getColor());
        System.out.println("Golden (chien) color : " + ((GoldenRetriever) aChien).getColor());

        Chien aGoldenTypeInChien = aGolden;
        aGoldenTypeInChien.sePresenter();
        ((GoldenRetriever) aGoldenTypeInChien).getColor();

        Animal aMalinoisCroiseMalinois = aMalinois.croiser(anotherMalinois);
        System.out.println(aMalinoisCroiseMalinois.getId());

        // Chien can't call vieillir
        List<Animal> animaux = List.of(
                aGolden,
                aMalinois,
                anAnimal,
                anotherAnimal);
        animaux.forEach(animal -> animal.vieillir());

        tournois(dogs).sePresenter();

        System.out.println("--- ToString & Equals ---");
        System.out.println(aChien); // default : class@hash
        List<Animal> animauxWithNull = new ArrayList<>(animaux);
        animauxWithNull.add(null);
        animauxWithNull.add(2,null);
        animauxWithNull.forEach(animal -> System.out.println(Objects.toString(animal, "Inconnu")));

        System.out.println("aGolden.equals(anAnimal) : " + aGolden.equals(anAnimal));

    }

    private static Chien tournois(List<Chien> competitors) {
        List<Chien> winners = new ArrayList<>();
        if (competitors.size() % 2 != 0) {
            winners.add(competitors.get(0));
            competitors.remove(0);
        }
        int mid = competitors.size() / 2;
        List<Chien> team1 = competitors.subList(0, mid-1),
                team2 = competitors.subList(mid, competitors.size() -1);

        for (int i = 0; i < team1.size(); i++) {
            winners.add(team1.get(i).fight(team2.get(i)));
        }
        return winners.size() == 1 ? winners.get(0) : tournois(winners);
    }
}