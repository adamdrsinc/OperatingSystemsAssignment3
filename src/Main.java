import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public static void main(String[] args) {

    while(true){
        String[] totalCommand = getUserCommand();

        Map<String, ConsoleCommand> commands = ConsoleCommands.commands;
        Map<String, ArrayList<String>> commandsAndArguments = new HashMap<String, ArrayList<String>>();

        String currentCommand = "";
        for (String currentItem : totalCommand) {
            if (commands.containsKey(currentItem)) {
                commandsAndArguments.put(currentItem, new ArrayList<String>());
                currentCommand = currentItem;
            } else {
                if(!currentCommand.isEmpty())
                    commandsAndArguments.get(currentCommand).add(currentItem);
            }
        }

        for(Map.Entry<String, ArrayList<String>> entry : commandsAndArguments.entrySet()){
            ConsoleCommand currentCommandObject = commands.get(entry.getKey());
            currentCommandObject.executeCommand(entry.getValue());

            ArrayList<String> commandAndArguments = new ArrayList<String>();
            commandAndArguments.add(entry.getKey());
            commandAndArguments.addAll(entry.getValue());
            CommandHistory.previousCommands.add(commandAndArguments);
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
