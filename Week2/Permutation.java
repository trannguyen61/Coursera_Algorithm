import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> arr = new RandomizedQueue<>();
        int k = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            arr.enqueue(StdIn.readString());
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(arr.dequeue());
        }
    }
 }