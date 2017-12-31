package recursion;

import java.util.ArrayDeque;
import java.util.Deque;

enum InstructionPointer {
	FIRST_FIBO, SECOND_FIBO, END,
}

class StackFrame {
	public StackFrame(int n) {
		this.n = n;
		this.ip = InstructionPointer.FIRST_FIBO;
		this.accumulator = 0;
	}

	int n;
	InstructionPointer ip;
	int accumulator;
}

public class FibonacciRecursionSimulator {

	public static int fibonacci(int n) {
		Deque<StackFrame> callStack = new ArrayDeque<StackFrame>();
		callStack.push(new StackFrame(n));
		while (callStack.size() > 1 || callStack.peek().ip != InstructionPointer.END) {
			StackFrame topFrame = callStack.peek();
			switch (topFrame.ip) {
			case FIRST_FIBO:
				if (topFrame.n == 0) {
					topFrame.ip = InstructionPointer.END;
					topFrame.accumulator = 0;
				} else if (topFrame.n == 1) {
					topFrame.ip = InstructionPointer.END;
					topFrame.accumulator = 1;
				} else {
					topFrame.ip = InstructionPointer.SECOND_FIBO;
					callStack.push(new StackFrame(topFrame.n - 1));
				}
				break;
			case SECOND_FIBO:
				topFrame.ip = InstructionPointer.END;
				callStack.push(new StackFrame(topFrame.n - 2));
				break;
			case END:
				int returnValue = topFrame.accumulator;
				callStack.pop();
				callStack.peek().accumulator += returnValue;
				break;
			}
		}
		return callStack.peek().accumulator;
	}
}
