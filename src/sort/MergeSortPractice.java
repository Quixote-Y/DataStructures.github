package sort;

import java.util.*;

class ListNode {
	int val;
	ListNode next = null;

	public ListNode(int val) {
      this.val = val;
    }
}

public class MergeSortPractice {
	/**
	 * �����е����������������������Ѿ�ָ���������޸ģ�ֱ�ӷ��ط����涨��ֵ����
	 *
	 *
	 * @param lists ListNode��ArrayList
	 * @return ListNode��
	 */
	public ListNode mergeKLists(ArrayList<ListNode> lists) {
		// write code here
		// //���������ƽ�
		// ListNode newHead = new ListNode(0);
		// ListNode tmp = newHead;
		// while (true) {
		// if (lists.isEmpty()) {
		// break;
		// }
		// lists.sort(new Comparator() {
		// @Override
		// public int compare(Object o1, Object o2) {
		// if (o1 == null && o2 == null) {
		// return 0;
		// } else if (o1 == null && o2 != null) {
		// return -1;
		// } else if (o1 != null && o2 == null) {
		// return 1;
		// } else {
		// return ((ListNode)o1).val - ((ListNode)o2).val;
		// }
		// }
		// });
		// tmp.next = lists.get(0);
		// lists.remove(0);
		// if (tmp.next == null) {
		// continue;
		// }
		// tmp = tmp.next ;
		// System.out.print(tmp.val);
		// if (tmp.next == null) {
		// continue;
		// }
		// lists.add(tmp.next);
		// }

		// return newHead.next;

		// ������һ���Ƽ��Ĺ鲢�㷨�����
		/*
		 * ����������������Ժ����׵��뵽�鲢��k���������ɿ��Թ鲢����Ͱ���k���������Ѿ��ֵ���С��ʱ���k�������Ե����Ϲ鲢��ֱ�����оͽ�����
		 */

		// return mergeSort(lists);

		return priorityQueueWay(lists);
	}

	// �鲢��ʽ
	// public ListNode mergeSort(ArrayList<ListNode> lists ) {
	// return divideMerge(lists,0,lists.size()-1);

	// }

	// private ListNode divideMerge(ArrayList<ListNode> lists ,int lo,int
	// hi){//���list��������һ��

	// if(hi<lo){
	// return null;
	// }
	// if(hi==lo){
	// return lists.get(lo);
	// }

	// int mid = lo + (hi-lo)/2;
	// // ListNode leftNode = divideMerge(lists,lo,mid);
	// // ListNode rightNode = divideMerge(lists,mid+1,hi);
	// // ListNode resNdoe = merge(leftNode,rightNode);
	// // return resNdoe;
	// //����һ��
	// return merge(divideMerge(lists,lo,mid),divideMerge(lists,mid+1,hi));

	// }

	// public ListNode merge(ListNode left,ListNode right){
	// ListNode newHead = new ListNode(0);
	// ListNode tmp = newHead;
	// while(left!=null&&right!=null){
	// if(left.val>right.val){
	// tmp.next = right;
	// right = right.next;
	// }else{
	// tmp.next = left;
	// left = left.next;
	// }
	// tmp = tmp.next;
	// }
	// if(left!=null){
	// tmp.next = left;
	// }
	// if(right!=null){
	// tmp.next = right;
	// }
	// newHead = newHead.next;
	// return newHead;
	// }

	// �����ף�java��ֱ�Ӵ������ȶ���
	public ListNode priorityQueueWay(ArrayList<ListNode> lists) {
		Queue<ListNode> priorityQueue = new PriorityQueue<>(new Comparator<ListNode>() {
			@Override
			public int compare(ListNode o1, ListNode o2) {
				return (o1).val - (o2).val;
			}

		});
		for (int i = 0; i < lists.size(); i++) {
			if (lists.get(i) != null) {
				priorityQueue.add(lists.get(i));
			}
		}
		// ���ȶ��й�����ɣ�amazing
		ListNode newHead = new ListNode(0);
		ListNode tmp = newHead;

		while (!priorityQueue.isEmpty()) {
			tmp.next = priorityQueue.poll();
			tmp = tmp.next;
			if (tmp.next != null) {
				priorityQueue.add(tmp.next); // ��ʵ���ҵ�һ�������ķ�ʽһ�����������������ȶ������滻�������ѭ������
			}
		}
		return newHead.next;
	}

}