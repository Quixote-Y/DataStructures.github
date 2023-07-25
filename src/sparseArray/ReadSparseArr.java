package sparseArray;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//读取前面压缩的二维稀疏数组，然后恢复
public class ReadSparseArr {
	public static void main(String[] args) {
		BufferedInputStream bufferedInputStream = null;
		try {
			bufferedInputStream = new BufferedInputStream(new FileInputStream("E:\\javaTestFile\\DataStruct\\sparseArr.txt"));
			//
			int[][] Array = new int[bufferedInputStream.read()][bufferedInputStream.read()]; 
			
			int count = bufferedInputStream.read();//这个要单独拿出来，然每次遍历都会去拿一遍
			for(int i=0;i<count;i++) {
				Array[bufferedInputStream.read()][bufferedInputStream.read()]=bufferedInputStream.read();
			}
			for (int[] row : Array) {
				for (int data : row) {
					System.out.printf("%d\t", data);
				}
				System.out.println("\n");
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			try {
				bufferedInputStream.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}

}
