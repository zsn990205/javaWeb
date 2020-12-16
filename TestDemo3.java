
class Book {
    private String name;
    private String author;
    private int price;
    public static void hello(){
        System.out.println("hello");
    }

    public static int size=0;
    public Book(){ //构造方法
        System.out.println("Book()");
    }
    {  //实例代码块 可以初始化非静态成员变量以及静态成员变量
        this.name="三国演义";
        this.author="罗贯中";
        int price=10;
        size=99;
        System.out.println("实例代码块");
    }
    static {  //只能初始化静态的成员变量 执行时先执行静态的但静态只执行一次
        size=969;
        System.out.println("静态代码块");
    }

    @Override //重写Object 会优先调用
    public String toString() { //打印书名 作者 价格的方法 alt+insert 选中toString
        return "Book{" +
                "书名='" + name + '\'' +
                ", 作者='" + author + '\'' +
                ", 价格=" + price +
                '}';
    }

    public Book(String name, String author, int price) { //构造方法,main函数无法new
        this.name = name;
        this.author = author; //this代表当前对象的引用!!!
        this.price = price;
        System.out.println("Book(String,String,int)"); //一般不用这些初始化 用构造方法或者get set
    }
    public void eat() {
        System.out.println("吃饭");
    }
    public String getName() {
        this.getAuthor(); //可以引用
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

public class TestDemo3 {
    //可变参数编程
    public static int sum(int ...arr) {
        int ret = 0;
        for (int x : arr) //不是数组拿不下下标 所以用for each
         {
            ret += x;
        }
          return ret;
    }
    public static void main(String[] args) {
        int []arr={1,4,6,9};
       int ret= sum(arr);
        System.out.println(ret);
    }
    public static void main4(String[] args) {
        new Book().eat();//匿名对象 只能用一次
    }
    public static void main3(String[] args) {
        Book book=new Book("三国演义","罗贯中",10);
        System.out.println(book);//打印结果为地址的哈希值 是唯一的
                                 // 加上toString可以打印出具体的属性
    }
    public static void main2(String[] args) {
        Book book = new Book();
        System.out.println(book.getName()); //获取名字
        System.out.println("=====================");
        Book book1=new Book();  //证明静态只能执行一次
    }

    private int count;

    public static void main1(String[] args) {
        Book book = new Book();
        book.setName("三国演义");
        System.out.println(book.getName());
        book.setAuthor("罗贯中");
        System.out.println(book.getAuthor());
        System.out.println(book.getName());//book.getName().sout可以直接获取
        Book book1 = null;
       // book1.hello();  //static 静态成员变量可以通过book1.hello但是没必要 程序会报警告
        System.out.println(Book.size);
        TestDemo3 test = new TestDemo3(88);
        System.out.println(test.count);
        book.eat();
    }
    TestDemo3(int a) {
        count = a;
    }
}
