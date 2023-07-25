package recusion;

import org.junit.jupiter.api.Test;

//8皇后问题回溯递归
public class EightQuenue {

	static int max =8;//8个皇后
	//一个数组，用于存放皇后放置位置的结果
	static int[] arr = new int[max];
	static int count=0;
	
	public static void main(String[] args) {
		getEightQuenue(0);
		System.out.println(count);
	}
	
	//打印8个皇后位置
	public static void printQuenue() {
		for(int i =0;i<max;i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
	
	//判断放置的第n个皇后是否与前面n-1个皇后发生冲突
	public static boolean judge(int n) {
		for(int i=0;i<n;i++) {
			if(arr[i]==arr[n]||Math.abs(n-i)==Math.abs(arr[n]-arr[i])) {
				//当前面的第i个皇后和我们添加的皇后位于同一直线，或两条斜线时，发生冲突
				return true;
			}
		}
		return false;
	}
	
	//开始寻找所有8皇后位置的方法
	//n 第几个皇后开始
	public static void getEightQuenue(int n) {
		for(int i=0;i<max;i++) {
			arr[n]=i;      //把第n位皇后放置到棋盘里
			if(judge(n)) {
				//如果发生冲突，跳过，看下一个数
				continue;
			}else {
				//没发生冲突，则这个位置暂时可行
				//如果放到了第8位皇后，通过了judge
				if(n==7) {
					printQuenue();
					count++;
					continue;
				}
				getEightQuenue(n+1); //开始放下一个皇后
			}
		}
		return;
	}
}
