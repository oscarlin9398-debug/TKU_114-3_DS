public class IntLinkedList {
    private IntNode head;
    private int size;

    public IntLinkedList() {
        head = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addFirst(int value) {
        IntNode newNode = new IntNode(value);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addLast(int value) {
        IntNode newNode = new IntNode(value);
        if (head == null) {
            head = newNode;
            size++;
            return;
        }

        IntNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        size++;
    }

    public boolean contains(int target) {
        IntNode current = head;
        while (current != null) {
            if (current.data == target) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public Integer removeFirst() {
        if (head == null) {
            return null;
        }
        int removed = head.data;
        head = head.next;
        size--;
        return removed;
    }

    public boolean removeValue(int target) {
        if (head == null) {
            return false;
        }
        if (head.data == target) {
            head = head.next;
            size--;
            return true;
        }

        IntNode previous = head;
        IntNode current = head.next;
        while (current != null) {
            if (current.data == target) {
                previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public void reverse() {
        IntNode previous = null;
        IntNode current = head;

        while (current != null) {
            IntNode nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
        }
        head = previous;
    }

    public void print() {
        IntNode current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
