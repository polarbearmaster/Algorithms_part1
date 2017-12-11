import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		//StdOut.println("k="+k);
		
		//Deque<String> dq = new Deque<String>();
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		
		
		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			//StdOut.println(s);
			//dq.addLast(s);
			rq.enqueue(s);
		}
		
		//StdOut.println("-------------------------------");
		
//		Iterator<String> it = dq.iterator();
//		
//		for (int i = 0; i < k;i++) {
//			String s = it.next();
//			StdOut.println(s);
//		}
		
//		for (String s: q) {
//			StdOut.println(s);
//		}
		
		
		
		//StdOut.println(rq.size());
//		for (String s: rq) {
//			StdOut.println(s);
//		}
		
//		StdOut.println(rq.sample());
//		StdOut.println(rq.sample());
//		StdOut.println(rq.sample());
		
		Iterator<String> itrq = rq.iterator();
		
		for (int i = 0; i < k;i++) {
			String s = itrq.next();
			StdOut.println(s);
		}
	}
}
