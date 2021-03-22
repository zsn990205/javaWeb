package _20210318;

import org.omg.CORBA.Object;

public class SeqList<E> {
    //date的类型需要在最终实例化SeqList的时候确定类型
    //E这样的泛型参数无法被实例化 因为当前不知道E是啥类型的
    //泛型参数只能是引用类型
    private E[] date = (E[])new Object[100];
    private int size;

    public void add(E elem) {
    //插入元素 elem是我要放在数组中的元素的
    //把这个元素插入到顺序表的尾端然后size++就行了
        date[size] = elem;
        size++;
    }

    public E get(int index) {
    //返回给定下标的值
        return date[index];
    }

    public static void main(String[] args) {
//        SeqList<String> seqList = new SeqList<>();
//        seqList.add("aaa");
//        seqList.add("bbb");
//        String str = seqList.get(0);
//        System.out.println(str);
//
//        SeqList<Animal> animal = new SeqList<>();
//        animal.add(new Animal());
//        animal.add(new Animal());
//        animal.add(new Animal());
//        Animal animals = animal.get(0);
        SeqList<Integer> integerSeqList = new SeqList<>();
        Integer num = new Integer(10); //装箱
        Integer num1 = Integer.valueOf(10); //装箱
        Integer num2 = 10; //可以直接把int值赋值给Integer自动装箱
//        integerSeqList.add(new Integer(1));
//        integerSeqList.add(new Integer(3));
//        integerSeqList.add(new Integer(2));

        //拆箱
        num2 = integerSeqList.get(0);
        int val = num2.intValue();  //手动拆箱
        int val2 = num2;   //自动拆箱
    }
}
