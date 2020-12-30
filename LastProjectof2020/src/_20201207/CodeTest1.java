package _20201207;

import java.util.Arrays;

//测试顺序表(底层是数组)
public class CodeTest1 {
    public int[] elem;    //数组
    public int usedSize;  //数组中有效元素的个数
    public static final int a = 10; //数组中存放的变量的个数

    public CodeTest1() {
        this.elem = new int[a];
        this.usedSize = 0;
    }

    // 打印顺序表
    public void display() {
    //打印顺序表的常规步骤 遍历顺序表中的每一个元素即可
       for (int i = 0; i < this.usedSize; i++) {
           System.out.print(this.elem[i]+" ");
       }
    }

    // 在 pos 位置新增元素
    public void add(int pos, int data) {
    //1.先判断pos位置是否是合法位置
        if (isPosTrue(pos)) {
            return;
        }
    //2.当数组已经满的时候还可以对数组进行扩容从而将数字放进去
        if (this.elem.length == this.usedSize) {
            this.elem = Arrays.copyOf(this.elem,this.elem.length*2);
        }
    //3.在合法位置的前提下进行插入操作 在pos位置新增元素则pos位置之后的元素均向后移动一个
        for (int i = pos; i <= this.usedSize; i++) {
            this.elem[i+1] = this.elem[i];
        }
        this.elem[pos] = data;
        this.usedSize++;
    }

    private boolean isPosTrue(int pos) {
        if (pos < 0 || pos > this.usedSize) {
            return false;
        }
        return true;
    }

    // 判定是否包含某个元素
    public boolean contains(int toFind) {
    //遍历数组一个个查查到就是true 查不到就是false
        for (int i = 0; i < this.usedSize; i++) {
            if (this.elem[i] == toFind) {
                return true;
            }
        }
        return false;
    }

    // 查找某个元素对应的位置
    public int search(int toFind) {
    //同上 找到元素返回下标 找不到返回-1
        for (int i = 0; i < this.usedSize; i++) {
            if (this.elem[i] == toFind) {
                return i;
            }
        }
        return -1;
    }

    // 获取 pos 位置的元素
    public int getPos(int pos) {
    //$$:此顺序表底层数组下标0-8 但是有效长度是8+1=9 所以当有效pos是9的时候程序就该报错了
    //同样是遍历数组操作
    //要判别数组是否为空
        if (this.usedSize == 0) {
            throw new RuntimeException("数组为空,无法获取pos位置元素");
        }
        if (pos < 0 || pos >= this.usedSize) {
            throw new RuntimeException("pos位置不合法请重新输入");
        }
        return this.elem[pos];
    }

    // 给 pos 位置的元素设为 value
    public void setPos(int pos, int value) {
    //$$:此顺序表底层数组下标0-8 但是有效长度是8+1=9 所以当有效pos是9的时候程序就该报错了
    //1.判断数组是否为空 判断pos位置是否合法
    //2.遍历数组将pos位置设为val即可
        if (pos < 0 || pos >= this.usedSize) {
            throw new RuntimeException("pos位置不合法请重新输入");
        }
        if (this.usedSize == 0) {
            throw new RuntimeException("数组为空,无法获取对应的pos位置的下标");
        }
        this.elem[pos] = value;
    }

    //删除第一次出现的关键字key
    public void remove(int toRemove) {
    //1.找到第一次出现的关键字下标
        int index = search(toRemove);
        if (index == -1) {
            throw new RuntimeException("你要删除的数字不存在");
        }
    //2.对他进行删除 删除的步骤是将pos后面的元素向前移动
        for (int i = index; i < this.usedSize; i++) {
            this.elem[i] = this.elem[i+1];
        }
        this.usedSize--;
    }

    // 获取顺序表长度
    public int size() {
        if (this.usedSize != 0) {
            return this.usedSize;
        }
        return 0;
    }

    // 清空顺序表
    public void clear() {
       this.usedSize = 0;
    }

    public static void main(String[] args) {
        CodeTest1 array = new CodeTest1();
        for (int i = 0; i < 10; i++) {
            array.add(i,i);
        }
        array.display();
        System.out.println();
        System.out.println("开始测试删除第一个出现数字的功能");
        array.remove(0);
        array.display();
        System.out.println("开始测试数组中有效元素的个数");
        System.out.println(array.size());
        System.out.println("开始测试数组的清除功能");
        array.clear();
        System.out.println("数组清除完毕");
        array.display();
    }

    public static void main2(String[] args) {
        CodeTest1 array = new CodeTest1();
        for (int i = 0; i < 10; i++) {
            array.add(i,i);
        }
        array.display();
        System.out.println();
        System.out.println("开始测试获取pos位置元素的功能");
        System.out.println(array.getPos(8));
        System.out.println("开始测试将数组pos位置的值修改为value");
        array.setPos(8,100);
        array.display();
    }

    public static void main1(String[] args) {
        CodeTest1 array = new CodeTest1();
        for (int i = 0; i < 10; i++) {
            array.add(i,i);
        }
        System.out.println("已测试新增顺序表");
        array.display();
        System.out.println();
        System.out.println("开始测试查找元素功能");
        System.out.println(array.contains(100));
        System.out.println("开始测试返回下标的功能");
        System.out.println(array.search(100));
    }
}
