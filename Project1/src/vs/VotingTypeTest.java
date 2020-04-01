package vs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class VotingTypeTest extends VotingType{
	public ArrayList<Candidate> candidateList = new ArrayList<Candidate>();
	public ArrayList<Ballot> ballotList = new ArrayList<Ballot>(); 

	@Override
	public void GenerateAudit() throws IOException {
		// TODO Auto-generated method stub
		String current = new java.io.File( "." ).getCanonicalPath();
		String auditFileLocation = current+"/src/vs/test.csv";
	}
	
    @Test
    public void testReadFile() {
    	
    }
}
