package at.nullpointer.rsssearch;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.sun.syndication.io.FeedException;


/**
 * Tests of {@link FeedUrlAggregator}
 * @author Thomas Pummer
 *
 */
public class FeedUrlAggregatorTest {

    @Test
    public void testGetUrls() {
        List<String> feeds = new ArrayList<String>();
        feeds.add("http://www.nullpointer.at/feed/");
        feeds.add("http://rss.orf.at/news.xml");
        Collection<URL> urls = null;
        try {
            urls = FeedUrlAggregator.getUrls(feeds);
        } catch (IllegalArgumentException e) {
            fail(e.toString());
        } catch (MalformedURLException e) {
            fail(e.toString());
        } catch (FeedException e) {
            fail(e.toString());
        } catch (IOException e) {
            fail(e.toString());
        } catch (URISyntaxException e) {
            fail(e.toString());
        }
        assertNotNull(urls);
        assertTrue(urls.size() > 0);
    }

}
