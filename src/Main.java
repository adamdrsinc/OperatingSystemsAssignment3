import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public static void main(String[] args) {
    while(true){
        String[] totalCommand = getUserCommand();

        Map<String, ConsoleCommand> commands = ConsoleCommands.commands;
        Map<String, ArrayList<String>> commandsAndArguments = new HashMap<>();
        ArrayList<String> arguments = new ArrayList<>();


        String command = totalCommand.length > 0 ? totalCommand[0] : "";
        arguments = totalCommand.length > 1
                ? new ArrayList<>(Arrays.asList(totalCommand).subList(1, totalCommand.length))
                : new ArrayList<>();

        if(!command.isEmpty()){
            if(commands.containsKey(command)){
                ConsoleCommand currentCommandObject = commands.get(command);
                currentCommandObject.executeCommand(arguments);
                arguments.clear();
            }
            else{
                StringBuilder argumentsString = new StringBuilder();
                for(String item : arguments){
                    argumentsString.append(item).append(" ");
                }

                ProcessBuilder pb = new ProcessBuilder (command, argumentsString.toString());
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


                    p.waitFor();
                }
                catch (Exception e){
                    System.out.println("Error: " + e.getMessage());
                }

            }
        }
        else{
            DirectoryUtilities.printDirectoryToCommandLine("No command entered.");
        }

    }
}





private static String[] getUserCommand(){
    Scanner scanner = new Scanner(System.in);
    String input = "";

    DirectoryUtilities.printDirectoryToCommandLine("");
    input = scanner.nextLine();

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
