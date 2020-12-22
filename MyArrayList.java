import java.util.Arrays;

public class MyArrayList {
    public int[] elem;
    public int usedSize;//储存有效数据的个数
    public static final int DEFAULT_SIZE = 4;//增加一个属性,数组的长度

    public MyArrayList() { //构造方法
        this.elem = new int[DEFAULT_SIZE]; //或者括号里直接写10
        this.usedSize = 0;
    }

    public void display() { //打印顺序表
        for (int i = 0; i < this.usedSize; i++) {
            System.out.println(this.elem[i] + " ");
        }
        System.out.println();
    }

    public boolean isFull() { //顺序表是否为满
        if (this.usedSize == this.elem.length) {
            System.out.println("数组是满的");
            return true;
        }
        return false;
    }

    public void add(int pos, int data) { //在pos位置增添数字 有问题
        if(isFull()) {
            throw new RuntimeException("数组是满的");
        }
        check(pos);
        grow();//扩容后一定要在add后加上函数 不然扩不了
        for (int i = this.usedSize-1; i >= pos; i--) { //注意-1
            this.elem[i+1]=this.elem[i];

        }   this.elem[pos] = data;
            this.usedSize++;
    }


    public boolean isEmpty() {
        if (this.elem.length == 0) {
            throw new ArrayIndexOutOfBoundsException("数组为空");
        }
        return false;
    }


    public boolean contains(int toFind) { //判定是否包含某个元素
        if (isEmpty()) {
            return false;
        }
        for (int i = 0; i < this.usedSize; i++) {
            if (this.elem[i] == toFind) {
                return true;
            }
        }
        return false;
    }

    public int search(int toFind) { //查找某个元素对应的位置
        if (isEmpty()) {
            System.out.println("空");
        }
        for (int i = 0; i < this.usedSize; i++) {
            if (this.elem[i] == toFind) {
                return i;
            }
        }

        return -1;
    }

    public int getPos(int pos) { //获取pos位置的元素
        if (isEmpty()) {
            System.out.println("空");
        }

        check(pos);
        return this.elem[pos];
    }
    public void check(int pos) {
        if (pos < 0 || pos > this.usedSize) {
            throw new ArrayIndexOutOfBoundsException("pos位置不合法");
        }
    }

    public void remove(int key) { //删除第一次出现的关键字key 有问题
        int index = search(key);
        if (index==-1) { //因为没有这个值得时候在search函数里返回值是-1
           return;
        }
        for (int i = index; i < this.usedSize-1; i++) { //- 1 非常重要最后一个下表是长度减一
            this.elem[i] = this.elem[i + 1];
        }
        this.usedSize--;
    }
    private void grow() {

        this.elem=Arrays.copyOf( this.elem,this.elem.length*2);
    }
}
