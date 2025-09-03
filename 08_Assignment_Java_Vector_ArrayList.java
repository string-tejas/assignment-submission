package generics.and.collections;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Difference between Vector and ArrayList in Java:
 *
 * 1. Synchronization:
 *    - Vector is synchronized (thread-safe).
 *    - ArrayList is not synchronized (not thread-safe).
 *
 * 2. Performance:
 *    - ArrayList is faster in single-threaded environments.
 *    - Vector is slower due to synchronization overhead.
 *
 * 3. Growth:
 *    - Vector doubles its size when capacity is exceeded.
 *    - ArrayList grows by 50% of its size.
 *
 * 4. Legacy:
 *    - Vector is a legacy class (from JDK 1.0).
 *    - ArrayList was introduced in Java 1.2 as part of the Collections Framework.
 */

public class VectorArrayListTest {
    public static void main(String[] args) {
        // Using ArrayList
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Apple");
        arrayList.add("Banana");
        arrayList.add("Cherry");

        System.out.println("ArrayList elements:");
        for (String fruit : arrayList) {
            System.out.println(fruit);
        }

        // Using Vector
        Vector<String> vector = new Vector<>();
        vector.add("Dog");
        vector.add("Elephant");
        vector.add("Fox");

        System.out.println("\nVector elements:");
        for (String animal : vector) {
            System.out.println(animal);
        }
    }

}
