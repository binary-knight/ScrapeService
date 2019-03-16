/**
 * Twitter Driver and Client
 *
 * @author Jason Knight
 * Original idea by Ria Galanos, whose documentation and source can be found at
 * https://github.com/ritagalanos/cs1-twitter
 **/

import java.io.PrintStream;
import java.util.Scanner;

public class TwitterDriver {
    private static PrintStream consolePrint;

    public static void main(String[] args) throws Exception {
        // set up classpath and properties file

        Twitterer userQuery = new Twitterer(consolePrint);

        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter a city and state or country: ");
        String userLoc = scan.nextLine();

        try {
            userQuery.geoCode(userLoc);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid location name.  Please enter a valid city and state/country.");
            return;
        }

        System.out.print("Please enter a radius (in miles): ");
        int userRadius = Integer.parseInt(scan.next());
        if (userRadius > 1000) {
            System.out.println("Search radius must be less than 1000 miles.  Try again.");
            return;
        }
        System.out.println("Please enter the starting date to search (YYYY-MM-DD)");
        String userTimeFrame = scan.next();
        System.out.println("Please enter the search term: ");
        String userSearchTerm = scan.next();
        userQuery.setParameters(userLoc, userRadius, userTimeFrame, userSearchTerm);
        scan.close();

    }
}