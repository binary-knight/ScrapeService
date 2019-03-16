import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import twitter4j.*;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Twitterer {

    private Twitter twitter;
    private PrintStream consolePrint;
    private List<Status> statuses;
    private int radius;
    private String timeFrame;
    private String searchQuery;
    private double lat;
    private double lng;


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
        query.setGeoCode(new GeoLocation(lat, lng), radius, Query.MILES);
        query.setSince(timeFrame);

        try {
            QueryResult result = twitter.search(query);
            int counter = 0;
            System.out.println("Count: " + result.getTweets().size());
            for (Status tweet : result.getTweets()) {
                counter++;
                System.out.println("Tweet #" + counter + ": @" + tweet.getUser().getName() +
                        " tweeted \"" + tweet.getText() + "\"");
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    public void setParameters(String location, int radIn, String timeIn,
                              String searchIn) throws InterruptedException,
            ApiException, IOException {

        this.geoCode(location);
        radius = radIn;
        timeFrame = timeIn;
        searchQuery = searchIn;
        System.out.printf("Thank you.  Displaying results for the following parameters: " +
                "\nSearch Location: %s\nRadius: " +
                "%d miles\nStarting from %s until today.\n%n", location, radius, timeFrame);
        saQuery(searchQuery);
    }

    /**
     * Geolocation based upon place name.
     *
     * @param address is address, which can be as generic as possible, that the target location should be.
     * @throws InterruptedException
     * @throws ApiException
     * @throws IOException
     */

    public void geoCode(String address) throws InterruptedException, ApiException, IOException {
        File file = new File("gmaps_api_key1"); // Add your google API key to a file and path to it.
        Scanner sc = new Scanner(file);
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(sc.nextLine())
                .build();
        GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
        List<String> list = new ArrayList<>();
        list.add("" + (results[0].geometry.location));
        String coords = Arrays.toString(list.toArray());
        var lat2double = coords.substring(1, 8);
        lat = Double.parseDouble(lat2double);
        var lng2double = coords.substring(13, 21);
        lng = Double.parseDouble(lng2double);


    }

}
