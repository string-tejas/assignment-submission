package generics.and.collections;

import java.util.Stack;

public class StackTest {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        System.out.println("Inserting 10 integers in stack");
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        System.out.println("Inserted 10 integers");
        System.out.println("Top element is " + stack.peek());
        System.out.println("Emptying stack");

        while (!stack.empty()) {
            System.out.println(stack.pop());
        }

        System.out.println("Size of stack is " + stack.size());
    }
}
