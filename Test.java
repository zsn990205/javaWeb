public class Test {
    private int[] elem;//null
    private int usedSize;//0

    //设置默认容量
    private static final int DEFAULT_SIZE = 10;

    public Test() {
        this.elem = new int[DEFAULT_SIZE];
        this.usedSize = 0;
    }


public void add(int pos,int data) {
    if(isFull()) {
        System.out.println("满了");
        return;
    }
    for(int i=usedSize-1;i>pos;i--) {
        this.elem[i+1]=this.elem[i];
        this.elem[pos]=data;
        usedSize++;
    }
}
        public boolean isFull() {
        if(this.usedSize == this.elem.length) {
        return true;
        }
        return false;
        }
        public boolean isEmpty() {
        return true;
        }
        public boolean contains(int toFind) { //是否包含某个元素
        if(this.elem==null) {
            System.out.println("空");
        }
        for(int i=0;i<=this.usedSize;i++) {
            if(this.elem[i]==toFind) {
                return true;
            }
        } return false;
        }
        public int search(int toFind) { //某个元素位置
        for(int i=0;i<=usedSize;i++) {
            this.elem[i]=toFind;
            return i;
        }
        return -1;
        }
        public int getPos(int pos ) { //获取pos位置元素
        if(isEmpty()) {
            System.out.println("空");
        }
        if(pos<0 || pos>this.usedSize) {
            System.out.println("pos位置不存在");
        }
        return this.elem[pos];
        }

        public void remove(int key) { //删除第一次出现的关键字key
        int index=search(key);
        if(index == -1) {
            return;
        }
          //从key的位置开始算减 把后面的数字赋值到他前面一个数字上去
         for(int i=index;i<this.usedSize-1;i++) {
             this.elem[i]=this.elem[i+1];
         } this.usedSize=this.usedSize-1;
        }


}
