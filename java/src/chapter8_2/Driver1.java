package chapter8_2;

public class Driver1 {
    public void drive(Vehicle1 vehicle1){
        if(vehicle1 instanceof Bus1){
            Bus1 bus = (Bus1) vehicle1;
            bus.checkFare();
        }
        vehicle1.run();
    }
}
