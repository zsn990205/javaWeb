import java.util.Arrays;

public class TestDemo4 {
    public static void exchange(int []arr) {
        int left=0;
        int right=arr.length-1;
        while(left<right) {
            while(left<right && arr[left]%2==0) {
                left++;
            } while(left<right && arr[left]%2!=0) {
                int tmp=arr[left];
                arr[left]=arr[right];
                arr[right]=tmp;
                right--;
            }
        }
    }

    public static void main(String[] args) {
        int []arr={5,6,8,4,2,6,5,8,9,11};
        exchange(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void swap(int[] arr) {
        int tmp=arr[0];
        arr[0]=arr[1];
        arr[1]=tmp;
    }

    public static void main1(String[] args) {
     int []arr={10,20};
        System.out.println(arr[0]+" "+arr[1]);
     swap(arr);
        System.out.println(arr[0]+" "+arr[1]);
    }
}

