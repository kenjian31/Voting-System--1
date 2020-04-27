/*
 * DroopQuotaTest.java
 * Copyright (c) 2020, CSCI5801 Team5. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */
package vs;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.api.Test;
/**
 * test DQ algorithm 
 * @author Pengyin Chen
 *
 */
class DroopQuotaTest {

	@Test
	/**
	 * test DQ ReadFile() method 
	 * @throws IOException
	 */
	void DroopQuotaReadFile() throws IOException {
		// /Users/frankchen/Desktop/team5/repo-Team5/Project1/testing/DQ_test_20b_7c.csv
		
		String current = new java.io.File( "." ).getCanonicalPath();
		String testFileLocation = current+"/testing/DQ_test_20b_7c.csv";
		
		DroopQuota myTest = new DroopQuota(1, testFileLocation);
		assertEquals(myTest.candidateCount, 0);
		assertEquals(myTest.ballotCount, 0);
		myTest.ReadFile();
		assertEquals(myTest.candidateCount, 7);
		assertEquals(myTest.ballotCount, 20);
	
		String testFileLocation1 = current+"/testing/DQ_test_20b_6c.csv";
		
		DroopQuota myTest1 = new DroopQuota(1, testFileLocation1);
		assertEquals(myTest1.candidateCount, 0);
		assertEquals(myTest1.ballotCount, 0);
		myTest1.ReadFile();
		assertEquals(myTest1.candidateCount, 6);
		assertEquals(myTest1.ballotCount, 20);
	

	}
	@Test
	/**
	 * test GenrateAudit() method 
	 * 2 test file 
	 * test the final winner 
	 * @throws IOException
	 */
	void DroopQuotaGenerateAudit() throws IOException {
		// /Users/frankchen/Desktop/team5/repo-Team5/Project1/testing/DQ_test_20b_7c.csv
		
		String current = new java.io.File( "." ).getCanonicalPath();
		String testFileLocation = current+"/testing/DQ_test_20b_7c.csv";
		
		DroopQuota myTest = new DroopQuota(1, testFileLocation);
		assertEquals(myTest.winner.size(), 0);
		myTest.shuffle_flag =0;
		myTest.ReadFile();
		myTest.GenerateAudit();
		assertEquals(myTest.winner.size(), 1);
		
		DroopQuota myTest2 = new DroopQuota(2, testFileLocation);
		assertEquals(myTest2.winner.size(), 0);
		myTest2.shuffle_flag =0;
		myTest2.ReadFile();
		myTest2.GenerateAudit();
		assertEquals(myTest2.winner.size(), 2);
		
		DroopQuota myTest3 = new DroopQuota(3, testFileLocation);
		assertEquals(myTest3.winner.size(), 0);
		myTest3.shuffle_flag =0;
		myTest3.ReadFile();
		myTest3.GenerateAudit();
		assertEquals(myTest3.winner.size(), 3);
		
		// there is a bug here 
		// since the ballot may not have enough vote list 
		// it may not be assigned to anyone 
		// so the total ballot pool is reduced 
		// candidate may not get enough ballot to be more than the droop 
		// candidate will be not able to fill all seats 
		DroopQuota myTest4 = new DroopQuota(4, testFileLocation);
		assertEquals(myTest4.winner.size(), 0);
		myTest4.shuffle_flag =0;
		myTest4.ReadFile();
		myTest4.GenerateAudit();
		assertEquals(myTest4.winner.size(), 3);
		
		// same as this one 
		// bug test 
		DroopQuota myTest5 = new DroopQuota(5, testFileLocation);
		assertEquals(myTest5.winner.size(), 0);
		myTest5.shuffle_flag =0;
		myTest5.ReadFile();
		myTest5.GenerateAudit();
		assertEquals(myTest5.winner.size(), 3);
		
		DroopQuota myTest12 = new DroopQuota(6, testFileLocation);
		assertEquals(myTest12.winner.size(), 0);
		myTest12.shuffle_flag =0;
		myTest12.ReadFile();
		myTest12.GenerateAudit();
		assertEquals(myTest12.winner.size(),4);
		
		DroopQuota myTest13 = new DroopQuota(7, testFileLocation);
		assertEquals(myTest13.winner.size(), 0);
		myTest13.shuffle_flag =0;
		myTest13.ReadFile();
		myTest13.GenerateAudit();
		assertEquals(myTest13.winner.size(),7);
		
		// /Users/frankchen/Desktop/team5/repo-Team5/Project1/testing/DQ_test_20b_6c.csv
		String testFileLocation1 = current+"/testing/DQ_test_20b_6c.csv";
		
		DroopQuota myTest6 = new DroopQuota(1, testFileLocation1);
		assertEquals(myTest6.winner.size(), 0);
		myTest6.shuffle_flag =0;
		myTest6.ReadFile();
		myTest6.GenerateAudit();
		assertEquals(myTest6.winner.size(), 1);
		
		DroopQuota myTest7 = new DroopQuota(2, testFileLocation1);
		assertEquals(myTest7.winner.size(), 0);
		myTest7.shuffle_flag =0;
		myTest7.ReadFile();
		myTest7.GenerateAudit();
		assertEquals(myTest7.winner.size(), 2);
		
		DroopQuota myTest8 = new DroopQuota(3, testFileLocation1);
		assertEquals(myTest8.winner.size(), 0);
		myTest8.shuffle_flag =0;
		myTest8.ReadFile();
		myTest8.GenerateAudit();
		assertEquals(myTest8.winner.size(), 3);
		
		// bug case 
		DroopQuota myTest9 = new DroopQuota(4, testFileLocation1);
		assertEquals(myTest9.winner.size(), 0);
		myTest9.shuffle_flag =0;
		myTest9.ReadFile();
		myTest9.GenerateAudit();
		assertEquals(myTest9.winner.size(),3 );
		
		//bug case 
		DroopQuota myTest10 = new DroopQuota(5, testFileLocation1);
		assertEquals(myTest10.winner.size(), 0);
		myTest10.shuffle_flag =0;
		myTest10.ReadFile();
		myTest10.GenerateAudit();
		assertEquals(myTest10.winner.size(),4 );

		DroopQuota myTest11 = new DroopQuota(6, testFileLocation1);
		assertEquals(myTest11.winner.size(), 0);
		myTest11.shuffle_flag =0;
		myTest11.ReadFile();
		myTest11.GenerateAudit();
		assertEquals(myTest11.winner.size(),6);

				
	}
	


	@Test
	/**
	 * test RemoveInvalidBallot() method 
	 * 2 test file 
	 * test the final winner 
	 * @throws IOException
	 */
	void DroopQuotaRemoveInvalidBallot() throws IOException {
		String current = new java.io.File( "." ).getCanonicalPath();
		String testFileLocation1 = current+"/testing/DQ_test_Invalid_1.csv";
		String testFileLocation = current+"/testing/DQ_test_Invalid_small.csv";

	    DroopQuota myTest1 = new DroopQuota(2, testFileLocation1);
	    assertEquals(myTest1.invalid_ballot.size(), 0);
	    myTest1.shuffle_flag = 0;
	    myTest1.ReadFile();
//	    myTest1.GenerateAudit();
	    myTest1.RemoveInvalidBallot();
	    assertEquals(myTest1.invalid_ballot.size(), 2284);
	    
	    DroopQuota myTest2 = new DroopQuota(2, testFileLocation);
	    assertEquals(myTest2.invalid_ballot.size(), 0);
	    myTest2.shuffle_flag = 0;
	    myTest2.ReadFile();
//	    myTest2.GenerateAudit();
	    myTest2.RemoveInvalidBallot();
	    assertEquals(myTest2.invalid_ballot.size(), 34);
	}
	@Test
	/**
	 * test RemoveInvalidBallot() method 
	 * 2 test file 
	 * test the final winner 
	 * @throws IOException
	 */
	void DroopQuotaWriteInvalidBallot() throws IOException {
		String current = new java.io.File( "." ).getCanonicalPath();
		String testFileLocation1 = current+"/testing/DQ_test_Invalid_1.csv";

	    DroopQuota myTest1 = new DroopQuota(2, testFileLocation1);
	    myTest1.shuffle_flag = 0;
	    myTest1.ReadFile();
	    myTest1.RemoveInvalidBallot();
	    String fileName = "/invalid_";
	    String filePath =current;
	    myTest1.WriteInvalidBallot(fileName, filePath);
	    assertEquals(myTest1.invalid_ballot.size(), 2284);
	    
	    String fileLocation = filePath + fileName+ ".txt";
		File file = new File(fileLocation); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String st;
		int line_count = 0;
		while ((st = br.readLine()) != null) {
			line_count++;
		}
		br.close();
		assertEquals(line_count, 2286);
	}



}
