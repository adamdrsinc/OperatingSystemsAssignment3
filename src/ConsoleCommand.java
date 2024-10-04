import java.util.ArrayList;

/**
 * An interface which denotes a class as a ConsoleCommand.
 * @author Adam Sinclair
 */
public interface ConsoleCommand {
    void executeCommand(ArrayList<String> arguments);
}
