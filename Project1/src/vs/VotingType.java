package vs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

abstract class VotingType {
	public ArrayList<Candidate> candidateList = new ArrayList<Candidate>();
	public ArrayList<Ballot> ballotList = new ArrayList<Ballot>(); 
	public int ballotCount = 0;
	public int candidateCount = 0;
	public String fileName;
	public int total_seat;
	public int shuffle_flag=1;
	// read file content
	// parse candidates and ballots
	// create candidates and ballots objects and store them in two lists
	public void ReadFile() throws IOException {
		System.out.println("===========================\n");
		System.out.println("start reading file\n\n");
		File file = new File(fileName); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String st; 
		st = br.readLine(); 
		String[] candidateNameList = st.split(",");
		System.out.println("create Candidate objects\n\n");
		for (int i =0; i< candidateNameList.length; i++) {
			candidateList.add(new Candidate(candidateNameList[i]));
			candidateCount++;
		}
		System.out.println("create Ballot objects\n");
		while ((st = br.readLine()) != null) {
			String[] voteListString = st.split(",",-1);
			int[] votelist = new int[voteListString.length];
			for (int i=0; i < voteListString.length; i++) {
				if(!voteListString[i].equals("")) {
					votelist[i] = Integer.parseInt(voteListString[i]);
				} else {
					votelist[i] = 0;
				}
			}
			ballotList.add(new Ballot(++ballotCount, votelist));
			
	  	}
		System.out.println("read file completed\n");
		br.close();
	}
	
	public abstract void GenerateAudit() throws IOException;
}