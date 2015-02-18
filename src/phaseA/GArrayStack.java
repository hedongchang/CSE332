/**
 * Dongchang He & Juan Cai
 * CSE 332B Project 2 Phase A
 * 2/2/2015
 * Instructor: Anderson Ruth
 */

package phaseA;
import providedCode.*;

import java.util.EmptyStackException;

/**
 * GArrayStack is a generic data type implemented by array
 * that has the same functions with a stack
 * 
 * @author Dongchang He
 * Student ID: 1239324
 */
public class GArrayStack<T> implements GStack<T> {
		
		public static final int INITIAL_SIZE = 10;
		public static final int RE_SIZE = 2;
		
		private int top;
		// index that indicates the top of a stack
		private T[] stack;
		// array that stores all values in a stack
		
		/**
		 * Constructs a GArrayStack
		 */
		@SuppressWarnings("unchecked")
		public GArrayStack() {
			this.top = -1;
			this.stack = (T[]) new Object[INITIAL_SIZE];
		}
		
		/**
		 * Resize the array if the current array is full
		 * @modifies stack
		 * @effects double the size of the array if the current one is full
		 */
		@SuppressWarnings("unchecked")
		private void resize() {
			int length = stack.length;
			T[] newStack = (T[]) new Object[length * RE_SIZE];
			for (int i = 0; i < length; i++) {
				newStack[i] = stack[i];
			}
			this.stack = newStack;
		}
		
		/**
		 * Determine whether the stack is empty
		 * @return true if the stack is empty, false otherwise
		 */
		@Override
		public boolean isEmpty() {
			return top == -1;
		}
		
		/**
		 * push one element at the top of the stack
		 * @param T data: the element desired to be pushed
		 * @modifies top of the stack
		 * @effects push d in the stack and the top points to d
		 */
		public void push(T data) {
			if (top == stack.length - 1) {
				resize();
			}
			top++;
			stack[top] = data;
		}
		
		/**
		 * pop one element from the stack
		 * @return the deleted value
		 * @modifies top of the stack
		 * @effects remove the element at the top and 
		 * top points to the element before the deleted element
		 * @throws EmptyStackException if stack is empty
		 */
		public T pop() {
			if (isEmpty()) {
				throw new EmptyStackException();
			}
			T data = stack[top];
			top--;
			return data;
		}
		
		/**
		 * Get the element at the top of the stack
		 * @return the element at the top of the stack
		 * @throws EmptyStackException if stack is empty
		 */
		public T peek() {
			if (isEmpty()) {
				throw new EmptyStackException();
			}
			return stack[top];
		}

}
