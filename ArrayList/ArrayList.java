import java.util.NoSuchElementException;
public class ArrayList<T> {
    public static final int CAPACITY = 9;
    private T[] backingArray;
    private int size;
    public ArrayList() {
        this.backingArray = (T[]) new Object[CAPACITY];
        this.size = 0;
    }

    /**
     * Adds the element at index
     * @param index index where to add new element
     * @param data data to add
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data provided is null. Unable to add this.");
        }
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("This index is invalid. There is no value at this index. The index must"
                + " be great than 0 and less than the size");
        }
        if (this.size == this.backingArray.length) {
            resizeArr();
        }
        if (index == this.size) {
            addToBack(data);
        } else if (index == 0) {
            addToFront(data);
        } else {
            for (int i = this.size; i > index; i--) {
                this.backingArray[i] = this.backingArray[i - 1];
            }
            this.backingArray[index] = data;
            size++;
        }
    }

    /**
     * Adds the element to the front of the list.
     * @param data data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data provided is null. Unable to add this.");
        }
        if (this.size == this.backingArray.length) {
            resizeArr();
        }
        T dataToPlace = data;
        T dataToMove;
        this.size++;
        for (int i = 0; i < this.size; i++) {
            dataToMove = this.backingArray[i];
            this.backingArray[i] = dataToPlace;
            dataToPlace = dataToMove;
        }
    }

    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data provided is null. Unable to add this.");
        }
        if (this.size == this.backingArray.length) {
            resizeArr();
        }
        this.backingArray[size] = data;
        this.size++;
    }

    private void resizeArr() {
        T[] newBackingArray = (T[]) new Object[this.backingArray.length * 2];
        for (int i = 0; i < this.size; i++) {
            newBackingArray[i] = this.backingArray[i];
        }
        this.backingArray = newBackingArray;
    }

    public T removeAtIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("The index is not valid. The index cannot be less than 0 or greater "
                    + "than/equal to the size. There is no value at this index.");
        }
        if (index == (this.size - 1)) {
            return removeFromBack();
        } else if (index == 0) {
            return removeFromFront();
        } else {
            T removedElement = this.backingArray[index];
            T dataToPlace = null;
            T dataToMove;
            for (int i = (size - 1); i >= index; i--) {
                dataToMove = this.backingArray[i];
                this.backingArray[i] = dataToPlace;
                dataToPlace = dataToMove;
            }
            this.size--;
            return removedElement;
        }
    }

    public T removeFromFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("The list is empty. Unable to remove any elements");
        }
        T removedElement = this.backingArray[0];
        T dataToPlace = null;
        T dataToMove;
        for (int i = (size - 1); i >= 0; i--) {
            dataToMove = this.backingArray[i];
            this.backingArray[i] = dataToPlace;
            dataToPlace = dataToMove;
        }
        this.size--;
        return removedElement;
    }

    public T removeFromBack() {
        if (isEmpty()) {
            throw new NoSuchElementException("The list is empty. Unable to remove an object.");
        }
        T removedElement = this.backingArray[this.size - 1];
        this.backingArray[this.size - 1] = null;
        this.size--;
        return removedElement;
    }

    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("The index is not valid. The index cannot be less than 0 or greater "
                    + "than/equal to the size. There is no value at this index.");
        }
        return (this.backingArray[index]);
    }

    public boolean isEmpty() {
        return (this.size == 0);
    }

    public void clear() {
        this.backingArray = (T[]) new Object[CAPACITY];
        this.size = 0;
    }

    public T[] getBackingArray() {
        return backingArray;
    }

    public int size() {
        return size;
    }
}
