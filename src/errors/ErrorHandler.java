package src.errors;

public class ErrorHandler {
    public static void handleError(String type, String locale, Exception error) {
        String preMessage = "[" + type + "] - " + locale + ": ";

        System.out.println(preMessage + error);
    }

    public static void handleWarning(String message) {
        String preMessage = "[AVISO]: ";
        System.out.println(preMessage + message);
    }
}
