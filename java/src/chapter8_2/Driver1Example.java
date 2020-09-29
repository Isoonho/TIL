package chapter8_2;

public class Driver1Example {
    public static void main(String[] args) {
        Driver1 driver = new Driver1();

        Bus1 bus = new Bus1();
        Taxi1 taxi = new Taxi1();

        driver.drive(bus);
        driver.drive(taxi);
    }
}
