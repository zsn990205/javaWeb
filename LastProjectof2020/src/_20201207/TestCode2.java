package _20201207;

public class TestCode2 {
    public static void main(String[] args) {
        CodeTest2 linkedList = new CodeTest2();
        System.out.println("开始测试删除所有数字");
        linkedList.addLast(2);
        linkedList.addLast(2);
        linkedList.addLast(6);
        linkedList.addLast(2);
        linkedList.addLast(5);
        linkedList.removeAllKey(2);
        linkedList.display();
    }

    public static void main3(String[] args) {
        CodeTest2 linkedList = new CodeTest2();
        System.out.println("开始测试随意插入");
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(5);
        linkedList.remove(2);
        linkedList.display();
    }

    public static void main2(String[] args) {
        CodeTest2 linkedList = new CodeTest2();
        System.out.println("开始测试随意插入");
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(5);
        linkedList.addIndex(0,100);
        linkedList.addIndex(5,1000);
        linkedList.display();
        System.out.println();
        System.out.println(linkedList.contains(1));
        System.out.println(linkedList.size());
    }

    public static void main1(String[] args) {
        CodeTest2 linkedList = new CodeTest2();
//        System.out.println("开始测试头插法");
//        linkedList.addFirst(1);
//        linkedList.addFirst(2);
//        linkedList.addFirst(3);
//        linkedList.addFirst(4);
//        linkedList.addFirst(5);
//        linkedList.display();
//        System.out.println();
        System.out.println("开始测试尾插法");
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(5);
        linkedList.display();
    }

}
