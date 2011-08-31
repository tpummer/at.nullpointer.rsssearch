package at.nullpointer.rsssearch;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

/**
 * Tests of {@link FeedEntryAggregator}
 * @author Thomas Pummer
 *
 */
public class FeedEntryAggregatorTest {

    @Test
    public void testReadUri() {
        String readUrl = null;
        try {
            readUrl = FeedEntryAggregator.readUrl(new URL(
                    "http://www.nullpointer.at"));
        } catch (ClientProtocolException e) {
            fail(e.toString());
        } catch (MalformedURLException e) {
            fail(e.toString());
        } catch (IOException e) {
            fail(e.toString());
        } catch (URISyntaxException e) {
            fail(e.toString());
        }
        assertNotNull(readUrl);

    }

}
