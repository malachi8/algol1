import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class TestRandomizedQueue {

	private String[] oneToN = { "0", "1", "2", "3", "4", "5" };
	
	private String[] abc = {"AA", "BB", "BB", "BB", "BB", "BB", "CC", "CC"};

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
	
	@Test
	public void testAbc() {
		
		RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
		
		for(String strNumber : abc){
			randomizedQueue.enqueue(strNumber);
		}
		Iterator<String> iterator = randomizedQueue.iterator();
		 
		 int cnt = 7;
		 int j = 0;
		 while(iterator.hasNext() && j < cnt){
			 String strInt = iterator.next();
			 System.out.println(strInt);
			 j++;
		 }
		
		
	}

}
