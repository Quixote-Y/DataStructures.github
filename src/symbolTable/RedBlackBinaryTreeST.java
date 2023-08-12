package symbolTable;

//������Ż����������
/*
 * ���������������Ԫ���Զ����£�������Ԫ�ر���һ��˳����12345678�����ģ����������ͻ�߶Ⱥܸߣ���͵����˲���Ч�ʵ��£�ƽ���������1.39lgN���������±��������黹Ҫ�����������ǲ��뿴����
 * 2-3���� �ڶ������Ļ����ϣ������ 3-�ڵ㣬���԰���������ֵ��3�����ӣ��ֱ���С�������������������м䣬��������������������ĵط����ڣ����������
 *         �������ϲ��룬����Ԫ����Զ����ײ���룬2-�ڵ�����3-�ڵ㣬3-�ڵ���룬��ʱ4-�ڵ㣬�ֽ⣬�м�����ƣ����븸�ڵ㣬����3-��4-�ڵ㣬�ظ�������ֱ������4-�ڵ㣬�����������ڵ㣬�Ǿ����ĸ߶�+1���ֽ�Ϊ����2-�ڵ�
 * ��������ö������ļܹ�ά��һ�� 2-3�� ���������һ����ɫ���� ������ɫΪ Red ʱ���ɷ�ƽ����ʾ������������㴦��ͬһ���߼��ڵ��У�blackΪ��ͨ���ӣ���������߼�����ȫ����2-3��
 */
/*
 * ���ά������һ���������ܹ��µĺ������
 *  ������ܵȼ���2-3���ĳ�Ҫ������
 *  1.�����Ӿ�Ϊ������              
 *  2.û���κ�һ���ڵ���������������-->��֤���ֻ��3-�ڵ�
 *  3.����������ɫƽ���
 *  
 *  ά����ʽ��
 *    1.��ת�������������������Ľṹ���ı�����ӵ�λ��
 *    2.��ɫת����һ���ڵ������ӽڵ��Ϊ�죬�ӽڵ�ȥ�죬ת�Ƶ��Լ�������������
 *    -->����һ���߼��ջ���
 *           1.һ���ڵ������Ӻ�������ͨ������ת����ת��Ϊ�����
 *           2.һ���ڵ������Ǻ��������ӵ��������Ǻ������Ǹ��ڵ�������������Գ�Ϊ������ɫת�����������
 *           3.���Һ��Ӿ��Ǻ���������ת��������������
 *           4.������ֱ��������������������������������������ô��1��ʱ��ͻὫ�Һ���ת��Ϊ������������2
 */
public class RedBlackBinaryTreeST<Key extends Comparable, Value> {
	private Node root;
	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private class Node {
		Key key;
		Value value;
		Node left;
		Node right;
		int count;
		Boolean color; // ��ɫ��ָ������ڵ�����ӵ���ɫ

		public Node(Key key, Value value, RedBlackBinaryTreeST<Key, Value>.Node left,
				RedBlackBinaryTreeST<Key, Value>.Node right, int count, Boolean color) {
			super();
			this.key = key;
			this.value = value;
			this.left = left;
			this.right = right;
			this.count = count;
			this.color = color;
		}

		public Node() {
			super();
		}

	}

	// ��������count
	private int size(Node node) {
		if (node == null)
			return 0;
		else {
			return node.count;
		}
	}

	// ���һ���������ؽ�����ɫ
	private boolean isRed(Node node) {
		if (node == null)
			return false; // ���Ҳ��ϸ�ڣ���nodeΪnull��ʱ��Ͳ����ڵ���node.color�ˣ����׳��쳣
		return node.color == RED;
	}

	// ��������
	private Node rotateLeft(Node h) {
		Node tmp = h.right;        //�������л�ͼ�����ṹ�ͺ������� �㷨��P277
		h.right = tmp.left;
		tmp.left = h;             //����֮��tmpָ��ԭ���ĸ��ڵ�h
		tmp.color = h.color;
		h.color = RED;
		// ����count
		tmp.count = h.count;
		h.count = size(h.left) + size(h.right) + 1;    //��Ϊ���ҽڵ��������û�з����仯���������ں������˵ı仯
		return tmp;
	}
	
	//��������
	private Node rotateRight(Node h) {
		Node tmp = h.left;
		h.left = tmp.right;
		tmp.right = h;
		tmp.color = h.color;
		h.color = RED;
		tmp.count = h.count;
		h.count = size(h.left)+size(h.right)+1;
		return tmp;
	}
	
	//�����Ҷ��Ǻ���ʱ��������ɫת��
	private void flipColors(Node node) {
		node.color =RED;        //��Ҫ���Լ������ڵ㣩��ΪRED����������
		node.left.color = BLACK;
		node.right.color = BLACK;
	}
	
	
	//��ʼ�����㷨
	public void put(Key key,Value value) {
		root = put(root,key,value);
		//���ڵ�Ĭ��ΪBLACK
		root.color = BLACK;
	}
	private Node put(Node node ,Key key,Value value) {
		if(node == null) {
			return new Node(key,value,null,null,1,RED);//Ĭ���Ժ������
		}
		int cmp = key.compareTo(node.key);
		if(cmp<0) {
			node.left = put(node.left,key,value);
		}else if(cmp>0) {
			node.right = put(node.right,key,value);
		}else {
			node.value = value;
		}
		
		//��ʼ��ؼ�������
		if(isRed(node.right)&&!isRed(node.left)) {               //�ұ��Ǻ����߲��Ǻ� -------------------������
			//����Ҳ˵һ�°ɣ����ȣ������ȫ�����Լ���д�ҿ��㲻���뵽д��isRed��������ȥֱ���жϣ�
			// ˵һ������д�ĺô��� �ؼ������ж��˵�right �� leftΪnull�����������дҲ���ӵ��������ɶ��Ը��ߣ�Ӧ��ѧϰ
			node =  rotateLeft(node);
			//���ﲻ�ܷ��أ���Ϊ�ͺ�����ж���һ��ģ��ݽ��Ĺ�ϵ
		}
		if(isRed(node.left)&&isRed(node.left.left)) {     //�Լ��ͺ��ӵ��������Ǻ�
			node = rotateRight(node);                      //�Լ��������������������ɫת��������
		}
		if(isRed(node.left)&&isRed(node.right)) {          //�Լ�����������������red
			flipColors(node);                              //��ɫת������������
		}
		
		//���Լ�������ݹ���ϲ�
		return node;
	}
	
	

}
