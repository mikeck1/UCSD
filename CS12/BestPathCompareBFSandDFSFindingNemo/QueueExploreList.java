import java.util.LinkedList;

class QueueExploreList implements ExploreList {
	private LinkedList<Square> ll = new LinkedList();
	private int nelems = 0;
	private int index = 0;
	public void init( ) {
		
	}
	
		/** Add a Square to the worklist, as appropriate 
	    * @param "The Square to add"
	    */
	    public void add(Square s) {
	    	ll.add(s);
	    	nelems++;
	    }

	    /** Removes and returns the next Square to be explored 
	    * @return The next Square to explore */
	    public Square getNext() {
	    	Square s = ll.getFirst();
	    	ll.removeFirst();
	    	index++;
	    	return (Square) s;
	    }

	    /** isEmpty
	    * @return true if the worklist is empty, false otherwise
	    */
	    public boolean isEmpty() {
	    	if(nelems - index == 0) {
	    		return true;
	    	}
	    	else {
	    		return false;
	    	}
	    	
	    }
	    
	    /** size of the worklist
	    * @return The number of elements in the worklist
	    */
	    public int size() {
	    	return nelems-index;
	    }
	
}