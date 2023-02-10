package operateurs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Boucles {
    public static void main(String[] args) {
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            System.out.println("Itération : " + i);
        }

        int origin = random.nextInt(1,10), bound = origin + random.nextInt(1, origin + 1);
        System.out.printf("Origin : %d , Bound %d \n", origin, bound);
        for (int i = origin; i < bound; i++) {
            System.out.println("Itération : " + i);
        }

        int[] anIntArray = random.ints(origin,origin,bound).toArray();
        for (int i = 0; i < anIntArray.length; i++) {
            System.out.printf("Index : %d , Value : %d\n",i,anIntArray[i]);
        }

        System.out.print("anIntArray : {");
        for (int value : anIntArray) {
            System.out.printf("%d, ", value);
        }
        System.out.println("}");


        // Solution 1
        int iteration = 0;
        System.out.print("anIntArray : {");
        for (int value : anIntArray) {
            iteration++;
            System.out.print(value + (iteration == anIntArray.length ? "}\n" : ", "));
        }


        // Solution 2
        System.out.print("anIntArray : {");
        for (int i = 0; i < anIntArray.length; i++) {
            System.out.print(anIntArray[i] + (i == anIntArray.length - 1 ? "}\n" : ", "));
        }

        // Solution 3
        System.out.printf("an IntArray {%s}\n",
                Arrays.stream(anIntArray)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(", ")));

        List<Integer> aRandomIntergerList = new ArrayList<>();
        for (int value : anIntArray)
        {
            aRandomIntergerList.add(value); // Implicit cast to Wrapper
        }
        System.out.print("aRandomIntegerList : {");
        for (int i = 0; i < aRandomIntergerList.size(); i++) {
            System.out.print(aRandomIntergerList.get(i) + (i == aRandomIntergerList.size() -1 ? "}\n" : ", "));
        }

        System.out.println(aRandomIntergerList.stream().map(String::valueOf).collect(Collectors.joining(", ")));

        int whileIteration = 0;
        while (whileIteration < aRandomIntergerList.size()) {
            System.out.println(aRandomIntergerList.get(whileIteration));
            whileIteration++; // Element in condition must be updated to avoid infinite looping
        }

        boolean condition = false;

        while (condition) { // Never reached
            System.out.println("Inside while");
        }

        do {
            System.out.println("Inside do-while"); // Done once
        } while (condition);

        boolean[][] aRandomBooleanTable = new boolean[random.nextInt(origin,bound)][random.nextInt(origin,bound)];
        for (int i = 0; i < aRandomBooleanTable.length; i++) {
            for (int j = 0; j < aRandomBooleanTable[i].length; j++) {
                aRandomBooleanTable[i][j] = random.nextBoolean();
            }
        }
        System.out.println("aRandomBooleanTable :");
        for (boolean[] line : aRandomBooleanTable) {
            System.out.print("{");
            for (boolean rowValue : line) {
                System.out.print(rowValue ? "O" : "X");
            }
            System.out.println("}");
        }
    }
}
