package edgeWeightGraph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//javaû���Դ�����Ӧ���ȶ���api�������Լ���hashMap��priorityQueueдһ�����ȶ���
public class IndexMinPQ {
	private HashMap<Integer,Double> map ;//Ȩ����Ϊ��ֵ����Ϊ���ȶ���Ҫ��Ȩ������
	private PriorityQueue<Double> pq;
	
	public IndexMinPQ() {
		map = new HashMap<>();
		pq = new PriorityQueue<>(new Comparator<Double>() {

			@Override
			public int compare(Double o1, Double o2) {
				// TODO �Զ����ɵķ������
				if(o1-o2>0.0) {
					return 1;
				}else if(o1-o2<0.0) {
					return -1;
				}else {
					return 1;
				}
			}
			
		});
	}
	
	public void insert(int index,double value) {
		map.put(index, value);
		pq.add(value);
	}
	
	public int delMin() { 
		double minValue = pq.poll();
		int minKey = 0;
		for(Object t:map.entrySet()) {
			Map.Entry<Integer, Double> m = (Map.Entry)t;
			if(m.getValue() == minValue) {
				minKey = m.getKey();
				break;
			}
		}
		map.remove(minKey);
		return minKey;
	}
	
	public void change(int index,double value) {
		map.put(index, value);
	}
	public boolean isEmpty() {
		return pq.isEmpty();
	}
	public boolean contains(int index) {
		return map.containsKey(index);
	}
}
