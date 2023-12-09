
public class Node<T> {

    private T data;
    private DoublyLinkedListNode<T> previous;
    private DoublyLinkedListNode<T> next;

    Node(T data, DoublyLinkedListNode<T> previous,
         DoublyLinkedListNode<T> next) {
        this.data = data;
        this.previous = previous;
        this.next = next;
    }

    Node(T data) {this(data, null, null);}

    T getData() {
        return data;
    }

    DoublyLinkedListNode<T> getPrevious() {
        return previous;
    }
    void setPrevious(DoublyLinkedListNode<T> previous) {
        this.previous = previous;
    }
    DoublyLinkedListNode<T> getNext() {
        return next;
    }
    void setNext(DoublyLinkedListNode<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node [ " + data + " ]";
    }
}