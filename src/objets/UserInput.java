package objets;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       /* System.out.println("Bienvenue");
        String next = scanner.next();
        System.out.println("Scanner.next() : " + next);
        System.out.println("Waiting for next line : ");
        scanner = new Scanner(System.in);
        String nextLine = scanner.nextLine();
        System.out.println("Scanner.nextLine() : " + nextLine);*/

        //System.out.println("Scanner.nextInt() : " + getInputInt());
        //System.out.println("Scanner.nextDouble() : " + getInputDouble());
        System.out.println("New Malinois id : " + createMalinois().id);
    }

    private static int getInputInt() {
        System.out.println("Waiting for int value : ");
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt(); // Can generate InputMismatchException
        } catch (InputMismatchException ime) {
            System.out.println("Saisie incorrecte, veuillez saisir un nombre.");
            return getInputInt();
        }
    }

    private static double getInputDouble() {
        System.out.println("Waiting for double value : ");
        try {
            return new Scanner(System.in).nextDouble(); // Must use , instead of .
        } catch (InputMismatchException ime) {
            System.out.println("Saisie incorrecte, veuillez saisir un nombre.");
            return getInputDouble();
        }
    }

    private static Malinois createMalinois() {
        System.out.println("Saisie des informations d'un nouveau Malinois");
        String name = getName();
        int age = getInputInt();
        System.out.println("Souhaitez vous saisir les parents ? (Y/N)");
        Scanner s = new Scanner(System.in);
        String choix = s.nextLine();
        return new Malinois(name,age,
                choix.equalsIgnoreCase("Y") ? createMalinois() : null,
                choix.equalsIgnoreCase("Y") ? createMalinois() : null);
    }
    private static String getName() {
        System.out.println("Saisie du nom");
        try {
            return new Scanner(System.in).next("[A-Z][a-z]{2,}");
        } catch (NoSuchElementException nee) {
            System.out.println("Nom invalide, veuillez reessayer");
            return getName();
        }
    }
}
