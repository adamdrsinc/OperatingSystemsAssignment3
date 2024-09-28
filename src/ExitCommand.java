import java.util.ArrayList;

public class ExitCommand implements ConsoleCommand{
    @Override
    public void executeCommand(ArrayList<String> arguments) {
        System.exit(0);
    }
}
