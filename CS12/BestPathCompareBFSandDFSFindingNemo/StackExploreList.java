import java.util.LinkedList;

class StackExploreList implements ExploreList {
	private int nelems;
	//holds squares from the map
	private LinkedList<Square> ll = new LinkedList();
	//iitialize the stack
	public void init() {
		nelems = 0;
	}
	/** Add a Square to the worklist, as appropriate 
	    * @param "The Square to add"
	    */
	    public void add(Square s) {
	    	ll.addLast(s);
	    	nelems++;
	    }

	    /** Removes and returns the next Square to be explored 
	    * @return The next Square to explore */
	    public Square getNext() {
	    	Square s = ll.getLast();
	    	ll.removeLast();
	    	nelems--;
	    	return (Square) s;
	    }

	    /** isEmpty
	    * @return true if the worklist is empty, false otherwise
	    */
	    public boolean isEmpty() {
	    	if(nelems == 0) {
	    		return true;
	    	}
	    	return false;
	    }
	    
	    /** size of the worklist
	    * @return The number of elements in the worklist
	    */
	    public int size() {
	    	return nelems;
	    }
}