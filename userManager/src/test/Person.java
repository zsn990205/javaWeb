package test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Person {
    private int id;
    private String name;
    private String password;

    public Person() {
        super();
    }

    public Person(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) throws JsonProcessingException {
        //对象-->字符串
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = new Person(1,"zz","123");
        String ret = objectMapper.writeValueAsString(person);
        System.out.println("ret: "+ret);
    }
}
