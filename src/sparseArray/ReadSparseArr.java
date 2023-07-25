package sparseArray;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//��ȡǰ��ѹ���Ķ�άϡ�����飬Ȼ��ָ�
public class ReadSparseArr {
	public static void main(String[] args) {
		BufferedInputStream bufferedInputStream = null;
		try {
			bufferedInputStream = new BufferedInputStream(new FileInputStream("E:\\javaTestFile\\DataStruct\\sparseArr.txt"));
			//
			int[][] Array = new int[bufferedInputStream.read()][bufferedInputStream.read()]; 
			
			int count = bufferedInputStream.read();//���Ҫ�����ó�����Ȼÿ�α�������ȥ��һ��
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally {
			try {
				bufferedInputStream.close();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}

}
