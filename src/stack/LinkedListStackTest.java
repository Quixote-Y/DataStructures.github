package stack;
//链表模拟栈

import java.util.Scanner;

public class LinkedListStackTest {

	public static void main(String[] args) {
		LinkedListStack stack = new LinkedListStack(5);
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
			int values = 0;
			switch (key) {
			case "show":
				stack.showStack();
				break;
			case "push":
				System.out.println("请输要添加的数字：");
				values = scanner.nextInt();
				stack.push(values);
				break;
			case "pop":
				try {
					values = stack.pop();
					System.out.println("取出内容为：" + values);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
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

//链表栈类
class LinkedListStack {
	private Node head = new Node(); // 栈底指针 , 构建一个带头结点的单向链表
	private Node top = head;
	private int maxSize = 10; // 默认最大长度
	private int size = 0; // 有效长度

	public LinkedListStack(int maxSize) {
		super();
		this.maxSize = maxSize;
	}

	public LinkedListStack() {
		super();
	}

	// 栈满 ； 好像没有判断链表栈满的必要，链表可以无限延长，除非有空间限制，需要指定最大栈容量，这样的话可以增加一个变量，记录栈长度
	public boolean isFull() {
		return (maxSize - size) == 0;
	}

	// 栈空
	public boolean isEmpty() {
		return size == 0;
	}

	// 进栈
	public void push(int values) {
		if (isFull()) {
			System.out.println("已经达到链表最大长度，无法继续添加");
			return;
		}
		Node node = new Node(values);
		top.setNext(node);
		top = node;
		size++;
	}

	// 出栈
	public int pop() {
		if (isEmpty()) {
			throw new RuntimeException("链表栈为空，出栈异常");
		}
		// 取出链表的最后一个元素
		Node tmp = top;
		size--;
		top = head;
		for (int i = 0; i < size; i++) {
			top = top.getNext();
		}
		return tmp.getValues();
	}

	// 显示栈数据
	public void showStack() {
		if (isEmpty()) {
			System.out.println("栈空");
			return;
		}
		Node tmp = head;
		for (int i = 0; i < size; i++) {
			tmp = tmp.getNext();
			System.out.println(tmp);
		}
	}

}

//链表节点
class Node {
	private int values;
	private Node next;

	public Node() {
		super();
	}

	public Node(int values) {
		super();
		this.values = values;
	}

	public int getValues() {
		return values;
	}

	public void setValues(int values) {
		this.values = values;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "Node [values=" + values + "]";
	}

}