package _20210317;

import java.util.HashMap;
import java.util.Map;

public class TestMap {
    public static void main(String[] args) {
        //泛型参数有两个一个是Key的类型一个是Value的类型
        //Key-Value最大的意义:就是根据key可以找到对应的Value
        //1.创建Map实例
        Map<String,String> map = new HashMap<>();
        //2.使用size获取元素个数
        System.out.println(map.size());
        //3.使用isEmpty判断是否为空
        System.out.println(map.isEmpty());
        //4.使用方法put插入元素
        map.put("秦霄贤","亲凯旋");
        map.put("刘筱亭","刘佳");
        map.put("孟鹤堂","梦祥辉");
        //5.使用size和isEmpty方法
        System.out.println(map.size());
        System.out.println(map.isEmpty());
        //6.使用get查看map中对应的元素
        System.out.println(map.get("秦霄贤"));
        //如果key不存在就返回Null
        System.out.println(map.get("周久良"));
        //getOrDefault方法: 如果要查找的元素不存在把它替换成后面的元素
        System.out.println(map.getOrDefault("何九华","何健"));
        //7.通过containsKey和containsValue查找对应的值是否存在
        //containsKey的执行较快 containsValue较慢 containsKey的效率更高
        System.out.println(map.containsKey("周久良"));
        System.out.println(map.containsValue("刘佳"));
        //8.使用isEmpty和size方法
        System.out.println(map.size());   //map中加入了三个键值对
        System.out.println(map.isEmpty());
        //9.遍历map中的每一个元素
        //Map中元素顺序和插入的顺序没有任何关系 取决于具体的实现方式
        for (Map.Entry<String,String> entry : map.entrySet()) {
            //Entry表示一个键值对 map.entrySet表示获取所有键值对
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
        //10.删除元素 删除的是key
        map.remove("刘筱亭");
        System.out.println(map.containsKey("刘筱亭"));
    }
}
