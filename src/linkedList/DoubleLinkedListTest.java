package linkedList;

//˫������
public class DoubleLinkedListTest {

	public static void main(String[] args) {
		// �����������β
		HeroNode2 node1 = new HeroNode2(1, "�ν�", "��ʱ��");
		HeroNode2 node2 = new HeroNode2(2, "¬����", "������");
		HeroNode2 node3 = new HeroNode2(3, "����", "�Ƕ���");
		HeroNode2 node4 = new HeroNode2(4, "�ֳ�", "����ͷ");
		HeroNode2 node5 = new HeroNode2(5, "������", "������");
		HeroNode2 node6 = new HeroNode2(6, "������", "������");
		HeroNode2 node7 = new HeroNode2(7, "�����ӳ�", "�����ӳ�");

		// ����˫�����������
		DoubleLinkedList list = new DoubleLinkedList();
		// ��
//		list.add(node1);
//		list.add(node2);
//		list.add(node3);
//		list.add(node4);
//		list.add(node5);
//		list.add(node6);
//		list.add(node7);
		// ��id�������
		list.addById(node1);
		list.addById(node7);
		list.addById(node4);
		list.addById(node2);
		list.addById(node5);
		list.addById(node3);
		list.addById(node6);

		// ��ʾ
		list.showList();
		System.out.println("=======================ɾ==================================");
		// ɾ
		list.deleteNode(5);
		list.showList();
		// ��
		System.out.println("=======================��==================================");
		list.modifyName(new HeroNode2(1, "³����", "С��ȸ"));
		list.showList();
	}
}

//����˫���������
class DoubleLinkedList {
	// private HeroNode2 head;// ͷ�ڵ�//���˳�ʼ��������headΪnull,��ӽ���ʱ�����head.next���ֿ�ָ���쳣
	private HeroNode2 head = new HeroNode2();

	// ĩβ���
	// Ԫ�ؼӵ�����β
	public void add(HeroNode2 node) {
		HeroNode2 tmp = head;
		while (true) {
			if (tmp.getNext() == null) {
				tmp.setNext(node);
				node.setPre(tmp);// ��ϵ�������Ͷ��˸�ǰ��ָ��ָ��
				break;
			} else {
				tmp = tmp.getNext();
			}
		}
	}

	// ������ʾ����
	public void showList() {
		if (head.getNext() == null) {
			System.out.println("����Ϊ�գ��޿���ʾ����");
			return;
		}
		HeroNode2 tmp = head;
		while (true) {
			if ((tmp = tmp.getNext()) != null) {
				System.out.println(tmp);
			} else {
				break;
			}
		}
	}

	// �޸�ָ���ڵ�����
	public void modifyName(HeroNode2 node) {
		if (head.getNext() == null) {
			System.out.println("����Ϊ�գ��޸�ʧ��");
			return;
		}
		HeroNode2 tmp = head;
		while (true) {
			tmp = tmp.getNext();
			if (tmp == null) {
				System.out.println("δ�ҵ���id���޸�ʧ��");
				break;
			}
			if (tmp.getId() == node.getId()) {
				tmp.setName(node.getName());
				tmp.setNickName(node.getNickName());// �͵���������ͬ����Ϊ���漰��ָ��Ĳ���
				break;
			}
		}
		return;
	}

	// ɾ��ָ��id�ڵ�
	public boolean deleteNode(int id) {
		if (head.getNext() == null) {
			System.out.println("����Ϊ�գ�ɾ��ʧ��");
			return false;
		}
		HeroNode2 tmp = head.getNext();
		// ����Ϳ���ֱ�Ӹ�ֵnext�ˣ���Ϊ˫����������������һ������tmp����¼ǰ���ڵ�λ�ã�����ʵ������ɾ�����е�ǰҪ��ɾ���ڵ����Ϣ���㹻����ɾ������
		while (true) {
			if (tmp == null) {
				System.out.println("δ�ҵ�ָ��id�ڵ�");
				return false;
			}
			if (tmp.getId() == id) {
				// ���Լ���ǰ���ڵ����һ���ĳ��Լ�����һ���������Լ�
				tmp.getPre().setNext(tmp.getNext());
				// ����ܹؼ������������жϣ��������ڵ������һ���ڵ㣬�ǾͲ���ȡtmp.next.pre��,��Ϊnext��null
				if (tmp.getNext() != null) {
					// ����������һ���ڵ㣬�ǾͰ��Լ�����һ���ڵ��ǰ��ָ��ָ�� �Լ�����һ���ڵ㣬ά����˫������
					tmp.getNext().setPre(tmp.getPre());
				}
				break;
			}
			tmp = tmp.getNext();
		}
		return true;
	}

	// ��id˳�����
	// ����ڵ�����������,��ΪҪ�޸�ǰ���ڵ㣬���Ե�Ҫ��ӽڵ�������ʱ��Ҳ����Ҫ��ӵ�����β��ʱ�򣬾�Ҫ�ı䷽����
	// ��Ϊ����β�Ľڵ㲻�� tmp.next.pre ��Ϊnext�Ѿ����� null �ˣ���ָ���쳣������˱���Ҫ�����
	// �������Ѿ��ҵ����һ���ڵ��ˣ��Ǿ� ֱ�Ӹ�ǰ����� 
	public void addById(HeroNode2 node) {
		//��Ӳ���tmp������ָ��head
		HeroNode2 tmp = head;
		while (true) {
			if (tmp.getNext() == null) {
				// ˵��tmp�Ѿ�ָ�������һ���ڵ㣬�Ǿ�ֱ�Ӽӵ�������ͺ�
				tmp.setNext(node); // tmp��next��node
				node.setPre(tmp); // node��ǰ����tmp
				break;
			}
			// ��ͷ��ʼ�������ӵ���һ��id��node���ǰ��
			if (tmp.getNext().getId() > node.getId()) {
				// ��ǰtmpָ��ڵ�id��node���Ǿͼ���tmpǰ��
				tmp.getNext().setPre(node);
				node.setNext(tmp.getNext());
				tmp.setNext(node);
				node.setPre(tmp);
				break;
			}
			// ����������
			tmp = tmp.getNext();
		}

	}

}

//�ڵ�
class HeroNode2 {
	private int id;
	private String name;
	private String nickName;
	private HeroNode2 next;// ָ����һ���ڵ�
	private HeroNode2 pre;

	public HeroNode2(int id, String name, String nickName) {
		super();
		this.id = id;
		this.name = name;
		this.nickName = nickName;
	}

	public HeroNode2() {
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

	public HeroNode2 getNext() {
		return next;
	}

	public void setNext(HeroNode2 next) {
		this.next = next;
	}

	public HeroNode2 getPre() {
		return pre;
	}

	public void setPre(HeroNode2 pre) {
		this.pre = pre;
	}

	@Override
	public String toString() {
		return "HeroNode [id=" + id + ", name=" + name + ", nickName=" + nickName + "]";
	}

}