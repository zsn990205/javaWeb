import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;


public class TestDemo {
    //不规则的数组可以手动赋值
    public static void main(String[] args) {
        int [][]arr=new int[2][];
        arr[0]=new int[]{4,5,6,7};
        arr[1]=new int[]{5,5,8,9,8,4,8,5,6,5,4};
       //arr[0]=new int[3];
       //arr[1]=new int[5];
        System.out.println(Arrays.deepToString(arr));

    }
    //二维数组
    public static void main12(String[] args) {
        int [][]arr=new int[2][5];
        int [][]arr1=new int[][]{{1,2,5},{6,8}};
        int [][]arr2=new int[][]{{2,5,6},{8,9}};
        for(int i=0;i<arr1.length;i++) { //行
            for(int j=0;j<arr1[i].length;j++) { //这一行的各个数
                System.out.print(arr1[i][j]+" ");
            }
            System.out.println();
        }


    }
    public static void swap(int []arr) {
        int left=0;
        int right=arr.length-1;
       while(left<right) {
            while(left<right&&arr[left]%2==0) { //left++有可能会下标越界 所以要再次<right
                left++;//偶数直接++
            } while(left<right&&arr[left]%2!=0) {
                right--;//奇数right-- 且交换
                int tmp=arr[left];
                arr[left]=arr[right];
                arr[right]=tmp;
            }
        }
    }

    public static void main11(String[] args) {
        int []arr={1,6,8,5,9,11};
        swap(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void reverse(int []arr) {
        int left=0;
        int right=arr.length-1;
        while(left<right) {
            int tmp=arr[left];
            arr[left]=arr[right];
            arr[right]=tmp;
            left++;
            right--;
        }
    }

    public static void main10(String[] args) {
        int []arr={1,5,6,8,9};
        System.out.println(Arrays.toString(arr));
        System.out.println("==========逆转==========");
        reverse(arr);
        System.out.println(Arrays.toString(arr));
    }

   public static String toString(int []arr) {
       String ret="[";
       for(int i=0;i<arr.length;i++) {
           ret+=arr[i];
           if(i!=arr.length-1) {
               ret+=",";
           }
       }  ret+="]";
       return ret;
   }

    public static void main9(String[] args) {
        int [] arr={2,5,6,8,9};
        toString(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void main8(String[] args) {
        int []arr={1,5,6,8,3};
        int []ret=new int[arr.length];
        System.arraycopy(arr,0,ret,0,arr.length);
        System.out.println(Arrays.toString(ret));
    }
    //冒泡排序升级版
    public static void bubbleSort(int []arr) {
       boolean flag=false;
        for(int i = 0;i<arr.length;i++) { //i为趟数
            flag=false;//有可能第一次换完就排好了
            for(int j=0;j<arr.length-1-i;j++) { //j为下移的指针
                if(arr[j]>arr[j+1]) {
                    int tmp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=tmp;
                    flag=true;//若交换就是TRUE
                }
            }
            } if(flag==false) {
                return;
        }
    }

    public static void main7(String[] args) {
        int []arr={5,6,25,18,9,15};
        bubbleSort(arr);//void无返回值
        System.out.println(Arrays.toString(arr));//结果是打印新的排序后的数组
    }

    //数组是否有序
    public static boolean isSorted(int[] arr) {
        for(int i=0;i<arr.length-1;i++) {
            if(arr[i]>arr[i+1]) {
                return false;
            }
        }   return true;
    }
    public static void main6(String[] args) {
        int []arr={1,5,61,8,9,47};
        System.out.println(isSorted(arr));
    }
    //比较数组是否相等 equals比较的是值
//==比较的是地址 地址一定不等
    public static void main5(String[] args) {
        int []arr1={1,2,5,6,8,9};
        int []arr2={2,5,6,8,9,7};
        System.out.println(Arrays.equals(arr1,arr2));
    }

    //java自己的关于排序和二分查找的函数
    public static void main4(String[] args) {
        int []arr={1,6,5,8,9,7};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        int ret=Arrays.binarySearch(arr,10);//返回-(low+1)
        System.out.println(ret);
    }
    //二分查找
    public static int count=0;
    public static  int binarySearch(int []arr,int toFind) {
        int left=0;
        int right=arr.length-1;
        while(left<=right) {
            count ++;
            int mid =(left+right)/2;
            if(arr[mid]>toFind) {
                right=mid-1;
            } else if(arr[mid]<toFind) {
                left=mid+1;
            } else {
                return mid;
            }
        } return -1;
    }
//数据特别大
    public static void main3(String[] args) {
        int[] arr = new int[10000];
        for (int i = 0; i < arr.length; i++) {
           arr[i]=i;
        }
        int ret = binarySearch(arr, 9999);
        System.out.println(ret);
        System.out.println(count);
    }

    public static void main2(String[] args) {
        int [] arr={1,2,5,6,8,9,17,25,36};
        int ret=binarySearch(arr,36);
        System.out.println(ret);
        System.out.println(count);
    }

    //局部拷贝
    public static void main1(String[] args) {
        int []arr={1,2,5,6,7,8};
        int []ret= Arrays.copyOfRange(arr,1,8); //左闭右开区间
        System.out.println(Arrays.toString(ret));
    }
}
