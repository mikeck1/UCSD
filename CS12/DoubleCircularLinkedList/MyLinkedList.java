/** NAME: Michael Kaufman
 *  PID: A15747235
 *  EMAIL: mckaufma@ucsd.edu
 * 
 */
import java.util.*;
public class MyLinkedList<E> extends AbstractList<E> {
  
  private int nelems;
  private Node dummy;
  /** Node list to make a LinkedList
   * 
   */
  protected class Node {
    E data = null;
    Node next = null;
    Node prev = null;
    
    /** Constructor to create singleton Node */
    public Node(E element)
    {
		/* Add your implementation here */
        data = element;  
    }
    
    /** Constructor to create singleton link it between previous and next 
      *   @param element Element to add, can be null
      *   @param prevNode predecessor Node, can be null
      *   @param nextNode successor Node, can be null 
      */
    public Node(E element, Node prevNode, Node nextNode)
    {
		/* Add your implementation here */
    	
        this.data = element;
    	prevNode.next = this;
    	this.prev = prevNode;
    	nextNode.prev = this;
    	this.next = nextNode;
    	
    }
    /** Remove this node from the list. Update previous and next nodes */
    public void remove()
    {
		/* Add your implementation here */
    	prev.next = next;
    	next.prev = prev;
    	prev = null;
    	next = null;
    }
    /** Set the previous node in the list
      *  @param p new previous node
      */
    public void setPrev(Node p)
    {
		/* Add your implementation here */
    	this.prev.next = p;
    	p.prev = this.prev;
    	this.prev = p;
    	p.next = this;
    }
	
    /** Set the next node in the list
      *  @param n new next node
      */
    public void setNext(Node n)
    {
		/* Add your implementation here */
    	this.next.prev = n;
    	n.next = this.next;
    	this.next = n;
    	n.prev = this;
    }
    
    /** Set the element 
      *  @param e new element 
      */
    public void setElement(E e)
    {
		/* Add your implementation here */
    	this.data = e;
    }
    /** Accessor to get the next Node in the list */
    public Node getNext()
    {
//      return (Node) null; // XXX-CHANGE-XXX
    	return this.next;
    }
    /** Accessor to get the prev Node in the list */
    public Node getPrev()
    {
      return this.prev;
    } 
    /** Accessor to get the Nodes Element */
    public E getElement()
    {
//      return (E) null; // XXX-CHANGE-XXX
    	return this.data;
    } 
  }
  
  /** ListIterator implementation */ 
  protected class MyListIterator implements ListIterator<E> {
    
    private boolean forward;
    private boolean canRemove;
    private Node left,right; // Cursor sits between these two nodes
    private int idx;        // Tracks current position, what next would return
    /** My linkedlist iterator
     *  
     */
    public MyListIterator()
    {
		/* Add your implementation here */
        this.left = dummy;
        this.right = dummy.next;
        this.idx = 0;
    }
    /** Add method
     *  @param p node to append to end of linkedlist
     */
    @Override
    public void add(E e) throws NullPointerException
    {
		/* Add your implementation here */
        Node n = new Node(e);
        n.prev = this.left;
        n.next = this.right;
        this.left.next = n;
        this.right.prev = n;
        this.right = n; 
        nelems++;
    }
    /** Rturns boolean if has next
     *  @param none
     */
    @Override
    public boolean hasNext()
    {
        boolean returnVal;
        if (idx + 1 <= nelems) {
            returnVal = true;
        }
        else {
            returnVal = false;
        }
      return returnVal; // XXX-CHANGE-XXX
    }
    /** Rturns boolean if has previous
     *  @param none
     */
    @Override
    public boolean hasPrevious()
    {
      
        boolean returnVal;
        if (idx - 1 <= nelems) {
            returnVal = true;
        }
        else {
            returnVal = false;
        }
        return returnVal;
        }
    
    /** Returns next node data
     *  @param none
     */
    @Override
    public E next() throws NoSuchElementException
    {
        if (idx+1 > nelems) {
            throw new NoSuchElementException();
        }
        else {
            idx = idx + 1;

            this.left = this.right;
            this.right = this.right.next;
        }
      return (E) this.left.data;  // XXX-CHANGE-XXX
    }
    /** Returns next index
     *  @param none
     */
    @Override
    public int nextIndex()
    {   
        if(idx==nelems) {
            idx = 0;
        }
        else if(idx + 1 > nelems) {
            idx = idx + 1;
            idx = idx % nelems;
        }
        else {
            idx = idx + 1;
        }
        return idx; // XXX-CHANGE-XXX
    }
    /** Returns previous node data
     *  @param none
     */
    @Override
    public E previous() throws NoSuchElementException
    {
        if (idx -1 < 0) {
            throw new NoSuchElementException();
        }
        else {
            idx = idx - 1;
            this.right = this.left;
            this.left = this.left.prev;
        }
      return (E) this.right.data; 
    }
    /** Returns previous index
     *  @param none
     */
    @Override
    public int previousIndex()
    {
        if(idx == 0) {
            idx = -1 + nelems;
        }
        else {
            idx = idx - 1;
        }
        return idx;  // XXX-CHANGE-XXX
    }
    /** Removes what interator curr points to
     *  @param none
     */
    @Override
    public void remove() throws IllegalStateException
    {
		/* Add your implementation here */
    	this.right = this.right.next;
    	this.right.prev = this.left;
    	this.left.next = this.right;
    	nelems--;
    }
    /** set's all nodes to data in set param.
     *  @param data
     */
    @Override
    public void set(E e) 
      throws NullPointerException,IllegalStateException
    {  
        Node curr = dummy.next;
		/* Add your implementation here */
        for(int i = 0; i < nelems; i++) {
            curr.data = e;
            curr = curr.next;
        }
    }  
  }
  
  //  Implementation of the MyLinkedList Class
  
  /** Only 0-argument constructor is define */
  public MyLinkedList()
  {
    /* Add your implementation here */
	 dummy = new Node(null);
     nelems = 0;
     dummy.next = dummy;
     dummy.prev = dummy;
  }
  /** Returns size of list, or num. of elem.
   *  @param none
   */
  @Override
  public int size()
  {
		  return this.nelems; // XXX-CHANGE-XXX 
  }
  /** Returns data from index in list
   *  @param int index
   */
  @Override
  public E get(int index) throws IndexOutOfBoundsException
  {
      Node indexedToNode = new Node(null);
      if(nelems > index || nelems != 0) {
	  indexedToNode = getNth(index);
      }
      else {
        throw new IndexOutOfBoundsException();
      }
      return (E) indexedToNode.data;  // XXX-CHANGE-XXX
  }
  /** adds data ti index in list
   *  @param none
   */
  @Override
  public void add(int index, E data) throws NullPointerException
  {
	  Node indexToNode = dummy;
	  ++nelems;
//	  lastNode = getNth(index);
	  if (indexToNode.next == dummy) {
		  Node addingNode = new Node(data);
		  addingNode.next = indexToNode;
		  addingNode.prev = indexToNode;
		  indexToNode.prev = addingNode;
		  indexToNode.next = addingNode;
	  }
	  else {
		  for(int i = 0; i <= index; i++) {
                indexToNode = indexToNode.next;
            }
          Node toBeInserted = new Node(data);
          toBeInserted.prev = indexToNode.prev;
		  toBeInserted.next = indexToNode;
		  indexToNode.prev.next = toBeInserted;
          indexToNode.prev = toBeInserted;
	  }
	  /* Add your implementation here */
  }
  /** adds data
   *  @param none
   */
  @Override
  public boolean add(E data) throws NullPointerException
  {
	  Node lastNode = dummy.prev;
	  Node newNode = new Node(data);
	  
	  newNode.prev = lastNode;
	  newNode.next = dummy;
	  dummy.prev = newNode;
	  lastNode.next = newNode;
	  nelems++;
    return true; // XXX-CHANGE-XXX
  }
  /** set
   *  @param none
   */
  @Override
  public E set(int index, E data) 
    throws IndexOutOfBoundsException,NullPointerException
  {
    Node nodeToChangeData = getNth(index);
    nodeToChangeData.data = data;
    return (E) nodeToChangeData.data; // XXX-CHANGE-XXX
  }
  /** Remove
   *  @param none
   */
  @Override
  public E remove(int index) throws IndexOutOfBoundsException
  {
    Node nodeToBeRemoved = getNth(index);
    nodeToBeRemoved.prev.next = nodeToBeRemoved.next;
    nodeToBeRemoved.next.prev = nodeToBeRemoved.prev;
    --nelems;
    return (E) nodeToBeRemoved.data; // XXX-CHANGE-XXX
  }
  /** Clear
   *  @param none
   */
  @Override
  public void clear()
  {
	    /* Add your implementation here */
        Node head = dummy;
        while (nelems > 0) {
            head = head.next;
            head.prev = null;
            nelems = nelems - 1;
            }
  }
  /** Is empty
   *  @param none
   */
  @Override
  public boolean isEmpty()
  {
    if (nelems > 0) {
        return false;
    }
    else {
        return true;
    }
  }
  
  // Helper method to get the Node at the Nth index
  public Node getNth(int index) 
  {
    Node indexedToNode = dummy;
        if(index >= 0) {    
        	indexedToNode = dummy.next;
    	  if(index < 0 || index > nelems) {  
  			
            index = index % nelems + nelems;
            index = index % nelems;
        }
        // }
        for (int i = 0; i < index; i++) {
    		    indexedToNode = indexedToNode.next;
    		    if(indexedToNode.data == null) {
        			indexedToNode = indexedToNode.next;
        		}
        }
        return (Node) indexedToNode;  // XXX-CHANGE-XXX
        }
        else {
        	indexedToNode = dummy;
        	if(index < 0 || index > nelems) {  
      			
                index = index % nelems;
                index = Math.abs(index);
            }
            // }
            for (int i = 0; i < index; i++) {
        		    indexedToNode = indexedToNode.prev;
        		    if(indexedToNode.data == null) {
            			indexedToNode = indexedToNode.prev;
            		}
            }
            return (Node) indexedToNode;  // XXX-CHANGE-XXX
        }
    }

  public Iterator<E> iterator() {
    return new MyListIterator();
  }
  
  public ListIterator<E> listIterator() {
    return new MyListIterator();
  }
  
}

// VIM: set the tabstop and shiftwidth to 4 
// vim:tw=78:ts=4:et:sw=4

