package Threads;

public class buffer {
    private int size = 5; // the buffer bound
    private int[] store;
    private int inptr = 0;
    private int outptr = 0;

    public buffer(int s){
        this.size=s;
        this.store = new int[size];
    }
    public void setSize(int s){
        this.size=s;
        this.store = new int[s];

    }

    semaphore spaces = new semaphore(size);
    semaphore elements = new semaphore(0);
    public void produce(int value) {
        spaces.P();
        store[inptr] = value;
        inptr = (inptr + 1) % size;
        elements.V();
    }
    public int consume() {
        int value;
        elements.P();
        value = store[outptr];
        outptr = (outptr + 1) % size;
        spaces.V();
        return value;
    }
}
