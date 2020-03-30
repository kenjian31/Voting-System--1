/*
 * Ballot.java
 * Copyright (c) 2020, CSCI5801 Team5. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */
package vs;
/**
 * data structure of Ballot
 * @author Pengyin Chen
 * @author Jian Wang
 *
 */
public class Ballot {

	int ballot_id;
	int[] vote_list;
	int choice = 1; 
	/**
	 * constrcutor 
	 * @param id ballot id 
	 * @param vlist voting list 
	 */
	public Ballot(int id, int[] vlist) {
		ballot_id = id;
		vote_list = new int[vlist.length];
		for(int i = 0; i < vlist.length; i++)
		{
			vote_list[i] = vlist[i];
		}
	}
	@Override
	/**
	 * represent this ballot with its id 
	 */
	public String toString() {
		return this.ballot_id + " ";
	}

}

