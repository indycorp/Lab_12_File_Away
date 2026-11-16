import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        ArrayList<String> records = new ArrayList<>();
        int idNumber = 1;
        boolean more;

        do
        {
            String first = safeInput.getNonZeroLenString(in, "Enter first name");
            String last = safeInput.getNonZeroLenString(in, "Enter last name");
            String id = String.format("%06d", idNumber);   // zero-padded, e.g. 000001
            String email = safeInput.getRegExString(in, "Enter email",
                    "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}");
            int year = safeInput.getRangedInt(in, "Enter year of birth", 1900, 2026);

            String record = first + ", " + last + ", " + id + ", " + email + ", " + year;
            records.add(record);
            System.out.println("Added: " + record);
            idNumber++;

            more = safeInput.getYNConfirm(in, "Add another record?");
        } while (more);

        String fileName = safeInput.getNonZeroLenString(in, "Enter the file name (without extension)");
        if (!fileName.toLowerCase().endsWith(".csv"))
        {
            fileName = fileName + ".csv";
        }

        Path file = Paths.get(System.getProperty("user.dir"), "src", fileName);

        try
        {
            Files.write(file, records);
            System.out.println("\nSaved " + records.size() + " record(s) to " + file);
        }
        catch (IOException e)
        {
            System.out.println("Error writing the file: " + e.getMessage());
        }
    }
}