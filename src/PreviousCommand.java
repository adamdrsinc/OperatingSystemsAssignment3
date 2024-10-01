import java.io.Console;
import java.util.ArrayList;

public class PreviousCommand implements ConsoleCommand{
    @Override
    public void executeCommand(ArrayList<String> arguments) {
        ArrayList<ArrayList<String>> previousCommands = CommandHistory.get();

        String indexStr = !arguments.isEmpty() ? arguments.getFirst() : "";
        if(indexStr.isEmpty()){
            DirectoryUtilities.printDirectoryToCommandLine("No command index given.\n");
            return;
        }

        Integer index = null;
        try{
            index = Integer.parseInt(indexStr);
        }catch(NumberFormatException e){
            DirectoryUtilities.printDirectoryToCommandLine("Invalid command index.\n");
            return;
        }

        if(index < 0 || index >= previousCommands.size() + 1){
            DirectoryUtilities.printDirectoryToCommandLine("Invalid command index. Number is too small or too great.\n");
            return;
        }


        ArrayList<String> previousCommand = previousCommands.get(index + 1);
        String command = previousCommand.getFirst();
        ArrayList<String> commandArguments = new ArrayList<>(previousCommand.subList(1, previousCommand.size()));

        ConsoleCommands.executeAnyCommand(command, commandArguments);

    }
}
