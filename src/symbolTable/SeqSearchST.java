package symbolTable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//基于无序链表符号表的顺序查找
/*用链表实现符号表的基本API
 * 
 */
public class SeqSearchST<Key, Value> {

	private Node head = new Node();// 链表头节点
	

	public SeqSearchST(SeqSearchST<Key, Value>.Node head) {
		super();
		this.head = head;
	}
	

	public SeqSearchST() {
		super();
	}


	// 符号表使用一个私有内部类Node来在链表中保存键和值，get和put方法会顺序查找给的的键，找到就返回或更新， put方法没找到插入新值
	private class Node {
		// 链表节点的定义
		Key key;
		Value value;
		Node next;
		
		public Node(Key key, Value value, Node next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}

		public Node() {
			super();
		}
		
	}

	public Value get(Key key) {
		Node tmp = head;
		while (true) {
			if (tmp.next != null) {
				tmp = tmp.next;
			} else {
				return null;
			}
			if (tmp.key.equals(key))
				return tmp.value;
		}
	}
	public void put(Key key ,Value value) {
		Node tmp =head;
		while(true) {
			if(tmp.next!=null) {
				tmp = tmp.next;
			}else {
				Node newNode = new Node(key,value,null);
				tmp.next = newNode;
				break;
			}
			if(tmp.key.equals(key)) {
				tmp.value = value;
				break;
			}
		}
	}
	public int size() {
		Node tmp = head;
		int length=0;
		while(true) {
			if((tmp=tmp.next)!=null) {
				length++;
			}else {
				break;
			}
		}
		return length;
	}
	//返回一个迭代器，
	//也就是返回一个List.iterator ，不过这个List里包含了所有的键
	public Iterator<Key> keys(){
		Node tmp = head;
		List<Key> list = new ArrayList<>();
		while(true) {
			if(tmp.next!=null) {
				tmp = tmp.next;
				list.add(tmp.key);
			}else {
				break;
			}
		}
		return list.iterator();
	}
	
	public void delete(Key key) {
		Node tmp = head;
		while(true) {
			if(tmp.next!=null) {
				if(tmp.next.key.equals(key)) {
					tmp.next = tmp.next.next;
					break;
				}
				tmp = tmp.next;
			}else {
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		SeqSearchST<Character,Integer> searchST = new SeqSearchST<>();
		searchST.put('A', 1);
		searchST.put('B', 2);
		searchST.put('C', 3);
		searchST.put('D', 4);
		searchST.put('E', 5);
		searchST.put('F', 5);
        //迭代器测试
		Iterator<Character> iterator = searchST.keys();
		while(iterator.hasNext()) {
			char key = (char)iterator.next();
			System.out.print(key+"  ");
		}
		
		int size =searchST.size();
		System.out.println("\n"+size);
		//删除测试
		searchST.delete('E');
		iterator = searchST.keys();
		while(iterator.hasNext()) {
			char key = (char)iterator.next();
			System.out.print(key+"  ");
		}
		
		int b =searchST.get('B');
		System.out.println("\nB的值是："+b);
	}
}
//这么看下来符号表就是java Map类的底层实现了
