public class CircularQueueDemo {
    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(4);

        queue.offer(10);
        queue.offer(20);
        queue.offer(30);
        queue.print();

        System.out.println("poll：" + queue.poll());
        System.out.println("poll：" + queue.poll());
        queue.offer(40);
        queue.offer(50);
        queue.offer(60);
        queue.print();

        System.out.println("peek：" + queue.peek());
        System.out.println("full：" + queue.isFull());
    }
}
