package hashTable;

//������������ɢ�б�
public class SeparateChainingHashST<Key extends Comparable ,Value> {

	//����ɢ�б�--��ϣ��
	private int count;//����Ч��ֵ����
	private int max ; //ɢ�б�Ĵ�С
	private ListST<Key,Value>[] st ;   //ɢ�б�����
	//���캯��
	public SeparateChainingHashST() {
		this(997);      //�޲ι��캯��Ĭ�ϴ���һ��997��С��ɢ�����飬997����������ɢ�й���ʵ�֣�
	}
	public SeparateChainingHashST(int max) {
		this.max = max;
		//��ʼ����ϣ��
		st = (ListST<Key, Value>[])new ListST[max];      //������������鲻�ӣ�������
		//��ϸ�ڣ����������Ҳ����Ҫ��ʼ��
		for(int i=0;i<max;i++) {
			st[i] = new ListST();
		}
	}
	
	
	//����ڵ�
	public void add(Key key ,Value value) {
		st[hash(key)].add(new NodeST(key,value));
	}
	//�����ڵ㣬�ͱ���Ҫ�ҵ�����������ͨ��hash����hashCode��ȡ  Ĭ��Hash Code�ͳ������������ʹ��
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff ) % max ; //ʲô��˼�أ����ǻ�ȡ��Ӧ��hashCodeֵ��Ĭ�������ĵ�ַ
		                                           //java�еĺܶೣ��������д��hashcode������ط���������Ļ���ʱ����int
		                                            //����ֵӦ���Ƕ�Ӧ��ֵ��Ȼ��0x7fffffff�����ȥ������λ ��Ȼ��ȡ��max���õ�����
	}
	//�õ��ڵ�
	public Value get(Key key) {
		int location =hash(key);
		if(st[location] == null) {
			return null;
		}
		return st[location].get(key);
	}
	
	//չʾ�������ĳ��ȣ��������ɢ�м��� �� ����ֵ���ȷֲ� ����Ҳ��ɢ����Ƶĺ���
	public void showSize() {
		for(int i =0;i<max ;i++) {
			System.out.printf("��%d�������ȣ�%d \t",i,st[i].size());
			if(i%5==0) {
				System.out.println();
			}
		}
	}
	
	
	
	
	//����дһ����֤����
	public static void main(String[] args) {
		SeparateChainingHashST<Integer, Integer> scST  = new SeparateChainingHashST<>();
		long start =System.currentTimeMillis();
		for (int i = 0; i < 80000; i++) {
			scST.add((int) (Math.random() * 80000000), i);
		}
		long end = System.currentTimeMillis();
		//����ӵ�������8���ڣ���ô���е����ݶ��ᱻ��ӵ�ǰ8���������������������ӣ��������ǵ�����Ĵ�СӦ���ǿ����������ֵ��������������С��֮����Ӧ��������Ҳ��С��
		//����ÿһ�����ܳ���Ч�ʽϵͣ������ݱȽϴ�ʱ�ٶȾͺܿ죬����ɢ�зֲ��ܺ�
		System.out.println("��ӽ��� ��ʱ:"+(end-start)/1000);
		scST.showSize();
	}
}

class ListST<Key extends Comparable, Value> {
	private NodeST head;

	//��С
	public int size() {
		if(head == null) {
			return 0;
		}
		NodeST tmp =head;
		int count =0;
		while(tmp.next != null) {
			count++;
			tmp = tmp.next;
		}
		return count++;
	}
	//��ȡͷ�ڵ�--��һ�����ݽڵ�
	public NodeST getHead() {
		return head;
	}
	

	// ֱ����ӵ���β
	public void add(NodeST node) {
		if (head == null) {
			head = node;
			return ;
		}
		NodeST tmp = head;
		while (tmp.next != null) {
			tmp = tmp.next;
		}
		tmp.next = node;
	}

	// �õ�
	public Value get(Key key) {
		if (head == null) {
			return null;
		}
		NodeST tmp = head;
		while (tmp.key != key && tmp.next != null) {
			tmp = tmp.next;
		}
		if(tmp.key == key) {
			return (Value)tmp.value;
		}
		return null;
	}

}
// ɢ�б�������Ľڵ�
class NodeST<Key extends Comparable, Value> {
	Key key;
	Value value;
	NodeST next;

	public NodeST(Key key, Value value) {
		super();
		this.key = key;
		this.value = value;
	}
	//������дhashCode

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((key == null) ? 0 : key.hashCode());
//		result = prime * result + ((value == null) ? 0 : value.hashCode());           
//		return result;                            
//	} 
	//-->��ô���������δ����أ� ��ϼ���HashCode���൱���ַ�����hashCode�����ǰѼ���������ϵ���һ��prime���Ƶ�����
	//   ������ֵ��� �� Value * prime *prime + key *prime

//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		NodeST other = (NodeST) obj;
//		if (key == null) {
//			if (other.key != null)
//				return false;
//		} else if (!key.equals(other.key))
//			return false;
//		if (next == null) {
//			if (other.next != null)
//				return false;
//		} else if (!next.equals(other.next))
//			return false;
//		if (value == null) {
//			if (other.value != null)
//				return false;
//		} else if (!value.equals(other.value))
//			return false;
//		return true;
//	}
	
}
