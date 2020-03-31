package sada;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import vs.Ballot;
import vs.Candidate;

public class GuiTest {
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
    }
}
