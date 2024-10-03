import java.io.File;
import java.util.ArrayList;

public class RemoveDirectoryCommand implements ConsoleCommand {
    @Override
    public void executeCommand(ArrayList<String> arguments) {
        File directoryFiles = new File(DirectoryUtilities.getCurrentDirectory());

        for(String directoryName: arguments){
            File newDirectory = new File(DirectoryUtilities.getCurrentDirectory() + "/" + directoryName);
            if(newDirectory.delete()){
                DirectoryUtilities.printDirectoryToCommandLine("Directory [" + directoryName + "] deleted successfully");
            }
            else{
                DirectoryUtilities.printDirectoryToCommandLine("Directory [" + directoryName + "] could not be deleted");
            }
        }
    }
}
