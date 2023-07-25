package sort;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.rules.Stopwatch;


//冒泡：每两个元素都要比较，然后进行了很多无效的交换
public class BubleSort {

	public static void main(String[] args) {
		//测试冒泡排序的速度  ，时间复杂度是O(2*n)
		int[] arr = new int[80000];
		for(int i=0;i<80000;i++) {
			arr[i]=(int )(Math.random()*80000000);
		}
		long start = System.currentTimeMillis();
		bubleSort2(arr);        //7.259 秒
		//bubleSort1(arr);          //6.962
		//其实两个方法相差无几，都是7秒左右
		long end = System.currentTimeMillis();
		double time = (end- start)/1000.0;
	    System.out.println(time);
	}
	
	public static void bubleSort1(int[] arr1) {
		//这是我常用的方式，严格来说应该是下沉排序
		int[] arr = arr1;
		for(int i=0;i<arr.length;i++) {
			for(int j=i+1;j<arr.length;j++) {
				if(arr[i]>arr[j]) {
					int tmp = arr[i];
					arr[i]=arr[j];
					arr[j]=tmp;
				}
			}
		}
		System.out.println(Arrays.toString(arr));
	}
	//每次相邻两个数据比较，按规则上浮，直到最上面，，感觉习惯一下这个会更好
	@Test
	public static void bubleSort2(int[] arr1) {
		//这个方式能优化
		int[] arr = arr1;
		int tmp =0;
		for(int i=0;i<arr.length;i++) {
			boolean flag = false;
			//这就是优化的关键
			for(int j=0;j<arr.length-1-i;j++) {
				//如果前面的数比后面的数大就交换
				if(arr[j]>arr[j+1]) {
					flag=true;
					tmp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=tmp;
				}
			}
			if(!flag) {
				//如果一次交替都没有，那就说明数据是已经完全有序的了，直接退出
				break;
			}
		}
		//System.out.println(Arrays.toString(arr));
	}
	
}
