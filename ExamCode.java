package _TestClassCode;

import java.util.Arrays;
class Father {
    public String name;
    public String food;
    public void eat() {
        System.out.println(this.name+"要吃");
    }
}
class Son extends Father {
    @Override
    public void eat() {
        super.eat();
        System.out.println(this.name+"要吃"+this.food);
    }
}

class Student {
    public int sno;
    public String sname;

    public Student(int so, String name) {
        this.sno = so;
        this.sname = name;
    }
}
class A {
    static int data_a = 3;
}
class B extends A {
    static int data_a = 3;
}
class C extends B {
    public void print_data() {
        System.out.println(data_a);
        System.out.println(A.data_a);
        System.out.println(B.data_a);
    }
}
class Cylinder {
    private double r;
    private int h;
    private double pi = 3.14;
    public String color;
    public Cylinder() {
        this(1,1,1);
    }

    public Cylinder(double r, int h, double pi) {
        this.r = r;
        this.h = h;
        this.pi = pi;
    }
}
public class ExamCode {
    public static void main2(String[] args) {
        int count = 0;
        for (int i = 2000; i <= 3000; i += 4) {
            int year = i;
            if (isLoopYear(year)) {
                count++;
                if (count <= 10) {
                    System.out.print(year+" ");
                    //continue;
                } else {
                    break;
                }
            }
        }
    }

    private static boolean isLoopYear(int year) {
        if (year % 400 == 0 || year % 4 == 0 && year % 100 != 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Father father = new Son();
        father.eat();
    }

    public static void main3(String[] args) {
        int[] arr = {1,2,3,4,5,6};
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
        System.out.println("===第二种打印方式==");
        System.out.println(Arrays.toString(arr));
    }
    public static void main1(String[] args) {
      C c = new C();
      c.print_data();
    }
}
