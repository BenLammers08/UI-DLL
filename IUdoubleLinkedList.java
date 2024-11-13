import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T> {
    private Node<T> head, tail;
    private int size;
    private int modCount;

    public IUDoubleLinkedList() {
        head = null;
        tail = null;
        size = 0;
        modCount = 0;
    }

    @Override
    public void addToFront(T element) {

        Node<T> newNode = new Node<T>(element);
        newNode.setNext(head);
        if (isEmpty()) {
            tail = newNode;
        } else {
            head.setPrevious(newNode);
        }

        head = newNode;
        size++;
        modCount++;
    }

    @Override
    public void addToRear(T element) {
        Node<T> newNode = new Node<T>(element);
        if (tail == null) {
            head = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
        }
        tail = newNode;
        size++;
        modCount++;
    }

    @Override
    public void add(T element) {
        addToRear(element);

    }

    @Override
    public void addAfter(T element, T target) {

        Node<T> targetNode = head;
        while (targetNode != null && !targetNode.getElement().equals(target)) {
            targetNode = targetNode.getNext();
        }
        if (targetNode == null) {
            throw new NoSuchElementException();
        }
        Node<T> newNode = new Node<T>(element);
        newNode.setNext(targetNode.getNext());
        newNode.setPrevious(targetNode);
        targetNode.setNext(newNode);
        if (newNode.getNext() != null) {
            newNode.getNext().setPrevious(newNode);
        } else {
            tail = newNode;
        }

        targetNode.getNext().setPrevious(newNode);

        size++;
        modCount++;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            addToFront(element);
        } else if (index == size) {
            addToRear(element);
        } else {
            Node<T> currentNode = head;

            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNext();
            }

            Node<T> newNode = new Node<T>(element);
            newNode.setNext(currentNode.getNext());
            newNode.setPrevious(currentNode);
            currentNode.getNext().setPrevious(newNode);
            currentNode.setNext(newNode);

            size++;
            modCount++;
        }
    }

    @Override
    public T removeFirst() {

        if (head == null) {
            throw new NoSuchElementException();
        }
        T retVal = head.getElement();
        head = head.getNext();
        modCount++;
        size--;
        return retVal;
    }

    @Override
    public T removeLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }

        T retVal = tail.getElement();

        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.getPrevious();
        }

        return retVal;
    }

    @Override
    public T remove(T element) {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Node<T> targetNode = head;

        while (targetNode != null && !targetNode.getElement().equals(element)) {
            targetNode = targetNode.getNext();
        }

        if (targetNode == null) {
            throw new NoSuchElementException();
        }

        T retVal = targetNode.getElement();

        if (targetNode == head) {
            head = head.getNext();
            if (head != null) {
                head.setPrevious(null);
            } else {
                tail = null;
            }
        }

        else if (targetNode == tail) {
            tail = tail.getPrevious();
            if (tail != null) {
                tail.setNext(null);
            } else {
                head = null;
            }
        }

        else {
            targetNode.getPrevious().setNext(targetNode.getNext());
            targetNode.getNext().setPrevious(targetNode.getPrevious());
        }

        size--;
        modCount++;
        return retVal;
    }

    @Override
    public T remove(int index) {

        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> currNode = head;
        for (int i = 0; i < index; i++) {
            currNode = currNode.getNext();
        }
        if (index == 0) {
            head = currNode.getNext();
        } else {
            currNode.getPrevious().setNext(currNode.getNext());
        }
        if (currNode == tail) {
            tail = currNode.getPrevious();
        } else {
            currNode.getNext().setPrevious(currNode.getPrevious());
        }

        size--;
        modCount++;
        return currNode.getElement();
    }

    @Override
    public void set(int index, T element) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> currNode = head;
        for (int i = 0; i < index; i++) {
            currNode = currNode.getNext();
        }
        currNode.setElement(element);
        modCount++;
    }

    @Override
    public T get(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> currNode = head;
        for (int i = 0; i < index; i++) {
            currNode = currNode.getNext();
        }

        return currNode.getElement();

    }

    @Override
    public int indexOf(T element) {

        Node<T> currentNode = head;
        int currentIndex = 0;
        while (currentNode != null && !currentNode.getElement().equals(element)) {
            currentNode = currentNode.getNext();
            currentIndex++;
        }
        if (currentNode == null) {
            currentIndex = -1;
        }
        return currentIndex;

    }

    @Override
    public T first() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        return head.getElement();
    }

    @Override
    public T last() {
        if (tail == null) {
            throw new NoSuchElementException();
        }
        return tail.getElement();
    }

    @Override
    public boolean contains(T target) {
        boolean retBool = false;
        if (isEmpty()) {
            retBool = false;
        } else {
            Node<T> targetNode = head;
            while (targetNode != null && !isEmpty()) {
                if (targetNode.getElement() == target) {
                    retBool = true;
                }

                targetNode = targetNode.getNext();
            }

        }
        return retBool;

    }

    @Override
    public boolean isEmpty() {
        return head == null;

    }

    @Override
    public int size() {
        return size;

    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();
        str.append("[");
        for (T element : this) {
            str.append(element.toString());
            str.append(", ");
        }
        if (size() > 0) {
            str.delete(str.length() - 2, str.length());
        }
        str.append("]");

        return str.toString();

    }

    @Override
    public Iterator<T> iterator() {

        return new DLLIterator();

    }

    @Override
    public ListIterator<T> listIterator() {

        return new DLLIterator();
    }

    @Override
    public ListIterator<T> listIterator(int startingIndex) {

        return new DLLIterator(startingIndex);

    }

    private class DLLIterator implements ListIterator<T> {
        private Node<T> nextNode;
        private int nextIndex;
        private int iterModCount;
        // private boolean canRemove;
        private Node<T> returnedNode;

        public DLLIterator() {
            nextNode = head;
            nextIndex = 0;
            iterModCount = modCount;
            returnedNode = null;
        }

        public DLLIterator(int startingIndex) {
            if (startingIndex < 0 || startingIndex > size) {
                throw new IndexOutOfBoundsException();
            }
            nextNode = head;
            for (int i = 0; i < startingIndex; i++) {
                nextNode = nextNode.getNext();
            }
            nextIndex = startingIndex;
            iterModCount = modCount;
            returnedNode = null;
        }

        @Override
        public boolean hasNext() {

            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return nextNode != null;
        }

        @Override
        public T next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T retVal = nextNode.getElement();
            returnedNode = nextNode;
            nextNode = nextNode.getNext();
            nextIndex++;

            return retVal;
        }

        @Override
        public boolean hasPrevious() {

            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return nextNode != head;
        }

        @Override
        public T previous() {

            if (hasPrevious() == true) {
                return nextNode.getPrevious().getElement();
            } else {
                throw new NoSuchElementException();
            }

        }

        @Override
        public int nextIndex() {

            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
        public void remove() {

            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (returnedNode == null) {
                throw new IllegalStateException();
            }
            if (returnedNode != head) {
                returnedNode.getPrevious().setNext(returnedNode.getNext());
            } else {
                head = head.getNext();
            }
            if (returnedNode != tail) {
                returnedNode.getNext().setPrevious(returnedNode.getPrevious());
            } else {
                tail = tail.getPrevious();
            }
            if (returnedNode != nextNode) {
                nextIndex--;
            } else {
                nextNode = nextNode.getNext();
            }

            returnedNode = null;
            size--;
            modCount++;
            iterModCount++;

        }

        @Override
        public void set(T e) {

            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (returnedNode != null) {
                returnedNode.setElement(e);
            } else {
                throw new IllegalStateException();
            }
            modCount++;
            iterModCount++;
        }

        @Override
        public void add(T e) {
            Node<T> newNode = new Node<T>(e);

            if (nextIndex == 0) {
                tail = newNode;
                newNode.setNext(nextNode);
                nextNode.setPrevious(newNode);
            } else {
                newNode.setPrevious(nextNode.getPrevious());
                nextNode.getPrevious().setNext(newNode);
                newNode.setNext(nextNode);
                nextNode.setPrevious(newNode);
            }

            size++;
            ;
            modCount++;
            iterModCount++;
        }

    }
}
