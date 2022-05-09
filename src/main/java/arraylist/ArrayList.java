package arraylist;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {
    private static final int ARRAY_INITIAL_SIZE = 4;

    public Object[] elements; // here we use an array of objects because we want to store Generic values
    public int size;          // the size of the elements stored in our list
    public int capacity;      // the total capacity of our data structure

    public ArrayList() {
        this.elements = new Object[ARRAY_INITIAL_SIZE];
        this.size = 0;
        this.capacity = ARRAY_INITIAL_SIZE;
    }

    @Override
    public boolean add(E element) {

        if (this.size == this.capacity) { // we need to resize our data structure if our capacity reached the size
            resize();
        }

        this.elements[this.size] = element; // we add an element on the last index
        this.size++;          // and then we need to increment the size

        return true;
    }

    @Override
    public boolean add(int index, E element) {
        if (!isValidIndex(index)) { // here we ensure that our index is valid
            return false;
        }
        if (this.size == this.capacity) { // once again we check if the capacity reached the size
            resize();
        }
        shiftRight(index); // first we want to shift right all elements after this index
        this.elements[index] = element; // and then we add the element
        this.size++; // also, we increment the size

        return true;
    }

    @Override
    public E get(int index) {
        throwIfInvalidIndex(index); // we make sure that our index is valid

        return (E) this.elements[index];  // and then just return the generic value at that index
    }

    @Override
    public E set(int index, E element) {
        throwIfInvalidIndex(index); // check if the index is valid, otherwise throw IndexOutOfBoundsException

        Object currentElement = this.elements[index]; // a tmp that I will need to return

        this.elements[index] = element; // setting the new element to that index

        return (E) currentElement; //return the previous element stored at that index
    }

    @Override
    public E remove(int index) {
        throwIfInvalidIndex(index); // check if the index is valid, otherwise throw IndexOutOfBoundsException

        Object elementToRemove = this.elements[index]; // here we store the current element in the given index
        shiftLeft(index); // that way we delete the element at the given index

        handleCapacity(); // that way we handle the capacity not to be too big

        this.size--; // we need to decrement the size when removing an element

        return (E) elementToRemove;
    }

    private void handleCapacity() {

        if (this.size > this.capacity / 3) {
            return;
        }
        this.elements = Arrays.copyOf(this.elements, this.capacity / 2);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int indexOf(E element) {
        return findIndexOfElement(element);
    }

    @Override
    public boolean contains(E element) {
        return this.findIndexOfElement(element) != -1;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            public E next() {
                return get(index++);
            }
        };

    }

    private void resize() {
        this.capacity *= 2;
        Object[] resized = new Object[this.capacity];

        for (int i = 0; i < this.elements.length; i++) {
            resized[i] = this.elements[i];
        }
        this.elements = resized;
    }

    private void shiftRight(int index) {

        for (int i = this.size - 1; i >= index; i--) {
            Object current = this.elements[i];
            this.elements[i + 1] = current;
        }
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < this.size;
    }

    private void throwIfInvalidIndex(int index) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException("Index out of bounds for index " + index);
        }
    }

    private int findIndexOfElement(E element) {
        for (int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    private void shiftLeft(int index) {

        for (int i = index; i < this.size - 1; i++) {
            this.elements[i] = this.elements[i + 1];
        }
    }
}
