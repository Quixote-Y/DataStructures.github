package linkedList;

import java.util.Stack;

//��������
public class SingleLinkedListTest {

	
	public static void main(String[] args) {
		//�����������β
		HeroNode node1 = new HeroNode(1,"�ν�","��ʱ��");
		HeroNode node2 = new HeroNode(2,"¬����","������");
		HeroNode node3 = new HeroNode(3,"����","�Ƕ���");
		HeroNode node4 = new HeroNode(4,"�ֳ�","����ͷ");
		HeroNode node5 = new HeroNode(5,"������","������");
		HeroNode node6 = new HeroNode(6,"������","������");
		HeroNode node7= new HeroNode(7,"�����ӳ�","�����ӳ�");
		//������ͷ ��Ҳ���Ǵ�������
		SingleLinkedList list = new SingleLinkedList();
		SingleLinkedList list2 = new SingleLinkedList();
		//���Ԫ��
//		list.add(node1);
//		list.add(node4);
//		list.add(node2);
//		list.add(node3);
		
		
		//���������������ģ���������һ��������������
		list.addById(node1);
		list.addById(node7);
		list.addById(node6);
		list.addById(node3);
		
		//����
//		list.modifyName(new HeroNode(1, "³����", "С��ȸ"));
		
		list2.addById(node5);
		list2.addById(node4);
		list2.addById(node2);
		
		//list.deleteNode(4);
		System.out.println("=======================list1==================================");
		list.showList();
		System.out.println("=======================list2==================================");
		list2.showList();
		//����
//		System.out.println("�����ǣ�"+list.size());
		//�����ڼ�λ
//		System.out.println(list.findLast(1));
		
//      // ����ת		
//		System.out.println("=========================================================");
//		list.reverse();
//		list.showList();
		
//		System.out.println("=====================���������ʾ====================================");
//		list.showListBack();
		
		System.out.println("=====================�������򴮺ϲ������ʾ====================================");
		list.mergeSortedList(list2.getHead());
		list.showList();
		
	}
}

class SingleLinkedList{//������Ľṹ�ͷ���
	//ͷ�ڵ�-->�����������������Ļ���ʼ����ʱ��ᴴ����--�������Ļ�����ʵ��һ������һ���㶨�����ͷ�ڵ���
	private HeroNode head = new HeroNode();
	
	//����ͷ�ڵ�
	public HeroNode getHead() {
		return head;
	}
	
	//Ԫ�ؼӵ�����β
	public void add(HeroNode node) {
		HeroNode tmp = head;
		while(true) {
			if(tmp.getNext()==null) {
				tmp.setNext(node);
				break;
			}else {
				tmp=tmp.getNext();
			}
		}
	}
	
	//������ʾ����
	public void showList() {
		if(head.getNext()==null) {
			System.out.println("����Ϊ�գ��޿���ʾ����");
			return;
		}
		HeroNode tmp = head;
		while(true) {
			if((tmp=tmp.getNext())!=null) {
				System.out.println(tmp);
			}else {
				break;
			}
		}
	}
	
	//�����ӡ����
	public void showListBack() {
		if(head.getNext()==null) {
			System.out.println("����Ϊ�գ��޿���ʾ����");
			return;
		}
		
		//����˼·��1.������ת ����ӡ��ת������� ��������ת�Ļ�����ṹ�����仯�����鷳
		//          2. ջ���������������������ÿ��Ԫ��ѹջ��֮����һ��һ����ջ��ʾ
		
		Stack<HeroNode> stack = new Stack<>();
		HeroNode tmp = head;
		//��ջ
		while(true) {
			tmp = tmp.getNext();
			if(tmp==null) {
				break;
			}
			stack.add(tmp);
		}
		//��ջ��ʾ
		int size = stack.size();
		for(int i = 0;i<size ;i++) {
			tmp = stack.pop();
			System.out.println(tmp);
		}
	}
	
	//�������
	
	public void addById(HeroNode node) {
		HeroNode tmp = head;
		while(true) {
			//����ж�λ����β�Ĳ����������ˣ��ͻ�����쳣�����ǵ�λ������棬����tmp��next�Ѿ�Ϊnull�ˣ���Ҫȥ����getid�������ͻᱨ��
			if(tmp.getNext()==null) {
				//˵��tmp�Ѿ�ָ�������һ���ڵ㣬�Ǿ�ֱ�Ӽӵ�������ͺ�
				break;
			}
			//Ҳ���Ǵ�ͷ��ʼ�������ӵ���һ��id��node���ǰ��
			if(tmp.getNext().getId()>node.getId()) {
				//��ǰtmpָ��ڵ����һ���ڵ�id��node���Ǿͼ���tmp����
				break;
			}
			//����������
			tmp=tmp.getNext();
		}
		//ָ�뽻��
		node.setNext(tmp.getNext());
		tmp.setNext(node);
	}
	
	//�޸�ָ���ڵ�����
	public void modifyName(HeroNode node) {
		if(head.getNext()==null) {
			System.out.println("����Ϊ�գ��޸�ʧ��");
			return;
		}
		HeroNode tmp = head;
		while(true) {
			tmp = tmp.getNext();
			if(tmp==null) {
				System.out.println("δ�ҵ���id���޸�ʧ��");
				break;
			}
			if(tmp.getId()==node.getId()) {
				tmp.setName(node.getName());
				tmp.setNickName(node.getNickName());
				break;
			}
		}
		return;
	}
	
	//ɾ��ָ��id�ڵ�
	public boolean deleteNode(int id) {
		if(head.getNext()==null) {
			System.out.println("����Ϊ�գ�ɾ��ʧ��");
			return false;
		}
		HeroNode tmp = head;
		while(true) {
			if(tmp.getNext()==null) {
				System.out.println("δ�ҵ�ָ��id�ڵ�");
				return false;
			}
			if(tmp.getNext().getId()==id) {
				tmp.setNext(tmp.getNext().getNext());//��tmp����һ���ڵ�ָ��ָ����һ������һ��
				break;
			}
			tmp = tmp.getNext();
		}
		return true;
	}

	/*�����ܽ�һ����ɾ�Ĳ�����׳��ִ���ĵط���
	 *     ��ʵ��ɾ���������׳�����Ϊ��ɾ�����ڵ���������tmpָ��Ӧ��ָ�������Ҫ�����ĵط�����һ���ڵ�λ��
	 *     ��Ĳ�ͬ����˳���˳��Ҳ���в��죬��Ҫע����ǣ�  
	 *              1.��Ӧ��ȥ�ж��Ƿ�Ϊ�յ��� tmp (��������Ӧ����tmp�ڵ㣬�Ĳ���)���� tmp.next (���������ǵ�ǰ�ڵ����һ���ڵ㣬����ɾ����)
	 *              2.�������ж������Ƿ�Ϊ��  head==null��
	 *              3. tmpָ����Ƶ�λ�ã� �����ǲ���tmpָ��ĵ�ǰ�ڵ�ʱ�����жϵ���tmp==null�� ����ʼ����tmp= head ��ô���Ӧ�ð�tmp���Ʒ����в�����ǰ��
	 *                                   �������ǲ��� tmpָ�����һ���ڵ�ʱ�����ע�Ķ�����ʵ��tmp����һ����������еıȽϲ���Ӧ�ö��ǻ��� tmp.next ��
	 *                                   ����tmpֻ��Ϊ�˵���ɾʱ�õ���һ���ڵ��ָ�룬������tmp����Ӧ�÷������в����������

	 */	
	
	//������Ч����
	public int size() {
		HeroNode tmp = head;
		int length = 0;
		while((tmp=tmp.getNext())!=null) {
			length++;
		}
		return length;	
	}
	
	//���ҵ�����ĵ�����k
	/*
	 * ˼·��
	 *   1.����ת��ȡ��k��Ԫ��
	 *   2.˫��ѭ�����ڲ���k��Ԫ��Ϊһ�飬�ҵ����һ�飬�ڸ�λ�þ��ǵ�����k��
	 *   3.  !!!!!�G
	 *     ��֪�������ˣ��ҵ�����k��Ԫ�أ������������ҵ� length - k ��Ԫ����
	 */
	
	//��Ϊ����ת�Ļ��͸ı�ԭ���ṹ�ˣ��������ȳ���˫��ѭ��
	public HeroNode findLast(int k) {
//		HeroNode tmp = head ;
//		HeroNode tmp2 = new HeroNode();
//		boolean find = false;
//		int length = this.size();
//		if(length<k) {
//			System.out.println("�����Ȳ������Ҳ���������k��");
//			return null;
//		}
//		if(k==0) {
//			k=1;
//		}
//		for(int i = 0; i< length ;i++) {
//			tmp = tmp.getNext();
//			tmp2 = tmp;
//			for (int j = 0; j < k-1; j++) {
//				tmp2 = tmp2.getNext();
//				if(tmp2.getNext()==null) {
//					find = true ;
//					break;
//				}
//			}
//			if(find==true) {
//				break;
//			}
//		}
//		return tmp;
		
		//����3
		int length = this.size();
		int index = length -k;
		if(index<0||index>length) {//�ж�����λ�ò��Ϸ�
			return null;
		}
		HeroNode tmp =head;
		for(int i = 0;i< index+1 ;i++) {
			tmp = tmp.getNext();
		}
		return tmp;
		
	}
	
	//������ת
	/**
	 * ˼·��
	 *   �½�һ������ͷ��Ȼ�����ԭ����ÿ��Ԫ��ͷ�巨�浽�������У�������ԭ����ͷָ���������ռ临�Ӷ�O��1��
	 *   
	 *   //ͷ�巨 �ʼ����ַ�ʽ
	 *     
	 */
	
	public void reverse() {
		if(head.getNext()==null||head.getNext().getNext()==null) {
			return ;//ֻ��1������û�нڵ㣬ֱ�ӷ���
		}
		HeroNode newHead = new HeroNode();  // �±�ͷ
		HeroNode tmp = head.getNext();                //ԭ�������ڱ������Ľ� ��
		HeroNode tmpNext = null;  //ǰһ���ڵ㱻ȡ������Ӻ� �����������ڼ�¼��һ��Ҫ������Ľڵ�
		
		while(true) {
			tmpNext = tmp.getNext();
			tmp.setNext(newHead.getNext());
			newHead.setNext(tmp);
			tmp = tmpNext;
			if(tmp==null) {
				break;
			}
		}
		//��ͷ���
		head.setNext(newHead.getNext());
	}
	
	//������������ϲ�Ϊ��������
	//����id��С����
	public void mergeSortedList(HeroNode newHead) {
		//�ж�����������Ϊnull
		if(head.getNext()==null) {
			head = newHead;
		}else if(newHead.getNext()==null) {
			return;
		}
		
		HeroNode tmp =head;                     //������ϸ�ڣ�������tmp��head��ʼ����Ϊ��ʵ�Ƚϲ�����ʱ tmpNext �������head.next��ʼ���Ǿͻ�©���������ĵ�һ���ڵ�
		HeroNode tmpNext = tmp.getNext();       // ʵ�ʲ���ȽϵĽڵ㣬������id�� newTmpָ���id��ʱ��˵��newTmp�ڵ�һ������ tmpNextǰ��
		HeroNode newTmp = newHead.getNext();    // �������Ľڵ���뵽������������
		
		//Ҫ��û��ȥ�Ͳ���ı�tmpNext��ֵ�����ִ���
		while(true) {
			if(tmpNext == null) {
				tmp.setNext(newTmp);
				break;
			}
			if(newTmp == null) {
				break;
			}
			if(tmpNext.getId()>newTmp.getId()) {
				tmp.setNext(newTmp);
				newTmp = newTmp.getNext();
				tmp.getNext().setNext(tmpNext);
				//�������
			}
			tmp = tmpNext;
			tmpNext = tmp.getNext();
		}
		
	}
}

class HeroNode{
	private int id;
	private String name;
	private String nickName;
	private HeroNode next ;//ָ����һ���ڵ�
	
	public HeroNode(int id, String name, String nickName, HeroNode next) {
		super();
		this.id = id;
		this.name = name;
		this.nickName = nickName;
		this.next = next;
	}

	
	public HeroNode(int id, String name, String nickName) {
		super();
		this.id = id;
		this.name = name;
		this.nickName = nickName;
	}




	public HeroNode() {
		super();
	}



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public HeroNode getNext() {
		return next;
	}


	public void setNext(HeroNode next) {
		this.next = next;
	}


	@Override
	public String toString() {
		return "HeroNode [id=" + id + ", name=" + name + ", nickName=" + nickName + "]";
	}

	
}
