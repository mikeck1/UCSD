/* eMail: mckaufma@ucsd.edu
 * User: cs15xqc
 * PID: A15747235
 */

/**TritonBlock is a object or nodes used for the TritonBlockChain class.
 * Holds index, timestamp, data, prev_hash and self_hash.
 */
public class TritonBlock {
	/*Class variables, all the attributes of the block*/
    private int index;
    private long timestamp;
    private TritonData data;
    private String prev_hash;
    private String self_hash;

    /*Constructor, builds a block with passed in variables, then creates a hash for curr block*/
    public TritonBlock(int index, long timestamp, TritonData data, String prev_hash){
        this.index = index;
	this.timestamp = timestamp;
	this.data = data;
	this.prev_hash = prev_hash;
	this.self_hash = hashBlock();
    }
    /*hashblock hashes converts the whole block to a string then converts to a hash string*/
    private String hashBlock(){
        String s = ""; //initialize the string to grab the whole block's string data
	s = "" + this.toString(); //turn the block into a string
	int k = 0; // constant derived from char hash, this will be mod'd to 1000000
	int x = 524287; // large prime number
	for(char c : s.toCharArray()) { //for char in the string
		k = k + c*x; //convert to a hash
	}
	String h = "" + k % 1000000; //trim to a specific size and convert to string
	return h; //return the string
    }

    /*Get index*/
    public int getIndex(){
        return index; //returns index in the blockchain. increments in TritonBlockChain
    }

    /*Get timestamp*/
    public long getTimestamp(){
        return timestamp; //holds the timestamp from when block is created
    }

    /*Get data block*/
    public TritonData getData(){
        return data; //data block, holds transactions
    }

    /*Get previous hash*/
    public String getPrev_hash(){
        return prev_hash; //returns the prev_hash, used for confirming blockchain is authentic
    }

    /*Get current hash*/
    public String getSelf_hash(){
        return self_hash; //returns self hash used by tritonblockchain for access to self_hash
    }

    /*Print the block*/
    public String toString(){
        String s = "\n";
	s = "TritonBlock " + index+"\n"; /*introduce the index*/
	s = s + "Index: " + index + "\n"; /*index*/
	s = s + "Timestamp: " + timestamp+ "\n"; /*add timestamp stats*/
	s = s + "Prev Hash: " + prev_hash + "\n"; /*add prev_hash*/
	s = s + "Hash: " + self_hash + "\n"; /*add self_hash*/
	s = s + "\nTritonData: " + data ; /*add the data block, all transactions*/
	s = s + "\n";
	return s;
    }
}
