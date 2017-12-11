import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
   private Node first;
   private Node last;
   private int N;
   private class Node {
	   Item item;
	   Node next;
   }
   public RandomizedQueue()                 // construct an empty randomized queue
   {
	   N = 0;
   }
   public boolean isEmpty()                 // is the queue empty?
   {
	   return first == null;
   }
   public int size()                        // return the number of items on the queue
   {
	   return N;
   }
   public void enqueue(Item item)           // add the item
   {
	   if (item == null) throw new java.lang.NullPointerException("client attempts to add a null item");
//	   int r;
//	   if (isEmpty()) r = 0;
//	   else r = StdRandom.uniform(N+1);
//	   Node insertion = goRSteps(r);
//	   Node oldInsertion = insertion;
//	   insertion = new Node();
//	   insertion.item = item;
//	   insertion.next = oldInsertion;
	   // 向表尾添加元素
	   Node oldlast = last;
	   last = new Node();
	   last.item = item;
	   last.next = null;
	   if (isEmpty()) first = last;
	   else oldlast.next = last;
	   N++;
   }
   public Item dequeue()                    // remove and return a random item
   {
	   if (isEmpty()) throw new java.util.NoSuchElementException("client attempts to remove an item from an empty deque");
	   int r = StdRandom.uniform(N);
	   Node insertion = goRSteps(r);
	   Item item = insertion.item;
	   insertion = insertion.next;
	   if (isEmpty()) last = null;
	   N--;
	   return item;
   }
   public Item sample()                     // return (but do not remove) a random item
   {
	   if (isEmpty()) throw new java.util.NoSuchElementException("client attempts to remove an item from an empty deque");
	   int r = StdRandom.uniform(N);
	   Node insertion = goRSteps(r);
	   Item item = insertion.item;
	   return item;
   }
   public Iterator<Item> iterator()         // return an independent iterator over items in random order
   {
	   return new ListIterator();
   }
   private class ListIterator implements Iterator<Item> {
	   private int currentNumber=0;
	   private int[] a;
	   
	   public ListIterator() {
		   a = new int[N];
		   for (int i = 0; i < N; i++){
			   a[i] = i;
		   }
		   StdRandom.shuffle(a);//随机
		   
	   }
	@Override
	public boolean hasNext() {
		return currentNumber<N;
	}

	@Override
	public Item next() {
		if (currentNumber == N) throw new java.util.NoSuchElementException
		("client calls the next() method in the iterator and there are no more items to return");
		int r = a[currentNumber];
		Node n = goRSteps(r);
		Item item = n.item;
		currentNumber++;
		return item;
	}
	
	public void remove() {}
	   
   }
   private Node goRSteps(int r) {
	   Node initial = first;
	   if (r == 0) return initial;
	   
	   for (int i = 0; i < r; i++) {
		   initial = initial.next;
	   }
	   return initial;
   }
   public static void main(String[] args)   // unit testing (optional)
   {
	   
   }
}