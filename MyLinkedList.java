package _prepareAgo;

class node {
    public node next;
    public node prev;
    public int data;

    public node(int data) {
        this.data = data;
    }
}
public class MyLinkedList {
    public node head;  //标志双向链表的头
    public node tail;  //标志双向链表的尾

    //头插法(插到双向链表头)
    public void addFirst(int data) {
    //1.先判断是不是第一次插入
    //2.第一次插入的话 直接让头指向尾即可
    //  不是第一次插入的话 修改prev和next指针即可
        node node = new node(data);
        if (this.head == null) {
           this.head = node;
           this.tail = node;
    } else {
            node.next = this.head;
            this.head.prev = node;
            this.head = node;  //让新节点成为头
        }
    }

    //尾插法
    public void addLast(int data) {
        node node = new node(data);
        if (this.head == null) {
            this.head = node;
            this.tail = node;
        } else {
            this.tail.next = node;
            node.prev = this.tail;
            this.tail = node;
        }
    }

    //任意位置插入(找到的插入位置之后应该往它前面插入),第一个数据节点为0号下标
    public void addIndex(int index,int data) {
        //判断index的合法性
        checkIndex(index);
        node newNode = new node(data);
        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == size()) {
            addLast(data);
            return;
        }
        //先找到index下标 让cur指向index位置
        node cur = searchIndex(index);
        //此时cur已经找到了自己的位置
        newNode.prev = cur.prev;
        newNode.next = cur;
        cur.prev.next = newNode;
        cur.prev = newNode;
    }

    private node searchIndex(int index) {
        node cur = this.head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size()) {
            throw new RuntimeException("index位置不合法");
        }
    }

    //查找是否包含关键字key是否在单链表当中
    public boolean contains(int key) {
        node cur = this.head;
        while (cur != null) {
            if (cur.data == key) {
                return true;
            }
            cur = cur.next;
        }
         return false;
    }

    //删除第一次出现关键字为key的节点
    public int remove(int key) {
    //找到要删除的节点的位置 进行删除
        node cur = this.head;
        while (cur != null) {
            if (cur.data == key) {
              int data = cur.data;
              if (cur == this.head) {
                  this.head = this.head.next;
                  this.head.prev = null;
              } else {
                  cur.prev.next = cur.next;
                  if (cur.next != null) {
                      cur.next.prev = cur.prev;
                  } else {
                      //考虑到最后一个位置会发生空指针异常
                      //当删除尾巴节点的时候 让tail指向cur前驱
                      this.tail = cur.prev;
                  }
              }
              return data;
            } else {
                cur = cur.next;
            }
        }
        return -1;
    }

    //删除所有值为key的节点
    public void removeAllKey(int key) {
        node cur = this.head;
        while (cur != null) {
            if (cur.data == key) {
                if (cur == this.head) {
                    this.head = this.head.next;
                    if (this.head != null) {
                        this.head.prev = null;
                    }
                } else {
                    cur.prev.next = cur.next;
                    if (cur.next != null) {
                        cur.next.prev = cur.prev;
                    } else {
                        //考虑到最后一个位置会发生空指针异常
                        //当删除尾巴节点的时候 让tail指向cur前驱
                        this.tail = cur.prev;
                    }
                }
                cur = cur.next;
            } else {
                cur = cur.next;
            }
        }
    }

    //得到单链表的长度
    public int size() {
        node cur = this.head;
        int count = 0;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
      return count;
    }

    public void display() {
     node cur = this.head;
     while (cur != null) {
         System.out.print(cur.data+" ");
         cur = cur.next;
     }
        System.out.println();
    }

    public void clear() {
        //一个一个节点进行释放
        while (this.head != null) {
            node cur = this.head.next;
            this.head.prev = null;
            this.head.next = null;
            this.head = cur;
        }
        //将尾巴也得置空
        this.tail = null;
    }
}

