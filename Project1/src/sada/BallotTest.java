package sada;

import org.junit.Test;

import vs.Ballot;

import static org.junit.Assert.*;

public class BallotTest {

    @Test
    public void testBallot() {
        int[] list = new int[] {1,2,3,4,5};
        Ballot myUnit = new Ballot(1, list);
        String Exresult = "1 ";
        String Exresult1 = "2 ";
        String result = myUnit.toString();
        assertEquals(Exresult, result);
        assertNotSame(Exresult1, result);
        
        Ballot test1 = new Ballot(10000, list);
        String actual = test1.toString();
        assertNotSame(Exresult, actual);

    }
}