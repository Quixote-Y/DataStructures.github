package symbolTable;

//红黑树优化二叉查找树
/*
 * 二叉查找树：插入元素自顶向下，当传入元素保持一定顺序，像12345678这样的，产生的树就会高度很高，这就导致了操作效率低下，平均情况下是1.39lgN，但最坏情况下比有序数组还要慢，这是我们不想看到的
 * 2-3树： 在二叉树的基础上，添加了 3-节点，可以包含两个键值，3条连接，分别是小于两个键，在两个键中间，大于两个键，他最巧妙的地方在于，插入操作，
 *         从下往上插入，插入元素永远在最底层插入，2-节点插入变3-节点，3-节点插入，临时4-节点，分解，中间键上移，进入父节点，构建3-或4-节点，重复操作，直到消除4-节点，当持续到根节点，那就树的高度+1，分解为两个2-节点
 * 红黑树：用二叉树的架构维护一个 2-3树 ，仅仅添加一个颜色属性 ，当颜色为 Red 时，可放平，表示相连的两个结点处于同一个逻辑节点中，black为普通连接，其余操作逻辑就完全符合2-3树
 */
/*
 * 如何维护这样一个二叉树架构下的红黑树？
 *  红黑树能等价于2-3树的充要条件：
 *  1.红链接均为左连接              
 *  2.没有任何一个节点连接两个红链接-->保证最多只有3-节点
 *  3.树是完美黑色平衡的
 *  
 *  维护方式：
 *    1.旋转：左旋右旋，调整树的结构，改变红链接的位置
 *    2.颜色转换：一个节点左右子节点均为红，子节点去红，转移到自己，红链接上移
 *    -->这是一个逻辑闭环：
 *           1.一个节点右链接红莲，那通过左旋转可以转变为左红链
 *           2.一个节点左链是红莲，左孩子的左链还是红链，那父节点红链右旋，可以成为符合颜色转换条件的情况
 *           3.左右孩子均是红链，红链转黑链，红链上移
 *           4.不会出现别的情况：如果是左链红链，左孩子右链红链，那么在1的时候就会将右红链转换为左红链进入情况2
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
		Boolean color; // 颜色，指向这个节点的连接的颜色

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

	// 调整树的count
	private int size(Node node) {
		if (node == null)
			return 0;
		else {
			return node.count;
		}
	}

	// 添加一个方法返回结点的颜色
	private boolean isRed(Node node) {
		if (node == null)
			return false; // 这个也很细节，当node为null的时候就不能在调用node.color了，会抛出异常
		return node.color == RED;
	}

	// 红链左旋
	private Node rotateLeft(Node h) {
		Node tmp = h.right;        //可以自行画图看看结构就很明白了 算法书P277
		h.right = tmp.left;
		tmp.left = h;             //左旋之后tmp指向原来的根节点h
		tmp.color = h.color;
		h.color = RED;
		// 调整count
		tmp.count = h.count;
		h.count = size(h.left) + size(h.right) + 1;    //因为左右节点的子树都没有发生变化，仅仅在于红链两端的变化
		return tmp;
	}
	
	//红链右旋
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
	
	//当左右都是红链时启动的颜色转换
	private void flipColors(Node node) {
		node.color =RED;        //先要把自己（父节点）变为RED，红链上移
		node.left.color = BLACK;
		node.right.color = BLACK;
	}
	
	
	//开始插入算法
	public void put(Key key,Value value) {
		root = put(root,key,value);
		//根节点默认为BLACK
		root.color = BLACK;
	}
	private Node put(Node node ,Key key,Value value) {
		if(node == null) {
			return new Node(key,value,null,null,1,RED);//默认以红链添加
		}
		int cmp = key.compareTo(node.key);
		if(cmp<0) {
			node.left = put(node.left,key,value);
		}else if(cmp>0) {
			node.right = put(node.right,key,value);
		}else {
			node.value = value;
		}
		
		//开始最关键的三步
		if(isRed(node.right)&&!isRed(node.left)) {               //右边是红而左边不是红 -------------------》左旋
			//这里也说一下吧：首先，如果完全让我自己来写我看你不会想到写个isRed方法，而去直接判断，
			// 说一下这样写的好处： 关键就是判断了当right 和 left为null的情况，这样写也更加的清晰，可读性更高，应该学习
			node =  rotateLeft(node);
			//这里不能返回，因为和后面的判断是一体的，递进的关系
		}
		if(isRed(node.left)&&isRed(node.left.left)) {     //自己和孩子的左链都是红
			node = rotateRight(node);                      //自己的左红链右旋，构造颜色转换的条件
		}
		if(isRed(node.left)&&isRed(node.right)) {          //自己的两个孩子链都是red
			flipColors(node);                              //颜色转换，红链上移
		}
		
		//将自己这课树递归给上层
		return node;
	}
	
	

}
