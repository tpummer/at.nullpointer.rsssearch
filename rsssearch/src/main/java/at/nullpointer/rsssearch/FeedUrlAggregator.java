package at.nullpointer.rsssearch;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * Retrieves the URL of the entries of given feeds
 * 
 * @author Thomas Pummer
 *
 */
public class FeedUrlAggregator {
    
    protected FeedUrlAggregator(){
        
    }
    
    /**
     * Retrieves the URL of the entries of given feeds
     * 
     * @param feeds
     * @return a Collection with the URLs of the entries of the feeds
     * @throws IllegalArgumentException
     * @throws MalformedURLException
     * @throws FeedException
     * @throws IOException
     * @throws URISyntaxException
     */
    public static Collection<URL> getUrls(List<String> feeds)
            throws IllegalArgumentException, MalformedURLException,
            FeedException, IOException, URISyntaxException {
        List<URL> result = null;

        if (feeds != null) {
            result = new ArrayList<URL>();

            for (String feedUrl : feeds) {
                SyndFeedInput input = new SyndFeedInput();
                SyndFeed feed = input.build(new XmlReader(new URL(feedUrl)));
                List<?> entries = feed.getEntries();
                for (Object entryObj : entries) {
                    SyndEntry entry = (SyndEntry) entryObj;
                    result.add(new URL(entry.getUri()));
                }
            }
        }

        return result;
    }

}
