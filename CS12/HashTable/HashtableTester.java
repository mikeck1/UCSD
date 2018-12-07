import org.junit.*;
import static org.junit.Assert.*;
public class HashtableTester {
	private HashTable testHashTable1;
	@Before
	public void setUp()
	{
		testHashTable1 = new HashTable(10);
	}
/*	
	@Test
	public void testInsert()
	{
		assertEquals("checking insert",true,testHashTable1.insert("abc"));
		assertEquals("Checking contains after insert",true,testHashTable1.contains("abc"));
	}
	@Test
	public void testDelete()
	{
     		testHashTable1.insert("abc");
		assertEquals("Checking delete",true,testHashTable1.delete("abc"));
		assertEquals("Checking contains after delete",false,testHashTable1.contains("abc"));
	}
	@Test
	public void testGetSize()
	{
		testHashTable1.insert("abc");
		testHashTable1.insert("pqr");
		testHashTable1.insert("xyz");
		assertEquals("Checking getSize",new Integer(3),new Integer(testHashTable1.getSize()));
	}
*/		
	@Test	
	public void testInsertMultiple()
	{
		assertEquals("checking insert",true,testHashTable1.insert("abc"));
		assertEquals("Checking contains after insert",true,testHashTable1.contains("abc"));
		assertEquals("checking insert",true,testHashTable1.insert("def"));
		assertEquals("Checking contains after insert",true,testHashTable1.contains("def"));
		assertEquals("Checking contains after insert",true,testHashTable1.contains("def"));
		assertEquals("Checking contains after insert",true,testHashTable1.insert("Whats up? How're you??"));
		assertEquals("Checking contains after insert",true,testHashTable1.insert("lol"));
		assertEquals("Checking contains after insert",true,testHashTable1.insert("djkl yeah okay??f"));
		assertEquals("Checking contains after insert",true,testHashTable1.insert("defdjdjdjdjd pppppp qdff"));
		assertEquals("Checking contains after insert",true,testHashTable1.insert("dkfkfkkf kkef"));
		assertEquals("Checking contains after insert",true,testHashTable1.insert("dedfsdf"));
		assertEquals("Checking contains after insert",true,testHashTable1.insert("lohehehehehl"));
		assertEquals("Checking contains after insert",true,testHashTable1.insert("djkljkljklf"));
		assertEquals("Checking contains after insert",true,testHashTable1.insert("defdff"));
		assertEquals("Checking contains after insert",false,testHashTable1.insert("def"));
		String fileName = "dicts/long.dict.txt";
		testHashTable1.insertFromFile(fileName);
		testHashTable1.printTable();
	}
	@Test	
	public void testPrint()
	{
		//assertEquals("checking insert",true,testHashTable1.insert("abc"));
	//	testHashTable1.printTable();
	}
}
