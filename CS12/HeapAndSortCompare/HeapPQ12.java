/*NAME: Michael Kaufman
 *EMAIL: mckaufma@ucsd.edu
 *PID: A15747235
 */

import java.util.ArrayList;
import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Collection;
/** HeapPQ12 class that implements an unbounded array-backed heap structure and is
 * an extension of the Java Collections AbstractQueue class 
 * The elements of the heap are ordered according to their natural 
 * ordering,  HeapPQ12 does not permit null elements. 
 * The top of this heap is the minimal or maximal element (called min/max)  
 * with respect to the specified natural ordering.  
 * If multiple elements are tied for min/max value, the top is one of 
 * those elements -- ties are broken arbitrarily. 
 * The queue retrieval operations poll and  peek 
 * access the element at the top of the heap.
 * A HeapPQ12 is unbounded, but has an internal capacity governing the size of 
 * an array used to store the elements on the queue. It is always at least as 
 * large as the queue size. As elements are added to a HeapPQ12, its capacity 
 * grows automatically. The details of the growth policy are not specified.
 * This class and its iterator implements the optional methods of the 
 * Iterator interface (including remove()). The Iterator provided in method 
 * iterator() is not guaranteed to traverse the elements of the HeapPQ12 in 
 * any particular order. 
 * Note that this implementation is not synchronized. Multiple threads 
 * should not access a HeapPQ12 instance concurrently if any of the 
 * threads modifies the HeapPQ12. 
 */
public class HeapPQ12<E extends Comparable <? super E>> extends AbstractQueue<E> {
    /* -- Define any private member variables here -- */
    private int size; //size of the heap
    private int nelems; //number of elements in the heap
    private boolean isMaxHeap; //boolean on if it is a max heap
    /* In particular, you will need an ArrayList<E> to hold the elements
     * in the heap.  You will also want many more variables */
    private ArrayList<E> heap; 

    //default at size 5 and min heap
    public HeapPQ12()
    {
	size = 5; 
	isMaxHeap = false;
	heap = new ArrayList<E>(size);
	heap.add(null); //1 based array
    }
    //set to a min/max heap with default settings
    public HeapPQ12(boolean isMaxHeap)
    {
	this.size = 5;
	this.isMaxHeap = isMaxHeap;
	this.heap = new ArrayList<E>(size);
	heap.add(null); //1 based array
    }
    //default settings plus add a capacity and if min/max heap.
    public HeapPQ12(int capacity, boolean isMaxHeap)
    {
	size = capacity;
	this.isMaxHeap = isMaxHeap;
	heap = new ArrayList<E>(size);
	heap.add(null); //1 based array
    }
    
    public HeapPQ12 (HeapPQ12<E> toCopy)
    {
        //TODO
	this.size = toCopy.size;
	this.nelems = toCopy.nelems;
	this.isMaxHeap = toCopy.isMaxHeap;
	this.heap = toCopy.heap;
    }

    /* The following are defined "stub" methods that provide degenerate
     * implementations of methods defined as abstract in parent classes.
     * These need to be coded properly for HeapPQ12 to function correctly
     */
    //get size
    public int size()
    {
        return nelems; //get the size of heap
    }
    /**Iterator
     *@return an iterator
     */
    public Iterator<E> iterator()
    {
	HeapPQ12Iterator iter = new HeapPQ12Iterator();
        return iter;  
    }

    /** Peeks at the root of the heap as in the min or max of the heap.
     *@return returns E for the type of value that is in the heap
     */
    public E peek() //peeks first element of the heap, does not remove
    {
	if(nelems != 0) { //check if not empty
		return heap.get(1); //peek the head
	}
        return (E) null; //returns null if empty
    }

    /**Gets the root of the heap.
     *@return returns E for the tyoe of value in the heap.
     */
    public E poll()
    {
	if(nelems != 0) { //check if not empty
		E o;  //copy head
		o = heap.get(1); //get head
		heap.remove(1); //remove head
		nelems--;
		return o; //return head
	}
        return (E) null;  //returns null if empty
    }

    /**Adds an element to the heap
     *@return boolean true if added to the heap.
     */
    public boolean offer (E e)
    {
	if(e == null) {throw new NullPointerException();}
	if(checkIfExpand()) {
		doubleSize();
	}
	heap.add(e);
	int nidx = nelems+1;
	heap = percUp(heap, nidx, heap.size());
	nelems++;
	//perform bubble up for min/ max heap for parent and child
        return true; //added element
    }

    /**Tells the user if the heap is empty
     *@return a boolean if the heap is empty of not.
     */
    public boolean isEmpty() {
      return size() == 0; // returns boolean if empty of not
    }

    /**
     *
     */
    public void clear() {
      while (poll() != null); //
    }

    /* ------ Private Helper Methods ----
     *  DEFINE YOUR HELPER METHODS HERE
     */

    /** Checks if heap is full and should expand
     * @return Returns boolean if should expand or not
     */
    private boolean checkIfExpand() { 
	    if(size <= nelems) { //if the heap is filled
		    return true; //return true
	    }
	    else return false; //else return false
    }
    //TODO
    /** Checks if heap is full and should expand
     * @return Returns boolean if should expand or not
     */
    private void doubleSize() { 
	size = size*2; //double the size
	ArrayList<E> temp = new ArrayList<E>(size); //copy the array
	temp.addAll(0, heap);//add all elements of old heap, starting from zero.
	heap = temp; //heap equals the new heap
    }
    /**swaps the indices in an arralist (helper function).
     *@return returns an araylist of type E.
     */
    private ArrayList<E> swap (ArrayList<E> l, int i, int j) {
	    E tmp = l.get(i); // make a temp to hold data
	    l.remove(i); //remove i
	    l.add(i, l.get(j)); //make i the new j
	    l.remove(j); //remove j
	    l.add(j, tmp); //make j the next i
	    return l; //return the heap
    }
    /**Bubbles or perculates up nodes dependent on size and if min or max heap.
     *@return returns an arralist of type E.
     */
    private ArrayList<E> percUp(ArrayList<E> a, int nodeIndex, int arraySize) {
	    int parentIndex = 0; //iniitalize parent index
	    if(isMaxHeap) { // if a max heap
	    while(nodeIndex > 1) { //while less than root
		    parentIndex = (nodeIndex)/2; //get the parent index
		    if(a.get(nodeIndex).compareTo(a.get(parentIndex)) < 0) { //if less than
			    return a; //return
		    }
		    else { //otherwise
			    a = swap(a, nodeIndex, parentIndex); //swap the parent and child
			    nodeIndex = parentIndex; //make child index the parent index
		    }
	    }
	    }
	    else{ //otherwise
	    while(nodeIndex > 1) { //while less than the root
		    parentIndex = (nodeIndex)/2; //get parent
		    if(a.get(nodeIndex).compareTo(a.get(parentIndex)) > 0) { //while greater than parent
			    return a; //we are done
		    }
		    else { //otherwsie
			    a = swap(a, nodeIndex, parentIndex); //swap child and parent
			    nodeIndex = parentIndex; //make child index equal to the parent index
		    }
	    }
	    }
	    
    return a;
    }
    /** Inner Class for an Iterator **/
    /* stub routines */

    private class HeapPQ12Iterator implements Iterator<E>
    {
	    int i; //index for the iterator
    	/**Initialize the heap iterator to start at the root.
    	 *@return void.
    	 */
        private HeapPQ12Iterator()
        {
	    int i = 1; //initialize at start of the 1 based heap.
        }

    	/**Checks to see if the heap as anything next to return.
    	 *@return Boolean on if the heap has another element in the heap.
    	 */
        public boolean hasNext()
        {
		if(this.i+1 < nelems){ //if the ith element in the heap is null
            		return true; //return true
		}
            return false; //else nothing in the heap 
        }

    	/**Returns the next value in the heap.
    	 *@return The value E that the heap has in it.
    	 */
        public E next() throws NoSuchElementException
        {
            return (E) heap.get(++this.i);  //
        }
    }
} 
