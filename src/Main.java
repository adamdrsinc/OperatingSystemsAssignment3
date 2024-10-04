import java.util.*;

//Update


public class Main{
    public static void main(String[] args) {

        //Repeatedly obtain command line input until the user uses the "Exit" command.
        while(true){
            String[] totalCommand = ConsoleCommands.getUserCommand();

            String command = totalCommand.length > 0 ? totalCommand[0] : "";
            ArrayList<String> arguments =
                    totalCommand.length > 1
                    ? new ArrayList<>(Arrays.asList(totalCommand).subList(1, totalCommand.length))
                    : new ArrayList<>();

            if(!command.isEmpty()){
                CommandHistory.add(new ArrayList<>(Arrays.asList(totalCommand)));
                if(!ConsoleCommands.executeAnyCommand(command, arguments)){
                    StringBuilder totalCommandStr = new StringBuilder();
                    for(String str : totalCommand){
                        totalCommandStr.append(str).append(" ");
                    }

                    System.out.println("Invalid Command: " + totalCommandStr);
                }
            }
        }
    }
}

