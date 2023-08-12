package symbolTable;

//基于链表的 二叉查找树 符号表
/*
 * 二叉查找树和二叉堆不同：
 *            二叉堆，数组实现，完全二叉树，层级结构，堆有序，下沉排序等
 *            二叉查找树：链表实现，构造的是一个中序遍历（BST）节点key有序的树，符号表主要操作：查找插入，删除等
 */
//二叉查找树的相关API
public class BinarySearchTree<Key extends Comparable, Value> {

	Node root;

	// 以一个私有内部类作为链表的节点
	private class Node {
		Key key;
		Value value;
		Node left; // 左子树
		Node right; // 右子树
		int count; // 以该节点为根节点的子树的节点数

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

	// 递归实现
	private Value get(Node node, Key key) {
		Node tmp = node;
		if (tmp == null) {
			// 如果找到最后节点指向为null说明没有这个节点
			return null;
		}
		int cmp = key.compareTo(tmp.key);
		if (cmp > 0) {// 找的key大于节点的key，往右边找
			return get(tmp.right, key);
		} else if (cmp < 0) {// 小于往左边
			return get(tmp.left, key);
		} else {// 等于，直接返回
			return tmp.value;
		}
	}

	// 起初返回值是void，这样的话就导致，我必须得在递归之前判断这个递归能否进去，tmp.left是否为null，right也是，为null如何处理，
	// 然后如果传入的是 root节点，此时root没有指向任何节点又如何处理，代码杂乱
	// 就因为添加了一个返回值，代码看着就优化简洁了很多，这段代码处理方式，好好想
	private Node put(Node node, Key key, Value value) {
		Node tmp = node;
		if (tmp == null) {
			// 子树为null，将新建的节点返回
			return new Node(key, value, null, null, 1);
		}
		int cmp = key.compareTo(tmp.key);
		if (cmp > 0) {// 找的key大于节点的key，往右边找 ，直接在这将返回值赋给right
			tmp.right = put(tmp.right, key, value);
		} else if (cmp < 0) {// 小于往左边
			tmp.left = put(tmp.left, key, value);
		} else {// 等于，直接修改
			tmp.value = value;
		}
		tmp.count = size(tmp.left) + size(tmp.right) + 1; // 当前节点的count是两个子节点的cout和+1，因为递归操作，这棵子树上的所有都会更新
		return tmp;
	}

	// 对公接口
	public void put(Key key, Value value) {
		root = put(root, key, value); // 以后遇到这种带头结点的链表都应该这样做，插入操作不是返回一个void 而是返回一个新的节点
		// 这样有很多的优势，首先，我们第一次put的时候就可以直接返回新的节点，让root指向它，而不是到put方法里去判断，这样就能减少很多
		// 杂乱的代码，你每次put操作返回的都是一个子树
		// root不显式初始化，在这里初始化
	}

	public Value get(Key key) {
		return get(root, key);
	}

	// 迭代实现
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
		//那么怎么更新count呢，这就显有点麻烦了，迭代的方式无法保留多个节点信息，递归时可以很方便的在递归回来时在去修改count的值
		//而迭代的方式却得重新去寻找这个节点的父节点
		while(true) {
			tmp.count = size(tmp.left)+size(tmp.right)+1;
			tmp = findParent(node,tmp);
			if(tmp == node) {
				break;
			}
		}
		return node;

	}
	//全图搜索，很傻逼
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
	
	
	//迭代方式对共接口
	public void put2(Key key ,Value value) {
		root =put(root,key,value);
	}
	public Value get2(Key key) {
		return get2(root,key);
	}
	
	public Key min() {
		//最小键
		return min(root).key;
	}
	private Node min(Node node) {
		//递归实现
		if(node.left==null)return node;
		return min(node.left);
	}
	
	//最大键
	public Key max() {
		return max(root).key;
	}
	private Node max(Node node) {
		if(node.right == null) {
			return node;
		}
		return max(node.right);                    //--这样的递归结构真的很漂亮 ，，如何自己写出来呢？直接把方法递归作为返回值
	}
	
	
	public Key floor(Key key) {
		//返回大于key的第一个key
		return floor(root,key).key;
	}
	//挺复杂的，大致理解，但不透彻
	private Node floor(Node node , Key key) {
		if(node == null) {
			return null;
		}
		int cmp = key.compareTo(node.key);
		if(cmp==0)return node;
		if(cmp<0) {
			//如果根节点大于key，那就往左走，直到第一个小于key的就是floor
			return floor(node.left,key);
		}
		Node tmp = floor(node.right,key);
		if(tmp!=null)return tmp;
		else return node;
	}
	
	
	//二叉查找树中较为难的问题，如何删除节点
	//删除最小节点
	public void deleteMin() {
		root = deleteMin(root); //这里赋给root，避免出现最小节点是根节点时无法删除的情况
	}
	private Node deleteMin(Node node) {
		//返回一个节点，思路很简单： 一直往左递归，找到左孩子指针为空的，那么他就是最小的节点，这时候最巧妙地点来了，
		// 返回他的右子树，然后把他的右子树赋给他的父节点的左子树，这样就把他从整个树中脱离了
		if(node.left==null) {
			return node.right;
		}
		node.left = deleteMin(node.left);
		node.count = size(node.left)+size(node.right)+1;//更新count
		return node;                                    //将自己返回给父节点
	}
	//删除最大节点
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
	
	//删除任意节点呢：
	public void delete(Key key) {
		root = delete(root,key);
	}
	private Node delete(Node node ,Key key) {
		//思路是这样的：希巴尔德算法
		//  先找到删除节点的位置
		//  如果节点左子树为null，那就返回他的右子树给父节点 -----递归
		//          右子树为null，            左子树          -----递归的优势，可以通过返回值由内层向外层传递信息
		//   都不为null，去找寻他的后继节点（下一个大于他的节点），然后把他的后继节点用来替换他
		//1.后继节点怎么找： 后继节点是删除节点右子树中最小元素的节点，写个方法 min(就可以找到他
		//2.删除 要删除节点右子树中最小的元素 --》把后继节点从右子树中删去 deleteMin
		//3.后继节点替换删除节点
		
		
		
		//实际运用中，二叉查找树的删除不能只取后继节点，这样会让整棵树的深度越来越大，因为你一直在取右子树的节点，树的深度影响着二叉查找树的性能，因此我们需要优化他
		//             让前驱节点和后继节点的选取随机发生
		if(node == null) {
			return null;
		}
		int cmp = key.compareTo(node.key);
		if(cmp<0) {
			node.left = delete(node.left,key);
		}else if(cmp>0) {
			node.right = delete(node.right,key);
		}else {
			//找到节点
			if(node.left == null) {
				return node.right;
			}
			if(node.right == null) {                 
				return node.left;
			}                                                        // -------->>.绝妙
			Node deleteWant = node;
			if(Math.random()<=0.5) {
			node =min(deleteWant.right); //找到后继节点,记录，为的是待会删除之后不会被垃圾回收，你还能找到他
			node.right  = deleteMin(deleteWant.right); //删去后继,并且直接赋给后继节点
			node.left = deleteWant.left;
			//删除结束，更新count
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
		System.out.println(bsST.size());         //用迭代方式是实现了，但好拉鸡
		
		bsST.deleteMin();
		System.out.println(bsST.get('A'));
		System.out.println(bsST.size());            //成功
		
		bsST.delete('B');
		System.out.println("  "+bsST.get('B'));
		
		System.out.println(bsST.max());
		bsST.delete('A');
		System.out.println("  "+bsST.size());  //成功
	}
}
