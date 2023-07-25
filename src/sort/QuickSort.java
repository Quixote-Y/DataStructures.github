package sort;

import java.util.Arrays;

/*快速排序：最常用的排序，原地排序，时间复杂度 Nlg(N)
 *  分治排序算法，切分，
 *  快速排序将数组分成两个部分，当两个子数组都有序了，那整个数组也就有序了
 * 
 */
public class QuickSort {

	public static void main(String[] args) {
		// 测试排序的速度
		int[] arr = new int[80000];
		for (int i = 0; i < 80000; i++) {
			arr[i] = (int) (Math.random() * 80000000);
		}
//		int[] arr = {1,5,3,7,-1};
		long start = System.currentTimeMillis();
		
//		quickSort(arr); // 80000条 0.01      1亿条 9.023

//		//三向切分
//		quick3WaySort(arr);      //80000条 0.026 800000条 0.099   1亿条数据时 ，耗时也才 12秒  优势得在有重复数据的时候

//		// 在尝试一下 对快速排序进行短小数组切换插入排序
		quickSort(arr);   // 80000  0.01     1亿条 7.53 优化还是很大的
//		//System.out.println(Arrays.toString(arr));

		long end = System.currentTimeMillis();
		double time = (end - start) / 1000.0;
		System.out.println(time);
		
		//找第10000小的元素
		int a = kFind(arr,3);
		System.out.println(a);
		//还是会有堆栈溢出
//		//怎么找第k大的呢
//		int b = arr.length-a;
//		int c =kFind(arr,b);
//		System.out.println(c);
	}

	// 对外
	public static void quickSort(int[] a) {
		// 先要打乱传入数组的顺序，消除对输入的依赖
		// shuffle
		quickSort(a, 0, a.length - 1);
	}

	public static void quick3WaySort(int[] a) {
		quick3WaySort(a, 0, a.length - 1);
	}

	//通过切分 找第k小的元素
	public static int kFind(int[] a,int k) {
		return kFind(a,0,a.length-1,k);
		
	}
	private static int kFind(int[] a, int lo, int hi, int k) {
		int res = 0;
		int j =partition(a,lo,hi);
		if(j==k) {//切分元素放置的位子就是这个元素最终要放到的位置，左边都小于他，他的坐标就是他在数组中的大小位置
			return a[lo];
		}else if(j<k) {
			//位于切分的右边
			res = kFind(a,j+1,hi,k);
		}else {//j>k，
			res = kFind(a,lo,j-1,k);
		}
		return res;
	}

	// 快速排序最重要的 切分
	/**
	 * 
	 * @param a  切分数组
	 * @param lo 数组被切分最低位，这个也就是切分元素
	 * @param hi 数组最高位
	 * @return 返回切分完成后切分元素的位置
	 */
	private static int partition(int[] a, int lo, int hi) {
		int partitionElement = a[lo];
		int tmp = 0;
		int i = lo, j = hi + 1; // 两个切分时的指针，这里j得是hi+1，因为后面a[--j]才能实现最后一次判断操作之后，j指向的就是判断生效的位置，j--的话就指向下一个位置了
		while (true) {
			// 扫描左边 , 当出现一个值比切分元素大时停止 ， 扫到队尾时停止
			while (a[++i] < partitionElement) {
				if (i == hi)
					break;
			}
			// 扫描右边，当出现一个值比切分元素小时停止，扫到队头时停止
			while (a[--j] > partitionElement) {
				if (j == lo)
					break;
			}
			// 先判断i 和 j 的相对位置 ，当左右指针交替的时候扫描结束
			if (i >= j)
				break;
			// 没结束，那就还在扫描中，交换两个位置
			tmp = a[i];
			a[i] = a[j];
			a[j] = tmp;
		}
		// 扫描结束，这个时候 j 的位置就是 切分元素该放的位置 ， 因为最后一轮j走过了i，这时候j指向的是最后一个小于切分元素的位置
		tmp = a[lo];
		a[lo] = a[j];
		a[j] = tmp;
		return j;// 返回切分元素位置
	}

	// 快排
	private static void quickSort(int[] a, int lo, int hi) {
		if (hi - lo <= 15) {
			// 当数组长度小于15时启动插入排序
			insertSort(a, lo, hi);
			return;
		}
//		//无插入优化
//		if (hi <= lo) {
//			return;
//		}
		int j = partition(a, lo, hi);
		// 切分后左侧还有没切分元素
		quickSort(a, lo, j - 1); // 记得把位置j，上一轮切分放置好的元素去除切分行列
		// 右侧还没切完,
		quickSort(a, j + 1, hi); // 记得把位置j，上一轮切分放置好的元素去除切分行列
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

	// 这里遇到了一个快速排序容易出现的错误： 堆栈溢出--------》emmmmm,出错并不是因为数据量太大了，而是因为我在切分时，最后改变切分元素位置的时候用了形参，没有用数组，导致没有改变，发生异常出现的
	// 因为上面的代码并未有任何的优化，当我们传入80000个数字后，数量极大的递归调用很快就把栈堆满了，发出了栈溢出错误
	// 下面进行改良
	// 快速排序堆栈溢出是一个常见的问题，可以通过优化快速排序算法、手动模拟递归过程或者增加栈空间等方法来解决。需要根据具体情况选择合适的方法，以达到最优的排序效果。

	// 快速排序算法改良：
	// 1.：因为对于小数组，快速排序算法的效率低于插入排序，因此我们在数组小时改用插入排序来为数组排序
	// 2.：三向切分（熵最优的排序），若出现大量的重复元素，上面的方法依旧会对重复元素进行切分，但我们没有必要去对他们进行操作，三向切分提出了很好的思路
	// 也就是Dijkstra由荷兰国旗问题引出的解法：三向切分
	/*
	 * 三向切分从左往右遍历数组一次，维护一个指针lt，使得a[lo...lt-1]中的元素都小于切分元素，维护一个gt，使a[gt+1.....hi]
	 * 中所有元素都大于切分元素， 一个指针i 使a[lt...i-1]中的元素都等于切分元素 a[i.....gt]中是还未进行比较的元素 一开始 i
	 * 等于lo+1 ，lt 等于 lo ， gt等于 hi ---》三向切分中切分元素也要参与移位 算法流程：比较a[i]和切分元素v 1).a[i]>v,则
	 * a[gt]和a[i]交换 ，gt-- 2).a[i]<v, a[lt]和a[i]交换， lt++ 3).a[i]=v, 将i++
	 * 这些操做都会保证数组元素不变且缩小gt-i的值，这样才能结束
	 */

	private static void quick3WaySort(int[] a, int lo, int hi) {
		if (hi <= lo) {
			return;
		}
		int lt = lo, i = lo + 1, gt = hi;// 三向切分的指针
		int partition = a[lo]; // 切分元素
		// 这是一次切分
		while (i <= gt) { // gt指向的是最后一个没有被切分比较的元素
			if (a[i] > partition) { // 大于切分元素，放到gt后面
				exch(a, i, gt);
				gt--;
			} else if (a[i] < partition) {// 放到lt的前面
				exch(a, i, lt);
				i++;
				lt++;
			} else {// 相等，那就i后移
				i++;
			}
		}
		quick3WaySort(a, lo, lt - 1); // 对切分元素左边的元素在进行切分
		quick3WaySort(a, gt + 1, hi); // 右边的 lt---gt间的是相同的

	}

	// 写一个方法，可以交换两个参数的值
	private static void exch(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
	
	
}
