import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public static void main(String[] args) {

    while(true){
        String[] command = getUserCommand();
        String[] commandArguments = new String[0];

        if (command.length > 1) {
            commandArguments = Arrays.copyOfRange(command, 1, command.length);
        }

        //System.out.println("Command: " + Arrays.toString(command));

        switch(command[0]){
            case ConsoleCommands.PTIME:
                System.out.println("ptime");
                break;

            case ConsoleCommands.LIST:
                ListCommand.performLSCommand(commandArguments);
                break;

            case ConsoleCommands.CD:
                String changeDir = ChangeDirectoryCommand.performCDCommand(commandArguments);
                switch(changeDir){
                    case ChangeDirectoryReturnStatements.INVALID_DIRECTORY:
                        DirectoryUtilities
                                .printDirectoryToCommandLine(ChangeDirectoryReturnStatements.INVALID_DIRECTORY + "\n");
                        break;
                    case ChangeDirectoryReturnStatements.NO_CHILDREN:
                        DirectoryUtilities
                                .printDirectoryToCommandLine(ChangeDirectoryReturnStatements.NO_CHILDREN + "\n");
                        break;
                    case null:
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + changeDir);
                }
                break;

            case ConsoleCommands.MDIR:
                System.out.println("mdir");
                break;

            case ConsoleCommands.RDIR:
                System.out.println("rdir");
                break;

            case ConsoleCommands.PIPE:
                System.out.println("pipe");
                break;

            case ConsoleCommands.EXIT:
                System.exit(1);
                break;

            default:
                System.out.println("Invalid command");
                break;
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
