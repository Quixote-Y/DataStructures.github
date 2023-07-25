package linkedList;

//双向链表
public class DoubleLinkedListTest {

	public static void main(String[] args) {
		// 测试无序插链尾
		HeroNode2 node1 = new HeroNode2(1, "宋江", "及时雨");
		HeroNode2 node2 = new HeroNode2(2, "卢俊义", "玉麒麟");
		HeroNode2 node3 = new HeroNode2(3, "吴用", "智多星");
		HeroNode2 node4 = new HeroNode2(4, "林冲", "豹子头");
		HeroNode2 node5 = new HeroNode2(5, "闪电侠", "闪电侠");
		HeroNode2 node6 = new HeroNode2(6, "钢铁侠", "钢铁侠");
		HeroNode2 node7 = new HeroNode2(7, "美国队长", "美国队长");

		// 创建双线链表类对象
		DoubleLinkedList list = new DoubleLinkedList();
		// 增
//		list.add(node1);
//		list.add(node2);
//		list.add(node3);
//		list.add(node4);
//		list.add(node5);
//		list.add(node6);
//		list.add(node7);
		// 按id排序添加
		list.addById(node1);
		list.addById(node7);
		list.addById(node4);
		list.addById(node2);
		list.addById(node5);
		list.addById(node3);
		list.addById(node6);

		// 显示
		list.showList();
		System.out.println("=======================删==================================");
		// 删
		list.deleteNode(5);
		list.showList();
		// 改
		System.out.println("=======================改==================================");
		list.modifyName(new HeroNode2(1, "鲁智深", "小麻雀"));
		list.showList();
	}
}

//创建双向链表的类
class DoubleLinkedList {
	// private HeroNode2 head;// 头节点//忘了初始化，导致head为null,添加结点的时候调用head.next出现空指针异常
	private HeroNode2 head = new HeroNode2();

	// 末尾添加
	// 元素加到链表尾
	public void add(HeroNode2 node) {
		HeroNode2 tmp = head;
		while (true) {
			if (tmp.getNext() == null) {
				tmp.setNext(node);
				node.setPre(tmp);// 相较单向链表就多了个前驱指针指向
				break;
			} else {
				tmp = tmp.getNext();
			}
		}
	}

	// 正序显示链表
	public void showList() {
		if (head.getNext() == null) {
			System.out.println("链表为空，无可显示内容");
			return;
		}
		HeroNode2 tmp = head;
		while (true) {
			if ((tmp = tmp.getNext()) != null) {
				System.out.println(tmp);
			} else {
				break;
			}
		}
	}

	// 修改指定节点内容
	public void modifyName(HeroNode2 node) {
		if (head.getNext() == null) {
			System.out.println("链表为空，修改失败");
			return;
		}
		HeroNode2 tmp = head;
		while (true) {
			tmp = tmp.getNext();
			if (tmp == null) {
				System.out.println("未找到该id，修改失败");
				break;
			}
			if (tmp.getId() == node.getId()) {
				tmp.setName(node.getName());
				tmp.setNickName(node.getNickName());// 和单向链表相同，因为不涉及到指针的操作
				break;
			}
		}
		return;
	}

	// 删除指定id节点
	public boolean deleteNode(int id) {
		if (head.getNext() == null) {
			System.out.println("链表为空，删除失败");
			return false;
		}
		HeroNode2 tmp = head.getNext();
		// 这里就可以直接赋值next了，因为双向链表不用向单向链表一样借助tmp来记录前驱节点位置，可以实现自我删除，有当前要被删除节点的消息就足够进行删除操作
		while (true) {
			if (tmp == null) {
				System.out.println("未找到指定id节点");
				return false;
			}
			if (tmp.getId() == id) {
				// 把自己的前驱节点的下一个改成自己的下一个，跳过自己
				tmp.getPre().setNext(tmp.getNext());
				// 这里很关键，总是忘记判断，如果这个节点是最后一个节点，那就不能取tmp.next.pre了,因为next是null
				if (tmp.getNext() != null) {
					// 如果不是最后一个节点，那就把自己的下一个节点的前驱指针指向 自己的上一个节点，维护好双向链表
					tmp.getNext().setPre(tmp.getPre());
				}
				break;
			}
			tmp = tmp.getNext();
		}
		return true;
	}

	// 按id顺序添加
	// 相较于单向链表的添加,因为要修改前驱节点，所以当要添加节点是最大的时候，也就是要添加到链表尾的时候，就要改变方法，
	// 因为链表尾的节点不能 tmp.next.pre 因为next已经返回 null 了，空指针异常，，因此必须要分情况
	// 当发现已经找到最后一个节点了，那就 直接改前驱后记 
	public void addById(HeroNode2 node) {
		//添加操作tmp都必须指向head
		HeroNode2 tmp = head;
		while (true) {
			if (tmp.getNext() == null) {
				// 说明tmp已经指向在最后一个节点，那就直接加到它后面就好
				tmp.setNext(node); // tmp的next是node
				node.setPre(tmp); // node的前驱是tmp
				break;
			}
			// 从头开始遍历，加到第一个id比node大的前面
			if (tmp.getNext().getId() > node.getId()) {
				// 当前tmp指向节点id比node大，那就加在tmp前面
				tmp.getNext().setPre(node);
				node.setNext(tmp.getNext());
				tmp.setNext(node);
				node.setPre(tmp);
				break;
			}
			// 遍历别忘了
			tmp = tmp.getNext();
		}

	}

}

//节点
class HeroNode2 {
	private int id;
	private String name;
	private String nickName;
	private HeroNode2 next;// 指向下一个节点
	private HeroNode2 pre;

	public HeroNode2(int id, String name, String nickName) {
		super();
		this.id = id;
		this.name = name;
		this.nickName = nickName;
	}

	public HeroNode2() {
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

	public HeroNode2 getNext() {
		return next;
	}

	public void setNext(HeroNode2 next) {
		this.next = next;
	}

	public HeroNode2 getPre() {
		return pre;
	}

	public void setPre(HeroNode2 pre) {
		this.pre = pre;
	}

	@Override
	public String toString() {
		return "HeroNode [id=" + id + ", name=" + name + ", nickName=" + nickName + "]";
	}

}