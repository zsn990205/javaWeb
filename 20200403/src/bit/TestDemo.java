package bit;

import java.awt.*;
/*
抽象类


 */

abstract class Shape {
    abstract public void draw() ;

}
class circle extends Shape {
    @Override
    public void draw() {

    }
}
class flower extends Shape {
    @Override
    public void draw() {
        System.out.println("画一朵花");
    }
}
public class TestDemo {
    public static void main(String[] args) {
    }
}
