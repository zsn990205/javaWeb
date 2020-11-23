package _20201122;
import java.util.Scanner;
class AngleError extends Exception {
    public AngleError(String message) {
        super(message);
    }
}
//边长异常
class LengthError extends Exception {
    public LengthError(String message) {
        super(message);
    }
}
public class JavaTest {
    public static void main1(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入三角形的三个角度");
        double A = scanner.nextDouble();
        double B = scanner.nextDouble();
        double C = scanner.nextDouble();
                try {
                    triangle(A,B,C);
                } catch (AngleError angleError) {
                    angleError.printStackTrace();
                } catch (LengthError lengthError) {
                    lengthError.printStackTrace();
                }
            }

    public static void main2(String[] args) {
        System.out.println("矩形9*9乘法表for循环");
        Rec();
        System.out.println("矩形9*9乘法表which循环");
        Rec2();
        System.out.println("矩形9*9乘法表下三角（L型");
        Triangle();
        System.out.println("矩形9*9乘法表下三角（L型）");
        Triangle2();
        System.out.println();
        Rec3();
    }

    public static void triangle( double A, double B, double C) throws AngleError, LengthError{
        if (A+B+C!=180 || A+B<C || A+C<B || B+C<A || A-B>C || A-C>B || B-C>A) {
            throw new AngleError("三角形B角度不符合应用条件！");
                }
        System.out.println("三角形B角度合理");
    }

    //矩形9*9乘法表————for循环方式
    public static void Rec() {
            for (int i = 1;i <= 9;i++){
                for (int j = 1; j <= 9; j++){
                    System.out.print(i +"*" + j + "=" + (i * j)+"\t");
                }
                System.out.println();
            }
        }
    //矩形9*9乘法表————while循环方式
    public static void Rec2() {
           int i = 1;
           int j = 1;
            while (i <= 9) {
            while (j <= 9){
                System.out.print(i +"*" + j + "=" + (i * j)+"\t");
                j++;
            }
            System.out.println();
            j = 1;//让j重新计数；
            i++;
        }
    }
    //矩形9*9乘法表————下三角（L型）break
    public static void Triangle() {
            for (int i = 1;i <= 9;i++) {
            for (int j = 1; j <= 9; j++) {
                if (j <= i) {
                    System.out.print(i +"*" + j + "=" + (i * j)+"\t");
                } else {
                    break;
                }
            }
            System.out.println();
        }
    }
    //矩形9*9乘法表————下三角（L型）continue
    public static void Triangle2() {
            for (int i = 1; i <= 9; i++){
            for (int j = 1; j <= 9; j++) {
                if (j <= i) {
                    System.out.print(i +"*" + j + "=" + (i * j)+"\t");
                } else {
                    continue;
                }
            }
            System.out.println();
        }
    }
    public static void Rec3() {
            for (int i = 1;i <= 9;i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(i +"*" + j + "=" + (i * j)+"\t");
            }
            System.out.println();
        }
    }
    //矩阵相加
    public static int[][] add(int[][] a, int[][] b) {
        int row = a.length;   //行的长度
        int col = a[0].length;   //列的长度
        int[][] ret = new int[row][col];
        //判断b矩阵是否和a矩阵的行列个数一样，如果一样继续执行
        if (row != b.length || col != b[0].length) {
            System.out.println("请输入一样长度的两个矩阵");
        } else{
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    ret[i][j] = a[i][j] + b[i][j];
                }
            }
        }
        return ret;
    }

    //矩阵相减
    public static int[][] sub(int[][] a, int[][] b) {
        int row = a.length;
        int col = a[0].length;
        int[][] ret = new int[row][col];
        if (row != b.length || col != b[0].length) {
            System.out.println("请输入一样长度的两个矩阵");
        } else {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    ret[i][j] = a[i][j] - b[i][j];
                }
            }
        }
        return ret;
    }

    //矩阵与常数k相乘
    public static int[][] dot(int[][] a, int b) {
        int row = a.length;
        int col = a[0].length;
        int[][] ret = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ret[i][j] = a[i][j] * b;
            }
        }
        return ret;
    }

    //矩阵相乘
    public static int[][] dot(int[][] a, int[][] b) {
        int row = a.length;
        int col = a[0].length;
        int[][] ret = new int[row][col];
        if (col != b.length) {
            System.out.println("错误！");
        } else {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    ret[i][j] = 0;
                    for (int k = 0; k < col; k++) {
                        ret[i][j] += a[i][k] * b[k][j];
                    }
                }
            }
        }
        return ret;
    }

    //矩阵的逆
    public static int[][] transport(int[][] a) {
        int row = a.length;
        int col = a[0].length;
        int[][] result = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result[j][i] = a[i][j];
            }
        }
        return result;
    }

    //打印格式
    public static void print(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            System.out.print("[");
            for (int j = 0; j < col; j++) {
                System.out.print(matrix[i][j]);
                if (j != col - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print("]\n");
        }
    }


    public static void main(String[] args) {
        int[][] a = { { 1, 2 }, { 3, 4 } };
        int[][] b = { { 7, 8 }, { 6, 5 } };
        //a矩阵为
        System.out.println("a = ");
        print(a);
        //b矩阵为
        System.out.println("b = ");
        print(b);
        //两矩阵的加法：
        int[][] ret1 = add(a, b);
        System.out.println("a + b = ");
        print(ret1);
        //两矩阵相减
        int[][] ret2 = sub(a, b);
        System.out.println("a - b = ");
        print(ret2);
        //矩阵与常数相乘
        int[][] ret3 = dot(a, 8);
        System.out.println("a * 8 = ");
        print(ret3);
        //两矩阵相乘
        int[][] ret6 = dot(a, b);
        System.out.println("a * b = ");
        print(ret6);
        //两矩阵相除
        int[][] ret4 = transport(a);
        System.out.println("a的逆 = ");
        print(ret4);
        int[][] ret5 = dot(a, ret4);
        System.out.println("a / b = ");
        print(ret5);
    }
}


