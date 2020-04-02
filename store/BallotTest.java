package vs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BallotTest {

	@Test
	public void testBallotConstrcutor() {
		int[] list = new int[] {1,2,3,4,5};
		String Exresult1 = "1 ";
		String Exresult2 = "2 ";
		String Exresult3 = "3 ";
		Ballot test1 = new Ballot(1, list);
		Ballot test2 = new Ballot(10000,list);
		Ballot test3 = new Ballot(100, list);
		String result1 = test1.toString();
		String result2 = test2.toString();
		String result3 = test3.toString();
		
	
		assertNotSame(Exresult1, result1);
		assertNotSame(Exresult2, result2);
		assertNotSame(Exresult3, result3);
	}
	@Test
	public void testBallotVoteList() {
		int[] list1 = new int[] {1,2,3,4,5};
		int[] list2 = new int[100];
		for (int i=0; i< list2.length; i++) {
			list2[i] = i+1;
		}
		int[] list3 = new int[10000];
		for (int i=0; i< list3.length; i++) {
			list3[i] = i+1;
		}
		//small, medium, and large list 
		Ballot test1 = new Ballot(1, list1);
		Ballot test2 = new Ballot(10000,list2);
		Ballot test3 = new Ballot(100, list3);
		
		for (int i =0 ; i < test1.vote_list.length; i++) {
			int Expint = test1.vote_list[i];
			assertEquals(Expint, i+1);
		}
		
		for (int i =0 ; i < test2.vote_list.length; i++) {
			int Expint = test2.vote_list[i];
			assertEquals(Expint, i+1);
		}
		
		for (int i =0 ; i < test3.vote_list.length; i++) {
			int Expint = test3.vote_list[i];
			assertEquals(Expint, i+1);
		}
	}

	@Test
	public void testToString() {
		int[] list1 = new int[] {1,2,3,4,5};
		int[] list2 = new int[100];
		for (int i=0; i< list2.length; i++) {
			list2[i] = i+1;
		}
		int[] list3 = new int[10000];
		for (int i=0; i< list3.length; i++) {
			list3[i] = i+1;
		}
		//small, medium, and large list 
		Ballot test1 = new Ballot(1, list1);
		Ballot test2 = new Ballot(10000,list2);
		Ballot test3 = new Ballot(100, list3);
		
		String result = test1.toString();
		assertEquals("1 ", result);

		String result1 = test2.toString();
		assertEquals("10000 ", result1);
		
		String result2 = test3.toString();
		assertEquals("100 ", result2);
	}
}
		