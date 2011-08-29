package at.nullpointer.rsssearch;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArgsValidator {

	private static final String OR = "-o";
	private static final String AND = "-a";
	
	private static final String HOW_TO_USE = "Usage: java -jar RssSearch.jar -o|-a <keyword1> <keyword2> <...> -f <feed1> <feed2> <....>\n-o ... or  - if one keyword is found in the source it opens the source in the browser\n-a ... all - if all of the keywords are found in the source it opens in the browser";
	private static final String COMMAND = "-";
	private static final String FEED = "-f";

	public static String isValid(String[] args) {
		boolean valid = true;
		
		// Wenn nicht genug Parameter angegeben wurden
		if(valid && (args.length < 4)){
			valid = valid && false;
		}
		
		// Wenn der and oder der or Parameter nicht vorkommen
		if(valid && (!Arrays.asList(args).contains(OR) && !Arrays.asList(args).contains(AND))){
			valid = valid && false;
		}
		
		// Wenn der and, or oder der feed parameter mehr als einmal vorkommen
		List<String> multiTest = new ArrayList<String>(Arrays.asList(args));
		if(multiTest.contains(OR)) multiTest.remove(OR);
		if(multiTest.contains(AND)) multiTest.remove(AND);
		if(multiTest.contains(FEED)) multiTest.remove(FEED);
		if(valid && (multiTest.contains(OR) || multiTest.contains(OR) || multiTest.contains(FEED))){
			valid = valid && false;
		}
		
		// Wenn and und or parameter vorkommen vorkommen
		if(valid && (Arrays.asList(args).contains(OR) && Arrays.asList(args).contains(AND))){
			valid = valid && false;
		}
			
		// wenn ein command parameter am ende steht
		if(valid && (args[args.length-1].startsWith(COMMAND))){
			valid = valid && false;
		}
			
		// wenn der erste parameter nicht mit einem strich anfängt
		if(valid && (!args[0].startsWith(COMMAND))){
			valid = valid && false;
		}
			
		// wenn kein feed parameter vorhanden ist
		if(valid && (!Arrays.asList(args).contains(FEED))){
			valid = valid && false;
		}
			
		// wenn einem command noch ein command folgt
		for(int i = 0; i+1 < args.length; i++){
			if(args[i].startsWith(COMMAND) && args[i+1].startsWith(COMMAND)){
				valid = valid && false;
			}
		}
			
		if(!valid){
			return HOW_TO_USE;
		} else {
			return null;
		}
	}

	public static SearchMode getMode(String[] args) {
		List<String> argList = Arrays.asList(args);
		if(argList.contains(OR)){
			return SearchMode.OR;
		} else if(argList.contains(AND)){
			return SearchMode.AND;
		} else{
			return null;
		}
	}

	public static List<String> getFeeds(String[] args) throws MalformedURLException {
		boolean feedparam = false;
		List<String> result = new ArrayList<String>();
		
		for(int i = 0; i < args.length; i++){
			if(args[i].equals(FEED)){
				feedparam = true;
			} else if(feedparam) {
				if(!args[i].startsWith(COMMAND)){
					result.add(args[i]);
				} else {
					feedparam = false;
				}
			}
		}
		return result;
	}

	public static String[] getKeywords(String[] args) {
		boolean keyparam = false;
		List<String> result = new ArrayList<String>();
		
		for(int i = 0; i < args.length; i++){
			if(args[i].equals(OR) || args[i].equals(AND)){
				keyparam = true;
			} else if(keyparam) {
				if(!args[i].startsWith(COMMAND)){
					result.add(args[i]);
				} else {
					keyparam = false;
				}
			}			
		}
		return toStringArray(result);
	}

	private static String[] toStringArray(List<String> list) {
		String[] result = new String[list.size()];
		int i = 0;
		for(String keyword : list){
			result[i] = keyword;
			i++;
		}
		return result;
	}

}