import java.util.Arrays;
import java.io.File;
import java.util.Objects;

public class ChangeDirectoryCommand {

    public static void performCDCommand(String[] arguments){
        //System.out.println("Performing cd command with arguments: " + Arrays.toString(arguments));

        //If no arguments are passed, go to the home folder.
        if(arguments.length == 0){
            System.setProperty("user.dir", System.getProperty("user.home"));
        }
        //Else, go to the folder specified if it is valid.
        else {
            String givenDirectory = arguments[0];
            File directory = new File(DirectoryUtilities.getCurrentDirectory());


            if(Objects.equals(givenDirectory, "..")){
                File parentDirectory = directory.getParentFile();
                if(parentDirectory != null){
                    System.setProperty("user.dir", parentDirectory.getAbsolutePath());
                }
                else{
                    DirectoryUtilities.printDirectoryToCommandLine(ChangeDirectoryReturnStatements.INVALID_DIRECTORY + "\n");
                }
            }
            else{
                File[] files = directory.listFiles();
                if(files == null){
                    DirectoryUtilities.printDirectoryToCommandLine(ChangeDirectoryReturnStatements.NO_CHILDREN + "\n");

                }


                for( File file : files){
                    if(file.isDirectory() && Objects.equals(file.getName(), givenDirectory)){
                        System.setProperty("user.dir", file.getAbsolutePath());
                        return;
                    }
                }

                DirectoryUtilities.printDirectoryToCommandLine(ChangeDirectoryReturnStatements.INVALID_DIRECTORY + "\n");
                ;
            }

        }

    }



}

class ChangeDirectoryReturnStatements{
    public static final String INVALID_DIRECTORY = "INVALID DIRECTORY";
    public static final String NO_CHILDREN = "No children in the directory";
}
