class Money implements java.lang.Cloneable {
    public int money=15;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class People implements java.lang.Cloneable {
    public int age;
    public Money m;

    public People(int age) {
        this.age = age;
        m =new Money();
    }

    @Override
    public String toString() {
        return "People{" +
                "age=" + age +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //发生深拷贝
        People people=(People)super.clone(); //先将people克隆一份
        people.m=(Money)this.m.clone(); //将money克隆一份并且传给m
        return people;
    }

}


public class clone {

    public static void main(String[] args) throws CloneNotSupportedException {
        People people=new People(10);
        People people1=(People)people.clone();
        System.out.println(people.m.money);
        System.out.println(people1.m.money);
        System.out.println("========================");
        people1.m.money=10000;
        System.out.println(people.m.money);
        System.out.println(people1.m.money);
    }

    public static void main2(String[] args) throws CloneNotSupportedException {
        //浅拷贝
        People people=new People(10);
        People people1=(People)people.clone();
        System.out.println(people.m.money);
        System.out.println(people1.m.money);
        System.out.println("========================");
        people1.m.money=200;
        System.out.println(people.m.money);
        System.out.println(people1.m.money);
    }


    public static void main1(String[] args) throws CloneNotSupportedException {
     People people=new People(10);
      People people1=(People)people.clone(); //alt+enter选择 类型不同所以需要强转
        System.out.println(people.age);
        System.out.println(people1.age);
        System.out.println("=======================");
        people1.age=20;
        System.out.println(people.age);
        System.out.println(people1.age);
    }
}
