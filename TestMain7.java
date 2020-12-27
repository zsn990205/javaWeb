
class Animal {
    public String name;

    public Animal(String name) {
        this.name = name;
    }
    protected void eat() {
        System.out.println(this.name+"正在吃");
    }
}
class Bird extends Animal{
    public int count;
     public Bird(String name) {
         super(name);
     }
     public void fly() {
         System.out.println(this.name+"正在飞");
     }
     @Override //手动@
     public void eat() { //子类的访问修饰限定符大于等于父类
         System.out.println(this.name+"正在吃米");
     }
 }


public class TestMain {
    public Animal func2() {
        //3.返回值向上转型(is a)
      Bird bird=new Bird("啊哈");
      return bird;
    }
    public  static void func1(Animal animal) {
        //2.直接传参向上转型
     animal.eat();
    }

    public static void main4(String[] args) {
        Animal animal=new Bird("芝芝");
        animal.eat();
       //instanceof 判断animal是不是bird的实例 需要时加上
        Bird bird=(Bird) animal; //强转进行向下转型
        bird.fly();
    }
    public static void main3(String[] args) {
        Bird bird=new Bird("呦呵");
        func1(bird);
    }
    public static void main(String[] args) {
        //父类引用子类叫做向上转型
        //1.直接赋值进行向上转型
        Animal animal=new Bird("芒芒");
        animal.eat();
        //animal.fly(); 向上转型需要注意的是:通过父类引用只能引用父类自己的属性或方法!!!
       //animal2.count2();
    }
    public static void main1(String[] args) {
    Animal animal=new Animal("芝芝");
    Bird bird=new Bird("也行");
    }
}
