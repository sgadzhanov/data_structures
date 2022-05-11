package queue;


import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {

    static class Node<E> {
        private E value;
        private Node<E> next;

        public Node(E element) {
            this.value = element;
        }
    }

    private Node<E> first;
    private int size;

    public Queue() {
        this.first = null;
        this.size = 0;
    }

    @Override
    public void offer(E element) {
        Node<E> newNode = new Node<>(element);

        if (this.isEmpty()) {
            this.first = newNode; // if the queue is empty, the newNode becomes head
            this.size++;
            return;
        }
        Node<E> currentFirst = this.first;

        while (currentFirst.next != null) { // that way we can traverse the nodes until we find the head/first
            currentFirst = currentFirst.next; //
        }

        currentFirst.next = newNode; // when we find the first, the newNode becomes the new head

        this.size++;
    }

    @Override
    public E poll() {
        throwIfEmpty(); // we make sure the queue is not empty

        Node<E> currentFirst = this.first; // a temp node with first value

        this.first = currentFirst.next; // that way we poll/remove the head
        this.size--; // and then decrement the size

        return currentFirst.value;
    }

    private void throwIfEmpty() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Cannot poll from empty Queue.");
        }
    }

    @Override
    public E peek() {
        throwIfEmpty(); // we can't peek if the queue is empty

        return this.first.value; // and we only return current head value
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> node = first;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                E value = this.node.value;
                this.node = this.node.next;

                return value;
            }
        };
    }
}
