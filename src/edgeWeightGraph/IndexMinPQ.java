package edgeWeightGraph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//java没有自带的索应优先队列api，所以自己用hashMap和priorityQueue写一个优先队列
public class IndexMinPQ {
	private HashMap<Integer,Double> map ;//权重作为键值，因为优先队列要对权重排序
	private PriorityQueue<Double> pq;
	
	public IndexMinPQ() {
		map = new HashMap<>();
		pq = new PriorityQueue<>(new Comparator<Double>() {

			@Override
			public int compare(Double o1, Double o2) {
				// TODO 自动生成的方法存根
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
