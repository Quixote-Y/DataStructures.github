package stack;

import java.util.Scanner;

public class ArrayStackTest {

	public static void main(String[] args) {
		ArrayStack<Character> stack = new ArrayStack(10);
		String key = "";
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		while (loop) {

			System.out.println("show:显示栈");
			System.out.println("push:添加数据到栈");
			System.out.println("pop:出栈");
			System.out.println("exit:退出程序");
			System.out.println("请输入你的选择：");
			key = scanner.next();
			char values = ' ';
			switch (key) {
			case "show":
				stack.showStack();
				break;
			case "push":
				System.out.println("请输要添加的内容：");
				values = scanner.next().charAt(0);
				stack.push(values);
				break;
			case "pop":
				values = stack.pop();
				System.out.println("取出内容为：" + values);
				break;
			case "exit":
				loop = false;
				break;
			default:
				break;
			}
		}
		System.out.println("退出程序");
	}
}

//定义一个ArrayStack表示栈

//后期我尝试把这个数组栈转换为传入泛型的类型来构成不同数据类型的栈，但很快便意识到数组泛型不能被初始化
/*
 * 这里找到一个可以初始化泛型数组的方式：
 * 
 * (T[])Array.newInstance(Object.class, <size int>); 
 * 其实就是 一个 Object[] 
 * 因为 Java的泛型实现机制是 type erasure，编译时类型擦除，泛型编译之后都是 Object 和 强制类型转换。
 * 需要注意的是，你的方法可以返回 T, List<T> 这都行，不要返回 T[]， 会出问题的，因为外部使用的时候， T 会有它实际的类型，
 * 这时如果你的类型检查正确， 你内部是一个 Object[]数组里面存的元素都是 T 类型，所以单个拿出来 cast 都没问题，但是 Object[]
 * 这个数组要 cast 成某种具体类型的数组，会抛出运行时异常。
 */

//检验可以实现

class ArrayStack<T> {
	private int maxSize;
	private T[] stack;
	private int top = -1; // 指向栈顶，栈的栈顶指针是变化的 ， 因为是数组模拟，所以栈底指针就用数组下标0来代替就好了

	public ArrayStack(int maxSize) {
		this.maxSize = maxSize;
		//创建Object对象数组，强制类型转换为T类型
		stack = (T[])new Object[maxSize];
	}

	// 栈满
	public boolean isFull() {
		return top == maxSize - 1;
	}

	// 栈空
	public boolean isEmpty() {
		return top == -1;
	}
	//栈长度
	public int size() {
		return top+1;
	}

	// 入栈--push
	public void push(T values) {
		if (isFull()) {
			System.out.println("栈满，无法添加");
			return;
		}
		stack[++top] = values;
	}

	// 出栈--pop
	public T pop() {
		if (isEmpty()) {
			// System.out.println("栈空，无法出栈");
			// return 0;
			// 抛出异常，更完美地解决方式 , 有效的避免了返回值的问题
			throw new RuntimeException("栈空，无法出栈");
		}
		return stack[top--];
	}

	// 显示栈
	public void showStack() {
		if (isEmpty()) {
			System.out.println("栈空，无法显示");
			return;
		}
		for (int i = top; i >= 0; i--) {
			System.out.println("位置"+i+":"+stack[i]);
		}
	}
}