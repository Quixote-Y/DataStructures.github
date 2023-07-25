package sort;

import java.util.Arrays;

public class SelectionSort {
	// 选择排序：每一轮选出所有数里最大或最小的元素和没排序的元素的第一个元素交换
    // 相较冒泡排序，更少的比较次数，无效交换更少，速度快的多
	public static void main(String[] args) {
		// 测试选择排序的速度 ，时间复杂度是O(2*n)
		int[] arr = new int[80000];
		for (int i = 0; i < 80000; i++) {
			arr[i] = (int) (Math.random() * 80000000);
		}
		long start = System.currentTimeMillis();
		selectionSort(arr);           //0.835 秒 相较冒泡排序速度提高了很多
		long end = System.currentTimeMillis();
		double time = (end - start) / 1000.0;
		System.out.println(time);
	}

	// 选择排序
	public static void selectionSort(int[] arr1) {
		int[] arr = arr1;
		int minIndex = 0;
		int min = arr[0];

		for (int i = 0; i < arr.length; i++) {
			min = arr[i];
			for (int j = i + 1; j < arr.length; j++) {
				if (min > arr[j]) {
					min = arr[j];
					minIndex = j;
				}
			}
			if (minIndex != i) {
				arr[minIndex] = arr[i];
				arr[i] = min;
			}
		}
	}
}
