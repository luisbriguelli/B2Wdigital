import static org.junit.Assert.*;

/**
* 
* Created by Luis Fernando Briguelli da Silva on 13/06/2016.
* 
*/
public class Test {
	Main m = new Main();
	
	@org.junit.Test
	public void rowsNumberInvalid() {
		m.rowsNumber = 0;
		assertEquals(false, m.isRowsNumberValid(m.rowsNumber));
	}
	
	@org.junit.Test
	public void rowsNumberValid() {
		m.rowsNumber = 1;
		assertEquals(true, m.isRowsNumberValid(m.rowsNumber));
	}
	
	@org.junit.Test
	public void calculateHellTriangule() {
		m.rowsNumber=2;
		m.array = new int[][] {{0},{1,2}};
		assertEquals(2, m.calculateHellTriangle(1,0,m.array[0][0]));	
	}
	
	@org.junit.Test
	public void calculateHellTrianguleLikeExample() {
		m.rowsNumber=4;
		m.array = new int[][] {{6}, {3,5}, {9,7,1}, {4,6,8,4}};
		assertEquals(26, m.calculateHellTriangle(1,0,m.array[0][0]));
	}
	
}
