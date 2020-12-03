package _20201122;

import java.util.Arrays;

abstract class Animal {
    public String name;
    public String color;
    abstract public void hobby();
}
class bird extends Animal {
    @Override
    public void hobby() {
        System.out.println(this.name+" "+this.color);
    }
}
public class TestText {
    public static void main(String[] args) {
        int a = 10;
        int b = (a++) * 10;
        int b2 = (++a) * 10;
        System.out.println(b);
        System.out.println(b2);
    }

    public static void main1(String[] args) {
        Animal animal = new bird();
        animal.color = "红色";
        animal.name = "小鸭子";
        animal.hobby();
        int[] arr = {1,2,3,4};
        System.out.println(Arrays.toString(arr));
    }
}
