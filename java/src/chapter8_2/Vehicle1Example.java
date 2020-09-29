package chapter8_2;

public class Vehicle1Example {
    public static void main(String[] args){
        Vehicle1 vehicle1 = new Bus1();

        vehicle1.run();
        //vehicle.checkFare();

        Bus1 bus1 = (Bus1) vehicle1;

        bus1.run();
        bus1.checkFare();
    }
}
