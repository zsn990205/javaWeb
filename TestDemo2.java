import java.util.Arrays;
import java.util.Scanner;

public class TestDemo {
    //水仙花数 等
    public static void main6(String[] args) {
        for(int i=0;i<99999;i++) {
            int count=0;
            int tmp=i;
            while(tmp!=0) {
                tmp=tmp/10;
                count++; //求数字位数
            }
            tmp=i;//经过上一步位数操作tmp为0
            int sum=0;//求和
            while(tmp!=0) {
                sum+=Math.pow(tmp%10,count);
                tmp=tmp/10;
            } if(i==sum) {
                System.out.println(sum);
            }
        }
    }
    //重载
    public static int add(int n1,int n2) {
        return n1+n2;
    }
    public static double add(double a,double b) {
        return a+b;
    }

    public static void main5(String[] args) {
        Scanner scan=new Scanner(System.in);
        int n1=scan.nextInt();
        int n2=scan.nextInt();
        System.out.println(add(n1,n2));
        double a=scan.nextDouble();
        double b=scan.nextDouble();
        System.out.println(add(a,b));
    }
    public static int fib(int n) {
        int f1=1;
        int f2=1;
        int f3=1;
        for(int i=3;i<=n;i++) {
            f1=f2;
            f2=f3;
            f3=f1+f2;
        } return f3;
    }

    public static void main4(String[] args) {
        Scanner scan=new Scanner(System.in);
        int n=scan.nextInt();
        int ret=fib(n);
        System.out.println(ret);
    }
    public static void move(char pos1,char pos3) {
        System.out.println(pos1+"----->"+pos3);
    }
    public static void hanoi(int n,char pos1,char pos2,char pos3) {
        if(n==1) {
            move(pos1,pos3);
            return;
        } hanoi(n-1,pos1,pos3,pos2);
        move(pos1,pos3);
        hanoi(n-1,pos2,pos1,pos3);
    }

    public static void main3(String[] args) {
        hanoi(1,'A','B','C');
        System.out.println();
        hanoi(2,'A','B','C');
        System.out.println();
        hanoi(3,'A','B','C');
    }



    public static int count=0;
    public static int binarySearch(int []arr,int toFind) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            count++;
            int mid = (right + left) / 2;
            if (arr[mid] > toFind) {
                right = mid - 1;
            } else if (arr[mid] < toFind) {
                left = mid + 1;
            } else {
               return mid;
            }

        }  return -1;
    }

    public static void main2(String[] args) {
        int []arr={2,6,5,8,9,11,25,36};
        int ret=binarySearch(arr,11);
        System.out.println(ret);
        System.out.println(count);
    }
    public static void bubbleSort(int []arr) {
        boolean flag=false;
        for(int i=0;i<arr.length;i++) {
            flag=false;
            for(int j=0;j<arr.length-1-i;j++) {
                if(arr[j]>arr[j+1]) {
                    int tmp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=tmp;
                    flag=true;//交换了 flag变为true
                }
            } if(flag==false) {
                return;
            }
        }
        }

    public static void main1(String[] args) {
        int []arr=new int[]{1,56,89,74,2,5,6,3,1};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    }
