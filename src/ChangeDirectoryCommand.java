import java.util.ArrayList;
import java.io.File;
import java.util.Objects;

//update

/**
 * A class for handling changing directory. Inherits from ConsoleCommand interface.
 * @author Adam Sinclair
 */
public class ChangeDirectoryCommand implements ConsoleCommand{

    /**
     *  * Method "executeCommand" will bring the user to the desired directory.
     *  * Entering ".." as an argument
     *  * will bring the user to the parent directory of their current directory.
     *  * Entering nothing will take them to their
     *  * home directory.
     * @param arguments Will use the first argument given as the directory the user wishes to change to. Discards the others.
     */
    @Override
    public void executeCommand(ArrayList<String> arguments) {

        final String INVALID_DIRECTORY = "Directory not found";
        final String NO_CHILDREN = "No children in the directory";

        //If no arguments are passed, go to the home folder.
        if(arguments.isEmpty()){
            System.setProperty("user.dir", System.getProperty("user.home"));
        }
        //Else, go to the folder specified if it is valid.
        else {
            String givenDirectory = arguments.getFirst();
            File directory = new File(DirectoryUtilities.getCurrentDirectory());

            //If the user enters "..", they will be taken to the parent directory.
            if(Objects.equals(givenDirectory, "..")){
                File parentDirectory = directory.getParentFile();
                if(parentDirectory != null){
                    //Switching directory to parent directory.
                    System.setProperty("user.dir", parentDirectory.getAbsolutePath());
                }
                else{
                    DirectoryUtilities.printDirectoryToCommandLine(INVALID_DIRECTORY + "\n");
                }
            }
            //If the user passes in a potential name of a directory
            else{
                //Get all files in current directory
                File[] files = directory.listFiles();
                if(files == null){
                    DirectoryUtilities.printDirectoryToCommandLine(NO_CHILDREN + "\n");
                }else{
                    //Find the directory that the user desires.
                    //Once found, switch directory to that.
                    for( File file : files){
                        if(file.isDirectory() && Objects.equals(file.getName(), givenDirectory)){
                            System.setProperty("user.dir", file.getAbsolutePath());
                            return;
                        }
                    }
                }

                //No directory found.
                DirectoryUtilities.printDirectoryToCommandLine(INVALID_DIRECTORY + "\n");

            }

        }
    }
}

