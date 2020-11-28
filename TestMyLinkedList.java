package _prepareAgo;


public class TestMyLinkedList {
    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addFirst(1);
        myLinkedList.addFirst(2);
        myLinkedList.addFirst(2);
        myLinkedList.addFirst(1);
        myLinkedList.addFirst(1);
        myLinkedList.display();
        myLinkedList.clear();
        System.out.println("==============");
        myLinkedList.display();
    }

    public static void main3(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addFirst(1);
        myLinkedList.addFirst(2);
        myLinkedList.addFirst(2);
        myLinkedList.addFirst(1);
        myLinkedList.addFirst(1);
        myLinkedList.display();
        myLinkedList.removeAllKey(1);
        myLinkedList.display();
    }

    public static void main2(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addFirst(1);
        myLinkedList.addFirst(3);
        myLinkedList.addFirst(5);
        myLinkedList.display();
        myLinkedList.addIndex(0,100);
        myLinkedList.display();
        myLinkedList.addIndex(4,10);
        myLinkedList.display();
        myLinkedList.addIndex(2,1000);
        myLinkedList.display();

    }

    public static void main1(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addFirst(1);
        myLinkedList.addFirst(2);
        myLinkedList.addFirst(5);
        myLinkedList.addFirst(6);
        myLinkedList.addFirst(7);
        myLinkedList.display();
        System.out.println(myLinkedList.size());
        System.out.println(myLinkedList.contains(2));
    }
}
