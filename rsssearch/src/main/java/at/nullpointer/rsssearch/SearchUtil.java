package at.nullpointer.rsssearch;

public class SearchUtil {

	public static boolean searchFor(SearchMode mode, String[] keywords,
			String readUri) {
		
		int hits = 0;
		
		for(String keyword : keywords){
			if(readUri.toLowerCase().contains(keyword.toLowerCase())){
				if(mode.equals(SearchMode.OR)){
					System.out.print(keyword+" ");
					return true;
				} else if(mode.equals(SearchMode.AND)){
					hits++;
				}
				
			}
		}
		
		if(mode.equals(SearchMode.AND) && hits == keywords.length){
			return true;
		} 
		
		return false;
	}

}
