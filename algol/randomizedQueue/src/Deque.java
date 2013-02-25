import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	
    private int N;          // size of the stack
    private Node first;     // top of stack
    private Node last;     // top of stack

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

   /**
     * Create an empty stack.
     */
    public Deque() {
        first = null;
        last = null;
        N = 0;
        assert check();
    }

   /**
     * Is the stack empty?
     */
    public boolean isEmpty() {
        return first == null;
    }

   /**
     * Return the number of items in the stack.
     */
    public int size() {
        return N;
    }

   /**
     * Add the item to the stack.
     */
    public void addFirst(Item item) {
    	if(item == null)
    		throw new NullPointerException();
        Node oldfirst = first;
      
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        
        if(oldfirst == null)
        	last = first;
        else
        	oldfirst.prev = first;
        
        N++;
        assert check();
    }

   /**
     * Delete and return the item most recently added to the stack.
     * @throws java.util.NoSuchElementException if stack is empty.
     */
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = first.item;        // save item to return
             
        N--;
        if(N == 0){
        	last = null;
        	first = null;
        } else {
        	first = first.next; 
        	first.prev = null;
        }
        
        assert check();
        return item;                   // return the saved item
    }

    // check internal invariants
    private boolean check() {
        if (N == 0) {
            if (first != null) return false;
        }
        else if (N == 1) {
            if (first == null)      return false;
            if (first.next != null) return false;
        }
        else {
            if (first.next == null) return false;
        }

        // check internal consistency of instance variable N
        int numberOfNodes = 0;
        for (Node x = first; x != null; x = x.next) {
            numberOfNodes++;
        }
        if (numberOfNodes != N) return false;

        return true;
    } 


   /**
     * Return an iterator to the stack that iterates through the items in LIFO order.
     */
    public Iterator<Item> iterator()  { return new ListIterator();  }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }


	public void addLast(Item item) {
		// insert the item at the end
		
		if(item == null)
    		throw new NullPointerException();
		
		if (isEmpty()) {
			addFirst(item);
			return;
		}

		
		Node newNode = new Node();
		newNode.item = item;
		last.next = newNode;
		newNode.prev = last;
		last = newNode;
		 N++;
	}

	public Item removeLast() {
		// delete and return the item at the end
		if (isEmpty()) {
			return null;
		}
		
		if(N == 1)
			return removeFirst();
		
		Item returned = last.item;
		last = last.prev;	
		last.next = null;
		 N--;
		 
		return returned;
	}

	
}