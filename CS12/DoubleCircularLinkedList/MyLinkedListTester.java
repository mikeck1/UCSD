import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

/**
 *  Title: class MyLinkedListTester
 *  Description: JUnit test class for MyLinkedList class
 *  @author Philip Papadopoulos, Christine Alvarado
 * */
public class MyLinkedListTester
{
  private MyLinkedList<Integer> empty;
  private MyLinkedList<Integer> one;
  private MyLinkedList<Integer> several;
  private MyLinkedList<String>  slist;
  private MyLinkedList<Integer> testingAdd;
  static final int DIM = 5;
  static final int FIBMAX = 30;
  
  /**
   * Standard Test Fixture. An empty list, a list with one entry (0) and 
   * a list with several entries (0,1,2)
   */ 
  @Before
  public void setUp()
  {
    empty = new MyLinkedList<Integer>();
    one = new MyLinkedList<Integer>();
    one.add(0,new Integer(0));
    several = new MyLinkedList<Integer>() ;
    testingAdd = new MyLinkedList<Integer>() ;
//    testingAdd.add(0,new Integer(0));
//    testingAdd.add(1,new Integer(1));
    // List: 1,2,3,...,Dim
    for(int i = 0; i < 5; i++) {
    	testingAdd.add(i,new Integer(i));
    }
    
    for (int i = DIM; i > 0; i--){
	  several.add(0,new Integer(i));
    }// List: "First","Last"
    slist = new MyLinkedList<String>();
    slist.add(0,"First");
    slist.add(1,"Last");
    
//    for(int i = 0; i < DIM; i++) {
//      System.out.println(several.get(i));
//    }
  }
  

  
  /** Test if add to lists are correct */
  @Test
  public void testAdd()
  {
//    System.out.println("testing add");
//	  for(int i = 0; i < 5; i++) {
//		  System.out.println(testingAdd.get(i));
//	  }
//
//    System.out.println("tesitnt  several");
//	  for(int i = 0; i < 5; i++) {
//		  System.out.println(several.get(i));
//	  }
    assertEquals("Check 0",new Integer(0),testingAdd.get(0));
    assertEquals("Check 1",new Integer(1),testingAdd.get(1));
    assertEquals("Check 2",new Integer(2),testingAdd.get(2));
    assertEquals("Check 3",new Integer(3),testingAdd.get(3));
    assertEquals("Check 4",new Integer(4),testingAdd.get(4));
  }
  
  /** Test if heads of the lists are correct */
  @Test
  public void testGetHead()
  {
    assertEquals("Check 0",new Integer(0),one.get(0));
    assertEquals("Check 0",new Integer(1),several.get(0));
  }
  
  /** Test if size of lists are correct */
  @Test
  public void testListSize()
  {
    assertEquals("Check Empty Size",0,empty.size()) ;
    assertEquals("Check One Size",1,one.size()) ;
    assertEquals("Check Several Size",DIM,several.size()) ;
  }
  
  /** Test setting a specific entry */
  @Test
  public void testSet()
  {
    several.set(1, new  Integer(6));
    assertEquals("setting linkedlist value", new Integer(6), several.get(1));
    assertEquals("setting linkedlist value", new Integer(6), several.get(6));
    assertEquals("setting linkedlist value", new Integer(6), several.get(11));
   // assertEquals("setting linkedlist value", new Integer(6), several.get(-1));
    assertEquals("setting linkedlist value", new Integer(6), several.get(-4));
    assertEquals("setting linkedlist value", new Integer(6), several.get(-9));
    slist.set(1,"Final");
    assertEquals("Setting specific value", "Final",slist.get(1));
    assertEquals("Setting specific value", "Final",slist.get(3));
    assertEquals("Setting specific value", "Final",slist.get(-1));
    assertEquals("Setting specific value", "Final",slist.get(-3));
  }
  
  /** Test isEmpty */
  @Test
  public void testEmpty()
  {
    assertTrue("empty is empty",empty.isEmpty()) ;
    assertTrue("one is not empty",!one.isEmpty()) ;
    assertTrue("several is not empty",!several.isEmpty()) ;
  }

  
  /** Test iterator on empty list and several list */
  @Test
  public void testIterator()
  {
    int counter = 0 ;
    ListIterator<Integer> iter;
    for (iter = empty.listIterator() ; iter.hasNext(); )
    {
      fail("Iterating empty list and found element") ;
    }
    counter = 0 ;
    for (iter = several.listIterator() ; iter.hasNext(); iter.next())
      counter++;
    assertEquals("Iterator several count", counter, DIM);
  }
  
  @Test
  public void testGetIndexOutOfBoundsException()
  {
    try 
    {
      empty.get(0);
      fail("Should have generated an IndexOutOfBoundsException");
    }
    catch(IndexOutOfBoundsException e)
    {
      //  normal
    }
  }

  //**test remove function 
  @Test
  public void testRemoveSizeOne()
  {
    assertEquals("Checking remove one", new Integer(0), one.remove(0));
    assertTrue("List One is empty", one.isEmpty());
    assertEquals("List One is empty", 0, one.size());
  }

  //Testing the clear function works fine
  @Test
  public void testClear()
  {
    one.clear();
    several.clear();
    slist.clear();
    assertEquals(0, one.size());
    assertEquals(0, several.size());
    assertEquals(0,slist.size());
  }

  /** test Iterator Fibonacci.
    * This is a more holistic test for the iterator.  You should add
    * several unit tests that do more targeted testing of the individual
    * iterator methods.  */
  @Test
  public void testIteratorFibonacci()
  {
    
    MyLinkedList<Integer> fib  = new MyLinkedList<Integer>();
    ListIterator<Integer> iter;
    // List: 0 1 1 2 3 5 8 13 ... 
    // Build the list with integers 1 .. FIBMAX
    int t, p = 0, q = 1;
    fib.add(0,p);
    fib.add(1,q);
    for (int k = 2; k <= FIBMAX; k++)
    {
      t = p+q;
      fib.add(k,t);
      p = q; q = t; 
    }
    // Now iterate through the list to near the middle, read the
    //for(int i = 0 ; i < 1; i++) {

//	System.out.println(fib.get(i));
	//}
    // previous two entries and verify the sum.
    iter = fib.listIterator();
    int sum = 0;
    for (int j = 1; j < FIBMAX/2; j++)
      sum = iter.next();
    iter.previous();
    assertEquals(iter.previous() + iter.previous(),sum);
    // Go forward with the list iterator
   // System.out.println(iter.previous() + iter.previous());
    assertEquals(iter.next() + iter.next(),sum);
  }
  /* Add your own tests here */
  
  
  @Test
  public void testAddIterator() {
//	ListIterator<Integer> iter;
//	iter = testingAdd.listIterator();
//	iter.add(5);
//    	System.out.println("Checking iter view next");
//	for(int i = 0; i < 6; i++) {
//		System.out.println(iter.next());
//	}	
//	iter.add(7);
//	System.out.println("Checking iter view prev");
//	for(int i = 0; i < 6; i++) {
//		System.out.println(iter.previous());
//	}	
//	iter.add(5);	
//    	System.out.println("Checking iter add");
//	for(int i = 0; i < 8; i++) {
//		System.out.println(iter.next());
//	}	
  }
  
  @Test
  public void testremoveIter() {
//	  ListIterator<Integer> iter;
//	  iter = testingAdd.listIterator();
//	  System.out.println("Checking iter view next");
//	  for(int i = 0; i < 5; i++) {
//		  System.out.println(iter.next());
//	  }
//	  iter.remove();
//	  System.out.println("Checking iter view prev");
//	  for(int i = 0; i < 5; i++) {
//		  System.out.println(iter.previous());
//	  }
//	  System.out.println("Checking iter view remove");
//	  iter.remove();
//	  for(int i = 0; i < 3;i++) {
//		  System.out.println(iter.next());
//	  }
//	  System.out.println("done");
	  

  }
  
  @Test
  public void testRemoveList() {  

  }
  
  @Test
  public void testAddList() {  
//	  System.out.println("Checking reverse");
//	  for(int i =4; i >=-2 ; i--) {
//		  System.out.println(testingAdd.get(i));
//	  }
//	  System.out.println("Checking get");
//	  for(int i =0; i < 5; i++) {
//		  System.out.println(testingAdd.get(i));
//	  }
//	  System.out.println("Checking add");
////	  testingAdd.add(new Integer(5));
////	  testingAdd.add(new Integer(6));
//	  System.out.println("Checking forward");
//	  for(int i =0; i <12 ; i++) {
//		  System.out.println(testingAdd.get(i));
//	  }
	  System.out.println("Checking reverse from -12");
	  for(int i =9; i >=1; i--) {
		  System.out.println(testingAdd.get(i));
	  }
	  
	  System.out.println("Checking reverse from -13");
	  for(int i =8 ; i >=1 ; i--) {
		  System.out.println(testingAdd.get(i));
	  }
	  System.out.println("Checking reverse from -13");
	  for(int i =0 ; i <=15; i++) {
		  System.out.println(testingAdd.get(i));
	  }
  }
  
  
  @Test
  public void testSetIterator() {
//	ListIterator<Integer> iter;
//	iter = testingAdd.listIterator();
//	iter.add(5);
//    	System.out.println("Checking iter view next");
//	for(int i = 0; i < 6; i++) {
//		System.out.println(iter.next());
//	}	
//	iter.add(7);
//	System.out.println("Checking iter view prev");
//	for(int i = 0; i < 6; i++) {
//		System.out.println(iter.previous());
//	}	
//	iter.set(5);	
//    	System.out.println("Checking iter set");
//	for(int i = 0; i < 7; i++) {
//		System.out.println(iter.next());
//	}	
  }
  
//  @Test
//  public void testClearIter() {
//	ListIterator<Integer> iter;
//	iter = testingAdd.listIterator();
//	testingAdd.clear();
//    	System.out.println("Checking iter view next");
//	for(int i = 0; i < 0; i++) {
//		System.out.println(iter.next());
//	}	
//	iter.add(7);
//	System.out.println("Checking iter view prev");
//	for(int i = 0; i < 1; i++) {
//		System.out.println(iter.next());
//	}	
//	iter.set(5);	
//    	System.out.println("Checking iter set");
//	for(int i = 0; i < 1; i++) {
//		System.out.println(iter.previous());
//	}	
//  }
  
}
