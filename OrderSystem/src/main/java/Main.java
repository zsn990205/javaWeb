import Frank.Duck;
import Frank.Duck2;
import Frank.DuckShop;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new
                ClassPathXmlApplicationContext("applications.xml");
        //通过bean的名称获取bean对象 bean名称就是xml中Bean的id
        //String bit = (String) context.getBean("bit");
//        String bit2 = (String) context.getBean("java.lang.String#0");
        //System.out.println(bit);
//        System.out.println(bit2);

        //通过类型获取对象 只能获取一个对象
        //String bit3 = context.getBean(String.class);
//        Duck d1 = (Duck)context.getBean("d");
//        System.out.println(d1);

//        Duck2 d2 = (Duck2)context.getBean("dk1");
//        System.out.println(d2);

        DuckShop shop = (DuckShop) context.getBean("shop");
        System.out.println(shop);

    }
}