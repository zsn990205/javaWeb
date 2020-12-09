package _20201207;

class Node {
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class CodeTest2 {
    public Node head;

    //头插法
    public void addFirst(int data) {
        Node node = new Node(data);
        //1.判断链表是否为空 为空就是首次插入
        if (this.head == null) {
            this.head = node;
            return;
        }
        //2.链表不为空的情况下 直接将新节点插入到头节点之前
        node.next = this.head;
        head = node;
    }

    //尾插法
    public void addLast(int data) {
        Node node = new Node(data);
        //当链表为空的时候就是头插法
        if (this.head == null) {
            addFirst(data);
            return;
        }
        //不为空的时候 进行尾插即可 找到尾节点进行删除即可
        Node cur = this.head;
        while (cur.next != null) {
            cur = cur.next;
        }
        //这个位置是cur==null的位置
        cur.next = node;
    }

    //任意位置插入,第一个数据节点为0号下标
    public void addIndex(int index, int data) {
        Node node = new Node(data);
        //先判断index位置是否合法
        if (index < 0 || index > size()) {
            throw new RuntimeException("index位置不合法请重新输入");
        }
        //当index=0的时候是头插法 index=链表长度时是尾插法
        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == size()) {
            addLast(data);
            return;
        }
        //index位置不为空 找到index下标的元素的前一个位置进行插入操作即可
        Node prev = findPrev(index);
        node.next = prev.next;
        prev.next = node;
    }

    private Node findPrev(int index) {
        //index是下标
        if (index < 0 || index > size()) {
            throw new RuntimeException("index位置不合法");
        }
        //prev是index-1位置
        Node cur = this.head;
        for (int i = 0; i < index - 1; i++) {
            cur = cur.next;
        }
        //此时cur已经走到了index-1位置
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
        if (this.head.data == key) {
            this.head = this.head.next;
            return;
        }
        //找到要删除的关键字的下标的前一个然后进行删除
        Node prev = findPrev2(key);
        if (prev == null) {
            System.out.println("没有要删除的这个节点");
            return;
        }
        Node del = prev.next;
        prev.next = del.next;
    }

    private Node findPrev2(int key) {
        Node cur = this.head;
//        while (cur.next != null) {
//           if (cur.next.data == key) {
//                return cur;
//            } else {
//                cur = cur.next;
//            }
//        }
//        return null;
//先查找要找的数字在不在链表中
//不在的话就没必要找了直接return
//有的话执行下输操作
        if (!(contains(key))) {
            System.out.println("要删除的key不在链表中");
            return null;
        }
        while (cur.next.data != key) {
            cur = cur.next;
        }
        return cur;
}

    //删除所有值为key的节点
    public void removeAllKey(int key) {

    }
    //得到单链表的长度
    public int size() {
        Node cur = this.head;
        int count = 0;
        if (this.head == null) {
            return 0;
        }
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    public void display() {
        Node cur = this.head;
        while (cur != null) {
            System.out.print(cur.data+" ");
            cur = cur.next;
        }
    }

    public void clear() {
        this.head = null;
    }
}
