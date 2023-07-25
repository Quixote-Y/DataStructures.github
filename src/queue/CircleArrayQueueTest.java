package queue;

import java.util.Scanner;

//ʹ������ʵ��ѭ������
public class CircleArrayQueueTest {

	public static void main(String[] args) {
		CircleArrayQueue arrayQueue = new CircleArrayQueue(5);

		boolean loop = true;
		int input = 0;
		Scanner scanner = new Scanner(System.in);

		while (loop) {
			System.out.println("1.���");
			System.out.println("2.����");
			System.out.println("3.��ʾ����");
			System.out.println("4.�˳�");
			System.out.println("���������볢�ԵĹ��ܣ�");
			input = scanner.nextInt();
			switch (input) {
			case 1:
				System.out.println("������Ҫ��ӵ�Ԫ��");
				input = scanner.nextInt();
				try {
					arrayQueue.enQueue(input);
				} catch (Exception e) {
					//��ΪenQueue���׳�����ʱ�쳣���������
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				try {
					int getInt = arrayQueue.getQueue();
					System.out.println("�õ���Ԫ���ǣ�"+getInt);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				try {
					arrayQueue.showQueue();
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				loop =false ;
				break;
			}
		}
		System.out.println("�˳�ϵͳ");
	}
}

class CircleArrayQueue {
	// 1.frontʼ��ָ�����ͷԪ�أ���rearʼ��ָ�����βԪ�ص���һ��λ��
	// 2.��ʼ front= rear =0
	// 3.��ЧԪ�ظ����� ��rear+ maxSize -front��% maxSize : ������rear��front�͡�front�������
	// 4.����ʱ��������أ� ���������ַ�ʽ �� 1.����һ������ı�־λ������ȷ���Ƿ������������һ�ζ�β���ڶ��׵�ʱ��Ϳ��԰�����Ϊtrue
	// 2.����һ���ռ�Ԫ�� ������Լ�� ����βԪ�� ����һ��Ԫ��Ϊ ����Ԫ��ʱ�����г��ֶ���״̬
	// 5.���ǲ��õڶ��ַ�ʽ�ж϶����� ��rear+1��%maxSize = front :ȡ�������ж϶�����0����rear�Ѿ�ָ�����һ��Ԫ�ص����

	private int maxSize;
	private int front;
	private int rear;
	private int[] arr;// �ײ������

	// ������
	public CircleArrayQueue(int maxSize) {
		this.maxSize = maxSize;
		arr = new int[maxSize];// ��ʼ������
		front = 0;
		rear = 0;
	}

	// ������ЧԪ�ظ���������
	public int size() {
		return (rear + maxSize - front) % maxSize;
	}

	// ����
	public boolean fullQueue() {
		if ((rear + 1) % maxSize == front) {
			return true;
		}
		return false;
	}

	// �ӿ�
	public boolean isEmpty() {
		if (front == rear) {
			return true;
		}
		return false;
	}

	// ����
	public boolean enQueue(int e) {
		if (fullQueue()) {// ����
			throw new RuntimeException("�����Ѿ����ˣ��޷����");
		} else {
			// 1.��Ԫ�طŵ�rearλ��
			// 2.rear�߼����� ,ȡ�� ��֤��λ�����һ��ʱ��ص�arr��0��
			arr[rear] = e;
			rear = (rear + 1) % maxSize;
			return true;
		}
	}

	// ����
	public int getQueue() {
		if (isEmpty()) {
			throw new RuntimeException("����Ϊ�գ��޷�ȡ������");
		} else {
			// �ó�frontָ���Ԫ��
			// front����
			int tmp = arr[front];
			front = (front + 1) % maxSize;
			return tmp;
		}
	}

	// ��ʾ������������
	public void showQueue() {
		if (isEmpty()) {
			throw new RuntimeException("����Ϊ�գ��޷���ʾ����");
		} else {
			for (int i = front; i < front + size(); i++) {
				System.out.println("arr["+i%maxSize+"]="+arr[i % maxSize]);
			}
		}
	}

}
