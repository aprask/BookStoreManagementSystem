package Administrator.Security;
import java.util.Scanner;

public class Admin implements Operator {
    private static final String ADMIN_PASSWORD = "project3";
    private final Scanner scanner;

    public Admin() {
        scanner = new Scanner(System.in);
    }

    /**
     *
     * @return boolean whether the manager passed the password test
     */
    @Override
    public boolean didPass()
    {
        System.out.printf("hint: \"%s\"\n\n", ADMIN_PASSWORD);
        int attempts = 5;
        while (attempts > 0) {
            System.out.println("Enter password:");
            System.out.println("You have " + attempts + " Attempts Left:\n");
            String passwordAttempt = scanner.next();
            if (passwordAttempt.equals(ADMIN_PASSWORD)) {
                System.out.println("You have successfully opened the store!");
                return true;
            } else {
                System.out.println("Incorrect Password");
                attempts--;
            }
        }
        System.out.println("You've run out of attempts. Access denied.");
        System.exit(0);
        return false;
    }
}