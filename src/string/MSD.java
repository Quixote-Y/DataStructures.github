package string;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

//高位优先的字符串排序
/*
 * 普适性的字符串排序算法，适用于各种类型的字符串
 * 从左向右扫描
 * 
 * 关键在于理清 count【r】的意义
 */
public class MSD {

	private static int R = 256; //基数，编码数
	private static int M =0 ;//这个是切换排序算法的阈值，这里就设为0就好了,用不上，不考率
	private static String[] aux; //辅助数组
	private static int charAt(String s,int d) {
		//重写字符串的charAt方法，能够处理d大于字符串长度的情况
		if(d<s.length())return s.charAt(d);
		else return -1;   //当d超出字符串时返回-1
	}
	
	public static void sort(String[] a) {
		int N = a.length;
		aux = new String[N];
		sort(a,0,N-1,0);
	}
	/**
	 *     递归字符串排序
	 * @param a           要排序的字符串数组
	 * @param lo          数组排序开始的位置
	 * @param hi                  结束
	 * @param d           排序基于的是第几个字符
	 * 
	 * 
	 *  这里很巧妙， 我们第一次对数组字符串首字母排序后 a 的一堆，b的一堆 c的一堆... ，然后递归进去，先对a的一堆排序，基于第二个字符，
	 *  然后第二个字符之后又是一堆，接着进去，直到所有的结束，返回到第一次递归的地方，在去对b的一堆同样的排序。
	 *  一个关键就是： 当到达字符串末尾时，计算频率的部分charAt重写的方法返回的值是-1，+2，就会变成count[1]，之后频率转换为索引，
	 *   count[1]+=count[0];此时count[0]就是长度为d的字符串在数组的起始位置，count[1]就是字符串长度为d的 终止位置
	 */
	private static void sort(String[] a,int lo,int hi,int d) { 
		//以第d个字符为键将a[lo]到a[hi]排序，前面已经排序过了，是有序的
		if(hi<=lo+M) {
			//调用别的排序方法，以优化算法
			return;
		}
		int[] count = new int[R+2];   //+2，不是+1，因为多了一个状态0当 count[0]表示字符串长度为d的字符串
		//计算频率
		for(int i =lo;i<=hi;i++) {
			count[charAt(a[i], d)+2]++; //+2 
		}
		//转化为索引
		for(int i= 0;i<R+1;i++) {
			count[i+1]+=count[i];
		}
		//分类排序
		for(int i=lo;i<=hi;i++) {
			//count[charAt(a[i], d)+1] :当长度是d时，存到count[0]对应的位置
			aux[count[charAt(a[i], d)+1]++] = a[i];
			//aux[]中间的count返回值是下标
		}
		
		//重写
		for(int i=lo;i<=hi;i++) {
			a[i] = aux[i-lo]; //aux[i-lo]，要区分清楚，aux和a的[r] r值是数量，，而count是字符表所有字符的统计，两者不一样
		}
		//递归 以每个字符为键进行排序
		//    a2131 a413221 a313232 b23123 b321321 b3213   a进行一组排序 b进行一组排序
		for(int i =0;i<R;i++) {
			//对所有的字符都进行一次递归排序
			sort(a,lo+count[i],lo+count[i+1]-1,d+1);
			//这里，lo+count[i] 像上面的列子：他会遍历ascal字符表所有的字符，前面一直都是0，因为没有第d位字母（第一位）对应的字符，
			//但当到97时，a被找到，对应的a有3个即当i=97时，count[97]=0,count[98]=3+1，因为count总是指向下一个字符放入的位置所以多+1
			//那么 对a 进行小组排序时 起始就是 0 到 count[98]-1 =3;
			// 进去之后又是递归 ，当第一次到达某个字符串末尾时，他就会被记录进count[1]，count[0]就是他起始的位置，在分类排序时就会被放到
			// 小组的最前面
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileInputStream("src\\tinyStringMSD.txt"));
		String[] a; //一个String类型的数组
		int N = scanner.nextInt();
		scanner.nextLine();
		a= new String[N];
		for(int i=0;i<N;i++) {
			a[i] = scanner.nextLine();
		}
		
		sort(a);
		for(int i=0;i<N;i++) {
			System.out.println(a[i]);
		}
	}
}
