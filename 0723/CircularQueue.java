public class CircularQueue {
    private int[] data;
    private int front;
    private int rear;
    private int size;

    public CircularQueue(int capacity) {
        if (capacity <= 0) {
            capacity = 1;
        }
        data = new int[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    public boolean offer(int value) {
        if (isFull()) {
            return false;
        }
        data[rear] = value;
        rear = (rear + 1) % data.length;
        size++;
        return true;
    }

    public Integer poll() {
        if (isEmpty()) {
            return null;
        }
        int value = data[front];
        front = (front + 1) % data.length;
        size--;
        return value;
    }

    public Integer peek() {
        if (isEmpty()) {
            return null;
        }
        return data[front];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    public void print() {
        System.out.print("front [ ");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % data.length;
            System.out.print(data[index] + " ");
        }
        System.out.println("] rear");
    }
}
