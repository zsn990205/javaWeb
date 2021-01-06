package _20201222;

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

public class CodeTest3 {
    //[合并两个有序链表]
    //思路:
    //1.定义一个新的头节点 他不动只是返回的时候使用
    //2.定义一个tmp节点指向头节点 他移动
    //3.两个链表的值进行比较 当任何一个链表为空的时就可以直接将另一个不为空的链表并入后面
    //返回的应该是头结点的下一个节点而不是头节点 头节点没有保存任何数据
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newHead = new ListNode(1); //就只是一个头节点而已没别的意思
        ListNode tmp = newHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tmp.next = l1;
                l1 = l1.next;
                tmp = tmp.next;
            } else {
                tmp.next = l2;
                l2 = l2.next;
                tmp = tmp.next;
            }
        }
        //程序走到这个位置 不是l1为空就是l2为空
        if (l1 == null) {
            tmp.next = l2;
        }
        if (l2 == null) {
            tmp.next = l1;
        }
        return newHead.next;
    }
    //[输出链表的倒数第k个节点]
    //思路:
    //1.定义一个fast和slow指针
    //2.fast走k-1步之后 slow和fast开始一起走
    //当fast到终点的时候slow所指向的位置就是倒数的第k个节点
    //[注意:] 要查找的数字在不在链表总数范围内
    public ListNode FindKthToTail(ListNode head,int k) {
        if (k <= 0 || head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (k-1 > 0) {
            if (fast.next != null) {
                fast = fast.next;
                k--;
            } else {
                System.out.println("要查找的倒数第k个数不存在");
                return null;
            }
        }
//        for (int i = 0; i < k-1; i++) {
//            if (fast.next != null) {
//                fast = fast.next;
//            } else {
//        //当fast在中途就为空 即:链表中只有3个数但你要找的是倒数第4个数字
//                System.out.println("要查找的倒数第k个数不存在");
//                return null;
//            }
//        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
    //[返回链表的中间节点]
    //思路:
    //1.定义一个fast和slow指针
    //2.fast一次两步走 slow一次一步走
    //当fast到达终点的时候slow所指向的位置一定是中间位置
    public ListNode middleNode(ListNode head) {
       ListNode fast = head;
       ListNode slow = head;
       while (fast != null && fast.next != null) {
           fast = fast.next.next;
           slow = slow.next;
       }
       //到这个位置的时候 fast.next==null也就是走到了链表最后一个位置
       //即就是slow也到了中间的位置
       return slow;
    }
    //[反转单链表]
    //思路:
    //1.定义一个prev和newHead最后返回的是newHead
    //2.反转链表 主要就是找他的前一个
    public ListNode reverseList(ListNode head) {
       ListNode cur = head;
       ListNode newHead = null;
       ListNode prev = null;
       while (cur != null) {
           ListNode curNext = cur.next;
           if (curNext == null) {
           //也就是说此时cur已经到最后一个元素了 此时的位置就是新头结点的位置
               newHead = cur;
           }
           cur.next = prev;
           prev = cur;
           cur = curNext;
       }
        return newHead;
    }

    //[删除链表等于给定值的元素]
    //思路:(大致等同于删除单链表中所有数字相同的元素)
    // 建立一个节点作为哨兵节点
    // 遍历整个链表找到要删除的元素的前一个 修改其指针即可
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode newHead = new ListNode(0);
        newHead.next = head;  //将哨兵和头节点连接起来
        ListNode prev = newHead;
        ListNode cur = head;
        while (cur != null) {
           if (cur.val == val) {
               prev.next = cur.next;
           } else {
               prev = cur;
           }
           cur = cur.next;
        }
       return newHead.next;
    }
}
