package vs;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class DroopQuotaTest {

	@Test
	void DroopQuotaReadFile() throws IOException {
		///Users/frankchen/Desktop/team5/repo-Team5/Project1/testing/DQ_test_20b_7c.csv
		
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
	void DroopQuotaGenerateAudit() throws IOException {
		///Users/frankchen/Desktop/team5/repo-Team5/Project1/testing/DQ_test_20b_7c.csv
		
		String current = new java.io.File( "." ).getCanonicalPath();
		String testFileLocation = current+"/testing/DQ_test_20b_6c.csv";
		
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
		assertEquals(myTest5.winner.size(), 4);
		
		
		
		
				
	}

}
