package priorityQueue;

//优先队列    --

/*
  优先队列是一种特殊的队列，它能够根据元素的优先级确定元素在队列中的顺序。每个元素都有一个与之相关的优先级，
  优先级高的元素先被处理。优先级队列通常使用堆这种数据结构来实现，因为堆能够在O(log n)的时间复杂度内对元素进行插入和删除操作。
  优先队列可以支持以下操作：
  插入元素：将元素按照优先级插入到队列中。
  删除元素：删除队列中优先级最高的元素。
  查找元素：查找队列中优先级最高的元素，但不删除它。
  修改元素：修改队列中的某个元素的优先级。
  优先队列常用于任务调度、事件驱动系统和最短路径算法等场景。
 */
//优先队列实现当时：数组（无序），数组（有序），链表，但实现效果最优的还是二叉堆的方式
/*
 * 堆有序：每个节点都大于他的两个子节点
 *       ：一个绝妙的设计：用数组来存储完全二叉树，在数组中按照层级来存储二叉树节点，从1号位置开始，0号不用，
 *         位置k的节点，他的父节点位于k/2，他的两个子节点位于2k，2k+1
 */

//泛型数组初始化的方式和Stack文件中类似，只不过时将Object类替换为了更具体的实现类Comparable类
/* 这里找到一个可以初始化泛型数组的方式： 
* (T[])Array.newInstance(Object.class, <size int>); 
* 其实就是 一个 Object[] 
* 因为 Java的泛型实现机制是 type erasure，编译时类型擦除，泛型编译之后都是 Object 和 强制类型转换。
* 需要注意的是，你的方法可以返回 T, List<T> 这都行，不要返回 T[]， 会出问题的，因为外部使用的时候， T 会有它实际的类型，
* 这时如果你的类型检查正确， 你内部是一个 Object[]数组里面存的元素都是 T 类型，所以单个拿出来 cast 都没问题，但是 Object[]
* 这个数组要 cast 成某种具体类型的数组，会抛出运行时异常。
*/
public class PriorityQueue<T extends Comparable<T>> {// 传一个泛型进来，后面创建他的对象

	// 数组二叉堆
	private T[] pq;
	private int length = 0;

	// 优先队列初始化
	public PriorityQueue(int maxLength) {
		// 泛型数组无法被初始化，但可以创建Comparable类然后强制类型转换为T泛型类
		pq = (T[]) new Comparable[maxLength + 1];// 0号位不用
	}

	// null判断
	public boolean isEmpty() {
		return length == 0;
	}

	// 有效长度
	public int size() {
		return length;
	}

	// 插入
	public void insert(T t) {
		pq[++length] = t;
		swim(length); // 新加入的这个位置的元素上浮
	}

	// 删除--->优先队列，删除对应的意思是 拿出最大的元素
	public T delete() {
		T max = pq[1];
		pq[1] = pq[length];// 将最后一个位置的元素拿到头上来，然后下沉重新堆有序
		pq[length--] = null; // 然后将最后一个位置的元素置空
		sink(1);
		return max;
	}

	// 堆有序过程会用到的方法
	// 上浮
	private void swim(int k) {
		while (k > 1 && less(k / 2, k)) {// 当父节点k/2小于子节点k时替换
			exch(k / 2, k);
			k = k / 2;
		}
	}

	// 下沉
	private void sink(int k) {
		// 判断有没有到达叶子节点
		while (2 * k <= length) {
			// 这个j用的，很巧妙
			int j = 2 * k;
			// 记录子节点中较大的一个
			if (j < length && less(j, j + 1))//必须判断在这j < headLenth，不然当只有奇数个数时每次下沉都会漏一个节点
				j++;
			if (!less(k, j))
				break; // 如果父节点不小于两个子节点，那就直接结束就完了，已经有序了
			exch(k, j);
			k = j;
		}
	}

	// 比较节点大小
	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0; // 当左侧节点值小于右侧节点值时返回true
	}

	// 替换位置
	private void exch(int i, int j) {
		T t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}

	// 测试一下
	public static void main(String[] args) {
		PriorityQueue<Integer> pQ = new PriorityQueue<>(800000);
		for (int i = 0; i < 800000; i++) {
			pQ.insert((int) (Math.random() * 80000));
		}
		// 依次取出元素，看是否有序
		for (int i = 0; i < 10; i++) {
			System.out.println(pQ.delete());
		}
		// successfully
	}
}
