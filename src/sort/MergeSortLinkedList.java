package sort;

//自底向上的归并和自顶向下的归并的数组比较次数和访问次数相同，但自底向上的归并更适用与链表排序
//只需组织链表链接就能实现原地归并，无需额外空间
public class MergeSortLinkedList {

	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		list.add(new Node(1));
		list.add(new Node(20));
		list.add(new Node(-320));
		list.add(new Node(3221));
		list.add(new Node(40));
		list.add(new Node(21));
		list.add(new Node(2));
		list.add(new Node(0));

		list.showList();
		mergeSort(list);
		System.out.println();
		list.showList();
	}

	// 尝试一下
	public static void mergeSort(LinkedList a) {
	//	a.getHead().setNext(mergeSort(a.getHead().getNext())) ;
		sortList(a.getHead().getNext());
	}

	private static Node mergeSort(Node head) {
		if (head == null||head.getNext()==null) {
			return head;
		}
		Node mid = findMid(head);
		Node right = mergeSort(mid.getNext());// 右边子链
		mid.setNext(null);
		Node left = mergeSort(head);
		return merge(left, right);
	}

	//没写出来
//	//自底向上
//	private static void mergeSort2(Node head) {
//		for(int sz =1 ;sz < size(head)-sz;sz=sz+sz) {
//			for(int lo = 0;lo<size(head)-sz-1;lo+=sz) {
//				Node left = getKey(head,lo);
//				Node right = getKey(head,lo+sz);
//				head = getKey(head,Math.min(lo+sz+sz,size(head)-1));
//				getKey(head,lo+sz-1).setNext(null);
//				getKey(head,Math.min(lo+sz+sz-1,size(head)-1)).setNext(null);
//				Node newHead =merge(left , right);
//				last(newHead).setNext(head);
//				head.setNext(newHead);
//				
//			}
//		}
//	}
	//有错
	public static Node sortList(Node head) {
	    if (head == null || head.getNext() == null) {
	        return head;
	    }
	    int len = getLength(head);
	    Node dummy = new Node(-1);
	    dummy.setNext(head);
	    for (int i = 1; i < len; i *= 2) {
	        Node cur = dummy.getNext();
	        Node tail = dummy;
	        while (cur != null) {
	            Node left = cur;
	            Node right = split(left, i);
	            cur = split(right, i);
	            Node merged = merge(left, right);
	            tail.setNext(merged);
	            while (tail.getNext() != null) {
	                tail = tail.getNext();
	            }
	        }
	    }
	    return dummy.getNext();
	}

	private static int getLength(Node head) {
	    int len = 0;
	    while (head != null) {
	        len++;
	        head = head.getNext();
	    }
	    return len;
	}

	private static Node split(Node head, int n) {
	    for (int i = 1; head != null && i < n; i++) {
	        head = head.getNext();
	    }
	    if (head == null) {
	        return null;
	    }
	    Node second = head.getNext();
	    head.setNext(null);
	    return second;
	}
	
	private static Node merge(Node left, Node right) {
		Node newHead = new Node(); // 新链头
		Node tmp = newHead; // 构建新链的指针

		while (left != null && right != null) {
			if (left.compareTo(right) == 1) {
				// 左边比右边大
				tmp.setNext(right);
				// 后移
				right = right.getNext();
				tmp = tmp.getNext();
			} else {
				// 左边小于等于右边
				tmp.setNext(left);
				// 后移
				left = left.getNext();
				tmp = tmp.getNext();
			}
		}

		if (left != null) {
			tmp.setNext(left);
		}
		if (right != null) {
			tmp.setNext(right);
		}
		
		return newHead.getNext();
	}

	public static Node getKey(Node head,int k) {
		Node tmp = head;
		for(int i=0;i<k;i++) {
			tmp = tmp.getNext();
		}
		return tmp;
	}
	//链表的长度
	public static int size(Node node) {
		Node tmp = node;
		int i = 0;
		while (tmp.getNext()!= null) {
			tmp=tmp.getNext();
			i++;
		}
		return i;
	}
	// 找到子链的中间节点
	public static Node findMid(Node node) {
		Node slow = node;
		Node fast = node.getNext();
		while (fast != null && fast.getNext() != null) {
			slow = slow.getNext();
			fast = fast.getNext().getNext();
		}
		return slow;

	}
	public static Node last(Node node) {
		Node tmp = node;
		while(tmp.getNext()!=null) {
			tmp = tmp.getNext();
		}
		return tmp;
	}
}
//因为归并要切断主链，分成一段段的子链然后拼接，如果加上头节点的话调用方法就显得繁杂

class LinkedList {
	// 带个头节点
	private Node head = new Node();

	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

	public boolean isEmpty() {
		return head.getNext() == null;
	}

	// 精妙
	public Node findMid() {
		Node slow = head;
		Node fast = head;
		while (fast != null && fast.getNext() != null) {
			slow = slow.getNext();
			fast = fast.getNext().getNext();
		}
		return slow;
	}

	public int size() {
		Node tmp = head;
		int i = 0;
		while (tmp.getNext() != null) {
			i++;
		}
		return i;
	}

	public void add(Node a) {
		Node tmp = head;
		while (true) {
			if (tmp.getNext() == null) {
				tmp.setNext(a);
				break;
			}
			tmp = tmp.getNext();
		}
	}
	public void showList() {
		Node tmp =head;
		while((tmp=tmp.getNext())!=null){
			System.out.print(tmp.getValue()+" ");
		}
	}
}

class Node implements Comparable<Node> {
	private int id;
	private int value;
	private Node next;

	public Node(int id, int value) {
		super();
		this.id = id;
		this.value = value;
	}

	public Node(int value) {
		super();
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node() {
		super();
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", value=" + value + "]";
	}

	@Override
	public int compareTo(Node o) {
		// TODO 自动生成的方法存根
		if (value < o.getValue())
			return -1;
		else if (value == o.getValue())
			return 0;
		else
			return 1;
	}

}
