import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleCommands {

    public static Map<String, ConsoleCommand> commands = Map.of(
            "ptime", new PTimeCommand(),
            "list", new ListCommand(),
            "cd", new ChangeDirectoryCommand(),
            "mdir", new MakeDirectoryCommand(),
            "rdir", new RemoveDirectoryCommand(),
            "exit", new ExitCommand(),
            "^", new PreviousCommand(),
            "history", new CommandHistory()
    );

    public static void executeAnyCommand(String command, ArrayList<String> arguments) {
        Map<String, ConsoleCommand> commands = ConsoleCommands.commands;

        if(commands.containsKey(command)){
            ConsoleCommand currentCommandObject = commands.get(command);
            //Executes built-in shell command
            currentCommandObject.executeCommand(arguments);
        }
        else{
            StringBuilder fullCommand = new StringBuilder(command);
            for (String argument : arguments) {
                fullCommand.append(" ").append(argument);
            }

            executeExternalCommand(fullCommand);

        }
    }

    private static void executeExternalCommand(StringBuilder fullCommand) {
        String operatingSystem = System.getProperty("os.name").toLowerCase();
        String commandPrefix = "";
        if(operatingSystem.contains("win")){
            commandPrefix = "cmd.exe"; //cmd.exe
        }
        else if(operatingSystem.contains("linux")){
            commandPrefix = "/bin/sh";
        }


        ProcessBuilder pb = new ProcessBuilder(
                commandPrefix,
                "/c",
                fullCommand.toString()
        );

        File directory = new File(DirectoryUtilities.getCurrentDirectory());
        pb.directory(directory);

        try{
            Process p = pb.start();

            //https://stackoverflow.com/questions/309424/how-do-i-read-convert-an-inputstream-into-a-string-in-java
            //Reference this ^
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            for(String line; (line = reader.readLine()) != null;){
                System.out.println(line);
            }


            if(!(fullCommand.charAt(fullCommand.length() - 1) == '&')){
                p.waitFor();
            }
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }


    public static String[] getUserCommand(){
        Scanner scanner = new Scanner(System.in);

        DirectoryUtilities.printDirectoryToCommandLine("");
        String input = scanner.nextLine();

        return splitCommand(input);
    }

    /**
     * Split the user command by spaces, but preserving them when inside double-quotes.
     * Code Adapted from: https://stackoverflow.com/questions/366202/regex-for-splitting-a-string-using-space-when-not-surrounded-by-single-or-double
     */
    private static String[] splitCommand(String command) {
        List<String> matchList = new ArrayList<>();

        Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
        Matcher regexMatcher = regex.matcher(command);
        while (regexMatcher.find()) {
            if (regexMatcher.group(1) != null) {
                // Add double-quoted string without the quotes
                matchList.add(regexMatcher.group(1));
            } else if (regexMatcher.group(2) != null) {
                // Add single-quoted string without the quotes
                matchList.add(regexMatcher.group(2));
            } else {
                // Add unquoted word
                matchList.add(regexMatcher.group());
            }
        }

        return matchList.toArray(new String[matchList.size()]);
    }
}