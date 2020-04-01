package vs;
public class Ballot {
	
	int ballot_id;
	int[] vote_list;
	int choice = 1; 

	public Ballot(int id, int[] vlist) {
		ballot_id = id;
		vote_list = new int[vlist.length];
		for(int i = 0; i < vlist.length; i++)
		{
			vote_list[i] = vlist[i];
		}
	}
	@Override
    public String toString() {
       return this.ballot_id + " ";
    }

}

