package recursion;

public class Fibonacci {
	public static int compute(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		return compute(n - 1) + compute(n - 2);
	}
}
