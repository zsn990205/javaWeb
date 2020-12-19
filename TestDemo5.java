import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

class Person {
    //类名遵循大驼峰
    public String name; //属性 也称成员变量
    public int age;
    public static int count=0; //静态成员属性 不属于对象 属于类本身
    public final int SIZE=0;
    public static final int SIZE2=100; //定义常量一般加上static
    public static void fun() {
        System.out.println("static,fun");
    }

    public void eat() {
        System.out.println("吃饭");
    }

    public void sleep() {
        System.out.println("睡觉");
    }
    public void show() {
        System.out.println("我叫"+name+"今年"+age+"岁");
    }
}
 public class TestDemo {
    public static void main(String[] args) {
         Person person=new Person(); //Person是一个类名
        person.name="didi";
        System.out.println(person.name);;
        person.age=10;
        System.out.println(person.age);
        Person person2=new Person();
        person2.name="曹操";
        person2.age=20;
        System.out.println(person2.name);
        System.out.println(person2.age);
        person.eat();
        person.sleep();
        person.show();
        person2.show();
        System.out.println("============================");
        System.out.println(person.SIZE); //有static的打印方式 用类名访问 静态的不依赖对象
        System.out.println(person2.SIZE);
    }

}
