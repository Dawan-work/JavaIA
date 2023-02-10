import core.CheatSheet;
import exceptions.TryCatch;
import flux.Streams;
import flux.lambda.Lambda;
import objets.Jardin;
import objets.UserInput;
import objets.generic.GenericSerializer;
import operateurs.Boucles;
import operateurs.Conditions;
import operateurs.MathOperations;
import serialization.CSV;
import serialization.TXT;
import variables.Types;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    private static Map<String, Consumer<String[]>> mains;
    private static String[] mainsName;
    private static void getAllMains() {
        mains = new HashMap<>(Map.of(
                "CheatSheet", CheatSheet::main,
                "Types", Types::main,
                "MathOperations", MathOperations::main,
                "Conditions", Conditions::main,
                "Boucles", Boucles::main,
                "Objets : Jardin", Jardin::main,
                "Objets : UserInput", UserInput::main,
                "Exceptions", TryCatch::main,
                "Flux : Lambdas", Lambda::main,
                "Flux : Streams", Streams::main
        ));
        mains.putAll(Map.of(
                "Serialization : CSV", CSV::main,
                "Serialization : TXT", TXT::main,
                "Objets : Généric", GenericSerializer::main));
        mainsName = mains.keySet() // Set with map's keys
                .toArray(new String[0]); // Can't get on Set -> convert to Array
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
        /*Start main : java {filename.ext}
        * Start compiled main : java {classname}
        * Compile : javac {filename.ext}
        * Compile with options : javac -optionName {optionValue} {filename.ext}
        * Start main with Args : java {filename.ext} {args1} {args2} {args3} ...*/
        System.out.printf("Args : {%s}\n", String.join(" | ",args));
        // String.join({delimiter},{stringArray}) called Collector.joining()
        getAllMains();
        displayMenu(args);
    }

    private static void startMain(int index, String[] args) {
        if(index == -1) return;
        try {
            mains.get(mainsName[index]).accept(args);
        } catch (Exception e) {
            System.out.println("L'index saisi ne correspond à aucun programme.");
        } finally {
            displayMenu(args); // Ever reached
        }
        // displayMenu(args);
    }

    private static void displayMenu(String[] args) {
        System.out.println("Saisissez le numéro du programme à lancer :");
        //mains.forEach((key,value) -> System.out.printf("%d\t-> %s\n", Arrays.stream(mainsName).toList().indexOf(key),key));
        for (int i = 0; i < mainsName.length; i++) {
            System.out.printf("%d\t-> %s\n",i,mainsName[i]);
        }
        System.out.println("-1\t->\t Quitter");
        startMain(getInpuInt(),args);
    }

    private static int getInpuInt() {
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            System.out.println("La saisie est invalide, veuillez réessayer");
            return getInpuInt();
        }
    }
}