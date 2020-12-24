package TestDemo2;
/*
这是关于多态的一系列问题
1.多态中父类只需要有一个方法 至于这个方法要干什么完全可以不care
2.在子类中 子类要继承父类的构造方法首先需要extends关键字
接着要在类内写上"重写!"方法 fn+alt+f12中的重写 重写内部写上你想打印的内容
3.在main函数中 可以通过new的形式打印 然后.draw方法
4.第二中方法是:写一个关于打印的方法 在main函数里传参
5.第三种方法是:写一个数组 通过for each循环打印


 */

class shape {
    public void draw() {

    }
}
class circle extends shape {
    @Override
    public void draw() {
            System.out.println("这是一个⚪");
        }
    }

class rectangle extends shape {
    @Override
    public void draw() {
            System.out.println("这是一个长方形");
        }
    }

public class TestDemo2 {

    public static void main(String[] args) {
        shape[] shapes= {new circle(),new rectangle()};
        for (shape shape:shapes)
              {
            shape.draw();
        }
    }
    public static void drawMap(shape shape) {
        shape.draw();
    }
    public static void main2(String[] args) {
        shape shape1=new circle();
        shape shape2=new rectangle();
        drawMap(shape1);
        drawMap(shape2);
    }
    public static void main1(String[] args) {
        shape shape1=new circle();
        shape1.draw();
        shape shape2=new rectangle();
        shape2.draw();
    }
}
