package hashTable;

//基于拉链法的散列表
public class SeparateChainingHashST<Key extends Comparable ,Value> {

	//这是散列表--哈希表
	private int count;//总有效键值对数
	private int max ; //散列表的大小
	private ListST<Key,Value>[] st ;   //散列表数组
	//构造函数
	public SeparateChainingHashST() {
		this(997);      //无参构造函数默认创建一个997大小的散列数组，997素数，便于散列功能实现，
	}
	public SeparateChainingHashST(int max) {
		this.max = max;
		//初始话哈希表
		st = (ListST<Key, Value>[])new ListST[max];      //这里类对象数组不加（）参数
		//很细节，里面的数组也必须要初始化
		for(int i=0;i<max;i++) {
			st[i] = new ListST();
		}
	}
	
	
	//插入节点
	public void add(Key key ,Value value) {
		st[hash(key)].add(new NodeST(key,value));
	}
	//想加入节点，就必须要找到索引，索引通过hash经由hashCode获取  默认Hash Code和除留余数法结合使用
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff ) % max ; //什么意思呢：先是获取对应的hashCode值，默认是他的地址
		                                           //java中的很多常用类型重写了hashcode，这个地方不出意外的话到时候传入int
		                                            //返回值应该是对应的值，然后0x7fffffff这个是去除符号位 ，然后取余max，得到索引
	}
	//拿到节点
	public Value get(Key key) {
		int location =hash(key);
		if(st[location] == null) {
			return null;
		}
		return st[location].get(key);
	}
	
	//展示所有链的长度，检验均匀散列假设 ： 所有值均匀分布 ，这也是散列设计的核心
	public void showSize() {
		for(int i =0;i<max ;i++) {
			System.out.printf("第%d条链长度：%d \t",i,st[i].size());
			if(i%5==0) {
				System.out.println();
			}
		}
	}
	
	
	
	
	//在这写一个验证函数
	public static void main(String[] args) {
		SeparateChainingHashST<Integer, Integer> scST  = new SeparateChainingHashST<>();
		long start =System.currentTimeMillis();
		for (int i = 0; i < 80000; i++) {
			scST.add((int) (Math.random() * 80000000), i);
		}
		long end = System.currentTimeMillis();
		//当添加的数据在8以内，那么所有的数据都会被添加到前8条链，后面的链都不会添加，所以我们的链表的大小应该是靠近数据最大值的素数，但当数小了之后，相应的链表数也就小了
		//导致每一条链很长，效率较低，当数据比较大时速度就很快，而且散列分布很好
		System.out.println("添加结束 耗时:"+(end-start)/1000);
		scST.showSize();
	}
}

class ListST<Key extends Comparable, Value> {
	private NodeST head;

	//大小
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
	//获取头节点--第一个数据节点
	public NodeST getHead() {
		return head;
	}
	

	// 直接添加到链尾
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

	// 拿到
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
// 散列表里的链的节点
class NodeST<Key extends Comparable, Value> {
	Key key;
	Value value;
	NodeST next;

	public NodeST(Key key, Value value) {
		super();
		this.key = key;
		this.value = value;
	}
	//试试重写hashCode

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((key == null) ? 0 : key.hashCode());
//		result = prime * result + ((value == null) ? 0 : value.hashCode());           
//		return result;                            
//	} 
	//-->怎么理解上面这段代码呢： 组合键的HashCode，相当于字符串的hashCode，我们把键的完整组合当作一个prime进制的数字
	//   这个数字等于 ： Value * prime *prime + key *prime

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
