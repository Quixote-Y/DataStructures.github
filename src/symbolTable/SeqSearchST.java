package symbolTable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//��������������ű��˳�����
/*������ʵ�ַ��ű�Ļ���API
 * 
 */
public class SeqSearchST<Key, Value> {

	private Node head = new Node();// ����ͷ�ڵ�
	

	public SeqSearchST(SeqSearchST<Key, Value>.Node head) {
		super();
		this.head = head;
	}
	

	public SeqSearchST() {
		super();
	}


	// ���ű�ʹ��һ��˽���ڲ���Node���������б������ֵ��get��put������˳����Ҹ��ĵļ����ҵ��ͷ��ػ���£� put����û�ҵ�������ֵ
	private class Node {
		// ����ڵ�Ķ���
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
	//����һ����������
	//Ҳ���Ƿ���һ��List.iterator ���������List����������еļ�
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
        //����������
		Iterator<Character> iterator = searchST.keys();
		while(iterator.hasNext()) {
			char key = (char)iterator.next();
			System.out.print(key+"  ");
		}
		
		int size =searchST.size();
		System.out.println("\n"+size);
		//ɾ������
		searchST.delete('E');
		iterator = searchST.keys();
		while(iterator.hasNext()) {
			char key = (char)iterator.next();
			System.out.print(key+"  ");
		}
		
		int b =searchST.get('B');
		System.out.println("\nB��ֵ�ǣ�"+b);
	}
}
//��ô���������ű����java Map��ĵײ�ʵ����
