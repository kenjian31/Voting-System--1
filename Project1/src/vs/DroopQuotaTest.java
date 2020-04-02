package vs;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class DroopQuotaTest {
	
	@Test
    public void testQuotaSeats() throws IOException {
		
		// test file 
		// 20 ballots 
		// 7 candidates 
		String current = new java.io.File( "." ).getCanonicalPath();
		String testFileLocation = current+"/testing/DQ_test_20b_7c.csv";
		
		DroopQuota myTest = new DroopQuota(1, testFileLocation);
		assertEquals(myTest.total_seat, 1);
		
		DroopQuota myTest2 = new DroopQuota(4, testFileLocation);
		assertEquals(myTest2.total_seat, 4);
		
		DroopQuota myTest3 = new DroopQuota(100, testFileLocation);
		assertEquals(myTest3.total_seat, 100);
		
		DroopQuota myTest4 = new DroopQuota(10000, testFileLocation);
		assertEquals(myTest4.total_seat, 10000);
       	
	}
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
	
	}
	
	@Test
	public void testDroopQuotaGenerateAudit() throws IOException {
		String current = new java.io.File( "." ).getCanonicalPath();
		String testFileLocation = current+"/testing/DQ_test_20b_7c.csv";
		
		DroopQuota myTest = new DroopQuota(4, testFileLocation);
		assertEquals(myTest.winner.size(), 0);
		myTest.ReadFile();
		myTest.GenerateAudit();
		assertEquals(myTest.winner.size(), 4);
		
		DroopQuota myTest2 = new DroopQuota(0, testFileLocation);
		assertEquals(myTest2.winner.size(), 0);
		myTest2.ReadFile();
		myTest2.GenerateAudit();
		assertEquals(myTest2.winner.size(), 0);
		

		//random case 
		DroopQuota myTest3 = new DroopQuota(0, testFileLocation);
		assertEquals(myTest3.winner.size(), 0);
		myTest3.ReadFile();
		myTest3.GenerateAudit();
		assertEquals(myTest3.winner.size(), 0);
		
		DroopQuota myTest5 = new DroopQuota(2, testFileLocation);
		assertEquals(myTest5.winner.size(), 0);
		myTest3.ReadFile();
		myTest3.GenerateAudit();
		assertEquals(myTest5.winner.size(), 2);
		
		DroopQuota myTest4 = new DroopQuota(1000, testFileLocation);
		assertEquals(myTest4.winner.size(), 0);
		myTest4.ReadFile();
		myTest4.GenerateAudit();
		assertEquals(myTest4.winner.size(), 4);
       	
	}

}

