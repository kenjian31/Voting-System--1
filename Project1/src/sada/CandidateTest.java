package sada;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.util.ArrayList;

import org.junit.Test;

import vs.Ballot;
import vs.Candidate;

public class CandidateTest {
    
    @Test
    public void testGetBallotNum() {
    	Candidate myTest = new Candidate("Tony");
    	ArrayList<Ballot> ballot_list = new ArrayList<Ballot>();
        int[] list = new int[] {1,2,3,4,5};
        Ballot ballot1 = new Ballot(1, list);
        myTest.AddBallot(ballot1);
    	ballot_list.add(ballot1);
    	int i = 1;
    	int j = myTest.GetBallotNum();
    	assertEquals(i, j);
    	
    	myTest.ClearCount();
    	int z = myTest.GetBallotNum();
    	int a = 0;
    	assertEquals(a,z);
    }
    
    
    
    @Test
    public void testcompareTo() {
    	Candidate myTest = new Candidate("Tony");
    	Candidate test = new Candidate("k");
    	ArrayList<Ballot> ballot_list = new ArrayList<Ballot>();
    	int[] list = new int[] {1,2,3,4,5};
    	Ballot ballot1 = new Ballot(1, list);
    	test.AddBallot(ballot1);
    	int result = myTest.compareTo(test);
    	int i = 1;
    	assertEquals(i, result);
    	
    	Candidate test1 = new Candidate("aa");
    	int actual = test1.compareTo(test1);
    	int j = 0;
    	assertEquals(j, actual);
    	
    }    
    @Test
    public void testToString() {
    	Candidate myTest = new Candidate("Tony");
        String Exresult = "Candidate name is: Tony\n";
        String Exresult1 = "2 ";
        String result = myTest.toString();
        assertEquals(Exresult, result);
    }
    
}
