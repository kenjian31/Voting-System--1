package vs;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Random;


public class DroopQuota extends VotingType {
	ArrayList<Candidate> winner = new ArrayList<Candidate>();
	ArrayList<Candidate> loser = new ArrayList<Candidate>();
	
	public DroopQuota(int seats, String f) {
		total_seat = seats;
		fileName = f;
	}
	
	// randomly swap items in ballot list 
	//repeat 5 times
    public void Shuffle() { 
    	Random rand = new Random();
    	for (int i = 0; i< 5; i++) {
	    	for (int j = 0; j < ballotList.size() ; j++) {
				int randomIndexToSwap = rand.nextInt(ballotList.size()-1);
				Collections.swap(ballotList, j, randomIndexToSwap);
			}
    	}
    } 
    
    
	public void GenerateAudit() throws IOException {
		// generate audit file to read
		String current = new java.io.File( "." ).getCanonicalPath();
		String auditFileLocation = current+"/src/vs/audit.txt";
		System.out.print(auditFileLocation);
		FileWriter fileWriter = new FileWriter(auditFileLocation);
	    PrintWriter printWriter = new PrintWriter(fileWriter);
			    
		String introString = "===========================\n" +
				"This is STV Droop Quota Algorithm\n" +
				"Total seats: " + total_seat+ "\n" +
				"Total candidates: " + candidateList.size()+ "\n"+
				"Total Ballots: " + ballotList.size()+ "\n";
		
		System.out.println(introString);
		printWriter.printf(introString);
		
		if (candidateList.size() <= total_seat) {
			String str = "total seats is great than or equal to the number of candidates\n"
					+ "All candidates are winners";
			System.out.println(str);
			printWriter.printf(str);
			
			for (int i = 0; i < total_seat; i++) {
				winner.add(candidateList.get(i));
			}
			System.out.println("\n"+"Winners:");
			printWriter.printf("\n"+"Winners:");
			for (int i =0; i< winner.size(); i++) {
				System.out.println(winner.get(i));
				printWriter.printf("\n"+winner.get(i));
			}
			printWriter.close();
			return;
		}
		
		// shuffle
		if(shuffle_flag ==1) {
			System.out.println("shuffle sption is on\n");
			printWriter.printf("shuffle sption is on\n");
			Shuffle();
		} else {
			System.out.println("shuffle sption is off\n");
			printWriter.printf("shuffle sption is off\n");
		}
		
		
		// calculate droop
		int droop_q = (ballotList.size()/(total_seat+1))+1;
		for (int i =0; i < candidateList.size(); i++) {
			loser.add(candidateList.get(i));
		}
		System.out.println("\n"+"calculate the droop value\n" + "Droop is: " + droop_q);
		
		System.out.println("\n"+"1st Round:" );
		System.out.println("Assign each ballot to their first desire candidate\n");
		
		printWriter.printf("\n"+"calculate the droop value\n" + "Droop is: " + droop_q);
		printWriter.printf("\n"+"1st Round:");
		printWriter.printf("Assign each ballot to their first desire candidate\n");
		
		// for each ballot, their choice starts at 1
		// loop through their vote list 
		// if their choice is a winner, choice + 1
		// if not a winner, assign to this candidate 
		for (int i = 0; i < ballotList.size(); i++) { 
			for (int j =0; j < ballotList.get(i).vote_list.length; j++) {
				if (ballotList.get(i).vote_list[j] == ballotList.get(i).choice &&
						winner.contains(candidateList.get(j))) {
					ballotList.get(i).choice++;
					continue;
				}
				if (ballotList.get(i).vote_list[j] == ballotList.get(i).choice && 
						!winner.contains(candidateList.get(j))) {
					candidateList.get(j).AddBallot(ballotList.get(i));
				}
			}
			
			// check if there are some candidates reach the droop 
			for (int k =0 ; k< candidateList.size(); k ++) {
				if (candidateList.get(k).count_ballot >= droop_q && 
						!winner.contains(candidateList.get(k))) {
					winner.add(candidateList.get(k));
					loser.remove(candidateList.get(k));
				}
			}	
			
		}
		System.out.println("1st Round finished");
		System.out.println("\n"+"Winners so far:");
		printWriter.printf("1st Round finished");
		printWriter.printf("\n"+"Winners so far:");
		for (int i =0; i< winner.size(); i++) {
			System.out.println(winner.get(i));
			printWriter.printf(winner.get(i).toString()+ "\nBallots received: ");
			for(int q =0; q< winner.get(i).ballot_list.size(); q++) {
				printWriter.printf(winner.get(i).ballot_list.get(q).toString());
			}
		}
		System.out.println("\n"+"Losers so far:");
		printWriter.printf("\n"+"Loser so far:");
		for (int i =0; i< loser.size(); i++) {
			System.out.println(loser.get(i));
			printWriter.printf(loser.get(i).toString()+ "\nBallots received: ");
			for(int q =0; q< loser.get(i).ballot_list.size(); q++) {
				printWriter.printf(loser.get(i).ballot_list.get(q).toString());
			}
		}
		
		int round = 2; 
		while(winner.size()<total_seat && loser.size()!=0) {
			System.out.println("\n"+round+"st Round:" );
			System.out.println("\n"+"sort loser list" );
			printWriter.printf("\n"+round+"st Round:");
			
			
			Collections.sort(loser);
			
			System.out.println("\n"+"redistribute ballots the candidates with the least ballots" );
			printWriter.printf("\n"+"redistribute ballots the candidates with the least ballots");
			for (int i =0; i < loser.get(loser.size() -1).ballot_list.size(); i++) {
				for (int j =0; j <loser.get(loser.size() -1).ballot_list.get(i).vote_list.length; 
						 j++) {
					if (loser.get(loser.size() -1).ballot_list.get(i).vote_list[j] 
							 == loser.get(loser.size() -1).ballot_list.get(i).choice && 
							winner.contains(candidateList.get(j))) {
						loser.get(loser.size() -1).ballot_list.get(i).choice++;
						continue;
						}
					 if (loser.get(loser.size() -1).ballot_list.get(i).vote_list[j] 
							 == loser.get(loser.size() -1).ballot_list.get(i).choice && 
							!winner.contains(candidateList.get(j)) &&
							loser.contains(candidateList.get(loser.get(loser.size() -1).ballot_list.get(i).choice))) {
						 candidateList.get(j).AddBallot(ballotList.get(i));
					}
				 }
			}
			
			System.out.println("\n"+round+"st Round finished" );
			printWriter.printf("\n"+round+"st Round finished" );
			
			System.out.println("\n"+"Winners so far:");
			printWriter.printf("\n"+"Winners so far:");
			
			for (int i =0; i< winner.size(); i++) {
				System.out.println(winner.get(i));
				printWriter.printf(winner.get(i).toString()+ "\nBallots received: ");
				for(int q =0; q< winner.get(i).ballot_list.size(); q++) {
					printWriter.printf(winner.get(i).ballot_list.get(q).toString());
				}
			}
			System.out.println("\n"+"Losers so far:");
			printWriter.printf("\n"+"Loser so far:");
			for (int i =0; i< loser.size(); i++) {
				System.out.println(loser.get(i));
				printWriter.printf(loser.get(i).toString()+ "\nBallots received: ");
				for(int q =0; q< loser.get(i).ballot_list.size(); q++) {
					printWriter.printf(loser.get(i).ballot_list.get(q).toString());
				}
			}
			
			round++;
			loser.remove(loser.size()-1);
			if(loser.size()==0) {
				System.out.println("\nThere is no more candidates in the loser list\n" );
				System.out.println("Result Generated\n" );
				printWriter.printf("\nThere is no more candidates in the loser list\n");
				printWriter.printf("Result Generated\n");
				
			}
		}
		
		System.out.println("================\n");
		System.out.println("Audit File finished");
		System.out.println("Path: " + auditFileLocation);
		printWriter.close();
		
	}
	
}




