package allChapterPractice;

import java.util.Scanner;

//TODO Scanner를 이용해서 키보드로 입력한 두 수를 덧셈하여 결과를 출력하세요.

public class chapter2_4_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("첫번째 수: ");
        String strNum1 = scanner.nextLine();

        System.out.print("두번째 수: ");
        String strNum2 = scanner.nextLine();

        int num1 = Integer.parseInt(strNum1);
        int num2 = Integer.parseInt(strNum2);
        int result = num1 + num2;
        System.out.println("덧셈 결과: " + result);
    }

}
