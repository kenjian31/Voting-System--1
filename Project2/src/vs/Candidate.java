/*
 * Candidate.java
 * Copyright (c) 2020, CSCI5801 Team5. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */
package vs;
import java.util.ArrayList;	
/**
 * data structure class of Candidates
 * @author Pengyin Chen 
 * @author Junren Huang
 *
 */
public class Candidate implements Comparable<Candidate>{
	String candidate_name;
	int count_ballot;
	ArrayList<Ballot> ballot_list = new ArrayList<Ballot>();
	/**
	 * Constructor method 
	 * @param name name of this candidate 
	 */
	public Candidate(String name) {
		candidate_name = name;
	}
	/**
	 * add a ballot to this candidate's ballot list 
	 * @param b ballot assigned to this candidate 
	 */
	public void AddBallot(Ballot b) { 
		ballot_list.add(b);
		count_ballot++;
	}
	@Override
	/**
	 * compare method to compare to candidate based on their ballot count 
	 */
	public int compareTo(Candidate compareCandidate) {
		// TODO Auto-generated method stub
		int compareage=((Candidate)compareCandidate).count_ballot;
		/* For Ascending order*/
		return compareage - this.count_ballot;
	}

	@Override
	/**
	 * represent this candidates with its name 
	 */
	public String toString() {
		return "Candidate name is: "+this.candidate_name + "\n";
	}

}

