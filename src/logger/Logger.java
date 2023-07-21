package src.logger;

public class Logger {
    public static void info(String message) {
        String preMessage = "[LOG INFO]: ";
        System.out.println(preMessage + message);
    }
}
