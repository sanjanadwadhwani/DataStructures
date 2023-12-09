import java.util.NoSuchElementException;

public class DoublyLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void addAtIndex(int index, T data) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("The index is not valid. There is no value at this index because it"
                + "is either negative or too large.");
        }
        if (data == null) {
            throw new IllegalArgumentException("The data to add is null. Unable to add this data.");
        }
        if (index == 0) {
            addToFront(data);
        } else if (index == this.size) {
            addToBack(data);
        } else if (index < (size / 2)) {

            Node<T> prevNode = this.head;
            for (int i = 0; i < (index - 1); i++) {
                prevNode = prevNode.getNext();
            }
            Node<T> newNode = new Node<>(data, prevNode, prevNode.getNext());
            prevNode.setNext(newNode);
            newNode.getNext().setPrevious(newNode);
            this.size++;
        } else {
            int numFromEnd = this.size - index;
            Node<T> prevNode = this.tail;
            for (int i = 0; i < numFromEnd; i++) {
                prevNode = prevNode.getPrevious();
            }
            Node<T> newNode = new Node<>(data, prevNode, prevNode.getNext());
            prevNode.setNext(newNode);
            newNode.getNext().setPrevious(newNode);
            this.size++;
        }
    }


    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data to add is null. Unable to add this data.");
        }
        Node<T> newNode = new Node<>(data);

        if (isEmpty()) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.head.setPrevious(newNode);
            newNode.setNext(this.head);
            this.head = newNode;
        }
        this.size++;
    }

    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data to add is null. Unable to add this data.");
        }
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            newNode.setPrevious(this.tail);
            this.tail.setNext(newNode);
            this.tail = newNode;
        }
        this.size++;
    }

    public T removeAtIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("The index is not valid. There is no value at this index because it"
                    + "is either negative or too large.");
        }
        if (index == 0) {
            return removeFromFront();
        } else if (index == (this.size - 1)) {
            return removeFromBack();
        } else if (index > (this.size / 2)) {
            int numFromEnd = this.size - index - 1;
            Node<T> removeNode = this.tail;
            for (int i = 0; i < numFromEnd; i++) {
                removeNode = removeNode.getPrevious();
            }
            T data = removeNode.getData();
            removeNode.getPrevious().setNext(removeNode.getNext());
            removeNode.getNext().setPrevious(removeNode.getPrevious());
            this.size--;
            return data;
        } else {
            Node<T> removeNode = this.head;
            for (int i = 0; i < index; i++) {
                removeNode = removeNode.getNext();
            }
            T data = removeNode.getData();
            removeNode.getPrevious().setNext(removeNode.getNext());
            removeNode.getNext().setPrevious(removeNode.getPrevious());
            this.size--;
            return data;
        }
    }

    public T removeFromFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("The list is empty. Unable to remove anything because there are no"
                + " elements to remove.");
        }
        T data = this.head.getData();
        this.head = this.head.getNext();
        if (this.head == null) {
            this.tail = null;
        } else {
            this.head.setPrevious(null);
        }
        this.size--;
        return data;
    }

    public T removeFromBack() {
        if (isEmpty()) {
            throw new NoSuchElementException("The list is empty. Unable to remove anything because there are no"
                    + " elements to remove.");
        }
        T data = this.tail.getData();
        this.tail = this.tail.getPrevious();
        if (this.tail == null) {
            this.head = null;
        } else {
            this.tail.setNext(null);
        }
        this.size--;
        return data;
    }

    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("The index is not valid. There is no value at this index because it"
                    + "is either negative or too large.");
        }
        if (index == 0) {
            return (this.head.getData());
        } else if (index == (this.size - 1)) {
            return (this.tail.getData());
        } else if (index < (this.size / 2)) {
            Node<T> node = this.head;
            for (int i = 0; i < index; i++) {
                node = node.getNext();
            }
            return node.getData();
        } else {
            int numFromEnd = this.size - index - 1;
            Node<T> node = this.tail;
            for (int i = 0; i < numFromEnd; i++) {
                node = node.getPrevious();
            }
            return node.getData();
        }
    }

    public boolean isEmpty() {
        return (this.size == 0);
    }

    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data to remove is null. Unable to find a null value.");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("The list is empty. Unable to remove anything because there are no"
                    + " elements to remove.");
        }
        Node<T> node = this.tail;
        for (int i = 0; i < this.size; i++) {
            if (node.getData().equals(data)) {
                T dataRemoved = node.getData();

                if (node == this.tail) {
                    return removeFromBack();
                } else if (node == this.head) {
                    return removeFromFront();
                } else {
                    node.getPrevious().setNext(node.getNext());
                    node.getNext().setPrevious(node.getPrevious());
                }
                this.size--;
                return dataRemoved;
            }
            if (node.getPrevious() != null) {
                node = node.getPrevious();
            }

        }
        throw new NoSuchElementException("");
    }

    public Object[] toArray() {
        Object[] arr = new Object[this.size];
        Node<T> node = this.head;
        for (int i = 0; i < this.size; i++) {
            arr[i] = node.getData();
            node = node.getNext();
        }
        return arr;
    }

    public Node<T> getHead() {return head;}
    public Node<T> getTail() {return tail;}
    public int size() {return size;}
}
