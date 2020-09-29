# chapter 10. 예외 처리

## 10-1. 예외와 예외 클래스

- **예외(exception)**란 사용자의 잘못된 조작 또는 개발자의 잘못된 코딩으로 인해 발생하는 **프로그램 오류**를 말한다.
- 예외가 발생되면 프로그램은 **곧바로 종료**된다. 
- 하지만 **예외 처리(Exception Handling)**을 통해 프로그램을 종료하지 않고 정상 실행 상태가 유지되도록 할 수 있다. 

<br>

- 예외에는 **일반 예외(Exception)**와 **실행 예외(Runtime Exception)** 두 가지가 있다.
- **일반 예외(컴파일러 체크 예외)**는, 자바 소스를 컴파일하는 과정에서 예외 처리 코드가 필요한지 검사한다. 만약 **예외 처리 코드가 없다면 컴파일 오류가 발생**한다.
- 실행 예외는 컴파일하는 과정에서 예외 처리 코드를 검사하지 않는 예외를 말한다. **컴파일 시 예외 처리를 확인**하는 차이일 뿐, 두 가지 예외는 모두 **예외 처리가 필요**하다.
- 자바에서는 **예외를 클래스로 관리**한다. JVM은 프로그램을 실행하는 도중에 예외가 발생하면 해당 예외 클래스로 객체를 생성한다. 

<img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200127203129834.png" alt="image-20200127203129834" style="zoom:60%;" />

<br>

## 10-2. 실행 예외

- 실행 예외는 자바 컴파일러가 체크를 하지 않기 때문에 오로지 **개발자의 경험에 의해서 예외 처리 코드를 삽입**해야 한다.
- 자주 발생되는 실행 예외들을 잘 알아둘 필요가 있다.

<br>

### 10-2-1. NullPointerException

- 자바 프로그램에서 가장 빈번하게 발생하는 실행 예외는 java.lang.NullPointerException 일 것이다.

- 이 예외는 **객체 참조가 없는 상태**, 즉 **null 값을 갖는 참조 변수로 객체 접근 연산자인 도트를 사용했을 때 발생**한다.

  ```java
  public class NullPointerExceptionExample{
    public static void main(String[] args){
      String data = null;
      System.out.println(data.toString());
    }
  }
  ```

  - 3라인에서 data 변수가 null 값을 가지고 있기 때문에 String 객체를 참조하고 있지 않다. 하지만 4라인에서 String 객체의 toString( ) 메소드를 호출하고 있다. 여기서 NullPointerException이 발생한다.  

<br>

### 10-2-2. ArrayIndexOutOfBoundsException

- **배열에서 인덱스 범위를 초과하여 사용할 경우**실행 예외인 java.lang.ArrayIndexOutOfBoundsException이 발생한다.

  ```java
  public class ArrayIndexOutOfBoundsExceptionExample{
    public static void main(String[] args){
      String data1 = args[0];
      String data2 = args[1];
      
      System.out.println("args[0] = " + data1);
      System.out.println("args[1] = " + data2);
    }
  }
  ```

  - 3라인에서 ArrayIndexOutOfBoundsException이 발생한다. 그 이유는 두 개의 실행 매개값을 주지 않았기 때문에 args[0], args[1] 인덱스를 사용할 수 없기 때문이다. 

  - 다음과 같이 수정하면 예외가 발생하지 않는 좋은 프로그램을 만들 수 있다. 

    

  ```java
  public class ArrayIndexOutOfBoundsExceptionExample{
    public static void main(String[] args){
      if(args.length == 2){
        String data1 = args[0];
        String data2 = args[1];
        System.out.println("args[0] : " + data1);
        System.out.println("args[1] : " + data2);
      }else{
        System.out.println("[실행 방법]");
        System.out.println("java ArrayIndexOutOfBoundsExceptionExample ");
        System.out.println("값1 값2");
      }
    }
  }
  ```

  <br>

### 10-2-3. NumberFormatException

- 문자열로 되어 있는 데이터를 숫자로 변경하는 경우가 자주 발생한다. 문자열을 숫자로 변경할때 가장 많이 쓰는 방법은 다음과 같다.

  | 변환 타입 | 메소드명(매개 변수)          | 설명                                 |
  | --------- | ---------------------------- | ------------------------------------ |
  | int       | Integer.parseInt(String s)   | 주어진 문자열을 정수로 변환해서 리턴 |
  | double    | Double.parseDouble(String s) | 주어진 문자열을 실수로 변환해서 리턴 |

- 이 메소드들은 매개값인 문자열이 숫자로 변환될 수 있다면 숫자를 리턴하지만, **숫자로 변환될 수 없는 문자가 포함되어 있다면** java.lang.NumberFormatException을 발생시킨다.

<br>

### 10-2-4. ClassCastException

- 타입 변환(Casting)은 상위 클래스와 하위 클래스 간에 발생하고 구현 클래스와 인터페이스 간에도 발생한다. 

- 이러한 관계가 아니라면 클래스는 다른 클래스로 타입 변환할 수 없다.

- **억지로 타입 변환을 시도할 경우** ClassCastException이 발생한다. 

  ![image-20200127211003448](/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200127211003448.png)

```java
public class ClassCastExceptionExample{
  public static void main(String[] args){
    Dog dog = new Dog();
    changeDog(dog);
    
    Cat cat = new Cat();
    changeDog(cat);
  }
  
  public static void changeDog(Animal animal){
    /* if(animal instanceof Dog){
        Dog dog = (Dog) animal;
  }*/
    
    class Animal{}
    class Dog extends Animal{}
    class Cat extends Animal{}
  }
}
```

- 위 예제를 실행하면 12라인에서 ClassCastException이 발생한다. 그 이유는 7라인에서 Cat 객체를 매개값으로 주었기 때문에 Dog 타입으로 변환할 수 없다. 

<br>

## 10-3. 예외 처리 코드

- 프로그램에서 예외가 발생했을 경우 **프로그램의 갑작스러운 종료를 막고**, **정상 실행을 유지할 수 있도록 처리**하는 코드를 예외 처리 코드라고 한다.

- 자바 컴파일러는 소스 파일을 컴파일할 때 일반 예외가 발생할 가능성이 있는 코드를 발견하면 컴파일 오류를 발생시켜 개발자로 하여금 강제적으로 예외 처리 코드를 작성하도록 요구한다. 

- 그러나 실행 예외는 컴파일러가 체크해주지 않기 때문에 예외 처리 코드를 개발자의 경험을 바탕으로 작성해야 한다. 

- 예외 처리 코드는 **try-catch-finally 블록을 이용한다.** try-catch-finally 블록은 생성자 내부와 메소드 내부에서 작성되어 일반 예외와 실행 예외가 발생할 경우 예외 처리를 할 수 있도록 해준다.

  <img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200127214655237.png" alt="image-20200127214655237" style="zoom:67%;" />

- **try 블록에는** **예외 발생 가능 코드가 위치한다. **try 블록의 코드가 예외 발생 없이 정상 실행되면 catch 블록의 코드는 실행되지 않고 finally 블록의 코드를 실행한다. 
- 만약 try 블록의 코드에서 예외가 발생하면 즉시 실행을 멈추고 catch 블록으로 이동하여 예외 처리 코드를 실행한다. 그 후 finally 블록의 코드를 실행한다. finally 블록은 옵션으로 생략 가능하다. 
- 예외 발생 여부와 상관없이 항상 실행할 내용이 있을 경우에만 finally 블록을 작성하면 된다. 또한 try, catch 블록에서 return 문을 사용하더라도 finally 블록은 실행된다. 

<br>

## 10-4. 예외 종류에 따른 처리 코드

### 10-4-1. 다중 catch

- try 블록 내부는 다양한 종류의 예외가 발생할 수 있는데, 이때 다중 catch 블록을 작성하면 된다. 
- catch 블록의 예외 클래스 타입은 try 블록에서 발생된 예외의 종류를 말하는데, try 블록에서 해당 타입의 예외가 발생하면 catch 블록을 실행하도록 되어 있다.

- catch 블록이 여러 개라 할지라도 **단 하나의 catch 블록만 실행**된다. 
- 그 이유는 try 블록에서 동시 다발적으로 예외가 발생하지 않고, 하나의 예외가 발생하면 즉시 실행을 멈추고 해당 catch 블록으로 이동하기 때문이다. 

<br>

### 10-4-2. catch 순서

- 다중 catch 블록을 작성할 때 주의할 점은 **상위 예외 클래스가 하위 예외 클래스보다 아래쪽에 위치해야 한다. **

- try 블록에서 예외가 발생했을 때, 예외를 처리해줄 catch 블록은 **위에서부터 차례대로 검색**된다. 만약 상위 예외 클래스의 catch 블록이 위에 있다면, 하위 예외 클래스의 catch 블록은 실행되지 않는다. 왜냐하면 하위 예외는 상위 예외를 상속했기 때문에 상위 예외 타입도 되기 때문이다.

  <img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200127222515852.png" alt="image-20200127222515852" style="zoom:50%;" />

<br>

### 10-4-3. 멀티 catch

- **catch 괄호() 안에 동일하게 처리하고 싶은 예외를 |로 연결하면 된다.**

  <img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200127222627790.png" alt="image-20200127222627790" style="zoom:50%;" />

<br>

## 10-5. 자동 리소스 닫기

- 자바 7에서 새로 추가된 try-with-resources를 사용하면 **예외 발생 여부와 상관없이** 사용했던 리소스 객체(각종 입출력 스트림, 서버 소켓, 소켓, 각종 채널)의 close() 메소드를 호출해서 안전하게 리소스를 닫아준다. 

- **리소스**란 여러 가지 의미가 있겠지만 여기서는 **데이터를 읽고 쓰는 객체**라고 생각하면 된다. 

- try-with-resources를 사용하면 코드가 간단해지며 close()를 명시적으로 호출한 곳이 없다.

  ```java
  try(FileInputStream fis = new FileInputStream("file.txt")){
    ...
  }catch(Exception e){
    ...
  }
  ```

<br>

## 10-6. 예외 떠넘기기

- 메소드 내부에서 예외가 발생할 수 있는 코드를 작성할 때 try-catch 블록으로 예외를 처리하는 것이 기본이지만, 경우에 따라서는 **메소드를 호출한 곳으로 예외를 떠넘길 수도 있다.**
- 이때 사용하는 키워드가 throws 이다. throws 키워드는 **메소드 선언부 끝에 작성**되어 메소드에서 처리하지 않은 예외를 호출한 곳으로 떠넘기는 역할을 한다. 
- throws 키워드 뒤에는 떠넘길 예외 클래스를 쉼표로 구분해서 나열해주면 된다.

<img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200127223459717.png" alt="image-20200127223459717" style="zoom:50%;" />

- **throws 키워드가 붙어있는 메소드는 반드시 try 블록 내에서 호출되어야 한다.** 그리고 catch 블록에서 떠넘겨 받은 예외를 처리해야 한다. 

<br>

## 10-7. 사용자 정의 예외와 예외 발생

- 자바 표준 API에서 제공하는 예외 클래스만으로는 다양한 종류의 예외를 표현할 수 없다.
- 애플리케이션 서비스와 관련된 예외를 **애플리케이션 예외(Application Exception)**라고 한다.
- 애플리케이션 예외는 개발자가 직접 정의해서 만들어야 하므로 사용자 정의 예외라고도 한다.
- Ex) 잔고 부족 예외, 계좌 이체 실패 예외, 회원 가입 실패 예외, ... 

### 10-7-1. 사용자 정의 예외 클래스 선언

- 일반 예외로 선언할 경우 Exception을 상속하면 되고, 실행 예외로 선언할 경우에는 RuntimeException을 상속하면 된다.

  ![image-20200128125421386](/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200128125421386.png)
  - 사용자 정의 예외 클래스 이름은 Exception으로 끝나는 것이 좋다. 
  - 사용자 정의 예외 클래스도 필드, 생성자, 메소드 선언들을 포함할 수 있지만 **대부분 생성자 선언**만을 포함한다.
  - 생성자는 두 개를 선언하는 것이 일반적인데, 하나는 매개 변수가 없는 **기본 생성자**이고, 다른 하나는 예외 발생 원인(예외 메시지)을 전달하기 위해 **String 타입의 매개 변수를 갖는 생성자**이다.

<br>

### 10-7-2. 예외 발생시키기

- 코드에서 예외를 발생시키는 방법은 다음과 같다.

  ![image-20200128125820484](/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200128125820484.png)
  - 예외 객체를 생성할 때는 기본 생성자 또는 예외 메시지를 갖는 생성자 중 어떤 것을 사용해도 된다.

  - 만약 catch 블록에서 예외 메시지가 필요하다면 예외 메시지를 갖는 생성자를 이용해야 한다. 

  - 예외 발생 코드를 가지고 있는 메소드는 내부에서 try-catch 블록으로 예외를 처리할 수 있지만, 대부분은 자신을 호출한 곳에서 예외를 처리하도록 throws 키워드로 예외를 떠넘긴다. 

    ![image-20200128130302840](/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200128130302840.png)

  - 그렇기 때문에 throws 키워드를 포함하고 있는 메소드는 호출한 곳에서 다음과 같이 예외 처리를 해주어야 한다.

    ```java
    try{
      method();
    }catch(XXXException e){
      //예외 처리
    }
    ```

<br>

## 10-8. 예외 정보 얻기

- try 블록에서 예외가 발생되면 예외 객체는 catch 블록의 매개 변수에서 참조하게 되므로 매개 변수를 이용하면 **예외 객체의 정보를 알 수 있다.**

- 모든 예외 객체는 Exception 클래스를 상속하기 때문에 Exception이 가지고 있는 메소드들을 모든 예외 객체에서 호출할 수 있다. 

  ![image-20200128130736907](/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200128130736907.png)
  - 예외 메시지의 내용에는 왜 예외가 발생했는지에 대한 간단한 설명이 포함된다. 좀 더 상세한 원인을 세분화하기 위해 예외 코드를 포함하기도 한다. 
  - 이와 같은 예외 메시지는 다음과 같이 catch 블록에서 **getMessage( ) 메소드**의 리턴값으로 얻을 수 있다.

  ![image-20200128141110511](/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200128141110511.png)



- **printStackTrace( ) 는** 메소드 이름에서도 알 수 있듯이 예외 발생 코드를 추적해서 모두 콘솔에 출력한다.

- 어떤 예외가 어디에서 발생했는지 상세하게 출력해주기 때문에 프로그램을 테스트하면서 오류를 찾을 때 활용된다.

  ![image-20200128141314192](/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200128141314192.png)































































