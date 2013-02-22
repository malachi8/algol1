import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class TestRandomizedQueue {

	private String[] oneToN = { "0", "1", "2", "3", "4", "5" };

	@Test
	public void testIterator() {
		RandomizedQueue<String> deque = new RandomizedQueue<String>();

		for (String strNumber : oneToN) {
			deque.enqueue(strNumber);
		}

		Iterator<String> iterator = deque.iterator();

		int j = oneToN.length;
		while (iterator.hasNext()) {
			String str = iterator.next();
			System.out.println(str);
			assert (str != null);
		}
	}
	
	@Test
	public void testOneToN() {
		
		RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
		
		for(String strNumber : oneToN){
			randomizedQueue.enqueue(strNumber);
		}
		
		int j = oneToN.length;
		for(int i = 0; i < oneToN.length; i++){ 
			assert(randomizedQueue.dequeue().equals(oneToN[j - 1]));
		}
		
		
	}

}
