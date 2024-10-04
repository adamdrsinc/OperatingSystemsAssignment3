import java.util.ArrayList;

/**
 * A built-in command for using any of the previous commands given to the command line in the current session.
 * The previous commands are found through the use of the CommandHistory class, through the get() method.
 * @author Adam Sinclair
 */
public class PreviousCommand implements ConsoleCommand{

    /**
     * Executes the desired command based upon the index given as an argument. The index is a base +1 index.
     * @param arguments The index of the desired command.
     * @author Adam Sinclair
     */
    @Override
    public void executeCommand(ArrayList<String> arguments) {
        ArrayList<ArrayList<String>> previousCommands = CommandHistory.get();

        String indexStr = !arguments.isEmpty() ? arguments.getFirst() : "";
        if(indexStr.isEmpty()){
            System.out.println("No command index given.");
            return;
        }

        Integer index = null;
        try{
            index = Integer.parseInt(indexStr);
        }catch(NumberFormatException e){
            System.out.println("Invalid command index.");
            return;
        }

        if(index <= 0 || index >= previousCommands.size()){
            System.out.println("Invalid command index. Maximum command index is " + (previousCommands.size()-1));
            return;
        }


        ArrayList<String> previousCommand = previousCommands.get(index - 1);
        String command = previousCommand.getFirst();
        ArrayList<String> commandArguments = new ArrayList<>(previousCommand.subList(1, previousCommand.size()));

        ConsoleCommands.executeAnyCommand(command, commandArguments);

    }
}
