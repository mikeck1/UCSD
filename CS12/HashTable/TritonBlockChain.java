/* EMAIL: mckaufma@ucsd.edu
 * NAME: Michael Kaufman
 * PID: A15747235
 */

/* TritonBlockchain - Dependencies : TritonData, TritonBlock
 * Provides proof of work test for transactions, appends block and rewards coins in print statments.
 */
import java.util.*;

public class TritonBlockChain {

	private static final String MINE_REWARD = "1";
    /*Blockchain clas variable*/
    private List<TritonBlock> blockchain;

    /*Constructor, takes in genesis block data to start the blockchain*/
    public TritonBlockChain(int index, long timestamp, TritonData data, String prev_hash) { 
        this.blockchain = new LinkedList<TritonBlock>(); //create a new blockchain of tritonblocks
        TritonBlock block = new TritonBlock(index, timestamp, data, prev_hash); //create a new block
	this.blockchain.add(block); //add block to the blockchain
    }

    /*Makes the next block after the proof of work from mining is finished*/
    public TritonBlock makeNewBlock(TritonBlock lastBlock, TritonData newData) {
        int i = lastBlock.getIndex(); //get the last block of the blockchain
	String h = lastBlock.getSelf_hash(); //get hasvalue
        TritonBlock block = new TritonBlock(i+1, System.currentTimeMillis(), newData, h); //create a new block
	return block; //return the new block
    }

    /*Mines the transaction and creates the block to add to the blockchain*/
    public boolean beginMine(List<String> curTransactions) {
        if(curTransactions.size() == 0) { //if no transactions
		return false; //did not deserve a reward
	}
	String s = "Triton coined earned: " + MINE_REWARD; //they got a coin!
	curTransactions.add(s); //add the string to the transactions
	TritonData d = new TritonData(proofOfWork(), curTransactions); //create a new data block
	TritonBlock b = makeNewBlock(getLastBlock(), d); //create a new block
	this.blockchain.add(b); //add it to the blockchain
	return true; //return true because they deserved the block
    }

    /*Simple proof of work algorithm to prove cpu usage was used to mine block*/
    public int proofOfWork() {
        boolean notDone = true; //intiz boolean
	int n = getLastBlock().getData().getProofId(); //get the last blocks proof of work
	if(n==0) {n=1;} //change to 1 based, not zero based
        while (notDone) {
		for(int i = n+1; i < 99999999; i++) { //for a very high number, always find the nuber divisible by 13 and the last proof of work id
			if(i % 13 == 0 && i % n == 0) {return i;} //once found return it
		}
	}
        return -1; //if not found reurn -1 for an error
    }

    /*Prints current blockchain*/
    public String toString() {
        String s = "";
        for(TritonBlock b : blockchain) { //for block in the blockchain
		if(b.getPrev_hash() != "0") { //if the prev hash is not zero, i.e skip genesis block
			s = s + b; //append a string of all the datas from each block
		}
	}
	return s; //return said string
    }

    /*Validates each block in the chain looking for any hash pointer descrepancies, which can point to a tampering problem*/
    public boolean validateChain() {
        TritonBlock p = blockchain.get(0); //start at the prev hash of the beginning
        for(TritonBlock b : blockchain) {
		if(b.getPrev_hash() != "0") { 
			if(b.getPrev_hash() != p.getSelf_hash()) { //check if the cur hash has a prev hash matching the prev hash
				return false; //we had a boo boo, looks like no match
			}
			p = b; //make cur = prev for the iterative loop, throught the chain of blocks
		}
	}
        return true; //they all matched, yay. return true
    }
    /*Get blockchain*/
    public List<TritonBlock> getBlockchain() {
        return blockchain; //gets the current blockchain
    }
    /* Gets the last block of the blockchain*/
    private TritonBlock getLastBlock() {
	return blockchain.get(blockchain.size() - 1); //get the last block in the blockchain
    }

}
