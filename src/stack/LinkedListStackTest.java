package stack;
//����ģ��ջ

import java.util.Scanner;

public class LinkedListStackTest {

	public static void main(String[] args) {
		LinkedListStack stack = new LinkedListStack(5);
		String key = "";
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		while (loop) {

			System.out.println("show:��ʾջ");
			System.out.println("push:������ݵ�ջ");
			System.out.println("pop:��ջ");
			System.out.println("exit:�˳�����");
			System.out.println("���������ѡ��");
			key = scanner.next();
			int values = 0;
			switch (key) {
			case "show":
				stack.showStack();
				break;
			case "push":
				System.out.println("����Ҫ��ӵ����֣�");
				values = scanner.nextInt();
				stack.push(values);
				break;
			case "pop":
				try {
					values = stack.pop();
					System.out.println("ȡ������Ϊ��" + values);
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
		System.out.println("�˳�����");
	}

}

//����ջ��
class LinkedListStack {
	private Node head = new Node(); // ջ��ָ�� , ����һ����ͷ���ĵ�������
	private Node top = head;
	private int maxSize = 10; // Ĭ����󳤶�
	private int size = 0; // ��Ч����

	public LinkedListStack(int maxSize) {
		super();
		this.maxSize = maxSize;
	}

	public LinkedListStack() {
		super();
	}

	// ջ�� �� ����û���ж�����ջ���ı�Ҫ��������������ӳ��������пռ����ƣ���Ҫָ�����ջ�����������Ļ���������һ����������¼ջ����
	public boolean isFull() {
		return (maxSize - size) == 0;
	}

	// ջ��
	public boolean isEmpty() {
		return size == 0;
	}

	// ��ջ
	public void push(int values) {
		if (isFull()) {
			System.out.println("�Ѿ��ﵽ������󳤶ȣ��޷��������");
			return;
		}
		Node node = new Node(values);
		top.setNext(node);
		top = node;
		size++;
	}

	// ��ջ
	public int pop() {
		if (isEmpty()) {
			throw new RuntimeException("����ջΪ�գ���ջ�쳣");
		}
		// ȡ����������һ��Ԫ��
		Node tmp = top;
		size--;
		top = head;
		for (int i = 0; i < size; i++) {
			top = top.getNext();
		}
		return tmp.getValues();
	}

	// ��ʾջ����
	public void showStack() {
		if (isEmpty()) {
			System.out.println("ջ��");
			return;
		}
		Node tmp = head;
		for (int i = 0; i < size; i++) {
			tmp = tmp.getNext();
			System.out.println(tmp);
		}
	}

}

//����ڵ�
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