import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class ListCommand implements ConsoleCommand{

/*    public static void performLSCommand(String[] arguments){
        //System.out.println("Performing ls command with arguments: " + Arrays.toString(arguments));

        File directory = new File(DirectoryUtilities.getCurrentDirectory());
        File[] files = directory.listFiles();

        if (files != null){
            for(File file: files){
                if(file.isDirectory() || file.isFile())
                    System.out.println(getFormattedFileString(file));
            }
        }
    }*/

    private static String getFormattedFileString(File file){
        String fileName = file.getName();

        String permissions = (file.isDirectory() ? "d" : "-")
                + (file.canRead() ? "r" : "-")
                + (file.canWrite() ? "w" : "-")
                + (file.canExecute() ? "x" : "-");

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy");
        String lastModifiedString = dateFormatter.format(file.lastModified());

        long fileSize = file.length();

        //drwx size dateModified fileName
        return String.format("%s %10d %20s %s", permissions, fileSize, lastModifiedString, fileName);

    }

    @Override
    public void executeCommand(ArrayList<String> arguments) {
        File directory = new File(DirectoryUtilities.getCurrentDirectory());
        File[] files = directory.listFiles();

        if (files != null){
            for(File file: files){
                if(file.isDirectory() || file.isFile())
                    System.out.println(getFormattedFileString(file));
            }
        }
    }
}

