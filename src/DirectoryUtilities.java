public class DirectoryUtilities {
    public static String getCurrentDirectory(){
        return System.getProperty("user.dir");
    }

    public static void printDirectoryToCommandLine(){
        System.out.print("[" + getCurrentDirectory() + "]: ");
    }
}
