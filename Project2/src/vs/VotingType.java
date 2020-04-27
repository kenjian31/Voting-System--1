/*
 * VotingType.java
 * Copyright (c) 2020, CSCI5801 Team5. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */
package vs;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 * This is an abstract class 
 * as the base class of Plurality and STV 
 * it contains ReadFile() methods 
 * and an abstract function GenerateAudit() to be overrided 
 * @author Pengyin Chen 
 *
 */
abstract class VotingType {
	public ArrayList<Candidate> candidateList = new ArrayList<Candidate>();
	public ArrayList<Ballot> ballotList = new ArrayList<Ballot>(); 
	public int ballotCount = 0;
	public int candidateCount = 0;
	public String fileName;
	public int total_seat;
	public int shuffle_flag=1;
	public int screen_input_flag = 1;
	ArrayList<Ballot> invalid_ballot = new ArrayList<Ballot>(); 
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
	// output invalidate ballots report to display panel 
	public void WriteInvalidBallot(String filename,String savelocation) throws IOException {
		String auditFileLocation = savelocation + filename + ".txt";
		FileWriter fileWriter = new FileWriter(auditFileLocation);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		System.out.println("this is invalid ballot:\n");
		printWriter.printf("this is invalid ballot:\n");
		
		System.out.println("invalid_ballot: " + invalid_ballot.size());
		printWriter.print("invalid_ballot: " + invalid_ballot.size() + "\n");
		for (int i = 0; i<invalid_ballot.size(); i++) {
			for (int j = 0; j< invalid_ballot.get(i).vote_list.length; j++) {
				System.out.print(invalid_ballot.get(i).vote_list[j]);
				printWriter.print(invalid_ballot.get(i).vote_list[j]);
			}
			System.out.println();
			printWriter.print("\n");
		}
		printWriter.close();
	}
	/**
	 * GenerateAudit() is an abstract method to be override
	 * @throws IOException write file may cause exception 
	 */
	public abstract void GenerateAudit() throws IOException;
}