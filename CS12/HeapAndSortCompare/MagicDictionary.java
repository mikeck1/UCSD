/*NAME: Mike Kaufman
 *PID:A15747235
 *EMAIL:mckaufma@ucsd.edu
 */
import java.util.*;

public class MagicDictionary {

  String[] magicWords;//string array of all words to be sorted
  
  /**initializes the words to be sorted
   */
  public MagicDictionary (String[] words) {
    magicWords = words;
  }

  /**Copies a string array to magicwords
   *@return string array
   */
  public String[] copy() {
    int len = magicWords.length; //get length
    String[] x = new String[len];
    for (int i = 0; i < len; i++) { //for all magic words
      x[i] = magicWords[i]; //add to x
    }
	return x; //return x
  }

  /**Sort Strat #1
   *@return natural order
   */
  public String[] sort1() {
    String[] tmp = copy();
    Arrays.sort(tmp, new Comparator<String>() {
      public int compare(String a, String b) {
        return a.compareTo(b);
      }
    });
    return tmp;
  }

  /**Sort strat #2
   *@return length and natural order
   */
  public String[] sort2() {
    //TODO
    String[] x = copy(); //get a copy
    int n = x.length; //get length
    x = nat(x); //get natural order
	for (int i = 0; i < n-1; i++) { //begin to sort by length
		for (int j = 0; j < n-i-1; j++){  //essentially bubble sort by length size
			if (x[j].length() <  x[j+1].length()) {
				String temp = x[j];
				x[j] = x[j+1];
				x[j+1] = temp; 
			}
		}}
    	return x; //return sorted array
  }

  /**sort strat #3
   *@return length and natural order
   */
  public String[] sort3() {
    //TODO
   	String[] x = copy(); //get magic numbers
    	int len = x.length; //get length
	Map<String, Integer> m = map(x); //get map of word freq
    	Arrays.sort(x, new Comparator<String>() { //sort by word freq
      	public int compare(String a, String b) { //compare a to b
		if(m.get(a) > m.get(b)) { //if a has mor word freq
			return -1; //a go first
		}
		if(m.get(a) < m.get(b)) { return 1; } //if b has more qword freq then b go first
        return a.compareTo(b); //return natural order
      	}
    	});
    return x;
  }

  /**sort strat #4
   *@return how many capital letters then nat order
   */
  public String[] sort4() {
    //TODO
   	String[] x = copy(); //get all magic numbers
	Map<String, Integer> m = mapCapitalLetters(x); //map how many capital letters
    	Arrays.sort(x, new Comparator<String>() { //sort by 
      	public int compare(String a, String b) {
		if(m.get(a) > m.get(b)) { //how many capital letters
			return -1; //makea go in front
		}
		if(m.get(a) < m.get(b)) { return 1; } //make b go in front
        return a.compareTo(b); // and natural order
      	}
    	});
    return x; //XX_CHANGE_XX
  }

  /**sort strat #5
   *@return if a palindrone, size of word and then natural order
   */
  public String[] sort5() {
   	String[] x = copy();
	Map<String, Boolean> m = mapPalin(x);
	
    	Arrays.sort(x, new Comparator<String>() {
      	public int compare(String a, String b) {
		if(m.get(a) && m.get(b)){ //if they are both palindrones
			if(a.length() > b.length()) { //if a is greater
				return -1; //a go in front
			}
			else if(a.length() < b.length()) { //if b if bigger
				return 1; //b go in front
			}
			else { //otherwise just nat order
				return a.compareTo(b); //and natural order
			}
		}
		else if(m.get(a)) { //if a a palindome only a go in front
			return -1;}
		else if(m.get(b)) {
			return 1;} //if ba palindrome only b go in front
        	return a.compareTo(b); //nat order
      		}
    	});
    return x; //return the sorted words
  }

  // HELPER FUNCTIONS
  private boolean rightMoreThanLeft(String s1, String s2) {
	if(s1.length() >= s2.length()) {return true;}
	return false;
  }
  /**reverses the order of an array
   *@return reversed array of strings
   */
  static String[]  reverse(String[] a) { 
	int len = a.length; //get length
    	String[] x = new String[len]; //get a string array
	int j = len;
	for(int i = 0; i < len; i++) { //copy in reverse
		x[i] = a[--j];
	}
	return x; //return reveresed array
  }
  /**gets natural order
   *@return natural order of an array of strings
   */
  static String[] order(String[] a) { //get natural order
    	String[] x = new String[a.length]; //init string array
	int j = a.length; //get size
	for(int i = 0; i < a.length; i++) {
		x[i] = a[--j]; //order in reverse
	}
	return x; //return the array
  }
  /**swaps one element i with anothe element j.
   *@return the swapped array
   */
  static void swap(String[] a, int i, int j) {
      String t = a[i]; //swap temp
      a[i] = a[j]; //swap j into i
      a[j] = t; //swap old i into j
   }
  /**get string value of an array
   *@return integer of string value
   */
   static int stringValue(String s) {
	   int total = 0;
	for (char c : s.toCharArray()) {//for char in array
		total += (int) c; //get the total
	}
	return total; //return the total
   }
  /**maps an array to determine  how many duplicates
   *@return map with count for duplicates in array
   */
   static Map<String, Integer> map(String[] arr){
   Map<String, Integer> map = new HashMap<>(); //make a map
       for (String w : arr) { //for el in array
	               Integer n = map.get(w); //enter the ele into the map
		               n = (n == null) ? 1 : ++n;
			               map.put(w, n); //get word freq in a  map
				           }
	return map;
}
  /**maps how many capital letters are in an array of strings
   *@return map of capital letters.
   */
   static Map<String, Integer> mapCapitalLetters(String[] arr){
	int count = 0;
   	Map<String, Integer> map = new HashMap<>(); //make a map
       	for (String w : arr) { //for el in arr
	       count = 0;
	       for(char c : w.toCharArray()) { //map capital letters
			if(Character.isUpperCase(c)) { //if an uppercase
				count++; //count increase
			}
	       }
	       map.put(w, count); //add count to the map
	}
	return map;
}
/**if a palindrom or not for array of strings
 *@return boolean if plalindrome of not
 */
static boolean isPalin(String s) {
    for (int i = 0; i < s.length()/2; ++ i) //if the same on both sides
        if (s.charAt(i) != s.charAt(s.length()-1-i)) //if not the same
            return false; //return false
    return true;//must be a palindrome
}
/**maps if a palindrom or not for array of strings
 *@return map if plalindrome of not
 */
   static Map<String, Boolean> mapPalin(String[] arr){
   	Map<String, Boolean> map = new HashMap<>(); //make a map
       	for (String w : arr) { //for el in arr
		if(isPalin(w)) {
	       		map.put(w, true); //put a true if a palindrome
		}
		else {map.put(w, false);} //otherwise put a false
	}
	return map;
}
/**gets natural order, sorts and returns sorted array
 *@return sorted array
 */
   static String[] nat(String[] arr) { 
	int n = arr.length; //init length
    Arrays.sort(arr, new Comparator<String>() { //sort by 
      public int compare(String a, String b) {
        return a.compareTo(b); //natural order
      }
    });
		return arr;	//then return sorted array	 	                                                                   }

}
}
