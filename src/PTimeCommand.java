import java.text.DecimalFormat;
import java.util.ArrayList;

public class PTimeCommand implements ConsoleCommand{
    @Override
    public void executeCommand(ArrayList<String> arguments) {
        System.out.println("Total time in child processes: " + getCurrentTimeSeconds() + " seconds");
    }

    private static double currentTimeMilliseconds = 0;

    public static String getCurrentTimeSeconds(){

        double timeSeconds = currentTimeMilliseconds / 1000;
        DecimalFormat df = new DecimalFormat("0.0000");
        return df.format(timeSeconds);

    }
    public static double getCurrentTimeMilliseconds(){
        return currentTimeMilliseconds;
    }


    public static void addMilliseconds(double milliseconds) {
        currentTimeMilliseconds += milliseconds;
    }

}
