

package synthesizer;
import java.util.Iterator;


public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;

    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {


        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[this.capacity];

    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {

        if (fillCount == this.capacity) {
            throw new RuntimeException("Ring Buffer Overflow");
        }
        rb[last] = x;
        last++;
        fillCount++;
        if (last == this.capacity) {
            last = 0;
        }

    }


    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {

        if (fillCount == 0) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T temp = rb[first];
        first++;
        fillCount--;
        if (first == this.capacity) {
            first = 0;
        }
        return temp;
    }


    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {

        if (fillCount == 0) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        return rb[first];
    }




    public Iterator<T> iterator() {
        return new MyIterator();
    }


    private class MyIterator implements Iterator<T> {
        private int ptr;

        MyIterator() {
            ptr = 0;
        }

        public boolean hasNext() {
            return (ptr != fillCount);
        }

        public T next() {
            T returnItem = rb[ptr];
            ptr = ptr + 1;
            return returnItem;
        }

    }
}


