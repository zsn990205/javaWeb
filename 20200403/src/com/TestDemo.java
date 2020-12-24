package com;

/*
接口
关键字是 interface
用implements实现多继承 必须含有自己的构造方法和接口的重写方法


 */
class Animal {
    protected String name;
    public Animal(String name) {
        this.name = name;
    }
}
interface IFlying {
    void fly();
}
interface IRunning {
    void run();
}
interface ISwimming {
    void swim();
}
class Cat extends Animal implements IRunning {
    public Cat(String name) {
        super(name);
    }
    @Override
    public void run() {
        System.out.println(this.name + "正在用四条腿跑");
    }
}
class Fish extends Animal implements ISwimming {
    public Fish(String name) {
        super(name);
    }
    @Override
    public void swim() {
        System.out.println(this.name + "正在用尾巴游泳");
    }
}
class Frog extends Animal implements IRunning, ISwimming { //两栖动物 实现了两个接口
    public Frog(String name) {
        super(name);
    }
    @Override
    public void run() {
        System.out.println(this.name + "正在往前跳");
    }
    @Override
    public void swim() {
        System.out.println(this.name + "正在蹬腿游泳");
    }
}
class Duck extends Animal implements IRunning, ISwimming, IFlying { //海陆空均可
    public Duck(String name) {
        super(name);
    }
    @Override
    public void fly() {
        System.out.println(this.name + "正在用翅膀飞");
    }
    @Override
    public void run() {
        System.out.println(this.name + "正在用两条腿跑");
    }
    @Override
    public void swim() {
        System.out.println(this.name + "正在漂在水上");
    }
}
public  class  TestDemo {
    public  static void walk(IRunning running) {
    running.run();
    }
    public static void swim(ISwimming swimming) {
        swimming.swim();
    }
    public static void main(String[] args) {
  Cat cat=new Cat("折耳");
  cat.run();
  Frog frog=new Frog("哇哦");
  frog.swim();

    }
        }