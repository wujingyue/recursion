package recursion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RecursionTest {

	@Test
	void testSimulatedFibonacci() {
		assertEquals(Fibonacci.compute(5), FibonacciRecursionSimulator.fibonacci(5));
	}

}
