package generics.and.collections;

import java.util.*;

public class ExtractionTest {

    public static void main(String[] args) {
        List<Student> trainingClass = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            trainingClass.add(new Student("Student" + i, i));
        }


        // 1. for each
        System.out.println("For Each Loop");
        for (Student student: trainingClass) {
            System.out.println(student);
        }

        // 2. iterator
        System.out.println("Iterator");
        Iterator<Student> iterator = trainingClass.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


        // 3. lambdas
        System.out.println("Lambda - impl");
        trainingClass.forEach(s -> System.out.println(s));
        System.out.println("Lambda - pass method");
        trainingClass.forEach(System.out::println);


        // 4. streams api
        System.out.println("Extracting names and printing them");
        trainingClass.stream().map(s -> s.name).forEach(System.out::println);

        // 5. for loop if applicable
        System.out.println("Traditional index based for loop");
        for (int i = 0; i < trainingClass.size(); i++) {
            System.out.println(trainingClass.get(i));
        }

        // 6. spliterator (read only when multithreading)
        System.out.println("Spliterators");
        Spliterator<Student> spliterator = trainingClass.spliterator();
        spliterator.forEachRemaining(System.out::println);


        Map<Student, Result> map = new HashMap<>();

        for (var student: trainingClass) {
            float physics = (float) Math.random() * 100;
            float chemistry = (float) Math.random() * 100;
            float maths = (float) Math.random() * 100;
            map.put(student, new Result(physics, chemistry, maths, student.rollNumber));
        }


        // 1. for each (Map Ver)
        System.out.println("For Each - Map");
        for (Map.Entry<Student, Result> entry: map.entrySet()) {
            System.out.println(entry.getKey().toString() + entry.getValue().toString());
        }

        // 2. extracting keys
        System.out.println("Traverse keys");
        for (Student key : map.keySet()) {
            System.out.println(key);
        }


        // 3. extracting values
        System.out.println("Traversing values");
        for (Result value : map.values()) {
            System.out.println(value);
        }


        // 4. Iterator
        Iterator<Map.Entry<Student, Result>> entryIterator = map.entrySet().iterator();
        System.out.println("Entry set iterator");
        while (entryIterator.hasNext()) {
            var entry = entryIterator.next();
            System.out.println(entry.getKey().toString() + ", " + entry.getValue().toString());

        }


        // 5. forEach method
        System.out.println("For each map method");
        map.forEach((key, value) -> System.out.println(key + ", " + value));


        // 6. stream api
        System.out.println("Stream API ");
        map.entrySet().stream()
                .map(v -> v.getKey().name + " physics = " + v.getValue().physics)
                .forEach(System.out::println);
    }
}

class Result {
    float physics;
    float chemsitry;
    float maths;
    int rollNumber;

    public Result(float physics, float chemsitry, float maths, int rollNumber) {
        this.physics = physics;
        this.chemsitry = chemsitry;
        this.maths = maths;
        this.rollNumber = rollNumber;
    }

    @Override
    public String toString() {
        return "Result{" +
                "physics=" + physics +
                ", chemsitry=" + chemsitry +
                ", maths=" + maths +
                ", rollNumber=" + rollNumber +
                '}';
    }
}
