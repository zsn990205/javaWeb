package bit;
class Animal {
   protected String name;

    public Animal(String name) {
        this.name = name;
    }
}
interface Running {
    void run();
}
interface Swimming {
    void swim();
}
interface Flying {
    void fly();
}
class Cat extends Animal implements Running {
    public Cat(String name) {
        super(name);
    }
    @Override
    public void run() {
        System.out.println(this.name+"正在用四条腿跑");
    }
}
class Frog extends Animal implements Swimming,Running {
    public Frog(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(this.name+"正在用四条腿跑");
    }

    @Override
    public void swim() {
        System.out.println(this.name+"正在往前蛙泳");
    }
}
class Duck extends Animal implements Running,Swimming,Flying {
    public Duck(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(this.name+"正在用四条腿跑");
    }

    @Override
    public void swim() {
        System.out.println(this.name+"正在往前蛙泳");
    }

    @Override
    public void fly() {
        System.out.println(this.name+"正在天上飞");
    }
}
public class TestDemo2 {
    public static void walk(Running running) {
        running.run();
    }

    public static void swim(Swimming swimming) {
        swimming.swim();
    }

    public static void main(String[] args) {
      Cat cat=new Cat("花花");
      cat.run();
      Frog frog=new Frog("哇哇");
      frog.swim();
    }
}