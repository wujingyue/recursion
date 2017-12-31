package recursion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RecursionTest {

	@Test
	void testSimulatedFibonacci() {
		assertEquals(Fibonacci.compute(0), FibonacciSimulator.compute(0));
		assertEquals(Fibonacci.compute(1), FibonacciSimulator.compute(1));
		assertEquals(Fibonacci.compute(2), FibonacciSimulator.compute(2));
		assertEquals(Fibonacci.compute(3), FibonacciSimulator.compute(3));
		assertEquals(Fibonacci.compute(4), FibonacciSimulator.compute(4));
		assertEquals(Fibonacci.compute(5), FibonacciSimulator.compute(5));
	}

}
