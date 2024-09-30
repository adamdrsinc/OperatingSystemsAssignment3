import java.util.Map;

public class ConsoleCommands {
    public static final String PTIME = "ptime";
    public static final String LIST = "list";
    public static final String CD = "cd";
    public static final String MDIR = "mdir";
    public static final String RDIR = "rdir";
    public static final String PIPE = "|";
    public static final String EXIT = "exit";
    public static final String PREVIOUS_COMMAND = "^";
    private static final String HISTORY = "history";

    public static Map<String, ConsoleCommand> commands = Map.of(
        PTIME, new PipeCommand(),
        LIST, new ListCommand(),
        CD, new ChangeDirectoryCommand(),
        MDIR, new MakeDirectoryCommand(),
        RDIR, new RemoveDirectoryCommand(),
        PIPE, new PipeCommand(),
        EXIT, new ExitCommand(),
        PREVIOUS_COMMAND, new PreviousCommand(),
        HISTORY, new CommandHistory()
    );
}