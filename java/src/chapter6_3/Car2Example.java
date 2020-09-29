package chapter6_3;

public class Car2Example {
    public static void main(String[] args){
        Car1 car1 = new Car1();  //생성자 선택1
        System.out.println("car1.company : " + car1.company);
        System.out.println();

        Car1 car2 = new Car1("자가용");   //생성자 선택2
        System.out.println("car2.company : " + car2.company);
        System.out.println();

        Car1 car3 = new Car1("자가용", "빨강");
        System.out.println("car3.company : " + car3.company);
        System.out.println("car3.model : " + car3.model);
        System.out.println("car3.color : " + car3.color);
        System.out.println();

        Car1 car4 = new Car1("택시", "검정", 200);
        System.out.println("car4.company : " + car4.company);
        System.out.println("car4.model : " + car4.model);
        System.out.println("car4.color : " + car4.color);
        System.out.println("car4.maxSpeed : " + car4.maxSpeed);

    }
}
