package _TestClassCode;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class FinalTest {
    public static void main(String[] args) {
        int[] arr = {1,2,5,6,35,20,2,1,0};
        int[] array = {1,2,5,6,8,9};
        mergeArr(arr,array);

    }

    private static void mergeArr(int[] arr, int[] array) {
        int[] cArray = new int[arr.length+array.length];
        for (int i = 0; i < arr.length; i++) {
            cArray[i] = arr[i];
        }
        for (int j = 0; j < array.length; j++) {
            cArray[arr.length + j] = array[j];
        }
        System.out.println(Arrays.toString(cArray));
        sortArr(cArray);
    }

    private static void sortArr(int[] cArray) {
        Arrays.sort(cArray);
        System.out.println(Arrays.toString(cArray));
    }

    public static void main16(String[] args) {
        int[] arr = {1,2,5,6,35,20,2,1,0};
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        deleteIndex(arr,index);
    }

    private static void deleteIndex(int[] arr, int index) {
        for (int i = index; i < arr.length-1; i++) {
                arr[i] = arr[i+1];
            }
        for (int j = 0; j < arr.length-1; j++) {
            System.out.print(arr[j]+" ");
        }
        }


    public static void main15(String[] args) {
        int[] arr = {1,2,5,6,35,20,2,1,0};
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        int x = scanner.nextInt();
        insertIndex(arr,index,x);
    }

    private static void insertIndex(int[] arr,int index,int x) {
        arr[index] = x;
        for (int i = arr.length-1; i >= index; i--) {
            arr[i] = arr[i-1];
        }
        System.out.print(Arrays.toString(arr));
    }

    public static void main14(String[] args) {
        int[] arr = {1,2,5,6,35,20,2,1,4};
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        System.out.println(isExit(x,arr));
    }

    private static boolean isExit(int x,int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (x == arr[i]) {
                return true;
            }
        }
        return false;
    }

    public static void main13(String[] args) {
        int[] arr = {1,2,5,6,35,20,2,1,4};
        System.out.println(countArr(arr));
    }

    private static double countArr(int[] arr) {
        double sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum * (1.0)/arr.length;
    }

    public static void main12(String[] args) {
        int[] arr = {1,2,5,6,35,20,2,1,4};
        findIndex(arr);
    }

    private static void findIndex(int[] arr) {
        int max = arr[0];
        int min = arr[0];
        int maxIndex = 0;
        int minIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
                maxIndex = i;
            }
            if (min > arr[i]) {
                min = arr[i];
                minIndex = i;
            }
        }
        System.out.println(max + " " + min);
        System.out.println(maxIndex +" " + minIndex);
    }

    public static void main11(String[] args) {
        int[] arr = {1,2,5,6,35,20,2,1,4};
        Arrays.sort(arr);
        downArr(arr);
    }

    private static void downArr(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.print(arr[i]+" ");
        }
    }

    public static void main10(String[] args) {
        int[] arr = {1,2,5,6,35,20,2,1,4};
        Arrays.sort(arr);
        printArr(arr);
    }

    private static void printArr(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }

    public static void main9(String[] args) {
        Random random = new Random();
        for (int i = 0 ; i < 10; i++) {
            int x = random.nextInt(10);
            double y = random.nextDouble();
            System.out.println(x+" ");
            System.out.println(y+" ");
        }
    }

    public static void main8(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int grade = scanner.nextInt();
        RankGrade(grade);
    }

    private static void RankGrade(int grade) {
        switch(grade) {
            case 1 :
                System.out.println("E");
            break;
            case 2 :
                System.out.println("D");
            break;
            case 3 :
                System.out.println("C");
            break;
            case 4 :
                System.out.println("B");
                break;
            case 5 :
                System.out.println("A");
                break;
            default :
                System.out.println("此类成绩不存在");
        }
    }

    public static void main7(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int month = scanner.nextInt();
        isSeason(month);
    }

    private static void isSeason(int month) {
        if (month > 12) {
            System.out.println("您输入的月份有误请重新输入");
            return;
        }
        if (month >= 2 && month <= 4) {
            System.out.println("是春季");
        } else if (month > 4 && month < 8) {
            System.out.println("是夏季");
        } else if (month >=8 && month <= 10) {
            System.out.println("是秋季");
        } else  {
            System.out.println("是冬季");
        }
    }

    public static void main6(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        System.out.println(isLoopYear(year));
    }

    private static boolean isLoopYear(int year) {
        if (year % 400 == 0 || year % 4 == 0 && year % 100 != 0) {
            return true;
        }
            return false;
    }

    public static void main5(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        System.out.println(CountY(x));
    }

    private static int CountY(int x) {
        if (x > 0) {
            return x+3;
        } else if (x == 0) {
            return 0;
        } else {
            return x*x-1;
        }
    }

    public static void main4(String[] args) {
        double sum = 0;
        for (int i = 1; i <= 99; i++) {
            for (int j = 1; j <= 99; j++) {
                sum += 1.0/j;
            }
        }
        System.out.println(sum);
    }

    public static void main3(String[] args) {
        int sum = 0;
        for (int i = 1; i <= 99; i++) {
            for (int j = 1; j <= 99; j++) {
                sum += j;
            }
        }
        System.out.println(sum);
    }

    public static void main2(String[] args) {
        double sum = 0;
        for (int i = 1; i <= 99; i++) {
        //注意:必须是1.0
            sum += 1.0/i;
        }
        System.out.println(sum);
    }

    public static void main1(String[] args) {
        int sum = 0;
        for (int i = 1; i <= 99; i++) {
            sum += i;
        }
        System.out.println(sum);
    }
}
