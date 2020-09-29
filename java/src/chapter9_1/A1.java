package chapter9_1;

public class A1 {
    //인스턴스 필드
    B1 field1 = new B1();
    C1 field2 = new C1();

    //인스턴스 메소드
    void method1(){
        B1 var1 = new B1();
        C1 var2 = new C1();
    }

    //정적 필드 초기화
    //static B1 field3 = new B1();
    static C1 field4 = new C1();

    //정적 메소드
    static void method2(){
        //B1 var1 = new B1();
        C1 var2 = new C1();
    }

    //인스턴스 멤버 클래스
    class B1{}

    //정적 멤버 클래스
    static class C1 {}

}
