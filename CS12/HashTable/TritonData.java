
/*EMAIL mckaufma@ucsd.edu
 *NAME Michael Kaufman
 *PID A15747235
 */
import java.util.ArrayList;
import java.util.List;

public class TritonData {

    private List<String> transactions;
    private int proofId;

    /**
     * Triton Data Constructor
     * @param None
     */
    public TritonData(){
	transactions = null;
	proofId = 0;
    }

    /*Constructor if specific values are specified*/
    public TritonData(int proofId, List<String> transactions){
        this.proofId = proofId; //intitlize 
	this.transactions = transactions; //intirialize
    }

    /*Get transactions*/
    public List<String> getTransactions() {
	return transactions; //returns trasnsactions
    }

    /*Get proofId*/
    public int getProofId() {
	return proofId; //returns proofid
    }

    /*Print the data block*/
    public String toString(){
        //TODO
        String s = "DATA Start--------------------------------"; //start of data
	s = s + "\nProof of work: " + proofId + "\n"; //proof of work
	int i = 0; //start at trnasaction 0
	if(transactions != null) { //for all transactions
		for (String transaction : transactions) {
			s = s + "Transaction "+ i + "\n" + "Transaction content: " + transaction + "\n"; //append to the string
			i++; //increment the index
		}
	}
//	s = s + "Transaction Content: Triton coin earned: 1\nDATA End --------------------------------\n";
	s = s + "DATA End --------------------------------\n";
	return s;
    }
}
