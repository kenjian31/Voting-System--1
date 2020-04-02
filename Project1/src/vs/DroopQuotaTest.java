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
	}

}
