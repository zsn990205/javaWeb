import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;

public class TestDemo {
    public static void reverse(int [] arr) {
        int left=0;
        int right=arr.length-1;
        int tmp=0;
        while(left<=right) {
             tmp=arr[left];
             arr[left]=arr[right];
            arr[right]=tmp;
            left++;
            right--;
        }
    }
    public static int count=0;
    public static int binarySearch (int [] arr,int toFind) {
        int left=0;
        int right=arr.length-1;
        while(left<=right) {
            count++;
            int mid = (right + left) / 2;
            if (arr[mid] < toFind) {
                left = mid + 1;
            } else if (arr[mid] > toFind) {
                right = mid - 1;
            } else {
                return mid;
            }
        } return -1;
    }

    public static void main18(String[] args) {
        int [] arr={1,6,8,45,65,89};
        Arrays.sort(arr);//数组排序
      //  Arrays.binarySearch(arr);//数组下的二分查找
        int ret=binarySearch(arr,45);
        System.out.println(ret);
        System.out.println(count);
    }
    public static double average(int [] arr) {
        double sum=0;
        for(int i=0;i<arr.length;i++) {
            sum+=arr[i];
        }
        double a=sum/(arr.length);
        return a;
    }

    public static void main17(String[] args) {
       int []arr=new int[] {1,2,3,4,5,6};
        double ret= average(arr);
        System.out.println(ret);
    }
    public static int sum(int [] arr) {
        int sum=0;
        for(int i=0;i<arr.length;i++) {
            sum+=arr[i];
        } return sum;
    }

    public static void main16(String[] args) {
        int []arr={1,2,3,4,5};
        int ret=sum(arr);
        System.out.println(ret);
    }
    public static void main15(String[] args) {
        int []arr=new int[100];
        for(int i=0;i<arr.length;i++) {
            arr[i]=i+1;
            }
             System.out.print(Arrays.toString(arr));
    }
    //复制数组的值
    public static void main14(String[] args) {
        int[] arr = {1, 5, 6, 8};
        int[] ret = arr.clone();
        System.out.println(Arrays.toString(ret));
    }
    //拷贝的第三种方法同一
    public static void main13(String[] args) {
        int []arr={1,6,5,8};
        int []result= Arrays.copyOf(arr,arr.length);
        System.out.println(Arrays.toString(result));
    }
    //拷贝的第二种方法
    public static void main12(String[] args) {
        int []arr={1,2,3,4,5};
        int []dest=new int[arr.length];
        System.arraycopy(arr,0,dest,0,arr.length);
        System.out.println(Arrays.toString(dest));
    }
    //拷贝的第一种方法
    public static int[] copyArray(int [] arr) {
        int [] arr1=new int[arr.length];
        for (int i = 0; i <arr.length; i++) {
            arr1[i] = arr[i];
        } return arr1;
    }

    public static void main11(String[] args) {
    int [] arr2={1,2,3,4};
    int [] ret = copyArray(arr2);
            System.out.println(Arrays.toString(ret));
    }


    //打印[1,2,3,4,5]
    public static String myString(int []arr) {
        String ret = "[";
        for (int i = 0; i < arr.length; i++) {
            ret = ret + arr[i];
            if (i != arr.length - 1) {
                ret = ret + ",";
            }
        }
            ret = ret + "]";
        return ret;
    }

    public static void main10(String[] args) {
        int []arr2={1,2,3,4,5,6};
        System.out.println(myString(arr2));
    }


    //用方法来改变数组内各个值的大小
    public static int[] transform(int [] arr) {
        int[] ret = new int[arr.length]; //创建一个储存新数据的数组
        for (int i = 0; i < arr.length; i++) {
            ret[i] = arr[i] * 2;
        } return ret;
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }
        public static void main9(String[]args){
            int[] arr2 = {1, 2, 5, 6};
            int[] out = transform(arr2);
            print(out);
        }

    //改变数组中每个值的大小
    public static void main8(String[] args) {
        int []arr={1,2,3,4,5};
        System.out.println(Arrays.toString(arr));
        for(int i=0;i<arr.length;i++) {
            arr[i]=2*arr[i];
        } System.out.println(Arrays.toString(arr));
    }
    //关于空指针
    public static void main7(String[] args) {
        int []arr=null;
        System.out.println(arr[2]);
        System.out.println(arr.length);
    }
    //交换两个数字(方法) 用数组
    public static void swap(int[] arr ) {
        int tmp=arr[0];
         arr[0]=arr[1];
         arr[1]=tmp;
    }

    public static void main6(String[] args) {
        int []arr2={10,20};
        System.out.println(arr2[0]+" "+arr2[1]);
        swap(arr2);
        System.out.println(arr2[0]+" "+arr2[1]);
    }

    //浅拷贝
    public static void printArr(int[] arr) {
        arr[1]=999;
        for (int i=0;i<arr.length;i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public static void main5(String[] args) {
        int [] arr1={1,2,3,4,5,6,7,8,9};
        printArr(arr1);
        System.out.println("===========================");
        System.out.println(Arrays.toString(arr1));
    }
    //数组的一些小知识
    public static void main4(String[] args) {
        int []arr1={1,2,3,4,5,6};
        int []arr2={2,6,5,8,9,5,4};
        arr2[2]=3;
        System.out.print(arr1[2]);
        System.out.println();
        System.out.println(arr2.length);
        System.out.print(arr2[2]);
    }
    //循环遍历每一个元素的值第一种方法
    public static void main3(String[] args) {
        int [] arr2 = new int[] {1,2,3,4,5,6,7,8,9};
        for (int i:arr2) {
            System.out.print(i+" ");
        }
    }

     //循环遍历每一个元素的值第二种方法
    public static void main2(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7};
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i]+" ");
        }
    }


    //循环遍历每一个元素的值第三种方法 以及数组的三种定义
    public static void main1(String[] args) {
        int[] arr1 = new int[3];
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};
        int[] arr3 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.print(Arrays.toString(arr1));
        System.out.println();
        System.out.print(Arrays.toString(arr2));
        System.out.println();
        System.out.print(Arrays.toString(arr3));

    }
}