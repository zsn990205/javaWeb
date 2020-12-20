class listNode {
    public int val;
    public listNode next;
    public listNode prev;
    public listNode(int val) {
        this.val=val;
    }
}

public class doubleLinkedListed {
    public listNode head;
    public listNode last;
//头插法
     public void addFirst(int data) {
         listNode node = new listNode(data);
         if (this.head == null) {
             this.head = node;
             this.last = this.head;
         } else {
             node.next = this.head;
             this.head.prev = node;
             this.head = node; //node成为头节点
         }
     }

//尾插
     public void addLast(int data) {
         listNode node = new listNode(data);
         if (this.head == null) {
             this.head = node; //matter
             this.last = this.head;
         } else {
             this.last.next = node;
             node.prev = this.last;
              this.last=node;
         }
     }

//任意位置插入,第一个数据节点为0号下标
  public void addIndex(int index,int data){
         listNode node =new listNode(data);
         listNode cur=this.head;
         if(index<0 || index>size()) {
             throw new RuntimeException("没有这个节点");
      }
         while(index>0) {
             cur=cur.next;
             index--;
         }
         if(index==0) {
             addFirst(data);
             return;
      } if(index>size()) {
             addLast(data);
             return;
      }  node.next=cur; //
         node.prev=cur.prev;
         cur.prev=node;
         cur.prev.next=node;
      }

//查找是否包含关键字key是否在单链表当中
 public boolean contains(int key){
         listNode cur=this.head;
         while(cur!=null) {
             if(cur.val==key) {
                 return true;
             } cur=cur.next;
         }
      return false;
    }
//删除第一次出现关键字为key的节点
 public void remove(int key){
     listNode cur = this.head;
     while (cur != null) {
         if (cur.val == key) {
             if (cur == this.head) { //
                 this.head = this.head.next;
                 this.head.prev = null;
             } else {
                 cur.prev.next = cur.next;
                 cur.next.prev = cur.prev;
             }

             if (this.last.val == key) {
                 this.last = this.last.prev;
                 this.last.next = null;
             }  return;
         } cur=cur.next;
     }
 }



    //删除所有值为key的节点
 public void removeAllKey(int key) {
     listNode cur = this.head.next;
     while (cur != null) {
         if (cur.val == key) {
             if (cur == this.head) { //
                 this.head = this.head.next;
                 this.head.prev = null;
             } else {
                 cur.prev.next = cur.next;
                 cur.next.prev = cur.prev;
             }

            if (this.last.val == key) {
                 this.last = this.last.prev;
                 this.last.next = null;
             }
         } cur=cur.next;
     }
 }

//得到单链表的长度
    public int size(){
   listNode cur=this.head;
   int count=0;
   while(cur!=null) {
       cur=cur.next;
       count++;
   }  return count;
    }
    public void display(){
   listNode cur=this.head;
   while(cur!=null) {
       System.out.println(cur.val+" ");
       cur=cur.next;
   }
    }
   public void clear(){
    this.head=null;
    this.last=null;
    }
}
