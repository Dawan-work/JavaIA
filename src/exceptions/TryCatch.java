package exceptions;

public class TryCatch {
    public static void main(String[] args) {
        System.out.println("Before Exception");
        String nullString = null;
        try {
            System.out.println("In try before Exception");
            nullString.replace(";","!");
            System.out.println("In try after Exception"); // Never reached
        } catch (NullPointerException e) {
            System.out.println("Exception class : " + e.getClass());
            System.out.println("Exception cause : " + e.getCause());
            System.out.println("Exception message : " + e.getMessage());
            e.printStackTrace(System.out); // Print in console's output
        }
        System.out.println("After Exception");
        System.out.println();
        try {
            nullString.replace(";","!");
        } catch (Exception e) { // Parent class
            e.printStackTrace(System.out);
        }

        System.out.println();

        try {
            throwException();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        try {
            throwCustomException();
        } catch (CustomException e) {
            e.printStackTrace(System.out);
        }

        try {
            throwException();
            throwCustomException();
        } catch (CustomException customException) {
            System.out.println("A custom Exception");
            customException.printStackTrace(System.out);
        } catch (Exception e) {
            System.out.println("A generic Exception");
            e.printStackTrace(System.out);
        }

        try {
            throwCustomException();
            throwException();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        try {
            throwCustomRuntimeException();
            throwCustomException();
            throwException();
        } catch (CustomException | CustomRuntimeException e) {
            System.out.println("A custom Exception");
            e.printStackTrace(System.out);
        } catch (Exception exception) {
            exception.printStackTrace(); // Will be written at the bottom of the console
        }

        System.out.println("After Exception catch");

        try {
            throwError();
            throwException();
        } catch (Exception | Error e) {
            e.printStackTrace(System.out);
        }
    }

    private static void throwException() throws Exception {
        throw new Exception("Generic Exception");
    }

    private static void throwCustomException() throws CustomException {
        throw new CustomException();
    }

    private static void throwCustomRuntimeException() throws CustomRuntimeException {
        throw new CustomRuntimeException("Custom Runtime Exception");
    }

    private static void throwError() {
        throw new Error("Generic Error");
    }
}
