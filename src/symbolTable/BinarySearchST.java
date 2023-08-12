package symbolTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/*
 * 二分查找：查找是对数级别lgN，但插入是线性级别N，最坏的情况是2N
 */

//基于有序数组的二分查找符号表
public class BinarySearchST<Key extends Comparable<Key>, Value> {

	// 两个数组：一个存储键，一个存储值，两个数组相平行，利用数组索引来高效的实现增加和删除
	// 这两个数组开始是空的，你每次往符号表里添加数据都会经由put方法，这也就构建了一个有序的数组
	// 实现符号表的关键在于Rank（Key）方法，他返回表中小于给定键的建的数量，也就是这个键应该存在的位置，使用的是二分查找的方式
	/*
	 * 二分查找： 根据数组的索引大大的缩减每次查找需要的比较次数，但这个数组必须保证有序
	 * 我们会和数组中间的值进行比较，大于往左，小于往右，直到定位到这个数值应该在的地方 就和快速排序如何找到第k小的元素一样
	 */
	private Key[] keys;
	private Value[] vals;
	private int length = 0;//在有序数组中是从0号未开始，别混了

	public BinarySearchST(int capacity) {
		keys = (Key[]) new Comparable[capacity];
		vals = (Value[]) new Object[capacity];
	}

	public int size() {
		return length;
	}

	public boolean isEmpty() {
		return length == 0;
	}

	// 因为下面要用到，所以这里先引入关键的方法 rank
	// 二分查找，返回键值key在数组keys中应该的位置\
	//二分查找mid元素的位置是数组0.5长度的位置，根据数据的分布情况不同，可以修改mid算法，以更高效的获取到目标位置
	//插值查找 ： mid = lo + (hi - lo)*(value-vals[lo])/(vals[hi]-vals[lo])   ，当数组value分布均匀时更高效
	public int rank(Key key, int lo, int hi) {
		// 递归的方式
		if (hi <= lo) {
			return lo;
		}
		int mid = lo + (hi - lo) / 2;
		int cmp = key.compareTo(keys[mid]); // Key没有具体的类，但是Comparable的实现类，所以可以调用comparable方法，但不能直接使用运算符
		if (cmp < 0) {
			return rank(key, lo, mid - 1);
		} else if (cmp > 0) {
			return rank(key, mid + 1, hi);
		} else {
			return mid;
		}
	}

	public int rank2(Key key) {
		// 迭代的方式
		int lo = 0, hi = length-1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if (cmp < 0) {
				hi = mid - 1;
				
			} else if (cmp > 0) {
				lo = mid + 1;
			} else {
				return mid;
			}
		}
		return lo;
	}

	public Value get(Key key) {
		if (isEmpty())
			return null;
		int i = rank2(key);
		if (i < length && keys[i].compareTo(key) == 0) {
			return vals[i];
		} else {
			return null;
		}
	}

	public void put(Key key, Value value) {
		int i = rank2(key);
		//有就改值
		if (i < length && keys[i].compareTo(key) == 0) {
			vals[i]=value;
			return;
		}
		//没有就加入，把所有这个位置后面的数据都往后移
		for(int j =length;j>i;j--) {
			keys[j] = keys[j-1];
			vals[j] = vals[j-1];
		}
		keys[i] = key;
		vals[i] = value;
		length++;
	}
	
	
	public Key[] getKeys() {
		return keys;
	}

	public Value[] getVals() {
		return vals;
	}

	public static void main(String[] args) {
		BinarySearchST<Character, Integer> binarySearchST = new BinarySearchST<>(10);
		binarySearchST.put('A', 1);
		binarySearchST.put('B', 2);
		binarySearchST.put('C', 3);
		binarySearchST.put('D', 4);
		binarySearchST.put('E', 5);
		binarySearchST.put('F', 6);
		
		Comparable[] key = binarySearchST.getKeys();
		System.out.println(Arrays.toString(key));
		
	}
}
