package recursion;

import java.util.ArrayDeque;
import java.util.Deque;

enum Breakpoint {
	BEGIN, AFTER_FIRST_FIBO, AFTER_SECOND_FIBO, RETURN
}

class StackFrame {
	public StackFrame(int n) {
		this.n = n;
		this.accumulator = 0xdeadbeef;
		this.returnValue = 0xdeadbeef;
		this.breakpoint = null;
	}

	int n;
	int accumulator;
	int returnValue;
	Breakpoint breakpoint;
}

public class FibonacciSimulator {

	// Rewrite the recursive version to a form that's closer to machine
	// instructions.
	//
	// @formatter:off
	// BEGIN:
	//   int accumulator;
	//   if (n == 0) {
	//     accumulator = 0;
	//     goto END;
	//   }
	//   if (n == 1) {
	//     accumulator = 1;
	//     goto END;
	//   }
	//   accumulator = 0;
	//   int returnValue = fibo(n - 1);
	//
	// AFTER_FIRST_FIBO:
	//   accumulator += returnValue;
	//   int returnValue = fibo(n - 2);
	//
	// AFTER_SECOND_FIBO:
	//   accumulator += returnValue;
	//
	// RETURN:
	//   return accumulator;
	// @formatter:on
	public static int compute(int n) {
		Deque<StackFrame> callStack = new ArrayDeque<StackFrame>();
		callStack.push(new StackFrame(n));
		Breakpoint instructionPointer = Breakpoint.BEGIN;

		int result = 0;
		while (!callStack.isEmpty()) {
			StackFrame topFrame = callStack.peek();
			switch (instructionPointer) {
			case BEGIN:
				if (topFrame.n == 0) {
					topFrame.accumulator = 0;
					instructionPointer = Breakpoint.RETURN;
				} else if (topFrame.n == 1) {
					topFrame.accumulator = 1;
					instructionPointer = Breakpoint.RETURN;
				} else {
					topFrame.accumulator = 0;
					topFrame.breakpoint = Breakpoint.AFTER_FIRST_FIBO;
					callStack.push(new StackFrame(topFrame.n - 1));
					instructionPointer = Breakpoint.BEGIN;
				}
				break;
			case AFTER_FIRST_FIBO:
				topFrame.accumulator += topFrame.returnValue;
				topFrame.breakpoint = Breakpoint.AFTER_SECOND_FIBO;
				callStack.push(new StackFrame(topFrame.n - 2));
				instructionPointer = Breakpoint.BEGIN;
				break;
			case AFTER_SECOND_FIBO:
				topFrame.accumulator += topFrame.returnValue;
				instructionPointer = Breakpoint.RETURN;
			case RETURN:
				int returnValue = topFrame.accumulator;
				callStack.pop();
				if (callStack.isEmpty()) {
					result = returnValue;
				} else {
					callStack.peek().returnValue = returnValue;
					instructionPointer = callStack.peek().breakpoint;
				}
				break;
			}
		}
		return result;
	}
}
