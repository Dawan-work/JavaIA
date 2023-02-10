package objets;

import java.util.Random;

public interface Chien { // Contract
    String DEFAULT_BARK = "WOUF"; // Constant

    default void bark() {
        bark(DEFAULT_BARK);
    }
    default void bark(String barkVoice) {
        System.out.println(barkVoice);
    }

    default Chien fight(Chien enemy) { // != from default visibility
        Chien winner = new Random().nextBoolean() ? this : enemy;
        if (winner == this) bark();
        return winner;
    }
    void sePresenter();
}
