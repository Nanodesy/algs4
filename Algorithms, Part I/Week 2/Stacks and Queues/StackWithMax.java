/**
 * Question 2.
 * Stack with max. Create a data structure that efficiently supports the stack operations (push
 * and pop) and also a return-the-maximum operation. Assume the elements are real numbers so that
 * you can compare them.
 * @param <E> double and it's subtypes.
 */
public class StackWithMax<E extends Double> {
    private final Stack<E> stack = new Stack<>();
    private final Stack<E> maxStack = new Stack<>();

    public void push(E item) {
        stack.push(item);

        if (maxStack.isEmpty() || item.compareTo(maxStack.peek()) > 0) {
            maxStack.push(item);
        }
    }

    public E pop() {
        E item = stack.pop();
        if (item.compareTo(maxStack.peek()) == 0) {
            maxStack.pop();
        }
        return item;
    }

    public E getMax() {
        return maxStack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

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

        public E peek() {
            return stack[n - 1];
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
