package recursion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RecursionTest {

	@Test
	void testSimulatedFibonacci() {
		assertEquals(8, FibonacciRecursionSimulator.fibonacci(5));
	}

}
