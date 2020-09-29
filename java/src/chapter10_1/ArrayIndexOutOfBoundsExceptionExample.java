package chapter10_1;

public class ArrayIndexOutOfBoundsExceptionExample {
    public static void main(String[] args) {
        String[] arr = new String[2];

        if (arr.length == 2) {
            String data1 = arr[0];
            String data2 = arr[1];
            System.out.println("args[0]: " + data1);
            System.out.println("args[1]: " + data2);
        } else {
            System.out.println("두 개의 실행 매개값이 필요합니다.");
        }
    }
}

