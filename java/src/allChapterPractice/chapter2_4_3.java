package allChapterPractice;

import java.util.Scanner;

public class chapter2_4_3 {
    //TODO scanner를 이용해서 이름, 주민번호 앞 6자리, 전화번호를 키보드에서 입력받고 출력하는 코드를 작성해보세요.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("[필수 정보 입력]");

        System.out.println("1. 이름: ");
        String name = scanner.nextLine();

        System.out.println("2. 주민번호 앞 6자리: ");
        String ssn = scanner.nextLine();

        System.out.println("3. 전화번호: ");
        String tel = scanner.nextLine();

        System.out.println();

        System.out.println("[입력한 내용]");

        System.out.println("이름: " + name);
        System.out.println("주민번호 앞 6자리: " + ssn);
        System.out.println("전화번호 : " + tel);


    }

}
