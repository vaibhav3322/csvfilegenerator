import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class EmployeeCsvGenerator {

    private static final String[] DEPARTMENTS = {
            "IT", "HR", "Finance", "Sales", "Marketing",
            "Support", "Admin", "Operations"
    };

    private static final String[] CITIES = {
            "Mumbai", "Pune", "Bangalore", "Hyderabad",
            "Chennai", "Delhi", "Noida", "Gurgaon"
    };

    public static void main(String[] args) throws IOException {

        int totalRecords = 1_000_000; // change to 5_000_000 or 10_000_000 if needed
        String fileName = "employee_1M.csv";

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        // CSV Header
        writer.write("id,firstname,lastname,salary,address,dateofbirth,department");
        writer.newLine();

        LocalDate baseDob = LocalDate.of(1980, 1, 1);

        for (int i = 1; i <= totalRecords; i++) {

            String row = i + "," +
                    "FirstName" + i + "," +
                    "LastName" + i + "," +
                    (30000 + (i % 170000)) + "," +
                    CITIES[i % CITIES.length] + "," +
                    baseDob.plusDays(i % 15000) + "," +
                    DEPARTMENTS[i % DEPARTMENTS.length];

            writer.write(row);
            writer.newLine();

            // Optional progress log
            if (i % 100000 == 0) {
                System.out.println("Generated: " + i);
            }
        }

        writer.flush();
        writer.close();

        System.out.println("CSV generation completed: " + fileName);
    }
}
