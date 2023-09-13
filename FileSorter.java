import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSorter {
    public static void main(String[] args) {
        String directoryPath = "C:/Users/sb185543/Desktop"; // Replace with the path of your directory
        File folder = new File(directoryPath);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    String extension = getFileExtension(file);
                    try {
                        Path folderPath = Paths.get(directoryPath + "/" + extension);
                        if (!Files.exists(folderPath)) {
                            Files.createDirectories(folderPath);
                        }
                        Files.move(file.toPath(), folderPath.resolve(file.getName()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static String getFileExtension(File file) {
        String fileName = file.getName();
        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // No extension
        }
        return fileName.substring(lastIndexOf);
    }
}