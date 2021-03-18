package _20210318;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        //1.创建list实例
        List<String> list = new ArrayList<>();
        //2.新增元素
        list.add("java");
        list.add("python");
        list.add("c");
        //3.打印整个list
        System.out.println(list);
        //4.按照下标来访问元素
        System.out.println(list.get(0));
        //5.根据下标来修改元素  下标不能越界
        list.set(0,"php");
        System.out.println(list);
        //6.使用for来循环访问每个元素
        for (String s : list) {
            System.out.print(s+" ");
        }
        System.out.println();
        //法二 list继承collection所以也继承了collection的方法
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i)+" ");
        }
        System.out.println();
        //7.使用sublist获取子序列
        System.out.println(list.subList(0, 2));
        //8.可以使用构造方法构造出新的list对象
        List<String> list2 = new ArrayList<>(list); //list2是把list中的内容拷贝一份
        System.out.println(list2);
        //验证是不是深拷贝 需要给list泛型参数填一个可变对象的类型才可以
        list.set(0,"C#");
        System.out.println(list2);
    }
}
