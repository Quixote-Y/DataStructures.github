package sort;

//插入排序
/*
 * 插入排序的核心就是，将一个数组分为两个部分，一个是有序的，一个是无序的
 *   ：起初，有序数组中只有一个元素，其余所有元素全是无序数组中，然后从数组的第二个位置开始遍历
 *    每次先记录他的值，然后进入前面的有序数组进行比较，因为前面的有序数组是按你的规则进行排列的，有序
 *    数组最右边的元素要么是最大的，要么是最小的，假设是最大的，然后用记录的值和他比较，比他大的话，那这个
 *    无序元素就该放置在原点，就不用改变，如果比他小，那就说明记录元素位置应该左移，就把有序元素直接赋给我们操作的
 *    元素，然后继续下一轮判断，，，起初的犹豫是在于赋值之后原值没了，但我们以来就记录了值，所以不会出错
 */

/*
 * 插入排序在序列基本有序时效率较高，这是因为插入排序的核心思想是将待排序序列分成已排序区间和未排序区间，每次从未排序区间中取出一个元素，插入到已排序区间的合适位置。
   当待排序序列基本有序时，已排序区间中的元素较多，未排序区间中的元素较少，因此每次插入操作需要比较的元素较少，插入的位置也相对容易确定，从而减少了比较和移动的次数，
   提高了排序的效率。
 */
public class InsertSort {

	public static void main(String[] args) {
		// 测试排序的速度
		int[] arr = new int[80000];
		for (int i = 0; i < 80000; i++) {
			arr[i] = (int) (Math.random() * 80000000);
		}
		long start = System.currentTimeMillis();
		insertSort(arr); // 1.541秒
		long end = System.currentTimeMillis();
		double time = (end - start) / 1000.0;
		System.out.println(time);
	}

	public static void insertSort(int[] arr) {
		int tmp = 0;
		int index = 0;
		for (int i = 1; i < arr.length; i++) {
			// 这是无序数组，从左往右，每次取出最左边的元素，插入到左侧的有序数组中
			tmp = arr[i]; // 记录当前操作的无序值
			index = i - 1; // 指向有序数组中的正在被比较的数，当这个数小于tmp时，说明tmp应该插在他的后面
			while (index >= 0 && tmp < arr[index]) {
				arr[index + 1] = arr[index];
				index--;
			}
			arr[index + 1] = tmp;
		}
	}

}
