import twitter4j.GeoLocation;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterException;

import java.io.IOException;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.List;

public class Twitterer {

    private Twitter twitter;
    private PrintStream consolePrint;
    private List<Status> statuses;
    private double latitude;
    private double longitude;
    private int miles;
    private String timeFrame;
    private String searchQuery;


    public Twitterer(PrintStream console) {
        twitter = TwitterFactory.getSingleton();
        consolePrint = console;
        statuses = new ArrayList<Status>();
    }

    public void tweetOut(String message) throws TwitterException, IOException {
        Status status = twitter.updateStatus(message);
        System.out.println("Successfully updated the status to [" + status.getText() + "].");
    }

    @SuppressWarnings("unchecked")
    public void queryHandle(String handle) throws TwitterException, IOException {
        statuses.clear();
        fetchTweets(handle);
        int counter = statuses.size();
        while (counter > 0) {
            counter--;
            System.out.println("Tweet #" + counter + ": " + statuses.get(counter).getText());
        }
    }

    public void fetchTweets(String handle) throws TwitterException, IOException {
        Paging page = new Paging(1, 200);
        int p = 1;
        while (p <= 10) {
            page.setPage(p);
            statuses.addAll(twitter.getUserTimeline(handle, page));
            p++;
        }
    }

    public void saQuery(String searchTerm) {
        Query query = new Query(searchTerm);
        query.setCount(100);
        query.setGeoCode(new GeoLocation(getLatitude(), getLongitude()),getMiles(), Query.MILES);
        query.setSince(getTimeFrame());

        try {
            QueryResult result = twitter.search(query);
            int counter = 0;
            System.out.println("Count: " + result.getTweets().size());
            for (Status tweet : result.getTweets()) {
                counter++;
                System.out.println("Tweet #" + counter + ": @" + tweet.getUser().getName() +
                        "tweeted \"" + tweet.getText() + "\"");
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    public void setLatitude(double latIn) {
        latitude = latIn;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLongitude(double longIn) {
        longitude = longIn;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setMiles(int milesIn) {
        miles = milesIn;
    }

    public int getMiles() {
        return miles;
    }

    public void setTimeFrame(String timeIn) {
        timeFrame = timeIn;
    }

    public String getTimeFrame() {
        return timeFrame;
    }

    public void setSearchQuery(String searchIn) {
        searchQuery = searchIn;
    }

    public String getSearchQuery() {
        return searchQuery;
    }
}



