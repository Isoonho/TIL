package chapter11_1;

public class ExitExample {
    public static void main(String[] args) {
        for(int i=0; i<10; i++){
            if(i==5){
                System.exit(0);
                //break;
            }
        }
        System.out.println("마무리 코드");
    }
}
//System.exit(0)은 프로그램을 강제 종료하므로
// 7라인에서 실행하면 11라인은 출력되지 않는다.
//만약 프로그램이 종료될 때 꼭 11라인을 실행해야 한다면
//System.exit(0) 대신에 for 문을 빠져나오는 break문을 사용하는 것이
//좋다.7라인과 8라인을 번갈아가며 주석 처리하고 실행해 보면 알수있다.