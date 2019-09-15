import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size = 0;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node newNode = new Node();
        newNode.item = item;
        newNode.next = first;
        newNode.prev = null;

        if (size == 0) last = newNode;

        first = newNode;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node newNode = new Node();
        newNode.item = item;
        newNode.next = null;
        newNode.prev = last;
        last = newNode;

        if (size == 0) first = last;
        else last.prev.next = newNode;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) throw new NoSuchElementException();

        Item rmvItem = first.item;
        first = first.next;
        size--; 
        if (size == 0) last = null;
        return rmvItem;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) throw new NoSuchElementException();
        Item rmvItem = last.item;
        last = last.prev;
        size--;
        if (size == 0) first = null;
        return rmvItem;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node curr = first;

        public boolean hasNext() {
            return curr != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            Item item = curr.item;
            curr = curr.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addLast(3);
        System.out.println("Is deque empty? " + deque.isEmpty());
        System.out.println("Size is: " + deque.size());
        deque.removeFirst();
        deque.removeLast();
        Iterator<Integer> i = deque.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }
        
    }

}