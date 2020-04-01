package sada;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import vs.Ballot;
import vs.Candidate;
import vs.Plurality;

public class PluralityTest {

    @Test
    public void testmain() throws IOException {
    	ArrayList<Candidate> candidate_list = new ArrayList<Candidate>();
       	Candidate myTest1 = new Candidate("Tony");
       	Candidate myTest2 = new Candidate("k");
    	Candidate list = myTest1;
//    	Candidate list1 = myTest2;
    	Plurality myTest = new Plurality(1, "/Users/KenJian/Desktop/Project1/src/vs/test.csv");
		candidate_list.add(list);
//		candidate_list.add(list1);
		myTest.RandomWinner(candidate_list, 1);
		myTest.GenerateAudit();
		String[] arg = new String[] {"/Users/KenJian/Desktop/Project1/src/vs/test.csv"};
		myTest.main(arg);
			
//    	assertEquals(exseat, 1); 	
    	
    }

}
