package sort;

/*希尔排序 时间复杂度在线性对数级别
 * 
 * 希尔排序是一种基于插入排序的排序算法，其核心思想是将待排序序列分成若干个子序列，
 * 对每个子序列进行插入排序，最终使整个序列达到有序。由于插入排序在序列基本有序时
 * 效率较高，希尔排序通过缩小子序列的间隔来利用序列的局部有序性，从而加快排序的速度。
 */
public class ShellSort {

	public static void main(String[] args) {
		// 测试排序的速度
		int[] arr = new int[80000];
		for (int i = 0; i < 80000; i++) {
			arr[i] = (int) (Math.random() * 80000000);
		}
		long start = System.currentTimeMillis();
		//可以看到shell排序的效能在中等数据集中是十分优秀的
		shellSort(arr);         // 0.014秒
		long end = System.currentTimeMillis();
		double time = (end - start) / 1000.0;
		System.out.println(time);
	}

	/*
	 * 也体现了移位操作的优越，记录要插入数据，没有找到确切位置之前都只是将数据后移一位，找到后才插入 ，相较冒泡的每次都交换，一次比较就要进行3次交换
	 */
	// 基于移位插入 的希尔排序
	public static void shellSort(int[] arr) {
		int tmp = 0;
		int index = 0;
		for (int gap = arr.length / 2; gap >= 1; gap /= 2) {
			// gap是每一次排序 ，同一组元素间相差的步长，像10个元素，第一次步长为5，分为5组，每组两个
			// 元素，组内元素进行插入排序，局部序列有序化，然后下一轮又变为步长为2，2组，每组5个，插入排序，
			// 利用的就是当被排序列已经大部分有序时，插入排序进行比较次数少的优点来优化算法--》有序的话每次只要和第一个元素比较就
			// 能确定位置，完美情况下时间复杂度n只需要n-1次比较，而且不需要任何移动操作，但无序情况下最坏时间复杂度n*2
			for (int i = 0; i < gap;i++) {
				// 每一组，每个相差gap位
				for (int j = i; j < arr.length; j += gap) {
					tmp = arr[j];
					index = j - gap;
					while (index >= 0 && tmp < arr[index]) {
						//这里采用插入排序的移位操作是最优的方法，移位操作带来的交换更少，如果每次都像冒泡一样的交换，那速率就会慢上很多
						// 没有遍历到头，并且tmp小于index指向的元素，就移位
						arr[index + gap] = arr[index];
						// 有序部分也是gap位间隔位一组
						index -= gap;
					}
					// 找到位置
					if (index + gap != j) {
						arr[index + gap] = tmp;
					}
				}
			}

		}
	}
}
