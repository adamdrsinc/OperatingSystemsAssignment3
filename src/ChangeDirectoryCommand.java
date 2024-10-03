import java.util.ArrayList;
import java.io.File;
import java.util.Objects;

public class ChangeDirectoryCommand implements ConsoleCommand{

    @Override
    public void executeCommand(ArrayList<String> arguments) {
        //System.out.println("Performing cd command with arguments: " + Arrays.toString(arguments));

        //If no arguments are passed, go to the home folder.
        if(arguments.isEmpty()){
            System.setProperty("user.dir", System.getProperty("user.home"));
        }
        //Else, go to the folder specified if it is valid.
        else {
            String givenDirectory = arguments.getFirst();
            File directory = new File(DirectoryUtilities.getCurrentDirectory());


            if(Objects.equals(givenDirectory, "..")){
                File parentDirectory = directory.getParentFile();
                if(parentDirectory != null){
                    System.setProperty("user.dir", parentDirectory.getAbsolutePath());
                }
                else{
                    DirectoryUtilities.printDirectoryToCommandLine(ChangeDirectoryReturnStatements.INVALID_DIRECTORY);
                }
            }
            else{
                File[] files = directory.listFiles();
                if(files == null){
                    DirectoryUtilities.printDirectoryToCommandLine(ChangeDirectoryReturnStatements.NO_CHILDREN);

                }


                for( File file : files){
                    if(file.isDirectory() && Objects.equals(file.getName(), givenDirectory)){
                        System.setProperty("user.dir", file.getAbsolutePath());
                        return;
                    }
                }

                DirectoryUtilities.printDirectoryToCommandLine(ChangeDirectoryReturnStatements.INVALID_DIRECTORY);
                ;
            }

        }
    }
}

class ChangeDirectoryReturnStatements{
    public static final String INVALID_DIRECTORY = "INVALID DIRECTORY";
    public static final String NO_CHILDREN = "No children in the directory";
}
