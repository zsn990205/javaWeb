import java.util.Arrays;

class Child implements Comparable<Child> {
public String name;
public int age;
public int score;

    public Child(String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    @Override
    public int compareTo(Child o) {
        return this.age-o.age;
    }

    @Override
    public String toString() {
        return "Child{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }
}


public class Practice {
    public static void main(String[] args) {
        Child[] child=new Child[3];
        child[0]=new Child("曹操",2,99);
        child[1]=new Child("曹丕",3,99);
        child[2]=new Child("曹植",1,99);
        Arrays.sort(child);
        System.out.println(Arrays.toString(child));

    }
}
