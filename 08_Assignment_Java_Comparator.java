package generics.and.collections;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;

public class ComparatorTest {
    public static void main(String[] args) {
        Student a = new Student("Jack", 1);
        Student b = new Student("Alice", 2);

//        TreeSet<Student> classByRoll = new TreeSet<>(new StudentComparator());
        TreeSet<Student> classByRoll = new TreeSet<>((o1, o2) -> Integer.compare(o1.rollNumber, o2.rollNumber));
        TreeSet<Student> classByName = new TreeSet<>(Comparator.comparing(o -> o.name));
//        TreeSet<Student> classByName = new TreeSet<>(Comparator.comparing(o -> o.name).reversed());

        classByName.add(a);
        classByName.add(b);

        classByRoll.add(a);
        classByRoll.add(b);

        System.out.println("TreeSet by Roll Number");
        for (var student : classByRoll) {
            System.out.println(student);
        }

        System.out.println("\nTreeSet by Name");
        for (var student : classByName) {
            System.out.println(student);
        }

    }
}

class Student {
    String name;
    int rollNumber;

    public Student(String name, int rollNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", rollNumber=" + rollNumber +
                '}';
    }
}

class StudentComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.name.compareTo(o2.name);
    }
}
