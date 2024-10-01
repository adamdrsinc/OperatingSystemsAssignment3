import java.util.*;

public class Main{
    public static void main(String[] args) {

        while(true){
            String[] totalCommand = ConsoleCommands.getUserCommand();


            String command = totalCommand.length > 0 ? totalCommand[0] : "";
            ArrayList<String> arguments = totalCommand.length > 1
                    ? new ArrayList<>(Arrays.asList(totalCommand).subList(1, totalCommand.length))
                    : new ArrayList<>();

            if(!command.isEmpty()){
                if(!ConsoleCommands.executeAnyCommand(command, arguments)){
                    DirectoryUtilities.printDirectoryToCommandLine("Invalid Command.\n");
                }
            }
            else{
                DirectoryUtilities.printDirectoryToCommandLine("No command entered.\n");
            }

            CommandHistory.add(new ArrayList<>(Arrays.asList(totalCommand)));

        }
    }

}

