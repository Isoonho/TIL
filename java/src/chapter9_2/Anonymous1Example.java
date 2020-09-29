package chapter9_2;

public class Anonymous1Example {
    public static void main(String[] args) {
        Anonymous1 anony = new Anonymous1();
        //익명 객체 필드 사용
        anony.field.turnOn();
        //익명 객체 로컬 변수 사용
        anony.method1();
        //익명 객체 매개값 사용
        anony.method2(
                new RemoteControl(){
                    @Override
                    public void turnOn(){
                        System.out.println("SmartTv를 켭니다.");
                    }
                    @Override
                    public void turnOff(){
                        System.out.println("SmartTV를 끕니다.");
                    }
                }
        );
    }
}
