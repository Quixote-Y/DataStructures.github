package symbolTable;

//��������� ��������� ���ű�
/*
 * ����������Ͷ���Ѳ�ͬ��
 *            ����ѣ�����ʵ�֣���ȫ���������㼶�ṹ���������³������
 *            ���������������ʵ�֣��������һ�����������BST���ڵ�key������������ű���Ҫ���������Ҳ��룬ɾ����
 */
//��������������API
public class BinarySearchTree<Key extends Comparable, Value> {

	Node root;

	// ��һ��˽���ڲ�����Ϊ����Ľڵ�
	private class Node {
		Key key;
		Value value;
		Node left; // ������
		Node right; // ������
		int count; // �Ըýڵ�Ϊ���ڵ�������Ľڵ���

		public Node(Key key, Value value, BinarySearchTree<Key, Value>.Node left,
				BinarySearchTree<Key, Value>.Node right, int count) {
			super();
			this.key = key;
			this.value = value;
			this.left = left;
			this.right = right;
			this.count = count;
		}

		public Node() {
			super();
		}
	}

	// size()
	public int size() {
		return size(root);
	}

	private int size(Node node) {
		if (node == null) {
			return 0;
		} else {
			return node.count;
		}
	}

	// �ݹ�ʵ��
	private Value get(Node node, Key key) {
		Node tmp = node;
		if (tmp == null) {
			// ����ҵ����ڵ�ָ��Ϊnull˵��û������ڵ�
			return null;
		}
		int cmp = key.compareTo(tmp.key);
		if (cmp > 0) {// �ҵ�key���ڽڵ��key�����ұ���
			return get(tmp.right, key);
		} else if (cmp < 0) {// С�������
			return get(tmp.left, key);
		} else {// ���ڣ�ֱ�ӷ���
			return tmp.value;
		}
	}

	// �������ֵ��void�������Ļ��͵��£��ұ�����ڵݹ�֮ǰ�ж�����ݹ��ܷ��ȥ��tmp.left�Ƿ�Ϊnull��rightҲ�ǣ�Ϊnull��δ���
	// Ȼ������������ root�ڵ㣬��ʱrootû��ָ���κνڵ�����δ�����������
	// ����Ϊ�����һ������ֵ�����뿴�ž��Ż�����˺ܶ࣬��δ��봦��ʽ���ú���
	private Node put(Node node, Key key, Value value) {
		Node tmp = node;
		if (tmp == null) {
			// ����Ϊnull�����½��Ľڵ㷵��
			return new Node(key, value, null, null, 1);
		}
		int cmp = key.compareTo(tmp.key);
		if (cmp > 0) {// �ҵ�key���ڽڵ��key�����ұ��� ��ֱ�����⽫����ֵ����right
			tmp.right = put(tmp.right, key, value);
		} else if (cmp < 0) {// С�������
			tmp.left = put(tmp.left, key, value);
		} else {// ���ڣ�ֱ���޸�
			tmp.value = value;
		}
		tmp.count = size(tmp.left) + size(tmp.right) + 1; // ��ǰ�ڵ��count�������ӽڵ��cout��+1����Ϊ�ݹ��������������ϵ����ж������
		return tmp;
	}

	// �Թ��ӿ�
	public void put(Key key, Value value) {
		root = put(root, key, value); // �Ժ��������ִ�ͷ��������Ӧ��������������������Ƿ���һ��void ���Ƿ���һ���µĽڵ�
		// �����кܶ�����ƣ����ȣ����ǵ�һ��put��ʱ��Ϳ���ֱ�ӷ����µĽڵ㣬��rootָ�����������ǵ�put������ȥ�жϣ��������ܼ��ٺܶ�
		// ���ҵĴ��룬��ÿ��put�������صĶ���һ������
		// root����ʽ��ʼ�����������ʼ��
	}

	public Value get(Key key) {
		return get(root, key);
	}

	// ����ʵ��
	private Value get2(Node node, Key key) {
		// Node tmp = node;
		while (true) {
			if (node == null) {
				return null;
			}
			int cmp = key.compareTo(node.key);
			if (cmp > 0) {
				node = node.right;
				continue;
			} else if (cmp < 0) {
				node = node.left;
				continue;
			} else {
				return node.value;
			}
		}

	}

	private Node put2(Node node, Key key, Value value) {
		Node tmp = node;
		while (true) {
			if (tmp == null) {
				return new Node(key, value, null, null, 1);
			}
			int cmp = key.compareTo(tmp.key);
			if (cmp > 0) {
				if (tmp.right == null) {
					Node newNode = new Node(key, value, null, null, 1);
					tmp.right = newNode;
					break;
				}
				tmp = tmp.right;
				continue;
			} else if (cmp < 0) {
				if (tmp.left == null) {
					Node newNode = new Node(key, value, null, null, 1);
					tmp.left = newNode;
					break;
				}
				tmp = tmp.left;
				continue;
			} else {
				tmp.value = value;
				break;
			}
		}
		//��ô��ô����count�أ�������е��鷳�ˣ������ķ�ʽ�޷���������ڵ���Ϣ���ݹ�ʱ���Ժܷ�����ڵݹ����ʱ��ȥ�޸�count��ֵ
		//�������ķ�ʽȴ������ȥѰ������ڵ�ĸ��ڵ�
		while(true) {
			tmp.count = size(tmp.left)+size(tmp.right)+1;
			tmp = findParent(node,tmp);
			if(tmp == node) {
				break;
			}
		}
		return node;

	}
	//ȫͼ��������ɵ��
	private Node findParent(Node node, Node child) {
	    if (node == null || node == child) {
	        return null;
	    }
	    if (node.left == child || node.right == child) {
	        return node;
	    }
	    Node parent = findParent(node.left, child);
	    if (parent != null) {
	        return parent;
	    }
	    return findParent(node.right, child);
	}
	
	
	//������ʽ�Թ��ӿ�
	public void put2(Key key ,Value value) {
		root =put(root,key,value);
	}
	public Value get2(Key key) {
		return get2(root,key);
	}
	
	public Key min() {
		//��С��
		return min(root).key;
	}
	private Node min(Node node) {
		//�ݹ�ʵ��
		if(node.left==null)return node;
		return min(node.left);
	}
	
	//����
	public Key max() {
		return max(root).key;
	}
	private Node max(Node node) {
		if(node.right == null) {
			return node;
		}
		return max(node.right);                    //--�����ĵݹ�ṹ��ĺ�Ư�� ��������Լ�д�����أ�ֱ�Ӱѷ����ݹ���Ϊ����ֵ
	}
	
	
	public Key floor(Key key) {
		//���ش���key�ĵ�һ��key
		return floor(root,key).key;
	}
	//ͦ���ӵģ�������⣬����͸��
	private Node floor(Node node , Key key) {
		if(node == null) {
			return null;
		}
		int cmp = key.compareTo(node.key);
		if(cmp==0)return node;
		if(cmp<0) {
			//������ڵ����key���Ǿ������ߣ�ֱ����һ��С��key�ľ���floor
			return floor(node.left,key);
		}
		Node tmp = floor(node.right,key);
		if(tmp!=null)return tmp;
		else return node;
	}
	
	
	//����������н�Ϊ�ѵ����⣬���ɾ���ڵ�
	//ɾ����С�ڵ�
	public void deleteMin() {
		root = deleteMin(root); //���︳��root�����������С�ڵ��Ǹ��ڵ�ʱ�޷�ɾ�������
	}
	private Node deleteMin(Node node) {
		//����һ���ڵ㣬˼·�ܼ򵥣� һֱ����ݹ飬�ҵ�����ָ��Ϊ�յģ���ô��������С�Ľڵ㣬��ʱ��������ص����ˣ�
		// ����������������Ȼ��������������������ĸ��ڵ���������������Ͱ�������������������
		if(node.left==null) {
			return node.right;
		}
		node.left = deleteMin(node.left);
		node.count = size(node.left)+size(node.right)+1;//����count
		return node;                                    //���Լ����ظ����ڵ�
	}
	//ɾ�����ڵ�
	public void deleteMax() {
		root = deleteMax(root);
	}
	private Node deleteMax(Node node) {
		if(node.right == null) {
			return node.left;
		}
		node.right = deleteMax(node.right);
		node.count = size(node.left)+size(node.right)+1;
		return node;
	}
	
	//ɾ������ڵ��أ�
	public void delete(Key key) {
		root = delete(root,key);
	}
	private Node delete(Node node ,Key key) {
		//˼·�������ģ�ϣ�Ͷ����㷨
		//  ���ҵ�ɾ���ڵ��λ��
		//  ����ڵ�������Ϊnull���Ǿͷ������������������ڵ� -----�ݹ�
		//          ������Ϊnull��            ������          -----�ݹ�����ƣ�����ͨ������ֵ���ڲ�����㴫����Ϣ
		//   ����Ϊnull��ȥ��Ѱ���ĺ�̽ڵ㣨��һ���������Ľڵ㣩��Ȼ������ĺ�̽ڵ������滻��
		//1.��̽ڵ���ô�ң� ��̽ڵ���ɾ���ڵ�����������СԪ�صĽڵ㣬д������ min(�Ϳ����ҵ���
		//2.ɾ�� Ҫɾ���ڵ�����������С��Ԫ�� --���Ѻ�̽ڵ����������ɾȥ deleteMin
		//3.��̽ڵ��滻ɾ���ڵ�
		
		
		
		//ʵ�������У������������ɾ������ֻȡ��̽ڵ㣬�������������������Խ��Խ����Ϊ��һֱ��ȡ�������Ľڵ㣬�������Ӱ���Ŷ�������������ܣ����������Ҫ�Ż���
		//             ��ǰ���ڵ�ͺ�̽ڵ��ѡȡ�������
		if(node == null) {
			return null;
		}
		int cmp = key.compareTo(node.key);
		if(cmp<0) {
			node.left = delete(node.left,key);
		}else if(cmp>0) {
			node.right = delete(node.right,key);
		}else {
			//�ҵ��ڵ�
			if(node.left == null) {
				return node.right;
			}
			if(node.right == null) {                 
				return node.left;
			}                                                        // -------->>.����
			Node deleteWant = node;
			if(Math.random()<=0.5) {
			node =min(deleteWant.right); //�ҵ���̽ڵ�,��¼��Ϊ���Ǵ���ɾ��֮�󲻻ᱻ�������գ��㻹���ҵ���
			node.right  = deleteMin(deleteWant.right); //ɾȥ���,����ֱ�Ӹ�����̽ڵ�
			node.left = deleteWant.left;
			//ɾ������������count
			}else {
				node = max(deleteWant.left);
				node.left = deleteMax(deleteWant.left);
				node.right = deleteWant.right;
			}
			
		}
		node.count = size(node.left)+size(node.right)+1;
		return node;
	}
	

	public static void main(String[] args) {
		BinarySearchTree<Character, Integer> bsST = new BinarySearchTree<>();
		bsST.put('A', 1);
		bsST.put('B', 2);
		bsST.put('C', 3);
		bsST.put('A', 4);
		System.out.println(bsST.get('A'));
		System.out.println(bsST.size());         //�õ�����ʽ��ʵ���ˣ���������
		
		bsST.deleteMin();
		System.out.println(bsST.get('A'));
		System.out.println(bsST.size());            //�ɹ�
		
		bsST.delete('B');
		System.out.println("  "+bsST.get('B'));
		
		System.out.println(bsST.max());
		bsST.delete('A');
		System.out.println("  "+bsST.size());  //�ɹ�
	}
}
