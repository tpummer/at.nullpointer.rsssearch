package at.nullpointer.rsssearch;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests of {@link SearchUtil}
 * @author Thomas Pummer
 *
 */
public class SearchModeUtil {

    private static final String SEARCHTEXT = "Das ist der Suchtext! Wirklich das ist der Suchtext den ich geschrieben habe!";

    private static final String[] NO_HIT = { "Sessel" };
    private static final String[] ONE_HIT = { "ist" };
    private static final String[] TWO_HITS = { "ist", "der" };
    private static final String[] ONE_HIT_ONE_MISS = { "ist", "Sessel" };
    private static final String[] LOWER_CASE = { "wirklich" };

    @Test
    public void testSearchFor() {
        // find one
        assertFalse(SearchUtil.searchFor(SearchMode.AND, NO_HIT, SEARCHTEXT));
        assertTrue(SearchUtil.searchFor(SearchMode.AND, ONE_HIT, SEARCHTEXT));
        assertTrue(SearchUtil.searchFor(SearchMode.AND, TWO_HITS, SEARCHTEXT));
        assertFalse(SearchUtil.searchFor(SearchMode.AND, ONE_HIT_ONE_MISS,
                SEARCHTEXT));
        assertTrue(SearchUtil.searchFor(SearchMode.AND, LOWER_CASE, SEARCHTEXT));

        assertFalse(SearchUtil.searchFor(SearchMode.OR, NO_HIT, SEARCHTEXT));
        assertTrue(SearchUtil.searchFor(SearchMode.OR, ONE_HIT, SEARCHTEXT));
        assertTrue(SearchUtil.searchFor(SearchMode.OR, TWO_HITS, SEARCHTEXT));
        assertTrue(SearchUtil.searchFor(SearchMode.OR, ONE_HIT_ONE_MISS,
                SEARCHTEXT));
        assertTrue(SearchUtil.searchFor(SearchMode.OR, LOWER_CASE, SEARCHTEXT));
    }

}
