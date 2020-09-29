package chapter9_1;

public class OutterExample {
    public static void main(String[] args){
        Outter1 outter = new Outter1();
        Outter1.Nested nested = outter.new Nested();
        nested.print();
    }
}
