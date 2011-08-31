package at.nullpointer.rsssearch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

/**
 * Tests of {@link ArgsValidator}
 * @author Thomas Pummer
 *
 */
public class ArgsValidatorTest {

    private static final String[] NOTHING = { "asds", "asdsa", "ads" };
    private static final String[] OR = { "asds", "-o", "or" };
    private static final String[] AND = { "-a", "one", "two" };
    private static final String[] ONE_FEED = { "-f", "one" };
    private static final String[] ONE_FEED_RESULT = { "one" };
    private static final String[] TWO_FEEDS = { "-f", "one", "two" };
    private static final String[] TWO_FEED_RESULT = { "one", "two" };

    private static final String[] ONE_KEYWORD = { "-a", "one" };
    private static final String[] ONE_KEYWORD_RESULT = { "one" };
    private static final String[] TWO_KEYWORD = { "-o", "one", "two" };
    private static final String[] TWO_KEYWORD_RESULT = { "one", "two" };

    private static final String[] ARGS_EMPTY = {};
    private static final String[] ARGS_AND = { "-a", "asdasd", "asdsd", "-f",
            "asdsad" };
    private static final String[] ARGS_TWO_AND = { "-a", "-a", "asdasd",
            "asdsd", "-f", "asdsad" };
    private static final String[] ARGS_OR = { "-a", "asdasd", "asdsd", "-f",
            "asdsad" };
    private static final String[] ARGS_TWO_OR = { "-o", "-o", "asdasd",
            "asdsd", "-f", "asdsad" };
    private static final String[] ARGS_AND_AND_OR = { "-a", "-o", "asdasd",
            "asdsd", "-f", "asdsad" };
    private static final String[] ARGS_ALL_OK = { "-a", "asdasd", "-f",
            "asdasd", "asdsd" };
    private static final String[] ARGS_TWO_FEED = { "-a", "asd", "-f", "-f",
            "asdasd", "asdsd" };
    private static final String[] ARGS_NO_FEED = { "-a", "asd" };
    private static final String[] ARGS_COMMAND_END = { "-a", "asdasd", "asdsd",
            "-f", "asdasd", "-o" };
    private static final String[] ARGS_COMMAND_NOT_FIRST = { "asdasd", "-a",
            "asdasd", "asdsd", "-o", "-f", "asdasd" };
    private static final String[] ARGS_TWO_COMMANDS = { "-o", "-f", "asdasd",
            "asdsd" };

    @Test
    public void testIsValid() {

        // Wenn nicht genug Parameter angegeben wurden
        assertNotNull(ArgsValidator.isValid(ARGS_EMPTY));

        // Wenn der and oder der or Parameter nicht vorkommen
        assertNull(ArgsValidator.isValid(ARGS_AND));
        assertNull(ArgsValidator.isValid(ARGS_OR));
        assertNotNull(ArgsValidator.isValid(ARGS_AND_AND_OR));
        assertNotNull(ArgsValidator.isValid(NOTHING));

        // Wenn der and, or oder der feed parameter mehr als einmal vorkommen
        assertNotNull(ArgsValidator.isValid(ARGS_TWO_AND));
        assertNotNull(ArgsValidator.isValid(ARGS_TWO_OR));
        assertNotNull(ArgsValidator.isValid(ARGS_TWO_FEED));

        // Wenn and und or parameter vorkommen vorkommen
        assertNotNull(ArgsValidator.isValid(ARGS_AND_AND_OR));

        // wenn ein command parameter am ende steht
        assertNotNull(ArgsValidator.isValid(ARGS_COMMAND_END));

        // wenn der erste parameter nicht mit einem strich anfängt
        assertNotNull(ArgsValidator.isValid(ARGS_COMMAND_NOT_FIRST));

        // wenn kein feed parameter vorhanden ist
        assertNotNull(ArgsValidator.isValid(ARGS_NO_FEED));

        // wenn einem command noch ein command folgt
        assertNotNull(ArgsValidator.isValid(ARGS_TWO_COMMANDS));

        // alles ok
        assertNull(ArgsValidator.isValid(ARGS_ALL_OK));
    }

    @Test
    public void testGetMode() {
        assertNull(ArgsValidator.getMode(NOTHING));
        assertEquals(SearchMode.AND, ArgsValidator.getMode(AND));
        assertEquals(SearchMode.OR, ArgsValidator.getMode(OR));
    }

    @Test
    public void testGetFeeds() {
        try {
            assertEquals(new ArrayList<String>(),
                    ArgsValidator.getFeeds(NOTHING));
            assertEquals(Arrays.asList(ONE_FEED_RESULT),
                    ArgsValidator.getFeeds(ONE_FEED));
            assertEquals(Arrays.asList(TWO_FEED_RESULT),
                    ArgsValidator.getFeeds(TWO_FEEDS));
        } catch (MalformedURLException e) {
            fail(e.toString());
        }
    }

    @Test
    public void testGetKeywords() {
        assertTrue(ArgsValidator.getKeywords(NOTHING).length == 0);
        assertTrue(Arrays.equals(ONE_KEYWORD_RESULT,
                ArgsValidator.getKeywords(ONE_KEYWORD)));
        assertTrue(Arrays.equals(TWO_KEYWORD_RESULT,
                ArgsValidator.getKeywords(TWO_KEYWORD)));

    }

}
