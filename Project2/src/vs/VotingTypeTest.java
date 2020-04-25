/*
 * VotingTypeTest.java
 * Copyright (c) 2020, CSCI5801 Team5. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */
package vs;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.junit.jupiter.api.Test;
/**
 * test voting type 
 * @author Pengyin Chen
 *
 */
class VotingTypeTest {

	@Test
	/**
	 * test ReadFile() method 
	 * @throws IOException
	 */
	void VotingSystemReadFile() throws IOException {
		///Users/frankchen/Desktop/team5/repo-Team5/Project1/testing/DQ_test_20b_7c.csv

		String current = new java.io.File( "." ).getCanonicalPath();
		String testFileLocation = current+"/testing/DQ_test_20b_7c.csv";

		//Plurality Test
		VotingType myTest = new Plurality(1, testFileLocation);
		assertEquals(myTest.candidateCount, 0);
		assertEquals(myTest.ballotCount, 0);
		myTest.ReadFile();
		assertEquals(myTest.candidateCount, 7);
		assertEquals(myTest.ballotCount, 20);

		String testFileLocation1 = current+"/testing/DQ_test_20b_6c.csv";

		VotingType myTest1 = new Plurality(1, testFileLocation1);
		assertEquals(myTest1.candidateCount, 0);
		assertEquals(myTest1.ballotCount, 0);
		myTest1.ReadFile();
		assertEquals(myTest1.candidateCount, 6);
		assertEquals(myTest1.ballotCount, 20);

		//DQ Test
		VotingType myTest2 = new DroopQuota(1, testFileLocation);
		assertEquals(myTest2.candidateCount, 0);
		assertEquals(myTest2.ballotCount, 0);
		myTest2.ReadFile();
		assertEquals(myTest2.candidateCount, 7);
		assertEquals(myTest2.ballotCount, 20);

		String testFileLocation2 = current+"/testing/DQ_test_20b_6c.csv";

		VotingType myTest3 = new DroopQuota(1, testFileLocation2);
		assertEquals(myTest3.candidateCount, 0);
		assertEquals(myTest3.ballotCount, 0);
		myTest3.ReadFile();
		assertEquals(myTest3.candidateCount, 6);
		assertEquals(myTest3.ballotCount, 20);


	}

}
