package chapter6_4;

public class CarExample1 {
    public static void main(String[] args) {
        Car1 myCar1 = new Car1();
        myCar1.keyTurnOn();
        myCar1.run();
        int speed = myCar1.getSpeed();
        System.out.println("현재 속도: " + speed + "km/h");
    }
}
