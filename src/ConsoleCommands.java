import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Update

/**
 * A class for handling the execution of built-in and external console commands.
 * @author Adam Sinclair
 */
public class ConsoleCommands {

    public static final Map<String, ConsoleCommand> commands = Map.of(
            "ptime", new PTimeCommand(),
            "list", new ListCommand(),
            "cd", new ChangeDirectoryCommand(),
            "mdir", new MakeDirectoryCommand(),
            "rdir", new RemoveDirectoryCommand(),
            "exit", new ExitCommand(),
            "^", new PreviousCommand(),
            "history", new CommandHistory()
    );

    /**
     * Checks if the given command is a build in command and executes it, if not,
     * the command is then run as an external command.
     * @param command The command for the shell to execute.
     * @param arguments The arguments for the command.
     * @return a boolean which states the successful execution of the command.
     * @author Adam Sinclair
     */
    public static boolean executeAnyCommand(String command, ArrayList<String> arguments) {
        Map<String, ConsoleCommand> commands = ConsoleCommands.commands;

        if(commands.containsKey(command)){
            ConsoleCommand currentCommandObject = commands.get(command);
            //Executes built-in shell command
            currentCommandObject.executeCommand(arguments);
            return true;
        }
        else{
            return executeExternalCommand(command, arguments);
        }
    }

    /**
     * Obtains a user's desired command and parses the separate arguments.
     * @return the parsed command.
     * @author Adam Sinclair
     */
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

    /**
     * Executes the given command along with arguments through use of the Process class.
     * @param command The command for the shell to execute.
     * @param arguments The arguments for the command.
     * @return a boolean which states the successful execution of the command.
     * @author Adam Sinclair
     */
    private static boolean executeExternalCommand(String command, ArrayList<String> arguments) {

        //Checking if the command will use Process.waitFor()
        boolean isAmpersand = false;
        if(!arguments.isEmpty()){
            isAmpersand = arguments.getLast().equals("&");
        }

        //Remove the ampersand as this is not part of the command.
        if(isAmpersand){
            arguments.removeLast();
        }

        StringBuilder fullCommand = new StringBuilder(command);
        if(!arguments.isEmpty()){
            for(String argument : arguments){
                fullCommand.append(" ").append(argument);
            }
        }

        ProcessBuilder pb;
        String operatingSystem = System.getProperty("os.name").toLowerCase();

        //Building the ProcessBuilder based upon the user's Operating System.
        if (operatingSystem.contains("win")) {
            pb = new ProcessBuilder("cmd.exe", "/c", fullCommand.toString());

        } else if (operatingSystem.contains("nix")
                || operatingSystem.contains("nux")
                || operatingSystem.contains("aix")
                || operatingSystem.contains("mac")) {
            pb = new ProcessBuilder("/bin/sh", "-c", fullCommand.toString());
        } else{
            System.out.println("Operating System not supported.");
            return false;
        }

        //Redirecting output and input to the parent process.
        pb.redirectInput(ProcessBuilder.Redirect.INHERIT);
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);

        File directory = new File(DirectoryUtilities.getCurrentDirectory());
        pb.directory(directory);

        try{
            Process p = pb.start();
            long time = System.currentTimeMillis();

            //Converting the stream of data obtained from the external command into strings.
            //Code adapted from: https://stackoverflow.com/questions/309424/how-do-i-read-convert-an-inputstream-into-a-string-in-java
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            for(String line; (line = reader.readLine()) != null;){
                System.out.println(line);
            }
            //End reference.

            //If the user did not pass in an &, then the child is waited on and the time taken added.
            if(!isAmpersand){
                boolean exitCode = (p.waitFor() == 0);
                time = System.currentTimeMillis() - time;
                PTimeCommand.addMilliseconds(time);

                return exitCode;
            }
            else{
                return true;
            }

        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}