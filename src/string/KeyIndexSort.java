package string;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

//建索引排序
/**
 *    键索引排序算法是对于 小整数键排序非常有效的算法，他的时间 是线性级别的，因为键索引计数法不需要比较，只通过key方法访问字符串，时间突破了
 *    排序算法 NlgN的下限
 *    
 *    步骤是： count数组 int型 ，0号位不用
 *           1：计算键的出现频率，count[a[i].key()+1]++; 计算键值为1字符串 的出现次数，存储到 count[2]中，别的以此类推
 *           2：将频率换为索引，count[i+1]+=count[i] ;   键值为2的字符串的第一个位置就是前一个键值为1字符串的数量，
 *              注意，此时count[1]是键值1的字符串的开始位置，count[2]是键值2的开始位置
 *           3：元素分类写入辅助数组，按照键值和对应的 count位置写入辅助数组 aux 中，也就是将字符串分类的过程
 *              aux[count[i].key()]++] =a[i];    
 *              //这里aux每添加一个元素，他对应的count就要++一次， 用于确定count对应索引的值的位置一定是最新的，也就是下一个数据要加入的位置
 * 
 *
 */
public class KeyIndexSort {
	//简单写一下，设计的一些api就不去实现了
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileInputStream("src\\tinyString.txt"));
		String[] a; //一个String类型的数组
		int N = scanner.nextInt();
		scanner.nextLine();
		a= new String[N];
		for(int i=0;i<N;i++) {
			a[i] = scanner.nextLine();
		}
		
		String[] aux = new String[N];
		//R是键值的数量
		int R = 5;
		int[] count = new int[R+1];
		
		//计算频率
		for(int i =0;i<N;i++) {
			count[a[i].charAt(0)-'0'+1]++;//假设字符第一个是键值
		}
		//频率转换为索引
		for(int i=0;i<R;i++) {
			count[i+1]+=count[i];
		}
		//分类排序
		for(int i=0;i<N;i++) {
			aux[count[a[i].charAt(0)-'0']++] = a[i];
		}
		//写回
		for(int i=0;i<N;i++) {
			a[i] = aux[i];
		}
		
		for(int i=0;i<N;i++) {
			System.out.println(a[i]);          //ok，排序成功
		}
	}
	

}
