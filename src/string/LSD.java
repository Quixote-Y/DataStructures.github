package string;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

//低位优先的字符串排序
/**
 * 
 *   基于键索引计数法，当要排序字符串是等长字符串的时候，可以使用LSD来排序
 *   从字符串最右边向左遍历，每个位置的字符作为键，进行键索引排序，用建索引计数法对字符串排序w遍
 *   
 *   能实现排序的 最底层是 因为 键索引计数法是稳定 的排序算法
 *
 */
public class LSD {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileInputStream("src\\tinyString.txt"));
		String[] a; //一个String类型的数组
		int N = scanner.nextInt();
		scanner.nextLine();
		a= new String[N];
		for(int i=0;i<N;i++) {
			a[i] = scanner.nextLine();
		}
		
		//通过前N个字符对a排序
		int R = 256;//ascal码范围到256
		String[] aux = new String[N];
		//LSD这个实现只能用于长度相同的字符串，w是字符串长度
		int W = a[0].length();
		
		//根据第d个字符用建索引排序方式对a排序
		for(int d = W-1;d>=0;d--) {
			int[] count = new int[R+1];    //这个声明只能在这，之前放外面出现错误，因为每一轮都要重新写count，放外面的话没有重置数据，就会出错
			//记录频率
			for(int i=0;i<N;i++) {
				count[a[i].charAt(d)+1]++;     //这里+1用于屏蔽0号位，用于后面转换为索引的时候可以 实现 count[i]就是i的起始位置，这里存的是数量
			}
			//频率转换为索引
			for(int i=0;i<R;i++) {
				count[i+1]+=count[i];
			}
			//排序分类
			for(int i=0;i<N;i++) {
				aux[count[a[i].charAt(d)]++] = a[i];//将a第i个位置的字符串放到aux 的按count统计的位置中
			}
			//复制回来
			for(int i=0;i<N;i++) {
				a[i] = aux[i];
			}
		}
		for(int i=0;i<N;i++) {
			System.out.println(a[i]);          //ok
		}
	}

}
