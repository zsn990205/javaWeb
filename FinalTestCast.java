package _TestClassCode;
interface Film {
    public void playMovie();

}
interface Tv {
    public void Tv();
}
interface Song {
    public void song();
}
class Artist implements Film,Tv,Song {
    public String name;
    public int age;
    @Override
    public void playMovie() {
        System.out.println(this.name+"会拍电影");
    }
    @Override
    public void Tv() {
        System.out.println(this.name+"会拍电视剧");
    }
    @Override
    public void song() {
        System.out.println(this.name+"会唱歌");
    }
    public Artist(String name) {
        this.name = name;
    }
}
class underStudent {
    public String name;
    public int age;
    public underStudent(String name,int age) {
        this.name = name;
        this.age = age;
    }
    public void show() {
        System.out.println("同学"+this.name+"年龄是"+this.age);
    }
}

class bookStudent extends underStudent {
    public String institute;
    public String project;

    public bookStudent(String name, int age) {
        super(name, age);
    }

    @Override
    public void show() {
        System.out.println("本科生"+this.name+"来自"+this.institute+"学院"+this.project+"专业");
    }
}
class Baby {
    public int age;
    public String name;
    public int grade;
    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }

    public Baby() {

    }
    public Baby(String name,int grade) {
        this.name = name;
        this.grade = grade;
    }
//
//    @Override
//    public String toString() {
//        return "Baby{" +
//                "age=" + age +
//                ", name='" + name + '\'' +
//                ", grade=" + grade +
//                '}';
//    }
    public void printMessage() {
        System.out.print(this.name+this.age+"岁"+"成绩是:"+this.grade);
    }
}
abstract class shape {
    abstract public double s();
    abstract public double c();
}
class rect extends shape {
    public int a;
    public int b;
    public int c;
    @Override
    public double s() {
        double p = 0.5*(a+b+c);
        double all = p*(p-a)*(p-b)*(p-c);
        return Math.sqrt(all);
    }

    @Override
    public double c() {
        return a+b+c;
    }
    public rect() {

    }
    public rect(int a,int b,int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
 }
 class circle extends shape {
    public int r;
    public int row;

     @Override
     public double s() {
         return 1/2*r*r;
     }

     @Override
     public double c() {
         return 2*Math.PI*r;
     }
     public circle() {

     }
     public circle(int r,int row) {
         this.r = r;
         this.row = row;
     }
 }

public class FinalTestCast {
    public static void main4(String[] args) {
      rect rect = new rect(6,8,10);
      System.out.println(rect.c());
      System.out.println(rect.s());
      circle circle = new circle(2,3);
      System.out.println(circle.c());
    }

    public static void main3(String[] args) {
        Artist artist = new Artist("杨洋");
        artist.playMovie();
    }

    public static void main2(String[] args) {
        bookStudent bookStudent = new bookStudent("呵呵",23);
        bookStudent.institute = "电子信息与人工智能";
        bookStudent.project = "计算机科学与技术";
        bookStudent.show();
    }

    public static void main1(String[] args) {
        Baby baby = new Baby("小汤汤",99);
        baby.setAge(3);
        baby.getAge();
        baby.printMessage();
    }
}
