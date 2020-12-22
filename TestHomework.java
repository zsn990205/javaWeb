public class TestHomework {
    public static void main(String[] args) {
        //LinkedList
        doubleLinkedList doubleLinkedList = new doubleLinkedList();
        doubleLinkedList.addLast(1);
        doubleLinkedList.addLast(2);
        doubleLinkedList.addLast(3);
        doubleLinkedList.addLast(4);
        doubleLinkedList.addLast(15);
        doubleLinkedList.addFirst(0);

        doubleLinkedList.display();
        doubleLinkedList.addIndex(0, 888);
        doubleLinkedList.display();
        doubleLinkedList.addIndex(3, 888);
        doubleLinkedList.display();
        doubleLinkedList.addIndex(8, 888);
        doubleLinkedList.display();

        doubleLinkedList.removeAllKey(888);
        doubleLinkedList.display();

    }
}