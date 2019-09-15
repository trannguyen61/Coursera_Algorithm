import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[2];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();

        if (size == items.length) resize(items.length * 2);
        items[size++] = item;
    }

    private void resize(int len) {
        Item[] temp = (Item[]) new Object[len];
        for (int i = 0; i < size; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) throw new NoSuchElementException();

        int randomIndex = StdRandom.uniform(size);
        Item rmvItem = items[randomIndex];
        for (int i = randomIndex; i < size; i++) {
            items[i] = items[i+1];
        }
        items[--size] = null;
        if (size == items.length/4 && size > 0) {
            resize(items.length/2);
        }
        return rmvItem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) throw new NoSuchElementException();

        int randomIndex = StdRandom.uniform(size);
        return items[randomIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private int i = 0;
        private final int[] randomIndex;

        private RandomIterator() {
            randomIndex = new int[size];
            for (int j = 0; j < size; j++) {
                randomIndex[j] = j;
            }
            StdRandom.shuffle(randomIndex);
        }

        public boolean hasNext() {
            return i < size;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            return items[randomIndex[i++]];
        }   
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);

        System.out.println("Empty? " + q.isEmpty());

        System.out.println("Size is " + q.size());

        System.out.println("Sample is " + q.sample());

        Iterator<Integer> i = q.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }

        q.dequeue();
        q.dequeue();

        i = q.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }

}