package vs;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class PluralityTest {

	@Test
    public void testPluralitySeats() throws IOException {
		
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
		assertEquals(myTest.ballotCount, 100);
       	
	}
	
	public void testPluralityGenerateAudit() throws IOException {
		String current = new java.io.File( "." ).getCanonicalPath();
		String testFileLocation = current+"/testing/plurality_test_5000b_4c.csv";
		
		Plurality myTest = new Plurality(4, testFileLocation);
		assertEquals(myTest.winner.size(), 0);
		myTest.GenerateAudit();
		assertEquals(myTest.winner.size(), 4);
		
		Plurality myTest2 = new Plurality(0, testFileLocation);
		assertEquals(myTest2.winner.size(), 0);
		myTest2.GenerateAudit();
		assertEquals(myTest2.winner.size(), 0);
		

		//random case 
		Plurality myTest3 = new Plurality(1, testFileLocation);
		assertEquals(myTest3.winner.size(), 0);
		myTest3.GenerateAudit();
		assertEquals(myTest3.winner.size(), 1);
		if(myTest3.winner.get(0).candidate_name == "R" || 
				myTest3.winner.get(0).candidate_name == "D") {
		assertEquals(1, 1);
		} else {
			assertEquals(1, 0);
		}

		Plurality myTest4 = new Plurality(1000, testFileLocation);
		assertEquals(myTest3.winner.size(), 0);
		myTest4.GenerateAudit();
		assertEquals(myTest3.winner.size(), 4);
       	
	}
}
