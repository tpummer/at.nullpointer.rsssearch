package at.nullpointer.rsssearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Retrieves the HTML code of feeds
 * 
 * @author Thomas Pummer
 *
 */
public class FeedEntryAggregator {
    
    protected FeedEntryAggregator(){
        
    }
    
    /**
     * Retrieves the HTML code of feeds
     * 
     * @param url of the feed
     * @return String with the HTML code
     * @throws ClientProtocolException
     * @throws IOException
     * @throws URISyntaxException
     */
    public static String readUrl(URL url) throws ClientProtocolException,
            IOException, URISyntaxException {

        DefaultHttpClient httpclient = new DefaultHttpClient();

        HttpGet httpget = new HttpGet(url.toURI());

        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        InputStream content = entity.getContent();

        return inputStreamToString(content);

    }

    private static String inputStreamToString(InputStream in)
            throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(in));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line + "\n");
        }

        bufferedReader.close();
        return stringBuilder.toString();
    }

}
