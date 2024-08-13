import java.util.Arrays;

public class StaticStack<T> {
    private int top = -1;
    private T[] data;

    public StaticStack(int size) {
        data = (T[]) new Object[size];  
    }

    public void push(T item) {
        if (isFull()) {
            throw new StackOverflowError("Stack is full");
        }
        data[++top] = item;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return data[top--];
    }

    public void clear() {
        Arrays.fill(data, null);
        top = -1;
    }

    public boolean isFull() {
        return top == data.length - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public T[] getData() {
        return Arrays.copyOf(data, top + 1);
    }
}