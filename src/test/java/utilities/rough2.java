package utilities;

import java.util.*;

public class rough2 extends rough{
		
	public static void main(String[] args) {
		LinkedList<String> l1 = new LinkedList<>();
		l1.add("a");
		l1.add("b");
		l1.add("c");
		l1.add("d");
		l1.add("e");
		l1.add("f");
		System.out.println("l1 = " + l1);
		Collections.sort(l1, Collections.reverseOrder());
		System.out.println("l1 = " + l1);
	}
	
}
