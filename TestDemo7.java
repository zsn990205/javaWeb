


public class TestDemo {

    public Node mergeTwoLists(Node headA, Node headB) {
        Node node =new Node(1);
        Node tmp=node;
        while(headA!=null && headB!=null) {
            if(headA.data<headB.data) {
                tmp.next=headA;
                tmp=tmp.next;
                headA=headA.next;
            } else {
                tmp.next=headB;
                tmp=tmp.next;
                headB=headB.next;
            }
        }
        if(headA!=null) {
            tmp.next=headA;
        } if(headB!=null) {
            tmp.next=headB;
        }
        return node.next;
    }
    public static void create(Node headA,Node headB) {
        headA.next=headB.next.next;
    }

    public static  Node getIntersectionNode(Node headA,Node headB) { //相交链表概念
        Node pl = headA;
        Node ps = headB;
        int lenA = 0;
        int lenB = 0;
        if (headA == null && headB == null) {
            return null;
        }
        while (pl != null) {
            lenA++;
            pl = pl.next;
        }
        while (ps != null) {
            lenB++;
            ps = ps.next;
        }
        pl = headA;
        ps = headB;
        int len=lenA-lenB;
        if (len < 0) {
            ps = headA;
            pl = headB;
            len = lenB - lenA;
        }
        for (int i = 0; i < len; i++) {
            pl = pl.next;
        }
        while (pl!=ps && ps != null && pl != null ) {
            pl = pl.next;
            ps = ps.next;
        }
        if (pl == ps && ps != null) {
            return pl;
        }
        return null;
    }

    public static void main(String[] args) {
        myLinklist myLinklist=new myLinklist();
        myLinklist.addLast(10);
        myLinklist.addLast(20);
        myLinklist.addLast(50);
        myLinklist.addLast(10);
        myLinklist.display();
        System.out.println();
        myLinklist myLinklist2=new myLinklist();
        myLinklist2.addLast(10);
        myLinklist2.addLast(5);
        myLinklist2.addLast(2);
        myLinklist2.addLast(6);
        myLinklist2.addLast(2);
        myLinklist2.display();
        System.out.println();
        System.out.println("=====================");
        create(myLinklist.head, myLinklist2.head);
        System.out.println();
        Node ret=getIntersectionNode(myLinklist.head, myLinklist2.head);
        System.out.println(ret.data);


    }
    public static void main6(String[] args) {
        myLinklist myLinklist=new myLinklist();
        myLinklist.addLast(10);
        myLinklist.addLast(20);
        myLinklist.addLast(50);
        myLinklist.addLast(10);
        myLinklist.display();
        System.out.println(myLinklist.chkPalindrome());
    }
    public static void main5(String[] args) {
        myLinklist myLinklist=new myLinklist();
        myLinklist.addLast(10);
        myLinklist.addLast(10);
        myLinklist.addLast(10);
        myLinklist.addLast(30);
        myLinklist.addLast(40);
        myLinklist.display();
        System.out.println();
        myLinklist.deleteDuplication();
        myLinklist.display();
    }
    public static void main4(String[] args) {
        myLinklist myLinklist=new myLinklist();
        myLinklist.addFirst(10);
        myLinklist.addFirst(20);
        myLinklist.addFirst(10);
        myLinklist.addFirst(40);
        myLinklist.addFirst(10);
        Node res=myLinklist.FindKthToTail(3);
        System.out.println(res.data);
    }
    public static void main3(String[] args) {
        myLinklist myLinklist=new myLinklist();
        myLinklist.addFirst(10);
        myLinklist.addFirst(20);
        myLinklist.addFirst(10);
        myLinklist.addFirst(40);
        myLinklist.addFirst(10);
        Node ret2=myLinklist.midNode();
        System.out.println(ret2.data);
    }
    public static void main2(String[] args) {
        myLinklist myLinklist=new myLinklist();
        myLinklist.addFirst(10);
        myLinklist.addFirst(20);
        myLinklist.addFirst(10);
        myLinklist.addFirst(40);
        myLinklist.addFirst(10);
        myLinklist.display();
        System.out.println();
        Node ret= myLinklist.reverse(); //注意这是一个引用 你咋能直接打印嘞
        myLinklist.display2(ret);


    }
    public static void main1(String[] args) {
        myLinklist myLinklist=new myLinklist();
        myLinklist.addFirst(10);
        myLinklist.addFirst(20);
        myLinklist.addFirst(10);
        myLinklist.addFirst(40);
        myLinklist.addFirst(10);
        myLinklist.display();
        System.out.println();
        myLinklist myLinklist2=new myLinklist();
        myLinklist2.addLast(10);
        myLinklist2.addLast(2);
        myLinklist2.display();
        System.out.println();
        myLinklist.addIndex(3,2);
        myLinklist.display();
        System.out.println();
        System.out.println(myLinklist.size());
        System.out.println(myLinklist.contains(20));
        System.out.println();
        myLinklist.removeKey(20);
        myLinklist.display();
        System.out.println();
        myLinklist.removeAllkey(10);
        myLinklist.display();

    }
}
