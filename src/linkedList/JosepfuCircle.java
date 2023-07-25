package linkedList;
//��ɪ������ �� һ������ÿ���ڵ㶼�б�ţ�ѡ��һ����ʼ�㣬ѡ��һ�����k��ÿ��kȡ��һ����������ȥ����ֱ������ȫȥ������ȡ��·��

import java.util.ArrayList;
import java.util.List;

//�õ���ѭ��������ʵ��

import queue.CircleArrayQueueTest;

public class JosepfuCircle {

	//10  1 3 ������3 -> 6 -> 9 -> 2 -> 7 -> 1 -> 8 -> 5 -> 10 -> 4 -> 
	//5 1 2 ---��2 -> 4 -> 1 -> 5 -> 3 -> 
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
	private Boy first;// һ���ڵ�����ָ���һ���ڵ�
	private Boy end; // ѭ�����������һ����ӵ�Ԫ��λ��

	public CricleSingleLinkedList(int length) {
		// ����ڵ�������������ѭ������
		if (length < 1) {
			System.out.println("����̫�̲����ɻ�");
			return;
		}
		for (int i = 1; i < length + 1; i++) {
			Boy node = new Boy(i);
			if (i == 1) {
				first = node; // ��һ���ڵ�
				end = node;
				node.setNext(node);// ѭ��
			} else {
				end.setNext(node);
				node.setNext(first);
				end = node;
			}
		}
	}

	public void showList() {
		if (first == null) {
			System.out.println("û�й���Լɪ��");
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
		// �ӵ�k��Ԫ�ؿ�ʼ��ÿ��m��ȡһ���ڵ�
		// ���ҵ���k��Ԫ��
		Boy tmp = first;
		for (int i = 0; i < k-1; i++) {
			tmp = tmp.getNext();
		}
		// ��ʼ����
		List<Integer> list = new ArrayList<>();
		int count = 0;
		while (true) {
			count++;
			if (count == m - 1) {
				// ������λ��Ҫȥ���ڵ��ǰһ������Ϊ�ǵ�������
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

//boy�ڵ㣬���ڱ�ʾѭ�������е� �ڵ�
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
