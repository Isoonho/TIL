package chapter6_5;

public class Car1 {
    int speed;

    void run() {
        System.out.println(speed + "으로 달립니다.");
    }

    public static void main(String[] args) {
        Car1 myCar = new Car1();
        myCar.speed = 60;
        myCar.run();
    }
}
