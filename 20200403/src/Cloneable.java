class Fee implements java.lang.Cloneable {
    public int money=100;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
class Person implements java.lang.Cloneable {
    public int age;
    public Fee m;

    public Person(int age) {
        this.age = age;
         m=new Fee();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person person=(Person)super. clone();
         person.m = (Fee)this.m.clone();
        return person;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                '}';
    }
}



public class Cloneable {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person person=new Person(10);
        Person person1=(Person)person.clone(); //拷贝
        System.out.println(person.m.money);
        System.out.println(person1.m.money);
        System.out.println("========================");
        person1.m.money=20;
        System.out.println(person.m.money);
        System.out.println(person1.m.money);
    }

    public static void main1(String[] args) throws CloneNotSupportedException {
        Person person=new Person(10);
        Person person1=(Person)person.clone(); //拷贝
        System.out.println(person.age);
        System.out.println(person1.age);
        System.out.println("========================");
        person1.age=20;
        System.out.println(person.age);
        System.out.println(person1.age);


    }
}
