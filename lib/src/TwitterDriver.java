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

        String message = "I'm testing out the twitter4j API for Java.";
        bigBird.tweetOut(message);

        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter a Twitter handle, do not include the '@' symbol (or 'done' to quit.)");
        String twitter_handle = scan.next();
        while (!"done".equals(twitter_handle)) ;
        {
            bigBird.queryHandle(twitter_handle);
            System.out.println("Please enter a Twitter handle, do no include the '@' symbol (or 'done' to quit.)");
            twitter_handle = scan.next();
        }
bigBird.saQuery("America");

    }
}