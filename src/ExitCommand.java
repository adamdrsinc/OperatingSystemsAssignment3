import java.util.ArrayList;

/**
 * A built-in command for exiting the terminal
 * @author Adam Sinclair
 */
public class ExitCommand implements ConsoleCommand{
    /**
     * Terminates the shell.
     * @param arguments Irrelevant.
     * @author Adam Sinclair
     */
    @Override
    public void executeCommand(ArrayList<String> arguments) {
        System.exit(0);
    }
}

