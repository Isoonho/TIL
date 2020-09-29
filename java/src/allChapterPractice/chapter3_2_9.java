package allChapterPractice;
import java.util.Scanner;

//TODO 입력된 첫 번째 수에 두 번째 수를 나눈 결과를 "결과:값"으로 출력하되, 두 번재 수에 0또는 0.0이 입력되었을 경우 " 결과:무한대"가 출력되도록 코드를 작성하라
//첫 번째 수:7.3
//두 번째 수:2.5

public class chapter3_2_9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("첫 번째 수를 입력하세요: ");
        double num1 = Double.parseDouble(scanner.nextLine());

        System.out.println("두 번째 수를 입력하세요: ");
        double num2 = Double.parseDouble(scanner.nextLine());

        System.out.println("----\t----\t----");
        if(num2 != 0.0){
            System.out.println("결과: " + (num1/num2));
        }else{
            System.out.println("결과: 무한대");
        }


    }
}
