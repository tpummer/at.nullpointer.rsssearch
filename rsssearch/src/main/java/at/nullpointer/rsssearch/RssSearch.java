package at.nullpointer.rsssearch;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sun.syndication.io.FeedException;

/**
 * Hello world!
 * 
 */
public class RssSearch {

	public static void main(String[] args) {
		// filter args

		String valid = ArgsValidator.isValid(args);
		if (valid == null) {
			System.out.println("Searching ...");

			// set mode
			SearchMode mode = ArgsValidator.getMode(args);

			// get list of feed urls
			List<String> feeds = null;
			try {
				feeds = ArgsValidator.getFeeds(args);
			} catch (MalformedURLException e) {
				System.out.println("Error - invalid Feed URL!");
			}

			Collection<URL> entries = null;
			try {
				entries = FeedUrlAggregator.getUrls(feeds);
			} catch (IllegalArgumentException e1) {
				System.out.println("Error - invalid Feed?");
			} catch (MalformedURLException e) {
				System.out.println("Error - invalid Feed?");
			} catch (FeedException e) {
				System.out.println("Error - invalid Feed?");
			} catch (IOException e) {
				System.out.println("Error - invalid Feed?");
			} catch (URISyntaxException e) {
				System.out.println("Error - invalid Feed?");
			} finally {
				if (entries == null) {
					entries = new ArrayList<URL>();
				}
			}

			// read and search feeds
			for (URL url : entries) {
				try {
					if (SearchUtil.searchFor(mode,
							ArgsValidator.getKeywords(args),
							FeedEntryAggregator.readUri(url))) {
						System.out.println(url);
						// open if it matches
						// try {
						// Desktop.getDesktop().browse(url.toURI());
						// } catch (IOException e) {
						// System.out
						// .println("Error opening feed entry in browser!");
						// } catch (URISyntaxException e) {
						// System.out
						// .println("Error opening feed entry in browser!");
						// }
					}
				} catch (IOException e) {
					System.out
							.println("Error opening http request to feed article");
				} catch (URISyntaxException e) {
					System.out
							.println("Error opening http request to feed article");
				}
			}
			System.out.println("done");
		} else {
			System.out.println(valid);
		}
	}
}
