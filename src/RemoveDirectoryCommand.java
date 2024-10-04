import java.io.File;
import java.util.ArrayList;

/**
 * A built-in command to handle removing directories.
 * @author Adam Sinclair
 */
public class RemoveDirectoryCommand implements ConsoleCommand {

    /**
     * Attempts to remove the desired directories given as arguments.
     * @param arguments The directories to be removed.
     * @author Adam Sinclair
     */
    @Override
    public void executeCommand(ArrayList<String> arguments) {
        for(String directoryName: arguments){
            File newDirectory = new File(DirectoryUtilities.getCurrentDirectory() + "/" + directoryName);
            if(newDirectory.isDirectory()) {
                if(newDirectory.delete()){
                    DirectoryUtilities.printDirectoryToCommandLine("Directory [" + directoryName + "] deleted successfully\n");
                }
                else{
                    DirectoryUtilities.printDirectoryToCommandLine("Directory [" + directoryName + "] could not be deleted\n");
                }
            }

        }
    }
}
