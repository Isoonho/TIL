package chapter6_6;

public class Car1Example {
    public static void main(String[] args){
        Car1 myCar = new Car1();

        //잘못된 속도 변경
        myCar.setSpeed(-50);

        System.out.println("현재속도: " + myCar.getSpeed());

        //올바른 속도 변경
        myCar.setSpeed(60);

        //멈춤
        if(!myCar.isStop()){
            myCar.setStop(true);
        }

        System.out.println("현재 속도: " + myCar.getSpeed());
    }
}
