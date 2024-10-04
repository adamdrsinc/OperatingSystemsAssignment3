import java.io.File;
import java.util.ArrayList;

/**
 * A build-in command for making a directory.
 * @author Adam Sinclair
 */
public class MakeDirectoryCommand implements ConsoleCommand {

    /**
     * Attempts to create the directories given as arguments.
     * @param arguments The names of the directories that should be created.
     * @author Adam Sinclair
     */
    @Override
    public void executeCommand(ArrayList<String> arguments) {
        for(String directoryName: arguments){
            //Repeatedly obtain the files in the current directory in case the user attempts to make the same directory
            //twice in the given arguments.
            File newDirectory = new File(DirectoryUtilities.getCurrentDirectory() + File.separator + directoryName);

            if(newDirectory.mkdir()){
                System.out.println("Directory [" + directoryName + "] created successfully");
            }
            else{
                System.out.println("Directory [" + directoryName + "] could not be created");
            }
        }
    }
}
