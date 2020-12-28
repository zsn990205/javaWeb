package Frank;


public class Duck2 {
    private String name;
    private Integer age;
    private Duck2 next;

    @Override
    public String toString() {
        return "Duck3{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", next=" + next +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Duck2 getNext() {
        return next;
    }

    public void setNext(Duck2 next) {
        this.next = next;
    }
}
