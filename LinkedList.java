package _prepareAgo;

class Node {
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}
public class LinkedList {
    public Node head;    //保存单链表头节点的引用 head的地址在变化
    //头插法
    public void addFirst(int data) {
        Node node = new Node(data);  //首先表示出新节点的地址
        //如果链表为空的话
        if (this.head == null) {
            this.head = node;
        //当第一次插完之后必须return 因为只有一个数字不return代码会继续向下运行
            return;
        }
        node.next = this.head;
        head = node;
    }

    //尾插法
    public void addLast(int data) {
         Node node = new Node(data);
         Node cur = this.head;
         if (this.head == null) {
             this.head = node;
             return;
         }
         //先找到cur.next为空的位置 找到后把节点尾插进去
         while (cur.next != null) {
             cur = cur.next;
         }
         cur.next = node;
    }

    //任意位置插入,第一个数据节点为0号下标
    public void addIndex(int index,int data) {
        Node node = new Node(data);
        if (index == 0) {
            //头插法
            addFirst(data);
            return;
        }
        if (index == this.size()) {
            //尾插法
            addLast(data);
            return;
        }
        //先找到index的前一个位置
        Node cur = searchIndex(index);
        node.next = cur.next;
        cur.next = node;
    }

    private Node searchIndex(int index) {
        if (index < 0 || index > this.size()) {
            throw new RuntimeException("index位置不合法");
        }
        Node cur = this.head;
        int count = 0;
        while (count == index - 1) {
            cur = cur.next;
            count++;
        }
        return cur;
    }

    //查找是否包含关键字key是否在单链表当中
    public boolean contains(int key) {
        Node cur = this.head;
        while (cur != null) {
            if (cur.data == key) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }
    
    //删除第一次出现关键字为key的节点
    public void remove(int key) {
        Node cur = this.head;

    }

    //删除所有值为key的节点
    public void removeAllKey(int key) {

    }
    //得到单链表的长度
    public int size() {
        Node cur = this.head;
        int count = 0;
        if (head == null) {
            return 0;
        }
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    //打印单链表
    public void display() {
        Node cur = this.head;
        while (cur != null) {
            System.out.print(cur.data+" ");
            cur = cur.next;
        }
    }

    public void clear() {

    }

}
