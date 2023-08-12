package sort;

import java.util.Arrays;

//基数排序  基于桶排序
//时间复杂度 nxk k:桶的数量
public class RadixSort {

	public static void main(String[] args) {

		// 测试排序的速度
		int[] arr = new int[80000];
		for (int i = 0; i < 80000; i++) {
			arr[i] = (int) (Math.random() * 80000000);
		}
		long start = System.currentTimeMillis();
		radixSort(arr); // 0.051秒
		//基数排序由于创建了额外的10个桶，每个桶的大小都是你数组的长度，即使是int类型
		// 1000000000 1亿个数据
		// 1000000000*4*8 /1024/1024/1024 =29G 对内存花费很大 
		long end = System.currentTimeMillis();
		double time = (end - start) / 1000.0;
		System.out.println(time);
	}

	public static void radixSort(int[] arr) {

		// 10个桶，用于存放每个数字对应的结果
		int length = arr.length;
		int[][] bucket = new int[10][length];
		// 一个数组，用于存每个桶里对应的数据数
		int[] bucketElementCount = new int[10];
		int max = arr[0];
		for (int i = 0; i < length; i++) {
			if (max < arr[i]) {
				max = arr[i];
			}
		}
		// 巧妙，转换为字符串，输出他的长度
		int maxLength = (max + "").length();

		int rand = 0;// 第几轮取数
		int tmp = 0;
		while (rand < maxLength) {
			rand++;
			// 下面是每一轮的存放数据
			for (int i = 0; i < length; i++) {
				tmp = (int) (arr[i] / Math.pow(10, rand - 1) % 10);
				bucket[tmp][bucketElementCount[tmp]++] = arr[i];
			}
			// 存进去之后顺序取出来
			tmp = 0;
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < bucketElementCount[i]; j++) {
					arr[tmp++] = bucket[i][j];
				}
				bucketElementCount[i] = 0;// 重置
			}

		}
	}
}
/*
            在不均匀的数据分布情况下，比较排序算法（如快速排序、归并排序、堆排序等）的时间复杂度可能会退化到 O(n^2)。这是因为在不均匀的数据分布情况下，
            选取的基准元素可能会导致子序列的长度差异很大，从而导致递归深度增加，时间复杂度变高。

              相比之下，非比较排序算法（如基数排序、桶排序、计数排序等）的时间复杂度不受数据分布的影响。
           以基数排序为例，它是根据元素的位数，将待排序的元素分配到桶中，然后按照桶的顺序依次输出元素，直到所有元素都输出完毕。
           无论数据分布是否均匀，基数排序的时间复杂度都是 O(d(n+k))，其中 d 是元素的最大位数，k 是桶的数量。

           因此，如果数据分布比较不均匀，使用基数排序等非比较排序算法可能会比使用比较排序算法更加高效。但是需要注意的是，
           基数排序等非比较排序算法需要额外的空间来存储桶，因此在空间限制比较严格的情况下，需要考虑其他的排序算法。
*/
