import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * A built-in command which lists the files and directories of the current directory.
 * @author Adam Sinclair
 */
public class ListCommand implements ConsoleCommand{

    /**
     * Prints the files and directories of the current directory to the command line.
     * @param arguments Irrelevant.
     * @author Adam Sinclair
     */
    @Override
    public void executeCommand(ArrayList<String> arguments) {
        File directory = new File(DirectoryUtilities.getCurrentDirectory());
        File[] files = directory.listFiles();

        if (files != null){
            for(File file: files){
                if(file.isDirectory() || file.isFile()){
                    System.out.println(getFormattedFileString(file));
                }
            }
        }
    }

    /**
     * Obtains the permissions, byte count, date last modified, and name of the given file, then returns this as
     * a formatted string.
     * @param file The file of which information is desired.
     * @return A formatted string denoting the permissions, byte count, date last modified, and name of the given file.
     * @author Adam Sinclair
     */
    private static String getFormattedFileString(File file){
        String fileName = file.getName();

        String permissions =
                  (file.isDirectory() ? "d" : "-")
                + (file.canRead()     ? "r" : "-")
                + (file.canWrite()    ? "w" : "-")
                + (file.canExecute()  ? "x" : "-");

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy");
        String lastModifiedString = dateFormatter.format(file.lastModified());

        long fileSize = file.length();

        //drwx size dateModified fileName
        return String.format("%s %9d %s %s", permissions, fileSize, lastModifiedString, fileName);

    }
}



