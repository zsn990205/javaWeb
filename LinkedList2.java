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
    //大致思路如下: 定义一个节点用来循环遍历整个链表找要删除的哪个元素记位del
    //找到后记录要删除的元素的前一个下标为prev
    //注意点:当将链表遍历完毕还没有找到要删除的元素 此时在往下找就会空指针异常
        if (this.head == null) {
            return;
        }
        if (this.head.data == key) {
            this.head = this.head.next;
            return;
        }
    //找到要删除的元素的前一个位置
        Node prev = searchPrev(key);
        if (prev == null) {
            System.out.println("没有要删除的这个节点");
            return;
        }
        Node del = prev.next;
        prev.next = del.next;
    }

    private Node searchPrev(int key) {
        Node cur = this.head;
        while (cur.next != null) {
            if (cur.next.data == key) {
                return cur;
            } else {
                cur = cur.next;
            }
        }
        return null;
    }

    //删除所有值为key的节点
    public void removeAllKey(int key) {
        if (this.head == null) {
            return;
        }
        Node prev = this.head;
        Node cur = prev.next;
        while (cur != null) {
            if (cur.data == key) {
                prev.next = cur.next;
                //cur = cur.next;
            } else {
               prev = cur;
                //cur = cur.next;
            }
            cur = cur.next;
        }
        if (this.head.data == key) {
            this.head = this.head.next;
        }
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
    //当head没人引用的时候 就会直接被jvm回收此时head指向的一系列元素都被回收
      this.head = null;
    }

    public Node reverseList() {
        Node cur = this.head;
        Node prev = null;
        Node newHead = null;
        while (cur != null) {
            Node curNext = cur.next;
            if (curNext == null) {
                newHead = cur;
            }
            cur.next = prev;
            prev = cur;  //prev后移
            cur = curNext;  //cur后移
        }
        return newHead;
    }

    public void display2(Node newHead) {
        Node cur = newHead;
        while (cur != null) {
            System.out.print(cur.data+" ");
            cur = cur.next;
        }
    }

    public Node middleNode() {
        Node fast = this.head;
        Node slow = this.head;
        while(fast != null && fast.next != null) {
            //只能是fast!= null在前面 不然会发生空指针异常
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public Node findKthToTail(int k) {
    //k表示倒数第几个节点
        if (k <= 0 || this.head == null) {
            System.out.println("k位置不合法 || 没有这个链表");
            return null;
        }
        Node fast = this.head;
        Node slow = this.head;
        //fast走k-1步 slow和fast一起走
//        while (k-1 > 0) {
//        //判断k位置是否合法
//            if (fast.next != null) {
//                fast = fast.next;
//                k--;
//            } else {
//                System.out.println("k位置不合法");
//                return null;
//            }
//        }
        for (int i = 0; i < k-1; i++) {
            if (fast.next != null) {
                fast = fast.next;
            } else {
                System.out.println("k位置不合法");
                return null;
            }
        }
        while (fast.next != null) {
           fast = fast.next;
           slow = slow.next;
        }
        return slow;
    }

    public Node partition(int x) {
        Node as = null;
        Node ae = null;
        Node bs = null;
        Node be = null;
        Node cur = this.head;
        while (cur != null) {
            if (cur.data < x) {
            //第一次插入数据
                if (bs == null) {
                    bs = cur;
                    be = bs;
                } else {
            //非第一次
                    be.next = cur;
                    be = be.next;
                }
            } else {
                //第一次插入数据
                if (as == null) {
                    as = cur;
                    ae = as;
                } else {
                    //非第一次
                    ae.next = cur;
                    ae = ae.next;
                }
            }
            //每循环一次cur向后移动一次
            cur = cur.next;
        }
        //循环结束
        //判断 当没有小于x的数据(即没有bs的时候)
        if (bs == null) {
        //因为题目要求是按照原来的顺序不变 重新打印链表
        //所以当bs为空的时候 ae.next一定为空不必在将其置为空
            return as;
        }
        //ae.next如果不设置为空的话就会死循环
        be.next = as;
        if (as != null) {
        //注意点:一定是as不为空 说明大于x那段存在即将其末尾引用的next置空
            ae.next = null;
        }
        return bs;
    }

    public Node duplication() {
        Node cur = this.head;
        //newHead仅仅是虚拟不动的节点 并非是最后返回的节点
        Node newHead = new Node(1);
        Node tmp = newHead;
        while (cur != null) {
            if (cur.next != null && cur.data == cur.next.data) {
                while (cur.next != null && cur.data == cur.next.data) {
                    cur = cur.next;
                }
                //让cur多走一步的原因是: 比如12228 2!=8但是2同样是重复节点必须删除
                cur = cur.next;
            } else {
                //为什么不能把tmp = newHead放在这个位置?
                //当放在这个位置 意味着每进一次else语句tmp都重新指向newHead而tmp应该是一直移动的
                tmp.next = cur;
                tmp = tmp.next;
                cur = cur.next;
            }
        }
        //当链表是12222时 链表仅有tmp一个值tmp.next不为空会死循环
        tmp.next = null;
        return newHead.next;
    }

    public boolean chkPalindrome() {
        //先判断特殊条件
        if (this.head == null) {
            return false;
        }
        if (this.head.next == null) {
        //只有一个节点一定是回文结构
            return true;
        }
        //1.先找到链表的中间位置
        Node fast = this.head;
        Node slow = this.head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //此时slow的位置即就是中间位置
        //2.找到中间位置之后改变后面几个元素的指向
        Node cur = slow.next;
        while (cur != null) {
            Node curNext = cur.next;
            cur.next = slow;
            slow = cur;
            cur = curNext;
        }
        //循环结束之后 cur指向链表的最后一个位置
        //3.将前后元素的值进行对比 看是否回文
        Node head = this.head;
        while (slow != this.head) {
            if (slow.data != this.head.data) {
                return false;
            }
            if (this.head.next == slow) {
         //当回文的结构是偶数的情况下 是这个情况
                return true;
            }
                slow = slow.next;
                this.head = this.head.next;
        }
        return true;
    }

    public boolean hasCircle() {
        //为啥不是三个四个移动 因为很慢甚至有时候根本无法重合
        if (this.head == null) {
            return false;
        }
        Node fast = this.head;
        Node slow = this.head;
        while (fast != null && fast.next != null) {
            //fast和slow必须时刻处于移动的过程才能判断
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    public Node detectCycle() {
        if (head == null) {
            return null;
        }
        Node fast = head;
        Node slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
        //有环 开始找入口点
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        //无环 直接返回空
        return null;
    }

}
