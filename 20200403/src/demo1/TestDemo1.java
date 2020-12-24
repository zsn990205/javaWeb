package demo1;




class Base {
public int a;
public Base(int a) {
    this.a=a;
    System.out.println(6);
}
static {
    System.out.println(1);
}
{
    System.out.println(2);
    }
    public void func() {
        System.out.println("第一个");
        System.out.println(6);
    }

}
class Derive extends Base {
    public Derive(int a) {
        super(a);
        System.out.println(5);
    }
    static {
        System.out.println(3);
    }
    {
        System.out.println(4);
    }
    public void func() {
        System.out.println("第一个");
    }
}
public class TestDemo1 {
    public static void main(String[] args) {
        Derive derive=new Derive(0);

    }
    public static void main1(String[] args) {
    Base base=new Derive(1);
    base.func();
    }
}
