package worksap2016.summer.test;

import java.util.Random;
import java.util.Scanner;

public class GenTestCase {
	public static void main(String[] args) {
		Random random = new Random();
		Scanner scanner = new Scanner(System.in);
		int n = random.nextInt(5);
		System.out.println(n);
		for(int j = 0; j < n; j ++) {
			long a = Math.abs(random.nextLong() % 100L);
			System.out.print(a);
			if(j != n - 1) {
				System.out.print(" ");
			}
			else {
				System.out.println();
			}
		}
		scanner.close();
	}
}