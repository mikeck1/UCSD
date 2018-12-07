import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class SearchTestFormatToCSV{
    private static int SEARCH_COUNT = 10;
	//Search objects

    private static int[] ns = {10, 20, 50, 100, 200};
    private static int[] works = {10, 20, 50};
    private static int[] hashs = {10, 500};

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
	//intitialize the arrays to search through
	String[] split = new String[1000];
	String[] twentywords;
	String[] fivetywords; 
	String[] hundredwords;
	String[] twohundredwords;
	
	//inititlaize the array lists to set_elements into search with.
        ArrayList<String[]> splitLine = new ArrayList<String[]>();
        ArrayList<String[]> twentya = new ArrayList<String[]>();
        ArrayList<String[]> fivetya = new ArrayList<String[]>();
        ArrayList<String[]> hundreda = new ArrayList<String[]>();
        ArrayList<String[]> twohundreda = new ArrayList<String[]>();
	
	FileInputStream fstream = new FileInputStream("search.csv");
	BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	String s1 = "";
	String s2 = "";
	String s3 = "";
	String strLine;
	alist = new ArrayList();
	while ((strLine = br.readLine()) != null)   {
		split = (strLine.split(","));
		if (split.length >= 4) {
			s1 = s1 + "" + split[0] + "," + split[1] + "," + split[2] + "," + split[3] + "\n";
		}
		strLine = br.readLine();
		split = (strLine.split(","));
		if (split.length >= 4) {
			s2 = s2 + "" + split[0] + "," + split[1] + "," + split[2] + "," + split[3] + "\n";
		}
		if ((strLine = br.readLine()) != null)   {
		split = (strLine.split(","));
		if (split.length >= 4) {
			s3 = s3 + "" + split[0] + "," + split[1] + "," + split[2] + "," + split[3] + "\n";
		}
		}
	}
	br.close();
	fstream.close();
	
	System.out.println(s1);
	System.out.println(s2);
	System.out.println(s3);
        fileWriter = new FileWriter("l.csv", false);
	BufferedWriter bw = new BufferedWriter(fileWriter);
	PrintWriter o = new PrintWriter(bw);
	append(s1+s2+s3);
    }



    @Test
    public void test_strategy_3() {
        assertEquals(4, 4);
    }

}
