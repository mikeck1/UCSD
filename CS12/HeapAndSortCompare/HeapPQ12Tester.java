/*NAME: Michael Kaufman
 *PID: A15747235
 *EMAIL: mckaufma@ucsd.edu
 */
import static org.junit.Assert.*;
import org.junit.*;
import java.util.Iterator;

// To demonstrate min/ max heap binary tree.
public class HeapPQ12Tester
{
    String[] arr; 
    int[] arr1;
    HeapPQ12 h;
    HeapPQ12 h1;
    Iterator iter;

    @Before
    public void setUp() throws Exception
    {
      arr = new String[]{"a", "b", "c", "d", "1e", "11e", "f", "z", "a1", "a", "a123", "bb2", "2b", "A", "C", "AA", "5F", "ilovecse12", "2b", "aaaa", "bbbb", "BB", "BBB", "BBBBBB"};
      String[] expected = {"11e", "1e", "2b", "2b", "5F", "A", "AA", "BB", "BBB", "BBBBBB", "C", "a", "a", "a1", "a123", "aaaa", "b", "bb2", "bbbb", "c", "d", "f", "ilovecse12", "z"};
      h = new HeapPQ12(true); //max heap
      h1 = new HeapPQ12(false); //min heap
      arr1 = new int[]{8,2,4,3,5,9};
      iter = h.iterator();
    }
    /** Test if heads of the lists are correct */

    @Test
    public void testSize()
    {
      System.out.println("testing Size");
      //sort by natural ordering
      int k = 0;
      h.add(1);
      k = h.size();
      assertEquals(k,1);
      h.add(1);
      k = h.size();
      assertEquals(k,2);
      h.add(1);
      k = h.size();
      assertEquals(k,3);
    }
    @Test
    public void testIterator() {
    }
    @Test
    public void testPeek() {
      System.out.println("testing Peek");
        for(int s:arr1) {
            h1.offer(s);
        }
        assertEquals(h1.peek(), 2);
    }
    @Test
    public void testPoll() {
        for(int s:arr1) {
            h1.offer(s);
        }
       /* for(String s:arr1) {
            System.out.println("polling: "+h.poll());
        }  
        for(String s:arr1) {
            System.out.println("polling h1: "+h1.poll());
        } */
        assertEquals(h1.poll(), 2); //testing min heap
        assertEquals(h1.poll(), 3);
        assertEquals(h1.poll(), 4);
        assertEquals(h1.poll(), 8);
        assertEquals(h1.poll(), 5);
        assertEquals(h1.poll(), 9);
      System.out.println("testing Poll");
    }
    @Test
    public void testOffer() {
        for(int s:arr1) {
            h.offer(s);
        }
        assertEquals(h.poll(), 9); //testing max heap
        assertEquals(h.poll(), 5);
        assertEquals(h.poll(), 8);
        assertEquals(h.poll(), 2);
        assertEquals(h.poll(), 3);
      System.out.println("testing Offer");
    }
    @Test
    public void testIsEmpty() {
      System.out.println("testing IsEmpty");
    }
    @Test
    public void testClear() {
        for(String s:arr) {
            h.offer(s);
        }
        h.clear();
        assertEquals(h.size(), 0);
      System.out.println("testing Clear");
    }
    @Test
    public void testHasNext() {
        for(int  s:arr1) {
            h.offer(s);
        }
        int i = 0;
        Iterator it = h.iterator();
        while(it.hasNext()) {
            //assertEquals(iter.next(), arr1[i++]);
      System.out.println("testing Has Next and getNext"+it.next());
        }
    }
    @Test
    public void testNext() {
    }


}


// VIM: set the tabstop and shiftwidth to 4 
// vim:tw=78:ts=4:et:sw=4
