package _20210317;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class TestCollection {
    public static void main(String[] args) {
        //这个代码发生了向上转型
        //<String>表示泛型参数 Collection集合类中包含若干个String对象
        //1.创建Collection
        Collection<String> collection = new ArrayList<>();
        //补充
        //求数组元素个数的时候 数组.length 不带括号
        //字符串获取字符个数.length() 带括号
        //集合获取元素格式.size() 带括号
        //2.使用Size方法
        System.out.println(collection.size());
        //3.使用isEmpty方法
        System.out.println(collection.isEmpty());
        System.out.println("======现在开始插入新元素=====");
        //4.使用add方法插入元素
        collection.add("我");
        collection.add("爱");
        collection.add("java");
        //5.使用size和isEmpty方法
        System.out.println(collection.size());
        System.out.println(collection.isEmpty());
        //6.查看数组中的每一个元素
        //法一] 可以toArray把Collection把转化成数组得到的是object[]
        Object[] object = collection.toArray();
        System.out.println(Arrays.toString(object));
        //法二] 使用for each()遍历循环
        for (String s : collection) {
            //s指向collection中的每一个字符串
            //打印出来的不是数组是每个字符串
            System.out.println(s);
        }
        //7.使用contains方法判定元素是否存在
        System.out.println(collection.contains("wo"));
        //8.删除元素
        System.out.println("=====要开始删除元素了=====");
        collection.remove("我");
        for (String s : collection) {
            System.out.println(s);
        }
        //9.清空所有元素
        collection.clear();
        for (String s : collection) {
            System.out.println(s);
        }
    }
}
