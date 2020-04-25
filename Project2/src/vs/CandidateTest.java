/*
 * CandidateTest.java
 * Copyright (c) 2020, CSCI5801 Team5. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */
package vs;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/**
 * test Candidate class
 * @author Pengyin Chen
 *
 */
class CandidateTest {

	@Test
	/**
	 * test constrcutor 
	 */
	public void testConstrcutor() {
		Candidate c1 = new Candidate("Tony");
		Candidate c2 = new Candidate("Sue");
		Candidate c3 = new Candidate("Scott");
		assertEquals(c1.candidate_name, "Tony");
		assertEquals(c2.candidate_name, "Sue");
		assertEquals(c3.candidate_name, "Scott");
	}
	

	@Test
	/**
	 * test addBallot() method 
	 */
	public void testAddBallot() {
		Candidate c1 = new Candidate("Tony");
		int[] list = new int[] {1,2,3,4,5};
		Ballot ballot1 = new Ballot(1, list);
		assertEquals(c1.count_ballot, 0);
		c1.AddBallot(ballot1);
		assertEquals(c1.ballot_list.get(0).toString(), "1 ");
		assertEquals(c1.count_ballot, 1);
	}
	
	 @Test
	 /**
	  * test compareTo override method 
	  */
	    public void testcompareTo() {
	    	Candidate c1 = new Candidate("Tony");
	    	Candidate c2 = new Candidate("k");
	    	Candidate c3 = new Candidate("aa");
	    	
	    	int[] list = new int[] {1,2,3,4,5};
	    	Ballot ballot1 = new Ballot(1, list);
	    	Ballot ballot2 = new Ballot(2, list);
	    	
	    	c2.AddBallot(ballot1);
	    	int result1 = c2.compareTo(c1);
	    	assertEquals(result1, -1);
	    	
	    	int result2 = c1.compareTo(c2);
	    	assertEquals(result2, 1);

	    	c3.AddBallot(ballot1);
	    	c3.AddBallot(ballot2);
	    	int actual1 = c3.compareTo(c2);
	    	int actual2 = c2.compareTo(c3);
	    	assertEquals(-1, actual1);
	    	assertEquals(1, actual2);
	    	
	    }
	 
	    @Test
	    /**
	     * test toString override method 
	     */
	    public void testToString() {
	    	Candidate c1 = new Candidate("Tony");
	        String Exresult = "Candidate name is: Tony\n";
	        String result = c1.toString();
	        assertEquals(Exresult, result);
	        
	        Candidate c2 = new Candidate("Sue");
	        String Exresult1 = "Candidate name is: Sue\n";
	        String result1 = c2.toString();
	        assertEquals(Exresult1, result1);
	        
	        Candidate c3 = new Candidate("Scott");
	        String Exresult2 = "Candidate name is: Scott\n";
	        String result2 = c3.toString();
	        assertEquals(Exresult2, result2);
	    }
}
