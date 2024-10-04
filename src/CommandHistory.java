import java.util.ArrayList;

/**
 * A class for handling the command history. Inherits from ConsoleCommand interface.
 * @author Adam Sinclair
 */
public class CommandHistory implements ConsoleCommand{
    private static ArrayList<ArrayList<String>> previousCommands = new ArrayList<>();

    /**
     * Adds to the list of previous commands.
     * @author Adam Sinclair
     */
    public static void add(ArrayList<String> command){
        previousCommands.add(command);
    }

    /**
     * Returns the contents of the command history.
     * @return An ArrayList of commands separated into individual ArrayLists.
     * @author Adam Sinclair
     */
    public static ArrayList<ArrayList<String>> get(){
        return previousCommands;
    }


    /**
     * Prints the added commands to the terminal.
     * @param arguments Irrelevant.
     * @author Adam Sinclair
     */
    @Override
    public void executeCommand(ArrayList<String> arguments) {

        System.out.println("-- Command History --");
        for(int i = 0; i < previousCommands.size(); i++){
            System.out.print((i + 1)  + ": " + previousCommands.get(i).getFirst() + " ");
            for(int j = 1; j < previousCommands.get(i).size(); j++){
                System.out.print(previousCommands.get(i).get(j) + " ");
            }
            System.out.print("\n");
        }

    }
}


