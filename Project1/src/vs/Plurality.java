package vs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import javafx.scene.shape.Path;


public class Plurality extends VotingType{
	int total_voter = ballotList.size();
	ArrayList<Candidate> winner = new ArrayList<Candidate>();
	
	public Plurality(int seat, String f) {
		total_seat = seat;
		fileName = f;
	}
	
	public void RandomWinner(ArrayList<Candidate> result, int length) throws IOException {
		
		Random rand = new Random();
		
		// random swap tie candidates 
		// add candidates to the winner list as we need in order 
		// for example, if we need two candidates 
		// add the first two to the winner 
		for (int i = 0; i < result.size(); i++) {
			int randomIndexToSwap = rand.nextInt(result.size()-1);
			Collections.swap(result, i, randomIndexToSwap);
		}
		
		for (int i =0; i < length; i++) {
			winner.add(result.get(i));
		}
	}
	

	@Override
	public void GenerateAudit() throws IOException {
		// generate audit file to read
		String current = new java.io.File( "." ).getCanonicalPath();
		String auditFileLocation = current+"/src/vs/audit.txt";
		
		FileWriter fileWriter = new FileWriter(auditFileLocation);
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    
		
		// TODO Auto-generated method stub
		String introString = "===========================\n" +
				"This is Plurality Algorithm\n" +
				"Total seats: " + total_seat+ "\n" +
				"Total candidates: " + candidateList.size()+ "\n"+
				"Total Ballots: " + ballotList.size()+ "\n";
		
		System.out.println(introString);
		printWriter.printf(introString);
<<<<<<< HEAD
		
		//seats number is 0
		if(total_seat ==0) {
			System.out.println("Seats Number is 0");
			System.out.println("Voting System done");
			System.out.println("\n\n================\n");
			System.out.println("Audit File finished");
			System.out.println("Path: " + auditFileLocation);
			printWriter.close();
			return;
			
		}
		
		// if total seats is greater than or equal to the number of candidates 
		// declare all candidates as winners 
=======
>>>>>>> 106d72586a33a0fe41570fad25f493c33ecff1af
		if (candidateList.size() <= total_seat) {
			String str = "total seats is great than or equal to the number of candidates\n"
					+ "All candidates are winners";
			System.out.println(str);
			printWriter.printf(str);
<<<<<<< HEAD

			for (int i = 0; i < candidateList.size(); i++) {
=======
			
			for (int i = 0; i < total_seat; i++) {
>>>>>>> 106d72586a33a0fe41570fad25f493c33ecff1af
				winner.add(candidateList.get(i));
			}
			System.out.println("\n"+"Winners:");
			printWriter.printf("\n"+"Winners:");
			//print winner info to users and write it to files 
			for (int i =0; i< winner.size(); i++) {
				System.out.println(winner.get(i)+ "Ballot percentage: "
						+" "+(winner.get(i).count_ballot/(double)ballotList.size())+"\n");
				printWriter.printf(winner.get(i)+ "Ballot percentage: "
						+" "+(winner.get(i).count_ballot/(double)ballotList.size())+"\n");
			}
			System.out.println("================\n");
			System.out.println("Audit File finished");
			System.out.println("Path: " + auditFileLocation);
			printWriter.close();
			return;
		}
		System.out.println("\n"+"Assign each ballot to their desire candidate\n");
		printWriter.printf("\n"+"Assign each ballot to their desire candidate\n");
		// count for vote 
		for (int i =0; i < ballotList.size(); i++) {
			for (int j = 0; j < ballotList.get(i).vote_list.length; j++) {
				if(ballotList.get(i).vote_list[j] ==1) {
					candidateList.get(j).AddBallot(ballotList.get(i));
				}
			}
		}
		
		System.out.println("\nsort candidates by their vote count \n");
		printWriter.printf("\nsort candidates by their vote count \n");
		// sort candidates by their vote count 
		 Collections.sort(candidateList);
		
		 // check if there are ties
		int tie_count = -1;
		ArrayList<Candidate> tieCandidates = new ArrayList<Candidate>();
		
		// check the candidates at the boundary condition 
		// if the next candidate has the same ballot count
		// there exists a tie
		// assign this candidate to tieCandidate list 
		int tie = candidateList.get(total_seat-1).count_ballot;
		if(tie== candidateList.get(total_seat).count_ballot) {
			for (int i =0; i< candidateList.size(); i++) {
				if (candidateList.get(i).count_ballot == tie) {
					tie_count = tie_count + 1;
					tieCandidates.add(candidateList.get(i));
				}
			}	
		}
		
		// if there is a tie 
		if (tie_count > 0) {
			System.out.println("\n"+ "There is a tie condition");
			printWriter.printf("\n"+ "There is a tie condition");
			int i;
			for (i =0; candidateList.get(i).count_ballot > tie; i++) {
				winner.add(candidateList.get(i));
			}
			try {
				RandomWinner(tieCandidates, total_seat - winner.size());
				System.out.println("\n"+"Winners:");
				printWriter.printf("\n"+"Winners:");
				for (int j =0; j< winner.size(); j++) {
					System.out.println(winner.get(j)+ "Ballot percentage: "
							+" "+(winner.get(j).count_ballot/(double)ballotList.size())+"\n");
					printWriter.printf(winner.get(j)+ "Ballot percentage: "
							+" "+(winner.get(j).count_ballot/(double)ballotList.size())+"\n");
				}
				System.out.println("\n"+"Losers:");
				printWriter.printf("\n"+"Losers:");
				for (int j =0; j< candidateList.size(); j++) {
					if(!winner.contains(candidateList.get(j))) {
						System.out.println(candidateList.get(j)+ "Ballot percentage: "
							+" "+(candidateList.get(j).count_ballot/(double)ballotList.size())+"\n");
						printWriter.printf(candidateList.get(j)+ "Ballot percentage: "
								+" "+(candidateList.get(j).count_ballot/(double)ballotList.size())+"\n");
					}
				}
				
				printWriter.printf("\n\nDetail of each candidate\n");
				for (int k=0; k< candidateList.size(); k++) {
					printWriter.printf("\n\n"+candidateList.get(k).toString() + "\n");
					printWriter.printf("Ballots: ");
					for(int q =0; q< candidateList.get(k).ballot_list.size(); q++) {
						printWriter.printf(candidateList.get(k).ballot_list.get(q).toString());
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else { // if there is no tie 
			System.out.println("\n"+ "There is no tie condition");
			printWriter.printf("\n"+ "There is no tie condition");
			for (int i = 0; i < total_seat; i++) {
				winner.add(candidateList.get(i));
			}
			System.out.println("\n"+"Winners:");
			printWriter.printf("\n"+"Winners:");
			for (int i =0; i< winner.size(); i++) {
				System.out.println(winner.get(i)+ "Ballot percentage: "
						+" "+(winner.get(i).count_ballot/(double)ballotList.size())+"\n");
				printWriter.printf(winner.get(i)+ "Ballot percentage: "
						+" "+(winner.get(i).count_ballot/(double)ballotList.size())+"\n");
			}
			
			System.out.println("\n"+"Losers:");
			printWriter.printf("\n"+"Losers:");
			for (int j =0; j< candidateList.size(); j++) {
				if(!winner.contains(candidateList.get(j))) {
					System.out.println(candidateList.get(j)+ "Ballot percentage: "
						+" "+(candidateList.get(j).count_ballot/(double)ballotList.size())+"\n");
				printWriter.printf(candidateList.get(j)+ "Ballot percentage: "
						+" "+(candidateList.get(j).count_ballot/(double)ballotList.size())+"\n");
				}
			}
			
			printWriter.printf("\n\nDetail of each candidate\n");
			for (int k=0; k< candidateList.size(); k++) {
				
				printWriter.printf(candidateList.get(k).toString() + "\n");
				printWriter.printf("Ballots: ");
				for(int q =0; q< candidateList.get(k).ballot_list.size(); q++) {
					printWriter.printf(candidateList.get(k).ballot_list.get(q).toString());
				}
			}
		}
		System.out.println("================\n");
		System.out.println("Audit File finished");
		System.out.println("Path: " + auditFileLocation);
		printWriter.close();
	}
	public static void main(String[] args) {
		VotingType vt = new Plurality(1, "/Users/frankchen/Desktop/team5/repo-Team5/Project1/src/vs/test.csv");
		try {
			vt.ReadFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			vt.GenerateAudit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
}
