package worksap2016.summer;

import java.util.Scanner;

// save pre n number xor result
class TrieNode {
	TrieNode left;
	TrieNode right;
}

public class Main {
	static int n;
	static long[] a;
	static TrieNode head;
	
	// add to trie
	private static void addTrie(long val) {
		TrieNode p = head;
		for(long bitOp = 0x10000000000L; bitOp > 0; bitOp >>= 1) {
			if((bitOp & val) == 0) {
				if(p.left == null) {
					p.left = new TrieNode();
				}
				p = p.left;
			}
			else {
				if(p.right == null) {
					p.right = new TrieNode();
				}
				p = p.right;
			}
		}
	}
	
	// find max by the specified right numbers
	private static long max(long xorRight) {
		long maxV = 0;
		TrieNode p = head;
		for(long bitOp = 0x10000000000L; bitOp > 0; bitOp >>= 1) {
			boolean zero = ((bitOp & xorRight) == 0);
			// find way to max
			if(p.right != null && zero) {
				maxV += bitOp;
				p = p.right;
			}
			else if(p.left != null && !zero) {
				maxV += bitOp;
				p = p.left;
			}
			else if(zero) {
				p = p.left;
			}
			else {
				p = p.right;
			}
		}
		return maxV;
	}
	
	private static void solve() {
		long maxV = 0;
		// init trie
		addTrie(0);
		long xor = 0;
		// find max using trie and right numbers' xor
		for(int i = 0; i < n; i ++) {
			xor ^= a[i];
			addTrie(xor);
		}
		// cal right numbers' xor
		long xorRight = 0;
		for(int j = n - 1; j >= 0; j --) {
			xorRight ^= a[j];
			// save temporary max
			long value = max(xorRight);
			maxV = maxV > value ? maxV : value;
		}
		// output
		System.out.println(maxV);
	}
	
	public static void main(String[] args) {
		// input
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()) {
			n = scanner.nextInt();
			a = new long[n];
			head = new TrieNode();
			for(int i = 0; i < n; i ++) {
				a[i] = scanner.nextLong();
			}
			// handle problem
			solve();
		}
		scanner.close();
	}
	
//	private static void test() {
//		Random random = new Random();
//		n = random.nextInt(10);
//		a = new long[n];
//		head = new TrieNode();
//		System.out.println(n);
//		for(int j = 0; j < n; j ++) {
//			long temp = Math.abs(random.nextLong() % 1000000000000L);
//			a[j] = temp;
//			System.out.print(temp);
//			if(j != n - 1) {
//				System.out.print(" ");
//			}
//			else {
//				System.out.println();
//			}
//		}
//		solve();
//	}
	
	// test cases
//	public static void main(String[] args) {
//		test();
//	}
}