import java.util.Arrays;

/*

关于排序的接口
1.要有定义的构造方法
2.接口是Comparable< 类名 >
3.重写方法要加上 toString
4.数组的排序是 Arrays.sort


 */


class Student implements Comparable<Student> {
public int age;
public int score;
public String name;
//重写比较方法 从大到小的话 反着减
    @Override
    public int compareTo(Student o) {
        return this.age-o.age;//从小到大排序
    }
 //必须有这三个的构造方法
    public Student(int age, int score, String name) {
        this.age = age;
        this.score = score;
        this.name = name;
    }
 //!!! 没有它打印出来的就是一堆哈希值 toString方法
    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", score=" + score +
                ", name='" + name + '\'' +
                '}';
    }
}

    public class TestDemo2 {
        public static void main(String[] args) {
            Student[] student=new Student[3];
            student[0]=new Student(10,99,"曹操");
            student[1]=new Student(20,91,"曹丕");
            student[2]=new Student(30,92,"曹植");
           Arrays.sort(student);
            System.out.println(Arrays.toString(student));
        }
}




