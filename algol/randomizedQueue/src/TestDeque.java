
import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;


public class TestDeque {
	
	private String[] oneToN = {"0", "1", "2", "3", "4", "5"};
	
	

	@Test
	public void testIterator() {
		Deque<String> deque = new Deque<String>();
		
		for(String strNumber : oneToN){
			deque.addFirst(strNumber);
		}

		Iterator<String> iterator = deque.iterator();
		
		int j = oneToN.length;
		while(iterator.hasNext()){
			String str = iterator.next();
			System.out.println(str);
			assert(str.equals(oneToN[j - 1]));
		}
	}
	
	@Test
	public void testOneToN() {
		
		Deque<String> deque = new Deque<String>();
		
		for(String strNumber : oneToN){
			deque.addFirst(strNumber);
		}
		
		int j = oneToN.length;
		for(int i = 0; i < oneToN.length; i++){ 
			assert(deque.removeLast().equals(oneToN[j - 1]));
		}
		
		assert(deque.isEmpty());
	}
	

}
