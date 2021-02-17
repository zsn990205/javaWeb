package _20210106;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class codeTest4 {
    //[将所有小于x的结点排在其余结点之前且不改变原来的数据顺序返回重新排列后的链表的头指针]
    //思路:
    //1.bs表示第一段数据的开始 be表示第一段数据的结束
    //2.定义一个cur指针从头开始走 cur的值和已给的值进行比较 <的放在bs-be区间
    //当bs是空的时候 就是第一次插入节点 [>的雷同]
    //否则是第二次插入节点 be.next是cur的值 be后移
    //3.返回bs即可
    //[注意: ] 当bs为空直接返回as
    //最后ae可能不为空必须把ae.next置为空 不然会死循环  be和as要连在一起
    public ListNode partition(ListNode pHead, int x) {
       ListNode as = null;
       ListNode ae = null;
       ListNode bs = null;
       ListNode be = null;
       ListNode cur = pHead;
       while (cur != null) {
           if (cur.val < x) {
               if (bs == null) {
                   bs = cur;
                   //此时只有一个节点
                   be = bs;
               } else {
                   //插入第一个节点的时候 bs和be在同一个位置
                   //当插入第二个节点的时候 be的下一个节点就是cur指向的元素
                   be.next = cur;
                   //be始终指向最后一个数字 所以指针后移
                  be = be.next;
               }
           }
           else {
               if (as == null) {
                   as = cur;
                   ae = as;
               } else {
                   ae.next = cur;
                   ae = ae.next;
               }
           }
       //每走一次cur指针后移
           cur = cur.next;
       }
       if (bs == null) {
           return as;
       }
       be.next = as;
       if (as != null) {
       //防止进入死循环
           ae.next = null;
       }
       return bs;
    }
}
