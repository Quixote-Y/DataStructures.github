package sparseArray;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//稀疏数组 的 创建,保存和恢复
public class SparseArray {

	public static void main(String[] args) {
		// 一个11*11的棋盘 ，0：表示没有子 1：黑子 2：白子
		int[][] chessArr = new int[11][11];
		chessArr[1][2] = 1;
		chessArr[2][3] = 2;
		chessArr[10][10] = 2;
		// 输出一下原始的二维数组
		System.out.println("============原始的二维数组==============");
		for (int[] row : chessArr) {
			for (int data : row) {
				System.out.printf("%d\t", data);
			}
			System.out.println("\n");
		}

		// 转为稀疏数组
		// 1.先遍历二维数组，得到非0数据的个数

		/*
		 * Apple[] apple;//声明 apple = new Apple[10];//创建 for(int i=0;i<10;i++) {
		 * apple[i]=new Apple(i,10*i); }//赋值，引用类型，实际给的是堆内数据的地址
		 */
		List<Node> list = new ArrayList<>();
		int count = 0;
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if (chessArr[i][j] != 0) {
					list.add(new Node(++count, i, j, chessArr[i][j]));
				}
			}
		}
		// 创建稀疏矩阵
		int[][] sparseArr = new int[count + 1][3];
		sparseArr[0][0] = 11;
		sparseArr[0][1] = 11;
		sparseArr[0][2] = count;
		// 行是非0的个数+1，因为第一行要存二维数组的长和宽和总非0个数，列是恒为3列，分别是x,y,val
		// 添加值
		for (int i = 0; i < list.size(); i++) {
			Node tmp = list.get(i);
			sparseArr[tmp.count][0] = tmp.x;
			sparseArr[tmp.count][1] = tmp.y;
			sparseArr[tmp.count][2] = tmp.value;
		}
		for (int i = 0; i < sparseArr.length; i++) {
			System.out.printf("%d\t%d\t%d\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
		}
		
		//把稀疏数组写道文件里去
		BufferedOutputStream bufferedOutputStream =null;
		try {
			bufferedOutputStream = new BufferedOutputStream(
					                                        new FileOutputStream("E:\\javaTestFile\\DataStruct\\sparseArr.txt"));
			for(int i=0;i<count+1;i++) {
				for(int j=0;j<3;j++) {
					bufferedOutputStream.write(sparseArr[i][j]);
				}
			}
			System.out.println("写入完成");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			try {
				bufferedOutputStream.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
}

/**
 * Node用来存放构建稀疏数组时需要的参数 
 * count :稀疏数组中几个0的元素，这是稀疏数组的y坐标，  x坐标分别是0，1，2，对应x,y,value; 
 * x： 这个非0元素的x坐标
 *  y： 这个非0元素的y坐标 value ;
 * 值
 */
class Node {
	int count;
	int x;
	int y;
	int value;

	public Node(int count, int x, int y, int value) {
		super();
		this.count = count;
		this.x = x;
		this.y = y;
		this.value = value;
	}

}