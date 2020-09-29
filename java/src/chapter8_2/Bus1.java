package chapter8_2;

public class Bus1 implements Vehicle1 {
    @Override
    public void run(){
        System.out.println("버스가 달립니다.");
    }

    public void checkFare(){
        System.out.println("승차요금을 체크합니다.");
    }

    public static void main(String[] args) {
        Bus1 bus1 = new Bus1();
    }
}
