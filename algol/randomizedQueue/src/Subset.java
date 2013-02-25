import java.util.Iterator;


public class Subset {
	
	 public static void main(String[] args){
		  
		 RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
		 
		 while(!StdIn.isEmpty()){
			 randomizedQueue.enqueue(StdIn.readString());
		 }
		 
		 Iterator<String> iterator = randomizedQueue.iterator();
		 
		 int cnt = Integer.parseInt(args[0]);
		 int j = 0;
		 while(iterator.hasNext() && j < cnt){
			 String strInt = iterator.next();
			 System.out.println(strInt);
			 j++;
		 }
	 }

}
