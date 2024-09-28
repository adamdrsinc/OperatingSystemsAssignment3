import java.io.File;
import java.util.ArrayList;

public class MakeDirectoryCommand implements ConsoleCommand {

/*    public static void makeDirectory(String[] arguments){
        File directoryFiles = new File(DirectoryUtilities.getCurrentDirectory());

        for(String directoryName: arguments){
            File newDirectory = new File(DirectoryUtilities.getCurrentDirectory() + "/" + directoryName);
            if(newDirectory.mkdir()){
                printDirectoryToCommandLine("Directory [" + directoryName + "] created successfully\n");
            }
            else{
                printDirectoryToCommandLine("Directory [" + directoryName + "] could not be created\n");
            }
        }

    }*/
    @Override
    public void executeCommand(ArrayList<String> arguments) {
        File directoryFiles = new File(DirectoryUtilities.getCurrentDirectory());

        for(String directoryName: arguments){
            File newDirectory = new File(DirectoryUtilities.getCurrentDirectory() + "/" + directoryName);
            if(newDirectory.mkdir()){
                DirectoryUtilities.printDirectoryToCommandLine("Directory [" + directoryName + "] created successfully\n");
            }
            else{
                DirectoryUtilities.printDirectoryToCommandLine("Directory [" + directoryName + "] could not be created\n");
            }
        }
    }
}
