import java.io.File;

public class DirectoryUtilities {
    public static String getCurrentDirectory(){
        return System.getProperty("user.dir");
    }

    public static void printDirectoryToCommandLine(String optional){
        System.out.print("[" + getCurrentDirectory() + "]: " + optional);
    }

    public static void makeDirectory(String[] arguments){
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

    }

    public static void removeDirectory(String[] arguments){
        File directoryFiles = new File(DirectoryUtilities.getCurrentDirectory());

        for(String directoryName: arguments){
            File newDirectory = new File(DirectoryUtilities.getCurrentDirectory() + "/" + directoryName);
            if(newDirectory.delete()){
                printDirectoryToCommandLine("Directory [" + directoryName + "] deleted successfully\n");
            }
            else{
                printDirectoryToCommandLine("Directory [" + directoryName + "] could not be deleted\n");
            }
        }
    }
}
