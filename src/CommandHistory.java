import java.util.ArrayList;

public class CommandHistory implements ConsoleCommand{
    private static ArrayList<ArrayList<String>> previousCommands = new ArrayList<>();

    public static void add(ArrayList<String> command){
        previousCommands.add(command);
    }

    public static ArrayList<ArrayList<String>> get(){
        return previousCommands;
    }


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
