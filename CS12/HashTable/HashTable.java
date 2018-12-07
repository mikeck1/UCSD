/* EMAIL: mckaufma@ucsd.edu
 * USER: cs15xqc
 * PID: A15747235
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.File;
import java.util.Scanner;
import java.lang.Math;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
/**
 * HashTable implements the IHashTable class and is used by the TritonBlockChain class.
 * @see Holds: nelems, expansions, collisions in table, maxCollisons, the file name of output 'out.txt', boolean
 * 	on if to print stats, the table, length of the hashtable, loadfactor i.e. number of lists in the table also
 * 	a boolean HAS_RAN which is used to create a new stats file (if one already exists) i.e. delete old stats
 * 	and append data after that.
 * 	@author Michael Kaufman
 */
public class HashTable implements IHashTable {
	
	//HashTable of LinkedLists. 
	
	private int nelems;  //Number of element stored in the hash table
	private int expand;  //Number of times that the table has been expanded
	private int collision;  //Number of collisions since last expansion
	private int maxCollisions; // count of the collisions or lists that have more than 1 element and are using chains
	private String statsFileName = "out.txt";     //FilePath for the file to write statistics upon every rehash
	private boolean printStats = false;   //Boolean to decide whether to write statistics to file or not after rehashing
	private LinkedList<String>[] table; // table used for the hash table
	private int length; //length of the array
	private int loadedBuckets; // buckets or indices that have non-null lists
	private double loadfactor = 0.0;
	private boolean HAS_RAN = false;
	//You are allowed to add more :)
	
	/**
	 * Constructor for hash table
	 * @param Initial size of the hash table
	 */
	public HashTable(int size) {
		this.nelems = 0;
		this.expand = 0;
		this.length = size;
		this.collision = 0;
		this.table = new LinkedList[size];
		printStats = false;
	}
	
	/**
	 * Constructor for hash table
	 * @param Initial size of the hash table
	 * @param File path to write statistics
	 */
	public HashTable(int size, String fileName){
		//Set printStats to true and statsFileName to fileName
		this.nelems = size;
		this.expand = 0;
		this.collision = 0;
		this.table = new LinkedList[size];
		this.statsFileName = fileName;
		this.printStats = true;
	}
	/** inserts an element in the hashtable
 	 *  @param  a string that will be inserted into the hashtable
	 */ 
	@Override
	public boolean insert(String value) {
		int i = hashValue(value); // index fro the string hash
		if(!contains(value)) { //if element in the hashtable return false and do not insert
			if(rehash()) { // if size is too big for the hashtable, resize
				i = hashValue(value); //index of the element after resizing
			}
			LinkedList<String> list = table[i]; //get the list from the table
			if(list == null) { // if list is null, create the new list
				initializeList(value, i); //creates a new list
				loadedBuckets++; // increase load factor, as new list created for an index
				//increase count of elements added to hashtable is in initializeList()
			}
			else {
				collision++;
				list.addLast(value); // add element to an existing list
				nelems = nelems + 1; //increase count of elements added to hashtable
			}
			return (boolean) true; //if added element return true
		}
		return (boolean) false; //didnot add because it already existed
	}

	
	/**Insert string from a file.
	 *@param String to be hashes.
	 */
	public void insertFromFile(String fileName) {
		Scanner s = null; //iitialize scanner
		try {
			File f	= new File(fileName); //create a new file
			s = new Scanner(f); //create the scanner for the file
			//FileReader r = new FileReader(f);
			//BufferedReader b = new BufferedReader(r);
			String line; //initialize the string to hold a line
			while(s.hasNext()) { //if keep going
				this.insert(s.next()); //get the next line
			}
			s.close(); //close the file
		}
		 catch(FileNotFoundException e) {
			
		}
		finally {
			s.close();
		}	
	}
	
	/**delete is used to delete a specific string in the hashtable
 	 *@param String value that ia added to the hashtable
	 *@return boolean true if deleted, false if did not exist in the hashtable
 	 */
	@Override
	public boolean delete(String value) {
		int i = hashValue(value); //get index fro hashvalue of string
		LinkedList<String> list = table[i]; //get the list
		if(list.remove(value)) { //delete list
			return true; // and return true
		}
		return (boolean) false; // list.remove returned false so it was not alrready in the list
	}

	/**If the hashtable contains the searched value return true, else false
 	 * @return boolean if it exists in the hashtable or not.
 	 */
	@Override
	public boolean contains(String value) {
		int i = hashValue(value); // get index of the value to be hashed
		LinkedList<String> list = table[i]; //get the list, not
		if(list != null) { //if the list is null
			for(String s : list) {
				if(s.equals(value)) {return true;} //if we find the value then return true
			}
		}
		return (boolean) false;
	}
	/**Prints the table.
	 *@param String to be hashes.
	 */
	@Override
	public void printTable() {
		int i = 0;
		System.out.println();
		for (LinkedList<String> list : table) { //go through table
			System.out.print(i+": "); //print the constant string and index
			if(list!=null) {printString(list);} //check if string not null
			else {System.out.println();} //print the line
			i++; //index increments
		}
	}
	/**printString fiunction.
	 *@param Linkedlist of strings..
	 */
	public void printString(LinkedList<String> list) {
		int c = 0;
		for(String s : list) {	
			//if not the first sentence, put a comma behind the sentence
			if(c != 0) {System.out.print(", "+s);}
			//print a sentence
			else {System.out.print(s);}
			c++;
		}
		System.out.println();
	}
	
	/**getSize gets the size of the number of elements.
	 *@param String to be hashes.
	 */
	@Override
	public int getSize() {
		return nelems;
	}	

	//Helper methods can make your code more efficient and look neater.
	//We expect AT LEAST the follwoing helper methods in your code
	//rehash() to expand and rehash the items into the table when load factor goes over the threshold.
	//printStatistics() to print the statistics after each expansion. This method will be called from insert/rehash only if printStats=true

	/**Calculate load factor. Changes size of array if too big.
 	 *@param None as if checks the size of the array list and resises the private table variable.
	 *@returns Return a boolean if the size of the table needs to be resized.
 	 */ 	 	
	private boolean rehash() {
		// checks size of array being at 
	//	Double l = 0.67 * new Double(length); //get size that hashtable should resize at
		loadfactor = new Double(loadedBuckets) / new Double(length);
		if(0.67 < loadfactor) { //get the amount of lists in the array
			this.length = length*2; //resize the list to twice the size
			loadedBuckets = 0;
			collision = 0;
			expand++; //increase count of expansions for logfile
			LinkedList<String>[] tempTable = new LinkedList[length]; //create a temp table to hold current list, before resizing the list
			for(LinkedList<String> list : table) { //go through the table
				if(list != null){ //if the list is not null
				for(String s : list) { //for string in the list 
					int h = hashValue(s); //grab hash value
					if (tempTable[h] == null) { //if the temp hash table has not added to this index already
						LinkedList<String> tempList = new LinkedList(); //create a new list and add element
						tempList.add(s); //add the element
						loadedBuckets++;
						tempTable[h] = tempList;
					}
					else {
						tempTable[h].addLast(s); //append string to the existing list
						collision++;
					}
				}
				}
			}
			this.table = tempTable;	//replace table with the new resized table
			printTable();
			if (printStats) { //if the boolean for printing logs is true
			try {
				writeToFile(); //print logs to file
			}
			catch(IOException e) {}
			}
			return true;
		}
		return false;
	}
	/**Initializes the list when empty
 	 *@param value to be added to the list as well as the hash indice of the list in the hashtable.
 	 */ 	 	
	private void initializeList(String value, int i) {
		LinkedList<String> list = new LinkedList<String>(); //create a new list
		list.add(value); //add the value
		table[i] = list; //add to the table
		nelems = nelems + 1; //increase elements
	}
	/**Hash function.
	 *@param String to be hashes.
	 */
	private int hashValue(String s) { // hashes a string
		int h = 0; // initialized the hash
		int x = 524287; //prime number
		char c = 'a';
		for(int i = 0; i < s.length(); i++) { // go through each char
			//int e = (int) Math.pow(i,2);
			c = s.charAt(i);
			h = h + c*x; //
		} 
		h = h % length ; // make sure we are inside the range of the table.
		return h; // retrun the hash
	}
	
	/*Writes stats to file. This is used if the boolean variable printStats is true.*/
	private void writeToFile() throws IOException {
		String s = ""; //inititalize string	
		if(!HAS_RAN) { //if this is the first time the progrm has ran
		File f = new File(statsFileName); //delete the file if it already exists
		if(f.exists()){ 
			f.delete();
			HAS_RAN = true; //set to true, that this has already ran and checked for a new clean log
			try {
				f.createNewFile(); //create the new file
			} catch (IOException e) { 
				e.printStackTrace();
				}
			}
		}	
		try(FileWriter fw = new FileWriter(statsFileName, true); //initz filewriter to the statsfilename
    		BufferedWriter bw = new BufferedWriter(fw); //make a buffered writer 
    		PrintWriter out = new PrintWriter(bw)) //intiz priterwriter to append data
	{
    		out.println(writeStats()); //append the data to the file
        	out.println(); //new line
	}catch(IOException e) {
		e.printStackTrace();
	}
	}

	/**Writes a string of stats and is used to write load percentage, collisions and max number of collisions.
 	 *@return String of all the data written to the stats file.
   	 */
	private String writeStats() {
		String s = ""; //initiz string the return
		findMostCollisions(); //find amount of collisions
		s =  expand + " resizes, ";
		s = s + String.format("%.2f", loadfactor)  +  " load factor percentage, ";
	//	s = s + loadedBuckets + " lists, ";  //bonus stats for the filelogger if req'd
	//	s = s + nelems + " nelems, "; //bonus
		s = s +  collision + " collisions, ";
		s = s +  maxCollisions + " longest chain, ";
	//	s = s +  length + " length of array."; //bonus
		return s; //return stats
	}
	/*Runs through the list and finds the max number of collisions for a particular list.*/
	private void findMostCollisions() {
		int count = 0;
		for (LinkedList<String> list : table) { //goes through the table
			count = 0;
			if(list != null) { //if list is not null
				for(String s : list) {
					count++; //cout how many collisions occured
				}
				if(count > maxCollisions) {maxCollisions = count;} //find the max amount of collisions
			}
		}
	}
}
