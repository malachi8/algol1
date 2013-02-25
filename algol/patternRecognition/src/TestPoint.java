import org.junit.Test;


public class TestPoint {
	
	@Test
	public void testCompareTo(){
		Point p = new Point(8,4);
		Point q = new Point(7,2);
		Point r = new Point(8,8);
		
		System.out.println(p.SLOPE_ORDER.compare(q, r));		
		System.out.println(p.slopeTo(q));	
		System.out.println( p.slopeTo(r));	
	}

}
