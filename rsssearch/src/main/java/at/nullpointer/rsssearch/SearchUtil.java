package at.nullpointer.rsssearch;

/**
 * Searches in a String
 * 
 * @author Thomas Pummer
 *
 */
public class SearchUtil {
    
    protected SearchUtil(){
        
    }

    /**
     * Searches in a String in a given mode for given keywords
     * 
     * @param mode
     * @param keywords
     * @param readUri
     * @return true if keywords matches in the mode, false if not
     */
    public static boolean searchFor(SearchMode mode, String[] keywords,
            String readUri) {

        int hits = 0;

        for (String keyword : keywords) {
            if (readUri.toLowerCase().contains(keyword.toLowerCase())) {
                if (mode.equals(SearchMode.OR)) {
                    System.out.print(keyword + " ");
                    return true;
                } else if (mode.equals(SearchMode.AND)) {
                    hits++;
                }

            }
        }

        if (mode.equals(SearchMode.AND) && hits == keywords.length) {
            return true;
        }

        return false;
    }

}
