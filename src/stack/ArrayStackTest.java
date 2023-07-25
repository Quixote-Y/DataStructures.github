package stack;

import java.util.Scanner;

public class ArrayStackTest {

	public static void main(String[] args) {
		ArrayStack<Character> stack = new ArrayStack(10);
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
			char values = ' ';
			switch (key) {
			case "show":
				stack.showStack();
				break;
			case "push":
				System.out.println("����Ҫ��ӵ����ݣ�");
				values = scanner.next().charAt(0);
				stack.push(values);
				break;
			case "pop":
				values = stack.pop();
				System.out.println("ȡ������Ϊ��" + values);
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

//����һ��ArrayStack��ʾջ

//�����ҳ��԰��������ջת��Ϊ���뷺�͵����������ɲ�ͬ�������͵�ջ�����ܿ����ʶ�����鷺�Ͳ��ܱ���ʼ��
/*
 * �����ҵ�һ�����Գ�ʼ����������ķ�ʽ��
 * 
 * (T[])Array.newInstance(Object.class, <size int>); 
 * ��ʵ���� һ�� Object[] 
 * ��Ϊ Java�ķ���ʵ�ֻ����� type erasure������ʱ���Ͳ��������ͱ���֮���� Object �� ǿ������ת����
 * ��Ҫע����ǣ���ķ������Է��� T, List<T> �ⶼ�У���Ҫ���� T[]�� �������ģ���Ϊ�ⲿʹ�õ�ʱ�� T ������ʵ�ʵ����ͣ�
 * ��ʱ���������ͼ����ȷ�� ���ڲ���һ�� Object[]����������Ԫ�ض��� T ���ͣ����Ե����ó��� cast ��û���⣬���� Object[]
 * �������Ҫ cast ��ĳ�־������͵����飬���׳�����ʱ�쳣��
 */

//�������ʵ��

class ArrayStack<T> {
	private int maxSize;
	private T[] stack;
	private int top = -1; // ָ��ջ����ջ��ջ��ָ���Ǳ仯�� �� ��Ϊ������ģ�⣬����ջ��ָ����������±�0������ͺ���

	public ArrayStack(int maxSize) {
		this.maxSize = maxSize;
		//����Object�������飬ǿ������ת��ΪT����
		stack = (T[])new Object[maxSize];
	}

	// ջ��
	public boolean isFull() {
		return top == maxSize - 1;
	}

	// ջ��
	public boolean isEmpty() {
		return top == -1;
	}
	//ջ����
	public int size() {
		return top+1;
	}

	// ��ջ--push
	public void push(T values) {
		if (isFull()) {
			System.out.println("ջ�����޷����");
			return;
		}
		stack[++top] = values;
	}

	// ��ջ--pop
	public T pop() {
		if (isEmpty()) {
			// System.out.println("ջ�գ��޷���ջ");
			// return 0;
			// �׳��쳣���������ؽ����ʽ , ��Ч�ı����˷���ֵ������
			throw new RuntimeException("ջ�գ��޷���ջ");
		}
		return stack[top--];
	}

	// ��ʾջ
	public void showStack() {
		if (isEmpty()) {
			System.out.println("ջ�գ��޷���ʾ");
			return;
		}
		for (int i = top; i >= 0; i--) {
			System.out.println("λ��"+i+":"+stack[i]);
		}
	}
}