import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;

public class SortTest {
    private static Sort s;
    private static int[] ns = {10, 20, 30, 40, 50};
    private static int[] works = {1, 2, 5};
    private static int[] offsets = {0, 200, 400, 800, 1000, 1200, 1400, 1600, 1800, 2000};
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
        s = new Sort(100, 100, 0);
        fileWriter = new FileWriter("sortHi.csv");
    }

    @Test
    public void test_strategy_1() {
        ArrayList<Entry> a = new ArrayList<Entry>();
        a.add(new Entry("bba", 2));
        a.add(new Entry("bbc", 3));
        a.add(new Entry("acc", 4));
        s.set_elements(a);
        s.sort_strategy_1();
        a = s.get_elements();
        assertEquals("acc", a.get(0).key);
        assertEquals("bba", a.get(1).key);
        assertEquals("bbc", a.get(2).key);
    }

    @Test
    public void test_strategy_2() {
        ArrayList<Entry> a = new ArrayList<Entry>();
        a.add(new Entry("bba", 2));
        a.add(new Entry("bbc", 3));
        a.add(new Entry("acc", 4));
        s.set_elements(a);
        s.sort_strategy_2();
        a = s.get_elements();
        assertEquals("acc", a.get(0).key);
        assertEquals("bba", a.get(1).key);
        assertEquals("bbc", a.get(2).key);
    }

    @Test
    public void test_strategy_3() {
        ArrayList<Entry> a = new ArrayList<Entry>();
        a.add(new Entry("bba", 2));
        a.add(new Entry("bbc", 3));
        a.add(new Entry("acc", 4));
        s.set_elements(a);
        s.sort_strategy_3();
        a = s.get_elements();
        assertEquals("acc", a.get(0).key);
        assertEquals("bba", a.get(1).key);
        assertEquals("bbc", a.get(2).key);
    }

    @Test
    public void demo() throws IOException {
        long t1, t2;
        s = new Sort(100, 100, 0);
        t1 = System.currentTimeMillis();
        s.sort_strategy_3();
        t2 = System.currentTimeMillis();
        append("sample_strategy_1_w1,100,"+(t2-t1));
       // System.out.println("Time: " + (t2-t1) + "\t Operations: "+s.get_work_counter());
    }
    @Test
    public void strat1() throws IOException {
        long t1, t2, tf=0, min=0, max=0, mean=0, sum=0;
	int wlength=works.length, nlength=ns.length, offlength=offsets.length;
	long[] t = new long[offlength];
	for(int k = 0; k < wlength; k++) {
	for(int n = 0; n < nlength; n++) {
		for(int o = 0; o < offlength; o++) {
			s =new Sort(works[k], ns[n], offsets[o]);
        		t1 = System.currentTimeMillis();
        		s.sort_strategy_1();
        		t2 = System.currentTimeMillis();
			sum = sum + t2-t1;
			t[o] = t2-t1;
			if(o==(offlength -1)) {
				Arrays.sort(t);
				min = t[0];
				max = t[offlength-1];
				mean = sum/offlength;
				append("sample_strategy_1_w1,100,"+(t2-t1)+","+works[k]+","+min+","+max+","+ns[n]+","
				+mean+","+s.get_work_counter()+"\n");
			}
		}
	}
	}
    }
    @Test
    public void strat2() throws IOException {
        long t1, t2, tf=0, min=0, max=0, mean=0, sum=0;
	int wlength=works.length, nlength=ns.length, offlength=offsets.length;
	long[] t = new long[offlength];
	for(int k = 0; k < wlength; k++) {
	for(int n = 0; n < nlength; n++) {
		for(int o = 0; o < offlength; o++) {
			s =new Sort(works[k], ns[n], offsets[o]);
        		t1 = System.currentTimeMillis();
        		s.sort_strategy_2();
        		t2 = System.currentTimeMillis();
			sum = sum + t2-t1;
			t[o] = t2-t1;
			if(o==(offlength -1)) {
				Arrays.sort(t);
				min = t[0];
				max = t[offlength-1];
				mean = sum/offlength;
				append("sample_strategy_2_w1,100,"+(t2-t1)+","+works[k]+","+min+","+max+","+ns[n]+","
				+mean+","+s.get_work_counter()+"\n");
			}
		}
	}
	}
    }
    @Test
    public void strat3() throws IOException {
        long t1, t2, tf=0, min=0, max=0, mean=0, sum=0;
	int wlength=works.length, nlength=ns.length, offlength=offsets.length;
	long[] t = new long[offlength];
	for(int k = 0; k < wlength; k++) {
	for(int n = 0; n < nlength; n++) {
		for(int o = 0; o < offlength; o++) {
			s =new Sort(works[k], ns[n], offsets[o]);
        		t1 = System.currentTimeMillis();
        		s.sort_strategy_3();
        		t2 = System.currentTimeMillis();
			sum = sum + t2-t1;
			t[o] = t2-t1;
        		System.out.println("Time: " + (t2-t1) + "\t Operations: "+s.get_work_counter());
			if(o==(offlength -1)) {
				Arrays.sort(t);
				min = t[0];
				max = t[offlength-1];
				mean = sum/offlength;
				append("sample_strategy_3_w1,100,"+(t2-t1)+","+works[k]+","+min+","+max+","+ns[n]+","
				+mean+","+s.get_work_counter()+"\n");
			}
		}
	}
	}
    }
}
