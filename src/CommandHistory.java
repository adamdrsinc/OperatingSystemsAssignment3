import java.util.ArrayList;

public class CommandHistory implements ConsoleCommand{
    public static ArrayList<ArrayList<String>> previousCommands = new ArrayList<>();


    @Override
    public void executeCommand(ArrayList<String> arguments) {

        for(int i = 0; i < previousCommands.size(); i++){
            System.out.print((i + 1)  + ": " + previousCommands.get(i).getFirst() + " ");
            for(int j = 1; j < previousCommands.get(i).size(); j++){
                System.out.print(previousCommands.get(i).get(j) + " ");
            }
            System.out.print("\n");
        }

    }
}
