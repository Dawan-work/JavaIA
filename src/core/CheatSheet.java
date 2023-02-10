package core;

import java.util.List;
import java.util.Scanner;

public class CheatSheet {
    public String title; // Object variable declaration : {visibility} {Type / Class} {name}
    protected int a = 1, b = 2; // Multiple declaration with initialisation;
    private static String jvm = "JVM"; // Static variable shared by all object of the class

    public CheatSheet(String title) { // Constructor
        this.title = title; // Object variable's initialisation
    }

    public int calcul (  //Object method declaration : {visibility} {return Type / Class} {name}
            int firstArg, // Method Parameter
            int secondArg,
            int thirdArg
    ) { // Method body
        int result = firstArg + secondArg - thirdArg; // Local Variable
        return result; // Method's Exit point
    }

    private static void printJVM() { // Static method shared by all object of the class
        System.out.print( // Console's output
                jvm // Parameter given to the method
        );
    }

    public static void main(String[] args) { // Shortcut : psvm
        System.out.println(); // Shortcut : sout
        CheatSheet cheatSheet = new CheatSheet("Cheat Sheet"); // Object creation
        int version = cheatSheet.calcul(cheatSheet.a, 2, cheatSheet.b); // Object method call
        // String.format : %s for String values, %d for numbers, \n line break, \t tab
        System.out.printf("Title : %s Version %d \n", cheatSheet.title, version);
        System.out.println();
        System.out.println(".java / .class / JAR / " + jvm + " / JRE / JDK"); // String concatenation
        System.out.println("Command 'javac' compile code in a .java file in Bytecode in a .class file");
        System.out.println("Multiple compiled files can be packaged (build) in an archive type : JAR (or WAR)");
        System.out.print("The ");
        printJVM();
        // """ Preserve the structure
        System.out.println("""
                 read and execute the Bytecode from the .class files
                As the code is not written for a specific environment but for the JVM, Java application are said 'WORA'
                It means 'Write Once Run Everywhere' as any machine with a JRE installed can run them""");
        String[] jre = {jvm, "java DDL containing java core's classes"};
        System.out.println("The JRE contains :");
        for (String string : jre ) { // 'foreach' loop : For Each {type} {name} : {array}
            System.out.println("-> " + string);
        }
        System.out.println("The JDK contains the JRE and the Java Development Tools (JDT) including compiler (javac), " +
                "JavaDebugger, JavaDoc, etc.");
        System.out.println("Do you want to see the java version benefiting of the Long-Term Support (LTS) ? Y/N");
        String userInput =
                new Scanner(System.in) // Console's Input
                        .next(); // Capture the first char validated (Enter) by the user
        System.out.println(userInput);
        if(userInput != null && userInput.equalsIgnoreCase("Y")) {
            List<String> lts = List.of( // List
                    "-> 7 (Ended on July 2022)",
                    "-> 8 (Until December 2030)",
                    "-> 11 (Until September 2026)",
                    "-> 17 (Until September 2029 or later)",
                    "-> 21 (Release September 2023 / Supported Until December 2030)"
            );
            lts.forEach(System.out::println); // Method reference
        }
    }
}
