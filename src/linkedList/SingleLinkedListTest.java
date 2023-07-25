package linkedList;

import java.util.Stack;

//单向链表
public class SingleLinkedListTest {

	
	public static void main(String[] args) {
		//测试无序插链尾
		HeroNode node1 = new HeroNode(1,"宋江","及时雨");
		HeroNode node2 = new HeroNode(2,"卢俊义","玉麒麟");
		HeroNode node3 = new HeroNode(3,"吴用","智多星");
		HeroNode node4 = new HeroNode(4,"林冲","豹子头");
		HeroNode node5 = new HeroNode(5,"闪电侠","闪电侠");
		HeroNode node6 = new HeroNode(6,"钢铁侠","钢铁侠");
		HeroNode node7= new HeroNode(7,"美国队长","美国队长");
		//创建表头 ，也就是创建链表
		SingleLinkedList list = new SingleLinkedList();
		SingleLinkedList list2 = new SingleLinkedList();
		//添加元素
//		list.add(node1);
//		list.add(node4);
//		list.add(node2);
//		list.add(node3);
		
		
		//但这种添加是无序的，下面增加一个按编号排序添加
		list.addById(node1);
		list.addById(node7);
		list.addById(node6);
		list.addById(node3);
		
		//更改
//		list.modifyName(new HeroNode(1, "鲁智深", "小麻雀"));
		
		list2.addById(node5);
		list2.addById(node4);
		list2.addById(node2);
		
		//list.deleteNode(4);
		System.out.println("=======================list1==================================");
		list.showList();
		System.out.println("=======================list2==================================");
		list2.showList();
		//长度
//		System.out.println("长度是："+list.size());
		//倒数第几位
//		System.out.println(list.findLast(1));
		
//      // 链表反转		
//		System.out.println("=========================================================");
//		list.reverse();
//		list.showList();
		
//		System.out.println("=====================倒序输出显示====================================");
//		list.showListBack();
		
		System.out.println("=====================两个有序串合并输出显示====================================");
		list.mergeSortedList(list2.getHead());
		list.showList();
		
	}
}

class SingleLinkedList{//单链表的结构和方法
	//头节点-->在这里声明，这样的话初始化的时候会创建它--》这样的话就能实现一条链就一个恒定不变的头节点了
	private HeroNode head = new HeroNode();
	
	//返回头节点
	public HeroNode getHead() {
		return head;
	}
	
	//元素加到链表尾
	public void add(HeroNode node) {
		HeroNode tmp = head;
		while(true) {
			if(tmp.getNext()==null) {
				tmp.setNext(node);
				break;
			}else {
				tmp=tmp.getNext();
			}
		}
	}
	
	//正序显示链表
	public void showList() {
		if(head.getNext()==null) {
			System.out.println("链表为空，无可显示内容");
			return;
		}
		HeroNode tmp = head;
		while(true) {
			if((tmp=tmp.getNext())!=null) {
				System.out.println(tmp);
			}else {
				break;
			}
		}
	}
	
	//逆序打印链表
	public void showListBack() {
		if(head.getNext()==null) {
			System.out.println("链表为空，无可显示内容");
			return;
		}
		
		//两种思路：1.将链表反转 ，打印反转后的链表 ，单链表反转的话链表结构发生变化，且麻烦
		//          2. 栈！！，将链表正序访问且每个元素压栈，之后在一个一个出栈显示
		
		Stack<HeroNode> stack = new Stack<>();
		HeroNode tmp = head;
		//进栈
		while(true) {
			tmp = tmp.getNext();
			if(tmp==null) {
				break;
			}
			stack.add(tmp);
		}
		//出栈显示
		int size = stack.size();
		for(int i = 0;i<size ;i++) {
			tmp = stack.pop();
			System.out.println(tmp);
		}
	}
	
	//排序添加
	
	public void addById(HeroNode node) {
		HeroNode tmp = head;
		while(true) {
			//这个判断位于链尾的操作老是忘了，就会出现异常，就是当位于最后面，明明tmp的next已经为null了，还要去调用getid方法，就会报错
			if(tmp.getNext()==null) {
				//说明tmp已经指向在最后一个节点，那就直接加到它后面就好
				break;
			}
			//也就是从头开始遍历，加到第一个id比node大的前面
			if(tmp.getNext().getId()>node.getId()) {
				//当前tmp指向节点的下一个节点id比node大，那就加在tmp后面
				break;
			}
			//遍历别忘了
			tmp=tmp.getNext();
		}
		//指针交替
		node.setNext(tmp.getNext());
		tmp.setNext(node);
	}
	
	//修改指定节点内容
	public void modifyName(HeroNode node) {
		if(head.getNext()==null) {
			System.out.println("链表为空，修改失败");
			return;
		}
		HeroNode tmp = head;
		while(true) {
			tmp = tmp.getNext();
			if(tmp==null) {
				System.out.println("未找到该id，修改失败");
				break;
			}
			if(tmp.getId()==node.getId()) {
				tmp.setName(node.getName());
				tmp.setNickName(node.getNickName());
				break;
			}
		}
		return;
	}
	
	//删除指定id节点
	public boolean deleteNode(int id) {
		if(head.getNext()==null) {
			System.out.println("链表为空，删除失败");
			return false;
		}
		HeroNode tmp = head;
		while(true) {
			if(tmp.getNext()==null) {
				System.out.println("未找到指定id节点");
				return false;
			}
			if(tmp.getNext().getId()==id) {
				tmp.setNext(tmp.getNext().getNext());//把tmp的下一个节点指针指向下一个的下一个
				break;
			}
			tmp = tmp.getNext();
		}
		return true;
	}

	/*这里总结一下增删改查操作易出现错误的地方：
	 *     其实增删操作最容易出错，因为增删操作在单项链表中tmp指针应该指向的是你要操作的地方的上一个节点位置
	 *     与改不同，因此程序顺序也就有差异，需要注意的是：  
	 *              1.你应该去判断是否为空的是 tmp (操作对象应该是tmp节点，改操作)还是 tmp.next (操作对象是当前节点的下一个节点，增，删操作)
	 *              2.别忘了判断链表是否为空  head==null？
	 *              3. tmp指针后移的位置： 当你是操作tmp指向的当前节点时，你判断的是tmp==null？ ，初始化是tmp= head 那么你就应该把tmp后移放所有操作最前面
	 *                                   而当你是操作 tmp指向的下一个节点时，你关注的对象其实是tmp的下一个，你的所有的比较操作应该都是基于 tmp.next ，
	 *                                   保留tmp只是为了当增删时拿到下一个节点的指针，因此你的tmp后移应该放在所有操作的最后面

	 */	
	
	//返回有效长度
	public int size() {
		HeroNode tmp = head;
		int length = 0;
		while((tmp=tmp.getNext())!=null) {
			length++;
		}
		return length;	
	}
	
	//查找单链表的倒数第k
	/*
	 * 思路：
	 *   1.链表翻转，取第k个元素
	 *   2.双重循环，内层以k个元素为一组，找到最后一组，内个位置就是倒数第k个
	 *   3.  !!!!!欸
	 *     都知道长度了，找倒数第k个元素，不就是正着找第 length - k 个元素吗？
	 */
	
	//因为链表翻转的话就改变原链结构了，所以我先尝试双重循环
	public HeroNode findLast(int k) {
//		HeroNode tmp = head ;
//		HeroNode tmp2 = new HeroNode();
//		boolean find = false;
//		int length = this.size();
//		if(length<k) {
//			System.out.println("链表长度不够，找不到倒数第k个");
//			return null;
//		}
//		if(k==0) {
//			k=1;
//		}
//		for(int i = 0; i< length ;i++) {
//			tmp = tmp.getNext();
//			tmp2 = tmp;
//			for (int j = 0; j < k-1; j++) {
//				tmp2 = tmp2.getNext();
//				if(tmp2.getNext()==null) {
//					find = true ;
//					break;
//				}
//			}
//			if(find==true) {
//				break;
//			}
//		}
//		return tmp;
		
		//方法3
		int length = this.size();
		int index = length -k;
		if(index<0||index>length) {//判断索引位置不合法
			return null;
		}
		HeroNode tmp =head;
		for(int i = 0;i< index+1 ;i++) {
			tmp = tmp.getNext();
		}
		return tmp;
		
	}
	
	//单链表反转
	/**
	 * 思路：
	 *   新建一个链表头，然后遍历原链表，每个元素头插法存到新链表中，结束后原链表头指向新链表，空间复杂度O（1）
	 *   
	 *   //头插法 笔记两种方式
	 *     
	 */
	
	public void reverse() {
		if(head.getNext()==null||head.getNext().getNext()==null) {
			return ;//只有1个或者没有节点，直接返回
		}
		HeroNode newHead = new HeroNode();  // 新表头
		HeroNode tmp = head.getNext();                //原链的正在被操作的节 点
		HeroNode tmpNext = null;  //前一个节点被取出来添加后 ，这个标记用于记录下一个要被处理的节点
		
		while(true) {
			tmpNext = tmp.getNext();
			tmp.setNext(newHead.getNext());
			newHead.setNext(tmp);
			tmp = tmpNext;
			if(tmp==null) {
				break;
			}
		}
		//改头结点
		head.setNext(newHead.getNext());
	}
	
	//两个有序链表合并为有序链表
	//有序id从小到大
	public void mergeSortedList(HeroNode newHead) {
		//判断两个链表有为null
		if(head.getNext()==null) {
			head = newHead;
		}else if(newHead.getNext()==null) {
			return;
		}
		
		HeroNode tmp =head;                     //这里有细节，你必须把tmp从head开始，因为真实比较操作的时 tmpNext ，如果从head.next开始，那就会漏掉这条链的第一个节点
		HeroNode tmpNext = tmp.getNext();       // 实际参与比较的节点，当它的id比 newTmp指向的id大时，说明newTmp节点一个插在 tmpNext前面
		HeroNode newTmp = newHead.getNext();    // 这条链的节点插入到上面内条链里
		
		//要是没进去就不会改变tmpNext的值，出现错误
		while(true) {
			if(tmpNext == null) {
				tmp.setNext(newTmp);
				break;
			}
			if(newTmp == null) {
				break;
			}
			if(tmpNext.getId()>newTmp.getId()) {
				tmp.setNext(newTmp);
				newTmp = newTmp.getNext();
				tmp.getNext().setNext(tmpNext);
				//交换完成
			}
			tmp = tmpNext;
			tmpNext = tmp.getNext();
		}
		
	}
}

class HeroNode{
	private int id;
	private String name;
	private String nickName;
	private HeroNode next ;//指向下一个节点
	
	public HeroNode(int id, String name, String nickName, HeroNode next) {
		super();
		this.id = id;
		this.name = name;
		this.nickName = nickName;
		this.next = next;
	}

	
	public HeroNode(int id, String name, String nickName) {
		super();
		this.id = id;
		this.name = name;
		this.nickName = nickName;
	}




	public HeroNode() {
		super();
	}



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public HeroNode getNext() {
		return next;
	}


	public void setNext(HeroNode next) {
		this.next = next;
	}


	@Override
	public String toString() {
		return "HeroNode [id=" + id + ", name=" + name + ", nickName=" + nickName + "]";
	}

	
}
