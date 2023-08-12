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
	 * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
	 *
	 *
	 * @param lists ListNode类ArrayList
	 * @return ListNode类
	 */
	public ListNode mergeKLists(ArrayList<ListNode> lists) {
		// write code here
		// //暴力排序破解
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

		// 下面用一下推荐的归并算法来解决
		/*
		 * 如果是两个链表，可以很轻易地想到归并，k个链表，依旧可以归并，你就把这k个链表当作已经分到最小的时候的k个链表，自底向上归并，直到所有就结束了
		 */

		// return mergeSort(lists);

		return priorityQueueWay(lists);
	}

	// 归并方式
	// public ListNode mergeSort(ArrayList<ListNode> lists ) {
	// return divideMerge(lists,0,lists.size()-1);

	// }

	// private ListNode divideMerge(ArrayList<ListNode> lists ,int lo,int
	// hi){//这个list就像数组一样

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
	// //整理一下
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

	// 就离谱，java能直接创建优先队列
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
		// 优先队列构建完成，amazing
		ListNode newHead = new ListNode(0);
		ListNode tmp = newHead;

		while (!priorityQueue.isEmpty()) {
			tmp.next = priorityQueue.poll();
			tmp = tmp.next;
			if (tmp.next != null) {
				priorityQueue.add(tmp.next); // 其实和我第一个暴力的方式一样，不过采用了优先队列来替换我上面的循环搜索
			}
		}
		return newHead.next;
	}

}