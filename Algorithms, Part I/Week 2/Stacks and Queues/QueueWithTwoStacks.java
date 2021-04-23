/**
 * Question 1.
 * Queue with two stacks. Implement a queue with two stacks so that each queue operations
 * takes a constant amortized number of stack operations.
 * @param <E> data type.
 */
public class QueueWithTwoStacks<E> {
    private final Stack<E> enqueueStack;
    private final Stack<E> dequeueStack;

    public QueueWithTwoStacks() {
        enqueueStack = new Stack<>();
        dequeueStack = new Stack<>();
    }

    public void enqueue(E item) {
        enqueueStack.push(item);
    }

    public E dequeue() {
        if (dequeueStack.isEmpty()) {
            while (!enqueueStack.isEmpty()) {
                dequeueStack.push(enqueueStack.pop());
            }
        }
        return dequeueStack.pop();
    }

    public boolean isEmpty() {
        return enqueueStack.isEmpty() || dequeueStack.isEmpty();
    }

    /**
     * Inner static class Stack for solving this task (I prefer use standard classes or my own,
     * rather than algs4 library's classes).
     * @param <E> data type.
     */
    private static class Stack<E> {
        private E[] stack;
        private int n = 0;

        public Stack() {
            stack = (E[]) new Object[1];
        }

        public void push(E item) {
            if (n == stack.length) resize(2 * stack.length);
            stack[n++] = item;
        }

        public E pop() {
            E item = stack[--n];
            stack[n] = null;
            if (n > 0 && n == stack.length / 4) resize(stack.length / 2);
            return item;
        }

        private void resize(int capacity) {
            E[] copy = (E[]) new Object[capacity];
            if (n >= 0) System.arraycopy(stack, 0, copy, 0, n);
            stack = copy;
        }

        public boolean isEmpty() {
            return n == 0;
        }
    }
}
