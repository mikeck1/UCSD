import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class SearchTest{
    private static int SEARCH_COUNT = 10;
	//Search objects
    private static Search s;
    private static Search tenS;
    private static Search twentyS;
    private static Search fivetyS;
    private static Search hundredS;
    private static Search twohundredS;
    private static String st;
    private static String[] words_10 = {"SMITH", "JOHNSON", "WILLIAMS", "JONES", "BROWN", "DAVIS", "MILLER", "WILSON", "MOORE", "TTT"};

    // Choose Your own words wisely
    private static String[] words_20 = {"SMITH","JOHNSON","WILLIAMS","JACKSON","WHITE","HARRIS","MARTIN","THOMPSON","GARVIA","MARTINEZ"};
    private static String[] words_50 = {"HILL","ALLEN","YOUNG","HERNANDEZ","KING","WRIGHT","LOPEZ","HILL","SCOTT","GREEN"};
    private static String[] words_100 = {"PRICE","BENNET","WOOD","BARNES","FOSTER","DIAZ","HAYES","GRIFFIN","RUSSELL","BRYANT"};
    private static String[] words_200 = {"MYERS","FORD","WOODS","BURNS","MILLS","FOX","WEAVER","CHAVEZ","AUSTIN","FRANKLIN"};

    private static int[] ns = {10, 20, 50, 100, 200};
    private static int[] works = {10, 20, 50};
    private static int[] hashs = {10, 500};
    private static String[][] words = {words_10, words_20, words_50, words_100, words_200};

    private static ArrayList<String> alist;

    private static FileWriter fileWriter;

    public static void append(String s) throws IOException {
        fileWriter.write(s);
    }

    @AfterClass
    public static void tearDown() throws IOException {
        fileWriter.close();

    }

    @BeforeClass
    public static void init() throws IOException {
	//make filewriter for output
        fileWriter = new FileWriter("search.csv");
	
	//All Work Times, All Hash Times all Types
	tenS = new Search(10, 10, 10);
	twentyS = new Search(100, 500, 20);
	fivetyS = new Search(100, 500, 50);
	hundredS = new Search(100, 500, 100);
	twohundredS = new Search(100, 500, 200);
	int counterTime = 0;
        long t1, t2;
	String wtag="", htag="",ntag="";
	//for number of elements
	for(int n = 0; n < ns.length; n++) {
		ntag = ""+ns[n];
		//for work times
		for(int w = 0; w < works.length; w++) {
			wtag = ""+works[w];
			//for hash times
			for(int h = 0; h < hashs.length; h++) {
				htag = ""+hashs[h];
				s = new Search(works[w], hashs[h], ns[n]);
				//pick what list of words to choose from
				String[] wors = {};
				if(n==10) {wors = words_10;}
				else if(n==20) {wors = words_20;}
				else if(n==50) {wors = words_50;}
				else if(n==100) {wors = words_100;}
				else {wors = words_200;}
				//for times to search a word from the list
				for(int i = 0; i < SEARCH_COUNT; i++) {
					
					//FIND MIN
					counterTime = s.get_work_counter();
					//Searching Search Strategy 1
        				t1 = System.currentTimeMillis();
        				s.search_strategy_1(wors[i]);
        				t2 = System.currentTimeMillis();
        				append("sample_strategy_1_"+ wtag +"_"+ htag +"_"+ ntag +","+(t2-t1)
					+ ","+ s.get_work_counter() +"\n");
        				System.out.println("sample_strategy_1_"+ wtag +"_"+ htag +"_"+ ntag +","+(t2-t1)
					+ ","+ s.get_work_counter() +"\n");
					
					//FIND MAX
					counterTime = s.get_work_counter();
					//Searching Search Strategy 
        				t1 = System.currentTimeMillis();
        				s.search_strategy_2(wors[i]);
        				t2 = System.currentTimeMillis();
        				append("sample_strategy_2_"+ wtag +"_"+ htag +"_"+ ntag +","+(t2-t1)
					+ ","+ s.get_work_counter() +"\n");
        				System.out.println("sample_strategy_2_"+ wtag +"_"+ htag +"_"+ ntag +","+(t2-t1)
					+ ","+ s.get_work_counter() +"\n");
					
					//FIND AVG
					counterTime = s.get_work_counter();
					//Searching Search Strategy 1
        				t1 = System.currentTimeMillis();
        				s.search_strategy_3(wors[i]);
        				t2 = System.currentTimeMillis();
        				append("sample_strategy_3_"+ wtag +"_"+ htag +"_"+ ntag +","+(t2-t1)
					+ ","+ s.get_work_counter() +"\n");
        				System.out.println("sample_strategy_3_"+ wtag +"_"+ htag +"_"+ ntag +","+(t2-t1)
					+ ","+ s.get_work_counter() +"\n");

				}
			}
		}
	}

	
	
    }

    @Test
    public void test_strategy_1() {
        ArrayList<Entry> a = new ArrayList<Entry>();
        a.add(new Entry("bac", 2));
        a.add(new Entry("bbc", 3));
        a.add(new Entry("acc", 4));
        s.set_elements(a);
        int v = s.search_strategy_1("acc");
        assertEquals(4, v);
    }

    @Test
    public void test_strategy_2() {
        ArrayList<Entry> a = new ArrayList<Entry>();
        a.add(new Entry("bac", 2));
        a.add(new Entry("bbc", 3));
        a.add(new Entry("acc", 4));
        s.set_elements(a);
        s.set_sorted_elements();
        int v = s.search_strategy_2("acc");
        assertEquals(4, v);
    }


    @Test
    public void test_strategy_3() {
        ArrayList<Entry> a = new ArrayList<Entry>();
        a.add(new Entry("bac", 2));
        a.add(new Entry("bbc", 3));
        a.add(new Entry("acc", 4));
        s.set_elements(a);
        s.set_sorted_elements();
        s.set_hash_elements();
        int v = s.search_strategy_3("acc");
        assertEquals(4, v);
    }

    @Test
    public void demo() throws IOException {
        long t1, t2;
        s = new Search(100, 500, 100);
        t1 = System.currentTimeMillis();
        s.search_strategy_1("WILSON");
        t2 = System.currentTimeMillis();
       // append("sample_strategy_1_w10_h500,100,"+(t2-t1));
        System.out.println("Demo: Time: " + (t2-t1) + "\t Operations: "+s.get_work_counter());
    }
    /*
    @Test
    public void SearchTen() throws IOException {
        long t1, t2;
	//Search for last few words of the list from data.txt
	for(int i = alist.size()-1; i > alist.size() - SEARCH_COUNT; i--) {
		st = alist.get(i);
	//Searching Search Strategy 1
        t1 = System.currentTimeMillis();
        tenS.search_strategy_1(st);
        t2 = System.currentTimeMillis();
        append("sample_strategy_1_w10_h500,100,"+(t2-t1)+ ","+ tenS.get_work_counter() +"\n");
        System.out.println("Search Ten Strat 1 -- Demo: Time: " + (t2-t1) + 
	"\t Operations: "+tenS.get_work_counter());
	//Searching Search Strategy 2
        t1 = System.currentTimeMillis();
        tenS.search_strategy_2(st);
        t2 = System.currentTimeMillis();
        append("sample_strategy_2_w10_h500,100,"+(t2-t1)+ ","+ tenS.get_work_counter() +"\n");
        System.out.println("Search Ten Strat 2 -- Demo: Time: " + (t2-t1) + 
	"\t Operations: "+tenS.get_work_counter());
	//Searching Search Strategy 3
        t1 = System.currentTimeMillis();
        tenS.search_strategy_3(st);
        t2 = System.currentTimeMillis();
        append("sample_strategy_3_w10_h500,100,"+(t2-t1)+ ","+ tenS.get_work_counter() +"\n");
        System.out.println("Search Ten Strat 3 -- Demo: Time: " + (t2-t1) + 
	"\t Operations: "+tenS.get_work_counter());
	}
    }

    public int getAvgTime(ArrayList<Integer> ops) {
	int avg = 0;
	for(Integer op : ops) {
		avg = ( avg + ( (int) op ) );
	}
	return ( avg / ops.size() );
    }
    public Integer getAvg(ArrayList<Long> l) {
	    int avg= 0;
	for(Long L : l) {
		avg = avg + (int) (long) L;
	}
	return avg/l.size();
    }
    
    @Test
    public void SearchTwenty() throws IOException {
        long t1, t2;
	//Search for last few words of the list from data.txt
	for(int i = alist.size()-1; i > alist.size() - SEARCH_COUNT; i--) {
		st = alist.get(i);
	//Searching Search Strategy 1
        t1 = System.currentTimeMillis();
        twentyS.search_strategy_1(st);
        t2 = System.currentTimeMillis();
        append("sample_strategy_1_w20_h500,100,"+(t2-t1)+ ","+ twentyS.get_work_counter() +"\n");
        System.out.println("Search Twenty Strat 1 -- Demo: Time: " + (t2-t1) + 
	"\t Operations: "+twentyS.get_work_counter());
	//Searching Search Strategy 2
        t1 = System.currentTimeMillis();
        twentyS.search_strategy_2(st);
        t2 = System.currentTimeMillis();
        append("sample_strategy_2_w20_h500,100,"+(t2-t1)+ ","+ twentyS.get_work_counter() +"\n");
        System.out.println("Search Twenty Strat 2 -- Demo: Time: " + (t2-t1) + 
	"\t Operations: "+twentyS.get_work_counter());
	//Searching Search Strategy 3
        t1 = System.currentTimeMillis();
        twentyS.search_strategy_3(st);
        t2 = System.currentTimeMillis();
        append("sample_strategy_3_w20_h500,100,"+(t2-t1)+ ","+ twentyS.get_work_counter() +"\n");
        System.out.println("Search Twenty Strat 3 -- Demo: Time: " + (t2-t1) + 
	"\t Operations: "+twentyS.get_work_counter());
	}
    }
    
    @Test
    public void SearchFivety() throws IOException {
        long t1, t2;
	//Search for last few words of the list from data.txt
	for(int i = alist.size()-1; i > alist.size() - SEARCH_COUNT; i--) {
		st = alist.get(i);
	//Searching Search Strategy 1
        t1 = System.currentTimeMillis();
        fivetyS.search_strategy_1(st);
        t2 = System.currentTimeMillis();
        append("sample_strategy_1_w50_h500,100,"+(t2-t1)+ ","+ fivetyS.get_work_counter() +"\n");
        System.out.println("Search Fivety Strat 1 -- Demo: Time: " + (t2-t1) + 
	"\t Operations: "+fivetyS.get_work_counter());
	//Searching Search Strategy 2
        t1 = System.currentTimeMillis();
        fivetyS.search_strategy_2(st);
        t2 = System.currentTimeMillis();
        append("sample_strategy_2_w50_h500,100,"+(t2-t1)+ ","+ fivetyS.get_work_counter() +"\n");
        System.out.println("Search Fivety Strat 2 -- Demo: Time: " + (t2-t1) + 
	"\t Operations: "+fivetyS.get_work_counter());
	//Searching Search Strategy 3
        t1 = System.currentTimeMillis();
        fivetyS.search_strategy_3(st);
        t2 = System.currentTimeMillis();
        append("sample_strategy_3_w50_h500,100,"+(t2-t1)+ ","+ fivetyS.get_work_counter() +"\n");
        System.out.println("Search Fivety Strat 3 -- Demo: Time: " + (t2-t1) + 
	"\t Operations: "+fivetyS.get_work_counter());
	}
    }
    @Test
    public void SearchHundred() throws IOException {
        long t1, t2;
	//Search for last few words of the list from data.txt
	for(int i = alist.size()-1; i > alist.size() - SEARCH_COUNT; i--) {
		st = alist.get(i);
	//Searching Search Strategy 1
        t1 = System.currentTimeMillis();
        hundredS.search_strategy_1(st);
        t2 = System.currentTimeMillis();
        append("sample_strategy_1_w100_h500,100,"+(t2-t1)+ ","+ hundredS.get_work_counter() +"\n");
        System.out.println("Search Hundred Strat 1 -- Demo: Time: " + (t2-t1) + 
	"\t Operations: "+hundredS.get_work_counter());
	//Searching Search Strategy 2
        t1 = System.currentTimeMillis();
        hundredS.search_strategy_2(st);
        t2 = System.currentTimeMillis();
        append("sample_strategy_2_w100_h500,100,"+(t2-t1)+ ","+ hundredS.get_work_counter() +"\n");
        System.out.println("Search Hundred Strat 2 -- Demo: Time: " + (t2-t1) + 
	"\t Operations: "+hundredS.get_work_counter());
	//Searching Search Strategy 3
        t1 = System.currentTimeMillis();
        hundredS.search_strategy_3(st);
        t2 = System.currentTimeMillis();
        append("sample_strategy_3_w100_h500,100,"+(t2-t1)+ ","+ hundredS.get_work_counter() +"\n");
        System.out.println("Search Hundred Strat 3 -- Demo: Time: " + (t2-t1) + 
	"\t Operations: "+hundredS.get_work_counter());
	}
    }
    @Test
    public void SearchTwoHundred() throws IOException {
        long t1, t2;
	//Search for last few words of the list from data.txt
	for(int i = alist.size()-1; i > alist.size() - SEARCH_COUNT; i--) {
		st = alist.get(i);
	//Searching Search Strategy 1
        t1 = System.currentTimeMillis();
        twohundredS.search_strategy_1(st);
        t2 = System.currentTimeMillis();
        append("sample_strategy_1_w200_h500,100,"+(t2-t1)+ ","+ twohundredS.get_work_counter() +"\n");
        System.out.println("Search Two Hundred Strat 1 -- Demo: Time: " + (t2-t1) + 
	"\t Operations: "+twohundredS.get_work_counter());
	//Searching Search Strategy 2
        t1 = System.currentTimeMillis();
        twohundredS.search_strategy_2(st);
        t2 = System.currentTimeMillis();
        append("sample_strategy_2_w200_h500,100,"+(t2-t1)+ ","+ twohundredS.get_work_counter() +"\n");
        System.out.println("Search Two Hundred Strat 2 -- Demo: Time: " + (t2-t1) + 
	"\t Operations: "+twohundredS.get_work_counter());
	//Searching Search Strategy 3
        t1 = System.currentTimeMillis();
        twohundredS.search_strategy_3(st);
        t2 = System.currentTimeMillis();
        append("sample_strategy_3_w200_h500,100,"+(t2-t1)+ ","+ twohundredS.get_work_counter() +"\n");
        System.out.println("Search Two Hundred Strat 3 -- Demo: Time: " + (t2-t1) + 
	"\t Operations: "+twohundredS.get_work_counter());
*/	
    
   
}
