/**
 * Controls various utilities concerning directories.
 * @author Adam Sinclair
 */
public class DirectoryUtilities {
    /**
     * Obtains the current directory.
     * @return the current directory.
     * @author Adam Sinclair
     */
    public static String getCurrentDirectory(){
        return System.getProperty("user.dir");
    }

    /**
     * Prints the current directory to the command line.
     * @param optional Additional information that is printed after the directory.
     * @author Adam Sinclair
     */
    public static void printDirectoryToCommandLine(String optional){
        System.out.print("[" + getCurrentDirectory() + "]: " + optional);
    }
}


//Update

