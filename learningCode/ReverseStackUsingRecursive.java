package class_8;

import java.util.Stack;

/**
 * 给你一个栈，请你逆序这个栈，不能申请额外的数据结构，只能使用递归函数 如何实现？
 */

 public class ReverseStackUsingRecursive {
     public static void reverseStack(Stack<Integer> stack) {
         if (stack.isEmpty()) {
             return;
         }
         int lastElement = getAndRemoveTheLastElement(stack);
         reverseStack(stack);
         stack.push(lastElement);
     }
     public static int getAndRemoveTheLastElement(Stack<Integer> stack) {
         int res = stack.pop();
         if (stack.isEmpty()) {
             return res;
         } else {
             int last = getAndRemoveTheLastElement(stack);
             stack.push(res);
             return last;
         }
     }

     public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();
		test.push(1);
		test.push(2);
		test.push(3);
		test.push(4);
		test.push(5);
		reverseStack(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}
	}
 }