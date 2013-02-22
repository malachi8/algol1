import java.util.Iterator;
import java.util.NoSuchElementException;



public class RandomizedQueue <Item> implements Iterable<Item> {
    private Item[] a;         // array of items
    private int N;            // number of elements on stack

    // create an empty stack
    @SuppressWarnings("unchecked")
	public RandomizedQueue() {
        a = (Item[]) new Object[2];
    }

    public boolean isEmpty() { return N == 0; }
    public int size()        { return N;      }



    // resize the underlying array holding the elements
    @SuppressWarnings("unchecked")
	private void resize(int capacity) {
        assert capacity >= N;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    // push a new item onto the stack
    public void enqueue(Item item) {
        if (N == a.length) resize(2*a.length);    // double size of array if necessary
        a[N++] = item;                            // add item
    }

    // delete and return the item most recently added
    public Item pop() {
        if (isEmpty()) { throw new RuntimeException("Stack underflow error"); }
        Item item = a[N-1];
        a[N-1] = null;                              // to avoid loitering
        N--;
        // shrink size of array if necessary
        if (N > 0 && N == a.length/4) resize(a.length/2);
        return item;
    }
    
    public Item dequeue() {
        if (isEmpty()) { throw new RuntimeException("Stack underflow error"); }
        
        int randomIndex = StdRandom.uniform(N);
        Item item = a[randomIndex];
        for (int i = randomIndex; i < N - 1; i++){
        	a[i] = a[i + 1];
        }
        a[N] = null;                              // to avoid loitering
        N--;
        // shrink size of array if necessary
        if (N > 0 && N == a.length/4) resize(a.length/2);
        return item;
    }
    
    public Item sample() {
        if (isEmpty()) { throw new RuntimeException("Stack underflow error"); }
        return a[StdRandom.uniform(N)];
        
    }


    public Iterator<Item> iterator()  { return new RandomArrayIterator();  }

    // an iterator, doesn't implement remove() since it's optional
    private class RandomArrayIterator implements Iterator<Item> {
        private int i;
        private Item[] copyOfa;   

        public RandomArrayIterator() {
            i = N;        
            copyOfa = (Item[]) new Object[N];
            int j = 0;
            for (Item item : a){
            	if(item != null)
            		copyOfa[j] = item;
            		j++;
            }
            StdRandom.shuffle(copyOfa);
        }

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copyOfa[--i];
        }
    }



	}
