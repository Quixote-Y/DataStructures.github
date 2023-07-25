package linkedList;
//与瑟夫环问题 ： 一个环，每个节点都有编号，选定一个起始点，选定一个间隔k，每隔k取出一个数，把他去除，直到环完全去除，求取的路线

import java.util.ArrayList;
import java.util.List;

//用单向循环链表来实现

import queue.CircleArrayQueueTest;

public class JosepfuCircle {

	//10  1 3 ——》3 -> 6 -> 9 -> 2 -> 7 -> 1 -> 8 -> 5 -> 10 -> 4 -> 
	//5 1 2 ---》2 -> 4 -> 1 -> 5 -> 3 -> 
	public static void main(String[] args) {
		CricleSingleLinkedList josepfu = new CricleSingleLinkedList(5);
		josepfu.showList();

		List list = josepfu.getJosepfu(1, 2);
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " -> ");
		}
	}
}

class CricleSingleLinkedList {
	private Boy first;// 一个节点用于指向第一个节点
	private Boy end; // 循环链表中最后一个添加的元素位置

	public CricleSingleLinkedList(int length) {
		// 传入节点数，创建单向循环链表
		if (length < 1) {
			System.out.println("长度太短不构成环");
			return;
		}
		for (int i = 1; i < length + 1; i++) {
			Boy node = new Boy(i);
			if (i == 1) {
				first = node; // 第一个节点
				end = node;
				node.setNext(node);// 循环
			} else {
				end.setNext(node);
				node.setNext(first);
				end = node;
			}
		}
	}

	public void showList() {
		if (first == null) {
			System.out.println("没有构建约瑟夫环");
			return;
		}
		Boy tmp = first;
		while (true) {
			System.out.println(tmp);
			tmp = tmp.getNext();
			if (tmp == first) {
				break;
			}
		}
	}

	public int size() {
		return end.getId() - first.getId();
	}

	public List<Integer> getJosepfu(int k, int m) {
		// 从第k个元素开始，每隔m个取一个节点
		// 先找到第k个元素
		Boy tmp = first;
		for (int i = 0; i < k-1; i++) {
			tmp = tmp.getNext();
		}
		// 开始计数
		List<Integer> list = new ArrayList<>();
		int count = 0;
		while (true) {
			count++;
			if (count == m - 1) {
				// 这里是位于要去除节点的前一个，因为是单向链表
				list.add(tmp.getNext().getId());
				tmp.setNext(tmp.getNext().getNext());
				count = 0;
			}
			tmp = tmp.getNext();
			if(tmp.getNext().getId()==tmp.getId()) {
				list.add(tmp.getId());
				break;
			}
		}
		return list;
	}
}

//boy节点，用于表示循环链表中的 节点
class Boy {
	private int id;
	private Boy next;

	public Boy(int id) {
		super();
		this.id = id;
	}

	public Boy() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boy getNext() {
		return next;
	}

	public void setNext(Boy next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "Boy [id=" + id + "]";
	}

}
