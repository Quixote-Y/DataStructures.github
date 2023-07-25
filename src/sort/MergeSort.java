package sort;

import java.util.Arrays;

//归并排序
public class MergeSort {

	/*
	 * 归并排序： 原地归并的话 空间复杂度 n ，时间复杂度nlgn 分为自顶向下 自底向上 两种归并方式 路线不同
	 * 核心：如果能将两个子数组排序，那就能通过归并两个子数组来让整个数组有序，当子数组只有一个元素时，他肯定是有序的
	 */

	private static int[] aux;// 辅助数组直接作为全局变量，方便递归调用

	public static void main(String[] args) {
		// 测试排序的速度
		int[] arr = new int[100000000];
		for (int i = 0; i < 100000000; i++) {
			arr[i] = (int) (Math.random() * 80000000);
		}
		long start = System.currentTimeMillis();
		// 无优化措施，自顶向下i
		mergeSort(arr); // 80000条 0.012 1亿条 12.856
		// 自底向上 1亿条 12.091 相差无几

		//使用无额外数组的归并 80000条 0.45  80万条 47.501 已经是很慢的了 ，尝试1亿条很久都没结束，便没有测试，但效率堪忧，毕竟是用时间换空间
		
		//在小数组时使用 插入排序 8万条 0.009 1亿条 6.027 可以看出来效率翻倍 很是强大
		
		long end = System.currentTimeMillis();
		double time = (end - start) / 1000.0;
		System.out.println(time);

//		int arr[] = { 12, 11,21,-4, 13, 5, 6, 7 };
//
//		mergeSort(arr, 0, arr.length - 1);
//
//		for (int i = 0; i < arr.length; i++) {
//			System.out.print(arr[i] + " ");
//		}
	}

	public static void mergeSort(int[] a) {
		// 只调用一次所以在这初始化aux
		aux = new int[a.length];
		mergeSort(a, 0, a.length - 1);
		// mergeSortBU(a);
	}

	// 这是自顶向下的归并
	private static void mergeSort(int[] a, int lo, int hi) {
		if (hi-lo<=15) {
			insertSort(a,lo,hi);
			return;
		}
		int mid = lo + (hi - lo) / 2;
		mergeSort(a, lo, mid); // 对左半进行归并排序 ，一直递归到只有一个元素的情况
		mergeSort(a, mid + 1, hi); // 右半
		merge(a, lo, mid, hi); // 当递归到只有一个元素的时候开始 原地归并 一组一组的使他有序化
	}

	private static void insertSort(int[] a, int lo, int hi) {// 插入排序,从lo开始到hi
		int tmp = 0; // 被插入的元素
		int index = 0; // 扫描有序数组，找到插入位置
		for (int i = lo + 1; i < hi - lo; i++) {
			tmp = a[i];
			index = i - 1;
			while (index >= i - 1 && tmp < a[index]) {
				// 移动值
				a[index + 1] = a[index];
				index--;
			}
			// 当找到位置后
			if (index != i - 1) {
				a[index + 1] = tmp;
			}
		}

	}
	// 自底向上归并
	private static void mergeSortBU(int[] a) {
		// 进行lgN次两两归并
		int length = a.length;
		for (int sz = 1; sz < length; sz += sz) { // sz是子数组的大小 ，先从1开始，然后2，4，8这样
			for (int lo = 0; lo < length - sz; lo += sz + sz) { // lo是归并时子数组的最左边的索引
				// 开始归并
				merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, length - 1));
				// 最后一个子数组的大小只有在 数组长度是sz的偶数倍时 才会等于sz，否则他会比sz小，关于为什么-1很简单，因为是从lo开始计数
			}

		}
	}

	// 核心：原地归并算法 ：将所有元素赋值到一个辅助数组中，在把归并结果放回原数组

	/**
	 * 对数组a的 [lo,mid]和[mid+1,hi]进行归并
	 * 
	 * @param a   目标数组
	 * @param lo  归并数据段的最左边索引
	 * @param mid 中间，用于下一次归并的两端
	 * @param hi  最右端
	 */
	private static void merge(int[] a, int lo, int mid, int hi) {
		int i = lo, j = mid + 1;// 合并的时候分别指向两个子数组

		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];// 复制到aux里
		}

		// 很巧妙
		for (int k = lo; k <= hi; k++) {// 将aux数据归并回到a中，从aux左侧开始扫描，一次扫一个数
			if (i > mid)
				a[k] = aux[j++];// 左边数组扫完
			else if (j > hi)
				a[k] = aux[i++];// 右边扫完
			else if (aux[i] > aux[j])
				a[k] = aux[j++];// 左边的数比右边的数更大
			else
				a[k] = aux[i++];// 右边的数大于等于左边的数
		}

	}

	// 现在考虑一下如何优化这个算法
	// 1.额外空间 ：能否直接在原数组上归并 ：但需要注意的是，这种方法的时间复杂度并不优于标准的归并排序（使用额外空间），它的主要优点是节省了空间。
	// 2.小数组临界 ； 小数组切换为插入排序
	private static void merge2(int[] a, int lo, int mid, int hi) {
		int i = lo, j = mid + 1;// 合并的时候分别指向两个子数组

		if (a[mid] < a[mid + 1]) {// 左数组最大小于右数组最小，那直接就是有序的了
			return;
		}
		int value = 0;
		int index = 0; //
		while (i <= mid && j <= hi) {//这个条件错了，错了很久，左边到尽头或者右边到尽头就结束
			if (a[i] > a[j]) {
				// 左边的第i个位置大于右边的第j个位置
				// 那就把左边i和之后的所有值右移一格，然后把a[j]插入进来，就像插入排序一样 ，注意此时因为左边子数组整体右移了所以mid也要+1，右移
				value = a[j];
				index = j - 1;
//				while (index >= i && value < a[index]) {//其实都没有必要判断，因为他肯定是要移动到i位置的
//					a[index+1]=a[index];//右移
//					index--;
//				}
				while (index >= i) {
					a[index + 1] = a[index];
					index--;
				}
				a[index + 1] = value;
				mid++;
				i++; // 因为i的值被往后移了一位
				j++;
			} else {
				// 左边的小于右边的，那就不变，i++
				i++;
			} // 其他情况就不变就好了
		}

	}
	//自底向上的归并和自顶向下的归并的数组比较次数和访问次数相同，但自底向上的归并更适用与链表排序
	//只需组织链表链接就能实现原地归并，无需额外空间
}
