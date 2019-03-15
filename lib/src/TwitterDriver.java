/**
 * Twitter Driver and Client
 *
 * @author Jason Knight
 * Original idea by Ria Galanos, whose documentation and source can be found at
 * https://github.com/ritagalanos/cs1-twitter
 **/

import com.google.maps.errors.ApiException;
import twitter4j.TwitterException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

public class TwitterDriver {
    private static PrintStream consolePrint;

    public static void main(String[] args) throws Exception {
        // set up classpath and properties file

        Twitterer bigBird = new Twitterer(consolePrint);

////        String message = "I'm testing out the twitter4j API for Java.";
////        bigBird.tweetOut(message);
//
////        Scanner scan = new Scanner(System.in);
////        System.out.println("Please enter a Twitter handle, do not include the '@' symbol (or 'done' to quit.)");
////        String twitter_handle = scan.next();
////        while (!"done".equals(twitter_handle))
////        {
////            bigBird.queryHandle(twitter_handle);
////            System.out.println("Please enter a Twitter handle, do no include the '@' symbol (or 'done' to quit.)");
////            twitter_handle = scan.next();
////        }
//
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter a city and state or country: ");
        String userLoc = scan.next();

        try {
            bigBird.geoCode(scan.next());
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid location name.  Please enter a valid city and state/country.");
            return;
        }

        System.out.print("Please enter a radius (in miles): ");
        int userRadius = Integer.parseInt(scan.next());
        if (userRadius > 1000)
        {
            System.out.println("Search radius must be less than 1000 miles.  Try again.");
            return;
        }
        System.out.println("Please enter the starting date to search (YYYY-MM-DD)");
        String userTimeFrame = scan.next();
        System.out.println("Please enter the search term: ");
        String userSearchTerm = scan.next();
        bigBird.setParameters(userLoc, userRadius, userTimeFrame, userSearchTerm);

    }
}