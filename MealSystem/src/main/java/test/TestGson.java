package test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//演示GSON的功能
public class TestGson {
    static class Student {
        private String name;
        private int id;
        private double score;

        public Student(String name, int id, double score) {
            this.name = name;
            this.id = id;
            this.score = score;
        }
    }
    public static void main(String[] args) {
        //1.实例化Gson对象(工厂模式)
        Gson gson = new GsonBuilder().create();
        //2.把一个对象转成json字符串
//        Student s = new Student("zsn",20,99.8);
//        String jsonString = gson.toJson(s);
//        System.out.println(jsonString);
        //3.把json字符串转回成对象
        String str = "{\"name\":\"zsn\",\"id\":20,\"score\":99.8}";
        Student s = gson.fromJson(str,Student.class);
        System.out.println(s.id+" "+s.name+" "+s.score);

    }

}
