package variables;

import java.util.*;

public class Types {
    public static void main(String[] args) {

        // region PRIMITIFS
        boolean aBoolean = false;    // 1 bit
        byte aByte = 0;              // 8 bits
        short  aShort = 0;           // 16 bits
        char aChar = '\u0000';       // 16 bits
        int anInt = 0;               // 32 bits
        float aFloat = 0.0f;         // 32 bits
        long aLong = 0;              // 64 bits
        double aDouble = 0.0d;       // 64 bits
        // endregion

        // region REFERENCES
        String aString = null; // Immutable (Must be reassigned)
        aString = "String Content"; // " for Strings | ' for chars
        System.out.println("Before Replace : " + aString); // Before Replace : String Content
        System.out.println("Replace : " + aString.replace(" ", "")); // Replace : StringContent
        System.out.println("After Replace : " + aString); // After Replace : String Content
        aString = aString.replace(" ", ""); // Reassignment of Replace
        System.out.println("After reassignment : "+ aString); // After reassignment : StringContent

        Object anObject = null; // Every object is References type
        // endregion

        // region WRAPPERS
        Integer anIntWrapper = 0; // int anInt = 0;
        int anIntFromWrapper = new Integer(0); // Deprecated
        int anIntFromWrapperValue = Integer.valueOf(0); // Situational
        int anIntFromWrapperStringValue = Integer.valueOf("0"); // Deprecated
        int anIntFromWrapperParseStringValue = Integer.parseInt("0"); // Usefully to validate user entries
        // endregion

        // region COLLECTIONS
        int anIntArray[] = {150,17,29,93,46};  // Create an array with the specified values (Arrays length is immutable)
        System.out.println(anIntArray[0]); // 150 (Index from 0 to length -1)
        String[] aStringArray = new String[10]; // type[] identifier = new type[length]
        System.out.println(aStringArray[0]); // null
        aStringArray[0] = "String Content";
        System.out.println(aStringArray[0]); // String Content
        // System.out.println(aStringArray[15]); ArrayIndexOutOfBoundsException

        boolean[][] aBooleanTable = {{true, false},{false,true,true},{}};
        System.out.println(aBooleanTable[1][2]); // true
        boolean[][] anotherBooleanTable = new boolean[4][5];
        System.out.println(anotherBooleanTable[1][2]); // false

        List<String> aStringList = new ArrayList<>();
        // System.out.println(aStringList.get(0)); IndexOutOfBoundsException
        aStringList.add("first");
        System.out.println(aStringList.get(0)); // first
        String second = "second";
        String third = "third";
        aStringList.add(second);
        aStringList.add(0,third);
        aStringList.add(1,"fourth");
        System.out.println(aStringList.get(0)); // third
        System.out.println(aStringList.indexOf(second)); // 3
        System.out.println(aStringList.indexOf("fifth")); // -1
        aStringList.remove(0);
        System.out.println(aStringList.get(0)); // fourth
        aStringList.remove(third);
        System.out.println(aStringList.indexOf(third)); // -1

        List<String> anotherStringList = Arrays.asList("first",second,third,"fourth");

        // Set<String> aStringSet = new HashSet<>();
        // Same Principle as List but No duplicate Value and cannot get

        Map<String,String> aStringStringMap = new HashMap<>(); // new Map String Keys and String values
        aStringStringMap.put("first","First Value");
        String secondValue = "Second Value";
        aStringStringMap.put(second,secondValue);
        aStringStringMap.put(third, "Third Value");
        String fourthValue = "Fourth Value";
        aStringStringMap.put("fourth", fourthValue);

        System.out.println(aStringStringMap.get("first")); // First Value
        System.out.println(aStringStringMap.get(second)); // Second Value
        aStringStringMap.put(second, "New Second Value"); // Update Value
        System.out.println(aStringStringMap.containsKey("fifth")); // false
        System.out.println(aStringStringMap.containsValue("Second Value")); // false
        System.out.println(aStringStringMap.remove(third)); // Third Value
        System.out.println(aStringStringMap.remove("fifth")); // null

        Map<String,String> anotherStringStringMap = Map.of(
                "first", "FirstValue",
                "second","Second Value"
        );
        //endregion
    }
}
