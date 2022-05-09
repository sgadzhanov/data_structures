package linkedlist;

import java.util.Iterator;

public class SinglyLinkedList<E> implements LinkedList<E> {

    private Node<E> head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    static class Node<E> {
        private E value;
        private Node<E> next;

        public Node(E element) {
            this.value = element;
        }
    }

    @Override
    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element); // creating a newNode with the given element
        if (this.isEmpty()) { // if the list is empty, automatically our newNode becomes a head
            this.head = newNode;
        } else {
            newNode.next = this.head; // the current head will become next to our newNode/new head
            this.head = newNode;
        }
        this.size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> newNode = new Node<>(element);

        if (this.isEmpty()) {
            this.head = newNode; // if the list is empty, our newNode becomes last and head in the same time
        } else {

            Node<E> currentHead = this.head; //a temp which holds head's value

            while (currentHead.next != null) { // we need to traverse it until we find the last element
                currentHead = currentHead.next; // and in every iteration, we need to move our temp(with current head value) to the next
            }

            currentHead.next = newNode; // when our temp has reached the last element, we make our newNode to its next
        }
        this.size++;
    }

    @Override
    public E removeFirst() {
        throwIfEmpty();

        Node<E> currentHead = this.head;

        this.head = currentHead.next; // when we remove our head, we need to make sure that current head's next is our head
        this.size--;
        return currentHead.value;
    }

    @Override
    public E removeLast() {
        throwIfEmpty();

        if (this.size == 1) {
            E value = this.head.value; // if we have only one element in our list, we will remove our head
            this.head = null;

            return value;
        }

        Node<E> preLast = this.head; // we need to store the before last element
        Node<E> currentLast = this.head.next;

        while (currentLast.next != null) { // that's the way we traverse nodes
            preLast = currentLast; // the before last element takes the currentLast's value
            currentLast = currentLast.next;
        }
        preLast.next = null; // the last element's next should always be null

        return currentLast.value;
    }

    @Override
    public E getFirst() {
        throwIfEmpty();

        return this.head.value;
    }

    @Override
    public E getLast() {

        Node<E> currentLast = this.head;

        while (currentLast.next != null) {
            currentLast = currentLast.next;
        }

        return currentLast.value;
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
            Node<E> node = head;

            @Override
            public boolean hasNext() {
                return node.next != null;
            }

            @Override
            public E next() {
                node = this.node.next;
                return node.value;
            }
        };
    }

    private void throwIfEmpty() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Cannot make this operation from an empty list!");
        }
    }

}