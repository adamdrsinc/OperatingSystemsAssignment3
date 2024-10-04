import java.text.DecimalFormat;
import java.util.ArrayList;

//Update

/**
 * A built-in command that, when executed, prints the time (in seconds) that external commands have been executing.
 * @author Adam Sinclair
 */
public class PTimeCommand implements ConsoleCommand{

    /**
     * The current time in milliseconds for which external commands have been running.
     */
    private static double currentTimeMilliseconds = 0;

    /**
     * Prints the time (in seconds) that external commands have been executing.
     * @param arguments Irrelevant.
     * @author Adam Sinclair
     */
    @Override
    public void executeCommand(ArrayList<String> arguments) {
        System.out.println("Total time in child processes: " + getCurrentTimeSeconds() + " seconds");
    }

    /**
     * Adds the given milliseconds to the accrued time.
     * @param milliseconds The milliseconds to be added to the accrued time.
     * @author Adam Sinclair
     */
    public static void addMilliseconds(double milliseconds) {
        currentTimeMilliseconds += milliseconds;
    }

    /**
     * Converts the current time in milliseconds that external commands have been running into seconds.
     * @return The time in seconds.
     * @author Adam Sinclair
     */
    private static String getCurrentTimeSeconds(){

        double timeSeconds = currentTimeMilliseconds / 1000;
        DecimalFormat df = new DecimalFormat("0.0000");
        return df.format(timeSeconds);

    }

}
