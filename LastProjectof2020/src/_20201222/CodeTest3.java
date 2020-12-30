package _20201222;

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

public class CodeTest3 {
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
