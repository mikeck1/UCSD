import java.util.LinkedList;
import java.util.Stack;
import java.util.ArrayList;
public class Explorer {

    // The exploreList to hold the search as it proceeds.
    //  private ExploreList exploreList;
    // The Sea to solve.
    private ExploreList exploreList;
    private LinkedList<Square> als = new LinkedList();
    private Stack<Square> stack = new Stack();
    private Sea sea = new Sea();
    // The path to the Nemo, if any
    private String path="Found Nemo!";
    private boolean gameOver = false; // game over, exit has(n't) been found
    private boolean foundNemo = false; // exit has been found
    // gets the total visited
    public int getTotalVisited(){
        return  this.getSea().countVisited();
    }

    /**
     * 
     * sets game over
     * @param none
     * @retuns  void
     */ 
    public void setGameOver() {
        gameOver = true;
    }

    
    /**
     * set found nemo
     *
     * @param none
     * @retuns  void
     */ 
    public void setFoundNemo() {
        foundNemo = true;
    }

    
    /**
     * game over
     *
     * @param none
     * @retuns boolean is the game is over or if found nemo
     */ 
    public boolean gameOver() {
        return gameOver || foundNemo;
    }

    
    /**
     * 
     *
     * @param 
     * @retuns 
     */ 
    public boolean isFoundNemo() {
        return foundNemo;
    }

   
    /**
     * makes the explore empy
     *
     * @param nonw
     * @retuns void
     */ 
    public void makeEmpty() {
        // remove Squares until empty
        while (!exploreList.isEmpty()) {
            exploreList.getNext();
        }
    }

    /**
     * the something from the explore
     *]
     * @param none 
     * @retuns returns a explorer list for searching the sea
     */ 
    public ExploreList getExploreList() {
        return this.exploreList;
    }

    /** isEmpty
    * @return true if the exploreList is empty, false otherwise
    */
    public boolean isEmpty() {
        return this.exploreList.isEmpty();
    }

    /** size of the exploreList
    * @return The number of elements in the exploreList
    */
    public int size() {
        return this.exploreList.size();
    }

    /** Make a new Solver with a given Sea and Worklist
    * @param theSea The Sea to solve
    * @param theExploreList The exploreList to use
    */
    Explorer(Sea theSea, ExploreList theExploreList){
        this.sea = theSea;
        this.exploreList = theExploreList;
    }

    /** 
    * Get the Sea object
    * @return the sea
    */
    public Sea getSea() {
        return this.sea;
    }
    
    /**
     * Solve the sea, if possible.
     * If a solution is found, set the path variable and the 
     * foundNemo variable appropriately.
     */
    public void solve() {
        // TODO
        // This function should use next.  You should also create and use any
        // other helper fuctions you find helpful here.
    	// get start position  	
    	Square s = sea.getStart();
    	exploreList.add(s);
    	while(this.exploreList.size() != 0) {
    		Square c = step();
    		if(c.hasNemo()) {
    			setPath(c);
    			foundNemo = true;
    			break;
    		}
    	}
    }

    /** Take the next step toward the goal
    * PRECONDITION: The exploreList is not empty
    * @return The next Square that has just been visited.
    */
    public Square step() {
        // TODO
    	Square c = exploreList.getNext();
    	Square realSquare = sea.getSea()[c.getRow()][c.getCol()];
        // check for if we are visited, if so return to skip processing current square
    	if(realSquare.isVisited()) {
    		return c;
    		
    	}
    	realSquare.setVisited();
    	for(Square neighs : sea.getAdjacentArea(realSquare)) {
    		if(neighs.getPrevious() ==null) {
    
    		neighs.setPrevious(c);
    		exploreList.add(neighs);
    		}
		else {
		if(!neighs.isVisited()) {
    			Square n = new Square(neighs.getRow(), neighs.getCol(), neighs.getType());
    			n.setPrevious(c);
    			exploreList.add(n);
}
		}
    	}
    	return c;

    }



    // Set the squares in the path appropriately and set the path
    // from start to finish.
    public void setPath(Square finish) {
        // TODO
    	path = "Found Nemo!\n" + "Path from start to finish: ";
    	String extnPAth = "";
    	while(finish != null) {
    	Square realSquare = sea.getSea()[finish.getRow()][finish.getCol()];
    		realSquare.setFinalPath();
    		//Add to stack
    		stack.push(finish);
    		finish = finish.getPrevious();
    	}
    	
    	//get start and add start
    	while(!stack.empty()) {
    		Square c = stack.pop();
    		extnPAth = extnPAth + "[" + c.getRow() + "," + c.getCol() + "] "; 
    	}
		path = path + extnPAth;
    }
    
    /**
     * Get the number of elements that are left on the exploreList
     * @return The size of the exploreList
     */
    public int getExploreListSize() {
        return exploreList.size();
    }
    
    /**
     * Get the path from start to exit, if any.
     * @return Path from S to E as a list of coordinates [row,col]
     * If not solvable, the path is a message
     */
    public String getPath() {
        if (foundNemo) {
            return path;
        } else {
            path = "Uh Oh!! Could not find Nemo!!";
            return path;
        }
    }


    /** A program to solve a sea using either BFS or DFS */
    public static void main(String[] args) {
        Sea sea;
        Explorer dory, marlin;
        int doryBetter=0, marlinBetter=0;
        for (int j=0; j<10; j++) {
            for (int i = 5; i < 105; i += 5) {
                sea = new Sea(i, i);
                dory = new Explorer(sea, new StackExploreList());
                dory.solve();
                int d, m;
                d = dory.getTotalVisited();
                sea.clearMaze();
                marlin = new Explorer(sea, new QueueExploreList());
                marlin.solve();
                m = marlin.getTotalVisited();
                if (d>m) doryBetter++;
                else marlinBetter ++;
            }
        }
        System.out.println("Number of times Dory found Nemo faster: "+doryBetter+"\nNumber of times Marlin found Nemo Faster: "+marlinBetter);
    }
}
