package _prepareAgo;

public class TestLinkedList {
    public Node mergeTwoLists(Node headA, Node headB) {
       //先定义一个newHead
        Node newHead = new Node(1);
        Node tmp = newHead;
        while (headA != null && headB != null) {
            if (headA.data < headB.data) {
                tmp.next = headA;
                tmp = tmp.next;
                headA = headA.next;
            } else {
                tmp.next = headB;
                tmp = tmp.next;
                headB = headB.next;
            }
        }
        if (headA != null) {
            tmp.next = headA;
        }
        if (headB != null) {
            tmp.next = headB;
        }
        return newHead.next;
    }

    public Node getIntersectionNode(Node headA, Node headB) {
        //1.先计算出两个链表的长度并求出差值
        //2.让长的单链表走差值步 然后两个一起走相遇即交点
        //计算两个链表的长度
        Node pL = headA;
        Node pS = headB;
        int lenA = 0;
        int lenB = 0;
        while (pL != null) {
            lenA++;
            pL = pL.next;
        }
        while (pS != null) {
            lenB++;
            pS = pS.next;
        }
        //到这一行 pL指向null pS指向null
        pL = headA;
        pS = headB;
        int len = lenA - lenB;
        if (len < 0) {
            pL = headB;
            pS = headA;
            len = lenB - lenA;
        }
        //让长的走差值步
        for (int i = 0; i < len; i++) {
            pL = pL.next;
        }
        //两个接下来一起走
        while (pL != pS) {
            pL = pL.next;
            pS = pS.next;
        }
        if (pL != null) {
        //在这个If语句中隐含的含义:pL!=null pS==pL&&pS!=null
            return pS;
        }
        return null;
    }

    public static void main5(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(2);
        linkedList.addLast(1);
        linkedList.display();
        System.out.println();
        boolean ret = linkedList.chkPalindrome();
        System.out.println(ret);
    }

    public static void main4(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(2);
        linkedList.addLast(1);
        linkedList.display();
        System.out.println();
        Node ret = linkedList.partition(2);
        linkedList.display2(ret);
    }

    public static void main3(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(5);
        linkedList.addLast(6);
        linkedList.display();
        System.out.println();
        Node ret = linkedList.reverseList();
        linkedList.display2(ret);
    }

    public static void main2(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.addLast(2);
        linkedList.addLast(1);
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(2);
        linkedList.removeAllKey(2);
        linkedList.display();
    }

    public static void main1(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(5);
        linkedList.addLast(6);
        linkedList.display();
        System.out.println();
        System.out.println(linkedList.contains(50));
        System.out.println(linkedList.size());
        linkedList.addIndex(6,25);
        linkedList.display();
        System.out.println();
        linkedList.remove(25);
        linkedList.display();
    }
}
