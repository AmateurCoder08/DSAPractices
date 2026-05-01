/**
 * A stack is a LIFO (Last In First Out) data structure
 * Only can only pop the last item from the stack
 */
package com.dsa.stack;

import java.util.Stack;

public class UnderstandingStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        System.out.println(stack.isEmpty());

        stack.push("item1");

        System.out.println(stack.isEmpty());

        stack.push("item2");
        stack.push("item3");
        stack.push("item4");

        System.out.println(stack.peek());

        System.out.println(stack);

        System.out.println(stack.pop());
        System.out.println(stack);

        stack.push("item4");
        System.out.println(stack.search("item1"));

        stack.push("item3");
        System.out.println(stack.search("item3"));

        System.out.println(stack.size());
    }
}
