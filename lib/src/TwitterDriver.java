/**
 * Twitter Driver and Client
 *
 * @author Jason Knight
 * Original idea by Ria Galanos, whose documentation and source can be found at
 * https://github.com/ritagalanos/cs1-twitter
 **/

import twitter4j.TwitterException;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class TwitterDriver {
    private static PrintStream consolePrint;

    public static void main(String[] args) throws TwitterException, IOException {
        // set up classpath and properties file

        Twitterer bigBird = new Twitterer(consolePrint);

//        String message = "I'm testing out the twitter4j API for Java.";
//        bigBird.tweetOut(message);

//        Scanner scan = new Scanner(System.in);
//        System.out.println("Please enter a Twitter handle, do not include the '@' symbol (or 'done' to quit.)");
//        String twitter_handle = scan.next();
//        while (!"done".equals(twitter_handle))
//        {
//            bigBird.queryHandle(twitter_handle);
//            System.out.println("Please enter a Twitter handle, do no include the '@' symbol (or 'done' to quit.)");
//            twitter_handle = scan.next();
//        }

        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter a latitude (Decimal Degrees): ");
        bigBird.setLatitude(Double.parseDouble(scan.next()));
        if (((bigBird.getLatitude()) < -90) || ((bigBird.getLatitude()) > 90)) {
            System.out.println("Sorry, check your latitude.  Valid latitude is between -90 and 90 degrees.");
        } else {
            System.out.println("Latitude set to " + bigBird.getLatitude() + "\n");
        }

        System.out.print("Please enter a longitude (Decimal Degrees): ");
        bigBird.setLongitude(Double.parseDouble(scan.next()));
        if (((bigBird.getLongitude()) < -180) || ((bigBird.getLongitude()) > 180)) {
            System.out.println("Sorry, check your latitude.  Valid longitude is between -180 and 180 degrees.");
        } else {
            System.out.println("Longitude set to " + bigBird.getLongitude() + "\n");
        }
        System.out.print("Please enter a search radius (in miles): ");
        bigBird.setMiles(Integer.parseInt(scan.next()));
        System.out.println("Search within " + bigBird.getMiles() + " miles of " +
                bigBird.getLatitude() + " " + bigBird.getLongitude() + "\n");

        System.out.print("Please enter a time-frame to search (YYYY-MM-DD): ");
        bigBird.setTimeFrame(scan.next());

        System.out.print("Please enter a term to search for: ");
        bigBird.setSearchQuery(scan.next());
        bigBird.saQuery(bigBird.getSearchQuery());


//bigBird.saQuery("Spurs");

    }
}