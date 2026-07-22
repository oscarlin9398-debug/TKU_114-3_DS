public class ArrayStack {
    private int[] data;
    private int top;

    public ArrayStack(int capacity) {
        if (capacity <= 0) {
            capacity = 1;
        }
        data = new int[capacity];
        top = -1;
    }

    public boolean push(int value) {
        if (isFull()) {
            return false;
        }
        top++;
        data[top] = value;
        return true;
    }

    public Integer pop() {
        if (isEmpty()) {
            return null;
        }
        int value = data[top];
        top--;
        return value;
    }

    public Integer peek() {
        if (isEmpty()) {
            return null;
        }
        return data[top];
    }

    public int size() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == data.length - 1;
    }

    public void print() {
        System.out.print("top [ ");
        for (int i = top; i >= 0; i--) {
            System.out.print(data[i] + " ");
        }
        System.out.println("] bottom");
    }
}