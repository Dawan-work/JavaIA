package operateurs;

import java.util.Random;

public class MathOperations {
    public static void main(String[] args) {
        // region Implicit Conversion
        int aInt = 10;
        double aDouble = 9.36;
        float aFloat = 9.81f; // Decimal values are considered double by default

        double DoubleFromInt = aInt;
        float aFloatFromInt = aInt;

        int anIntFromDouble = (int) aDouble; // must be cast because double is more precise (heavier)
        float aFloatFromDouble = (float) aDouble; // same reason
        int anIntFromFloat = (int) aFloat; // must be cast because of decimal part

        int anIntResultFromIntAndInt = aInt + aInt;
        double aDoubleResultFromIntAndDouble = aInt + aDouble; // Result's Type will be the most precise
        int getAnIntResultFromIntAndDouble = (int) (aInt + aDouble); // Casting the result
        int getAnIntResultFromIntAndDoubleCasted = aInt + ((int) aDouble); // Either cast the result or the parameter
        // endregion

        // region Strings
        String aString = "String Content";
        System.out.println(aString + " String Added"); // Concatenation
        System.out.println(aString + " And an Int : " + aInt); // concat. with number
        aString += " And a double : ";
        aString += aDouble; // Implicit conversion before concat.
        System.out.println(aString); // String Content And a double : 9.36
        System.out.println(String.format("%s And a Float : %10.3f", aString, aFloat)); // %{length}.{length}f
        System.out.printf("%s And a Float : %10.3f\n", aString, aFloat); // printf do not add the line break by default
        // endregion

        // region Random
        Random random = new Random();
        boolean randomBoolean = random.nextBoolean();
        int randomInt = random.nextInt(); // can be negative
        long randomLong = random.nextLong();
        int randomIntWithBound = random.nextInt(10); // From 0 to 9
        int randomIntFromTo = random.nextInt(5,16); // From 5 to 15
        double[] aRandomDoubleArray = random.doubles(5).toArray();
        int[] aRandomIntArray = random.ints(5, 1, 101).toArray();
        // endregion
    }
}
