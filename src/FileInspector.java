import javax.swing.JFileChooser;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileInspector
{
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();

        chooser.setCurrentDirectory(new File(System.getProperty("user.dir") + File.separator + "src"));

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = chooser.getSelectedFile();
            Path file = selectedFile.toPath();

            int lineCount = 0;
            int wordCount = 0;
            int charCount = 0;

            try (BufferedReader reader = Files.newBufferedReader(file))
            {
                System.out.println("--- Contents of " + selectedFile.getName() + " ---");
                String line;
                while ((line = reader.readLine()) != null)
                {
                    System.out.println(line);

                    lineCount++;
                    charCount += line.length();

                    String trimmed = line.trim();
                    if (!trimmed.isEmpty())
                    {
                        wordCount += trimmed.split("\\s+").length;
                    }
                }

                System.out.println("\n--- Summary Report ---");
                System.out.println("File name: " + selectedFile.getName());
                System.out.println("Number of lines: " + lineCount);
                System.out.println("Number of words: " + wordCount);
                System.out.println("Number of characters: " + charCount);
            }
            catch (IOException e)
            {
                System.out.println("Error reading the file: " + e.getMessage());
            }
        }
        else
        {
            System.out.println("No file was selected. Exiting.");
        }
    }
}