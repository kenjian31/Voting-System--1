/*
 * PluralityTest.java
 * Copyright (c) 2020, CSCI5801 Team5. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */
package vs;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.junit.jupiter.api.Test;
/**
 * test Plurality Algorithm 
 * @author Pengyin Chen 
 *
 */
class PluralityTest {


	@Test
	/**
	 * test constructor
	 * @throws IOException
	 */
	public void testPluralityConstructor() throws IOException {
		
		// test file 
		// 5000 ballots 
		// 4 candidates 
		String current = new java.io.File( "." ).getCanonicalPath();
		String testFileLocation = current+"/testing/plurality_test_500b_4c.csv";
		
		Plurality myTest = new Plurality(1, testFileLocation);
		assertEquals(myTest.total_seat, 1);
		
		Plurality myTest2 = new Plurality(4, testFileLocation);
		assertEquals(myTest2.total_seat, 4);
		
		Plurality myTest3 = new Plurality(100, testFileLocation);
		assertEquals(myTest3.total_seat, 100);
		
		Plurality myTest4 = new Plurality(10000, testFileLocation);
		assertEquals(myTest4.total_seat, 10000);
       	
	}
	
	@Test
	/**
	 * test Plurality ReadFile method 
	 * @throws IOException
	 */
    public void testPluralityReadFile() throws IOException {
		String current = new java.io.File( "." ).getCanonicalPath();
		String testFileLocation = current+"/testing/plurality_test_5000b_4c.csv";
		
		Plurality myTest = new Plurality(1, testFileLocation);
		assertEquals(myTest.candidateCount, 0);
		assertEquals(myTest.ballotCount, 0);
		myTest.ReadFile();
		assertEquals(myTest.candidateCount, 4);
		assertEquals(myTest.ballotCount, 5000);
		
		String current1 = new java.io.File( "." ).getCanonicalPath();
		String testFileLocation1 = current1+"/testing/plurality_test_100b_7c.csv";
		
		Plurality myTest2 = new Plurality(4, testFileLocation1);
		myTest2.ReadFile();
		assertEquals(myTest2.candidateCount, 7);
		assertEquals(myTest2.ballotCount, 100);
       	
	}
	@Test
	/**
	 * test GenerateAudit() mehtod
	 * @throws IOException
	 */
	public void testPluralityGenerateAudit() throws IOException {
		String current = new java.io.File( "." ).getCanonicalPath();
		String testFileLocation = current+"/testing/plurality_test_5000b_4c.csv";
		
		Plurality myTest = new Plurality(4, testFileLocation);
		assertEquals(myTest.winner.size(), 0);
		myTest.ReadFile();
		myTest.GenerateAudit();
		assertEquals(myTest.winner.size(), 4);
		
		Plurality myTest2 = new Plurality(0, testFileLocation);
		assertEquals(myTest2.winner.size(), 0);
		myTest2.ReadFile();
		myTest2.GenerateAudit();
		assertEquals(myTest2.winner.size(), 0);
		

		//random case 
		Plurality myTest3 = new Plurality(1, testFileLocation);
		assertEquals(myTest3.winner.size(), 0);
		myTest3.ReadFile();
		myTest3.GenerateAudit();
		assertEquals(myTest3.winner.size(), 1);

		Plurality myTest4 = new Plurality(1000, testFileLocation);
		assertEquals(myTest4.winner.size(), 0);
		myTest4.ReadFile();
		myTest4.GenerateAudit();
		assertEquals(myTest4.winner.size(), 4);
       	
	}
}
