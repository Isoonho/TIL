package chapter5_2;

public class ArrayReferenceObjectExample {
    public static void main(String[] args) {
        String[] strArray = new String[3];
        strArray[0] = "Java";
        strArray[1] = "Java";
        strArray[2] = new String("java");

        System.out.println( strArray[0] == strArray[1]);
        System.out.println( strArray[0] == strArray[2]);
        System.out.println( strArray[0].equals(strArray[2]));
    }
}
