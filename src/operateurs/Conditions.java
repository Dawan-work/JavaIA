package operateurs;

import java.util.Random;

public class Conditions {
    public static void main(String[] args) {
        Random random = new Random(); // Create once

        // region Two Case (true/false)
        int a = random.nextInt(), b = random.nextInt(5), c = random.nextInt(2,5);
        System.out.printf("a = %d; b = %d; c = %d\n", a,b,c);

        if ( a == b ) {
            System.out.println("a == b");
        } else if (a == c) {
            System.out.println("a == c");
        } else if (b == c) {
            System.out.println("b == c");
        } else {
            System.out.println("a != b != c");            
        }
        
        if (a == b && a == c) {
            System.out.println("a == b == c");
        } else if (a != b && a != c && b != c) {
            System.out.println("a != b != c");
        } else {
            if (a == b) {
                System.out.println("a == b");
            } else {
                System.out.println("b == c");
            }
        }

        if(random.nextBoolean()) {
            System.out.println("Condition vraie");
        } else {
            System.out.println("Condition fausse");
        }

        // condition ? {si vrai} : {si faux}
        System.out.println("Condition " + ( random.nextBoolean() ? "vraie" : "fausse"));
        // endregion

        // region Multiple Cases
        switch (b) {// b From 0 to 4
            case 0:
                System.out.println("Origin Value");
                break;
            case 2:
                System.out.println("Mid Value");
                break;
            case 4:
                System.out.println("Bound Value");
                break;
            default:
                System.out.println("Impair");
        }

        switch (b) {// b From 0 to 4
            case 0 -> System.out.println("Origin Value");
            case 2 -> System.out.println("Mid Value");
            case 4 -> System.out.println("Bound Value");
            default -> System.out.println("Impair");
        }

        switch (b) {
            case 0:
                System.out.println("Origin Value");
            case 1:
                System.out.println("Lower Half");
                break;
            case 4:
                System.out.println("Bound Value");
            case 3:
                System.out.println("Upper Half");
            default:
                System.out.println("Mid value");
        }
        //endregion
    }
}
