package queue;

import java.util.Scanner;

//使用数组实现循环队列
public class CircleArrayQueueTest {

	public static void main(String[] args) {
		CircleArrayQueue arrayQueue = new CircleArrayQueue(5);

		boolean loop = true;
		int input = 0;
		Scanner scanner = new Scanner(System.in);

		while (loop) {
			System.out.println("1.入队");
			System.out.println("2.出队");
			System.out.println("3.显示队列");
			System.out.println("4.退出");
			System.out.println("请输入您想尝试的功能：");
			input = scanner.nextInt();
			switch (input) {
			case 1:
				System.out.println("请输入要添加的元素");
				input = scanner.nextInt();
				try {
					arrayQueue.enQueue(input);
				} catch (Exception e) {
					//因为enQueue会抛出运行时异常，捕获，输出
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				try {
					int getInt = arrayQueue.getQueue();
					System.out.println("拿到的元素是："+getInt);
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
		System.out.println("退出系统");
	}
}

class CircleArrayQueue {
	// 1.front始终指向队列头元素，而rear始终指向队列尾元素的下一个位置
	// 2.初始 front= rear =0
	// 3.有效元素个数是 （rear+ maxSize -front）% maxSize : 包含了rear《front和》front两种情况
	// 4.上面时候队列满呢？ 可以有两种方式 ： 1.设置一个额外的标志位，用于确定是否队列满。当第一次队尾等于队首的时候就可以把他置为true
	// 2.少用一个空间元素 ，我们约定 当队尾元素 的下一个元素为 队首元素时，队列呈现队满状态
	// 5.我们采用第二种方式判断队满： （rear+1）%maxSize = front :取余数，判断队首在0，而rear已经指向最后一个元素的情况

	private int maxSize;
	private int front;
	private int rear;
	private int[] arr;// 底层的数组

	// 构造器
	public CircleArrayQueue(int maxSize) {
		this.maxSize = maxSize;
		arr = new int[maxSize];// 初始化数组
		front = 0;
		rear = 0;
	}

	// 队中有效元素个数，长度
	public int size() {
		return (rear + maxSize - front) % maxSize;
	}

	// 队满
	public boolean fullQueue() {
		if ((rear + 1) % maxSize == front) {
			return true;
		}
		return false;
	}

	// 队空
	public boolean isEmpty() {
		if (front == rear) {
			return true;
		}
		return false;
	}

	// 进队
	public boolean enQueue(int e) {
		if (fullQueue()) {// 队满
			throw new RuntimeException("队列已经满了，无法添加");
		} else {
			// 1.把元素放到rear位置
			// 2.rear逻辑后移 ,取余 保证当位于最后一个时会回到arr【0】
			arr[rear] = e;
			rear = (rear + 1) % maxSize;
			return true;
		}
	}

	// 出队
	public int getQueue() {
		if (isEmpty()) {
			throw new RuntimeException("队列为空，无法取出数据");
		} else {
			// 拿出front指向的元素
			// front后移
			int tmp = arr[front];
			front = (front + 1) % maxSize;
			return tmp;
		}
	}

	// 显示队列所有数据
	public void showQueue() {
		if (isEmpty()) {
			throw new RuntimeException("队列为空，无法显示数据");
		} else {
			for (int i = front; i < front + size(); i++) {
				System.out.println("arr["+i%maxSize+"]="+arr[i % maxSize]);
			}
		}
	}

}
