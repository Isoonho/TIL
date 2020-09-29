package chapter6_3;

public class Car1 {
    //필드
    String company = "현대자동차";
    String model;
    String color;
    int maxSpeed;

    //생성자1
    Car1() {
    }

    //생성자2
    Car1(String model){
        this.model = model;
    }

    //생성자3
    Car1(String model, String color){
        this.model = model;
        this.color = color;
    }

    //생성자4
    Car1(String model, String color, int maxSpeed){
        this.model = model;
        this.color = color;
        this.maxSpeed = maxSpeed;
    }
}

