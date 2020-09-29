package chapter9_1;

public class Outter1 {
    String field = "Outter1-field";
    void method(){
        System.out.println("Outter1-method");
    }

    class Nested{
        String field = "Nested-field";
        void method(){
            System.out.println("Nested-method");
        }
        void print(){
            System.out.println(this.field);
            this.method();
            System.out.println(Outter1.this.field);
            Outter1.this.method();
        }
    }
}
