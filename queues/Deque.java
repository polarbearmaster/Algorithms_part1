import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
	
	private Node first;
	private Node last;
	private int N;
	
	private class Node {
		Item item;
		Node previous;
		Node next;
	}	
	public Deque()                           // construct an empty deque
	{
		this.N = 0;
	}
	public boolean isEmpty()                 // is the deque empty?
	{
		return first == null;
	}
	public int size()                        // return the number of items on the deque
	{
		return N;
	}
	public void addFirst(Item item)          // add the item to the front
	{
		if (item == null) throw new java.lang.NullPointerException("client attempts to add a null item");
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.previous = null;
		first.next = oldfirst;	
		if (isEmpty()) last = first;
		else oldfirst.previous = first;	
		N++;
	}
	public void addLast(Item item)           // add the item to the end
	{
		if (item == null) throw new java.lang.NullPointerException("client attempts to add a null item");
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		last.previous = oldlast;
		if (isEmpty()) first = last;
		else oldlast.next = last;
		N++;
	}
	public Item removeFirst()                // remove and return the item from the front
	{
		if (isEmpty()) throw new java.util.NoSuchElementException("client attempts to remove an item from an empty deque");
		Item item = first.item;
		first = first.next;
		first.previous = null;
		if (isEmpty()) last = null;
		N--;
		return item;
	}
	public Item removeLast()                 // remove and return the item from the end
	{
		if (isEmpty()) throw new java.util.NoSuchElementException("client attempts to remove an item from an empty deque");
		Item item = last.item;
		last = last.previous;
		last.next = null;
		if (isEmpty()) first = null;
		N--;
		return item;
	}
	public Iterator<Item> iterator()         // return an iterator over items in order from front to end
	{
		return new ListIterator();
	}
	private class ListIterator implements Iterator<Item>
	{
		private Node current = first;
		@Override
		public boolean hasNext() {
			return current != null;
		}
		
		@Override
		public Item next() {
			if (current == null) throw new java.util.NoSuchElementException
			("client calls the next() method in the iterator and there are no more items to return");
			Item item = current.item;
			current = current.next;
			return item;
		}
		
		public void remove() {}
	}
	public static void main(String[] args)   // unit testing (optional)
	{
		
	}

}
