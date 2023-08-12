package priorityQueue;

import java.util.Arrays;

@SuppressWarnings("all")
public class HeadSort<T extends Comparable<T>> {

	// 基于优先队列二叉堆 的 排序算法
	// 堆排序

	/*
	 * 时间复杂度稳定：堆排序的时间复杂度为O(nlogn)，与输入数据的初始状态无关，因此它具有稳定的时间复杂度。
	 * 
	 * 空间复杂度低：堆排序只需要一个数组作为辅助空间，不需要额外的内存空间，因此它的空间复杂度是O(1)级别的。
	 * 
	 * 不受数据分布影响：对于任何输入数据，堆排序的时间复杂度都是稳定的，不受数据分布的影响。
	 * 
	 * 不占用额外内存: 堆排序是一种原地排序算法，不需要占用额外的内存空间，因此它适用于内存有限的情况。
	 * 
	 * 可以方便地实现优先级队列：堆排序是优先级队列的基础，可以方便地实现优先级队列的相关功能。
	 */
	// 不过要重写一下二叉堆的下沉算法，使他争对指定数组
	/**
	 * 指定数组a，从索引k开始下沉
	 * 
	 * @param a         被下沉的数组
	 * @param k         下沉元素
	 * @param headLenth 二叉堆长度，因为后期下沉排序要将最大的元素放到最后，然后把他从二叉堆里去掉
	 */

	// 重写堆有序的一些方法
	public void sink(Comparable[] a, int k, int headLenth) {
		while (2 * k <= headLenth) {
			int j = 2 * k;
			if (j < headLenth && less(a, j, j + 1))//必须判断在这j < headLenth，不然当只有奇数个数时每次下沉都会漏一个节点
				j++;
			if (!(less(a, k, j)))
				break;// 如果k不小于子节点
			exch(a, k, j);
			k = j;
		}
	}

	private boolean less(Comparable[] a, int i, int j) {
		return a[i].compareTo(a[j]) < 0;
	}

	private void exch(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public void headSort(Comparable[] a) {
		// 先把数组堆有序
		// 采用下沉枝节点的方式，减少对节点的访问比较次数，可以避免访问叶节点
		int length = a.length - 1;
		for (int i = length / 2; i > 0; i--) {
			// length/2 为最后一个枝节点的位置
			sink(a, i, length);
		} // 堆有序
			// 下沉排序
		int headLength = length;
		while (headLength > 1) { // 一直进行交换，下沉操作，二叉堆中没有节点

			exch(a, 1, headLength--);// 交换二叉堆的1号元素和最后一个叶子节点元素，最大放最后
			sink(a, 1, headLength);
		}
	}

	// 测试一下
	public static void main(String[] args) {
		HeadSort<Integer> hS = new HeadSort<>();
		//
		// 依旧不用0号
		Integer[] arr = new Integer[10+1];
		for (int i = 1; i < 10+1; i++) {
			arr[i] = (int) (Math.random() * 80000000);
		}
		long start = System.currentTimeMillis();
		hS.headSort(arr);    //80000 条 0.004  1亿条 210.981 感觉时间花费在装箱和和拆箱上了
		long end = System.currentTimeMillis();
		double time = (end - start) / 1000.0;
		System.out.println(time);
		System.out.println(Arrays.toString(arr));
	}
}
