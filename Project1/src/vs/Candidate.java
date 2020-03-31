package vs;
import java.util.ArrayList;	

public class Candidate implements Comparable<Candidate>{
	String candidate_name;
	int count_ballot;
	ArrayList<Ballot> ballot_list = new ArrayList<Ballot>();
	
	public Candidate(String name) {
		candidate_name = name;
	}
	
	public int GetBallotNum() {	
		return ballot_list.size();
	}
	
	public void ClearCount() {
		ballot_list.clear();
	}
	
	public void AddBallot(Ballot b) { 
		ballot_list.add(b);
		count_ballot++;
	}
	@Override
	public int compareTo(Candidate compareCandidate) {
		// TODO Auto-generated method stub
		 int compareage=((Candidate)compareCandidate).count_ballot;
	        /* For Ascending order*/
	     return compareage - this.count_ballot;
	}
	
	@Override
    public String toString() {
       return "Candidate name is: "+this.candidate_name + "\n";
    }

}

