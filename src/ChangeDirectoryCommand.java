import java.util.Arrays;
import java.io.File;
import java.util.Objects;

public class ChangeDirectoryCommand {

    public static String performCDCommand(String[] arguments){
        //System.out.println("Performing cd command with arguments: " + Arrays.toString(arguments));

        //If no arguments are passed, go to the home folder.
        if(arguments.length == 0){
            System.setProperty("user.dir", System.getProperty("user.home"));
            return null;
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
                    return ChangeDirectoryReturnStatements.INVALID_DIRECTORY;
                }
            }
            else{
                File[] files = directory.listFiles();
                if(files == null){
                    return ChangeDirectoryReturnStatements.NO_CHILDREN;
                }


                for( File file : files){
                    if(file.isDirectory() && Objects.equals(file.getName(), givenDirectory)){
                        System.setProperty("user.dir", file.getAbsolutePath());
                        return null;
                    }
                }

                return ChangeDirectoryReturnStatements.INVALID_DIRECTORY;
            }

        }
        return null;
    }



}

class ChangeDirectoryReturnStatements{
    public static final String INVALID_DIRECTORY = "INVALID DIRECTORY";
    public static final String NO_CHILDREN = "No children in the directory";
}
