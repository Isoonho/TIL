# chpater7. 상속

# 7-1. 상속 개념

- 프로그램에서는 부모 클래스를 상위 클래스라고 부르기도 하고, 자식 클래스를 하위 클래스, 또는 파생 클래스라고 부른다.
- 상속은 이미 잘 개발된 클래스를 재사용해서 새로운 클래스를 만들기 때문에 코드의 **중복을 줄여준다.**
- 부모 클래스에서 **private 접근 제한을 갖는 필드와 메소드는 상속 대상에서 제외된다.** 그리고 부모 클래스와 자식 클래스가 다른 패키지에 존재한다면 default 접근 제한을 갖는 필드와 메소드도 상속 대상에서 제외된다. 

<br>

# 7-2. 클래스 상속

- 프로그램에서의 상속은 자식이 부모를 선택한다. 

- 자식 클래스를 선언할 때 어떤 부모 클래스를 상속받을 것인지를 결정하고 선택된 부모 클래스는 다음과 같이 extends 뒤에 기술한다.

  ```java
  class 자식클래스 extends 부모클래스{
    //필드
    //생성자
    //메소드
  }
  ```

- 다른 언어와는 달리 **자바는 다중 상속을 허용하지 않는다.** 즉 여러 개의 부모 클래스를 상속할 수 없다. \

  ```java
  class 자식클래스 extends 부모클래스1, 부모클래스2{}   //<-- 부모클래스2 상속 안됨
  ```

<br>

# 7-3. 부모 생성자 호출

- 현실에서 부모 없는 자식이 있을 수 없듯이 자바에서도 **자식 객체를 생성하면, 부모 객체가 먼저 생성되고 자식 객체가 그 다음에 생성된다.** 

- 아래 코드는 DmbCellPhone 객체만 생성하는 것 처럼 보이지만, 사실은 내부적으로 부모인 CellPhone 객체가 먼저 생성되고, DmbCellPhone 객체가 생성된다.

  ```java
  DmbCellPhone dmbCellPhone = new DmbCellPhone();
  ```

  - 이것을 메모리로 표현하면 다음과 같다.

  <img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20191228163732496.png" alt="image-20191228163732496" style="zoom:50%;" />

- 모든 객체는 클래스의 생성자를 호출해야만 생성된다. 부모 객체도 예외는 아니다. **부모 생성자는 자식 생성자의 맨 첫 줄에서 호출된다.** 

- 예를 들어 DmbCellPhone의 생성자가 명시적으로 선언되지 않았다면 컴파일러는 다음과 같은 기본 생성자를 생성해 낸다.

  ```java
  public DmbCellPhone(){
    super();
  }
  ```

  - 첫 줄에 super( ) 가 추가된 것을 볼 수 있다. **super()는 부모의 기본 생성자를 호출한다. ** 즉 CellPhone 클래스의 다음 생성자를 호출한다.

  ```java
  public CellPhone(){}
  ```

  - 위의 소스코드에서도 CellPhone의 생성자가 선언되지 않았지만 컴파일러에 의해 기본 생성자가 만들어지므로 문제없이 실행된다. 만약 **직접 자식 생성자를 선언하고 명시적으로 부모 생성자를 호출하고 싶다면 다음과 같이 작성하면 된다.**

  ```java
  자식클래스(매개변수선언, ...){
    super(매개값, ...);
  }
  ```

<br>

- super(매개값, ... )는 매개값의 타입과 일치하는 부모 생성자를 호출한다. 만약 매개값의 타입과 일치하는 부모 생성자가 없을 경우 컴파일 오류가 발생한다.
- super(매개값, ... )가 생략되면 컴파일러에 의해 super()가 자동적으로 추가되기 때문에 부모의 기본 생성자가 존재해야 한다. 
- 부모 클래스에 기본 생성자가 없고 매개 변수가 있는 생성자만 있다면 자식 생성자에서 반드시 **부모 생성자 호출을 위해 super(매개값, ...)를 명시적으로 호출해야 한다.** 
- **super(매개값, ...)는 반드시 자식 생성자 첫 줄에 위치해야 한다.**

<br>

# 7-4. 메소드 재정의

- 부모 클래스의 모든 메소드가 자식 클래스에 맞게 설계되어 있다면 가장 이상적인 상속이지만, **어떤 메소드는 자식 클래스가 사용하기에 적합하지 않을 수도 있다. ** 
- 이 경우 상속된 일부 메소드는 자식 클래스에서 다시 수정해서 사용해야 한다. 자바는 이런 경우를 위해 메소드 오버라이딩 기능을 제공한다.

<br>

## 7-4-1. 메소드 재정의 (@Override)

- **메소드 오버라이딩**은 상속된 메소드의 내용이 자식 클래스에 맞지 않을 경우, 자식 클래스에서 동일한 메소드를 재정의하는 것을 말한다. 
- 메소드가 오버라이딩되었다면 부모 객체의 메소드는 숨겨지기 때문에, 자식 객체에서 메소드를 호출하면 오버라이딩된 자식 메소드가 호출된다.
- 메소드를 오버라이딩할 때는 다음과 같은 **규칙에 주의해서 작성**해야한다.
  - 부모의 메소드와 동일한 시그너처(리턴 타입, 메소드 이름, 매개 변수 리스트)를 가져야 한다.
  - 접근 제한을 더 강하게 오버라이딩할 수 없다.
  - 새로운 예외(Exception)를 throws할 수 없다.
- 접근 제한을 더 강하게 오버라이딩할 수 없다는 것은 부모 메소드가 public 접근 제한을 가지고 있을 경우 오버라이딩하는 자식 메소드는 default나 private 접근 제한으로 수정할 수 없다는 뜻이다.
- 반대로, 부모 메소드가 default 접근 제한을 가지면 재정의되는 자식 메소드는 default 또는 public 접근 제한을 가질 수 있다. 
- @Override 어노테이션은 생략해도 좋으나, 이것을 붙여주게 되면 메소드가 정확히 오버라이딩된 것인지 컴파일러가 체크하기 때문에 **개발자의 실수를 줄여준다.**

<br>

## 7-4-2. 부모 메소드 호출(super)

- 자식 클래스에서 부모 클래스의 메소드를 오버라이딩하게 되면, 부모 클래스의 메소드는 숨겨지고 오버라이딩된 자식 메소드만 사용된다. 

- 그러나 자식 클래스 내부에서 오버라이딩된 부모 클래스의 메소드를 호출해야 하는 상황이 발생한다면 **명시적으로 super 키워드를 붙여서 부모 메소드를 호출할 수 있다.** 

  ```java
  super.부모메소드();
  ```

  <img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20191228171604702.png" alt="image-20191228171604702" style="zoom:50%;" />



<br>

# 7-5. final 클래스와 final 메소드

- final 키워드는 클래스, 필드, 메소드 선언 시에 사용할 수 있다. 
- final 키워드는 해당 선언이 최종 상태이고, 결코 수정될 수 없음을 뜻한다. 

<br>

## 7-5-1. 상속할 수 없는 final 클래스

- 클래스를 선언할 때 final 키워드를 class 앞에 붙이게 되면 이 클래스는 최종적인 클래스이므로 **상속할 수 없는 클래스가 된다.** 즉, final 클래스는 부모 클래스가 될 수 없어 자식 클래스를 만들 수 없다는 것이다. 

  ```java
  public final class 클래스 {...}
  ```

- final 클래스의 대표적인 예는 자바 표준 API에서 제공하는 String 클래스이다. String 클래스는 다음과 같이 선언되어 있다.

  ```java
  public final class String{...}
  ```

<br>

## 7-5-2. 오버라이딩할 수 없는 final 메소드

- 메소드를 선언할 때 final 키워드를 붙이게 되면 이 메소드는 최종적인 메소드이므로 **오버라이딩(Overriding)할 수 없는 메소드가 된다.**
  - 즉, 부모 클래스를 상속해서 자식 클래스를 선언할 때 부모 클래스에 선언된 final 메소드는 자식 클래스에서 재정의할 수 없다는 것이다. 

<br>

# 7-6. protected 접근 제한자

<img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20191229154433501.png" alt="image-20191229154433501" style="zoom:50%;" />



<img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20191229154441160.png" alt="image-20191229154441160" style="zoom:50%;" />

- protected는 public 과 default 접근 제한의 중간쯤에 해당한다. 같은 패키지에서는 default와 같이 접근 제한이 없지만 다른 패키지에서는 자식 클래스만 접근을 허용한다. 
- protected는 **필드와 생성자, 메소드 선언에 사용될 수 있다.** 

<br>

# 7-7. 타입 변환과 다형성

- 다형성은 같은 타입이지만 실행 결과가 다양한 객채를 이용할 수 있는 성질을 말한다. 
- 코드 측면에서 보면 다형성은 하나의 타입에 여러 객체를 대입함으로써 다양한 기능을 이용할 수 있도록 해준다. 
- 다형성을 위해 자바는 부모 클래스로 타입 변환을 허용한다. 즉, 부모 타입에 모든 자식 객체가 대입될 수 있다. 이것을 이용하면 객체는 부품화가 가능하다. 

<br>

## 7-7-1. 자동 타입 변환(Promotion)

- 자동 타입 변환은 프로그램 실행 도중에 자동적으로 타입 변환이 일어나는 것을 말한다. 자동 타입 변환은 다음과 같은 조건에서 일어난다.

  <img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20191229160324307.png" alt="image-20191229160324307" style="zoom:50%;" />

- 자동 타입 변환의 개념은 **자식은 부모의 특징과 기능을 상속받기 때문에 부모와 동일하게 취급될 수 있다는 것이다.**
- 예를 들어 고양이는 동물의 특징과 기능을 상속받았다. 그래서 "고양이는 동물이다."가 성립한다. Animal과 Cat 클래스가 다음과 같이 상속 관계에 있다고 보자.

<img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20191229160621852.png" alt="image-20191229160621852" style="zoom:50%;" />

- Cat 클래스로부터 Cat 객체를 생성하고 이것을 Animal 변수에 대입하면 자동 타입 변환이 일어난다.

<img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20191229160703786.png" alt="image-20191229160703786" style="zoom:50%;" />

- 위 코드로 생성되는 메모리 상태를 그림으로 묘사하면 다음과 같다.  cat과 animal 변수는 타입만 다를 뿐, 동일한 Cat객체를 참조한다.

<img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20191229160751721.png" alt="image-20191229160751721" style="zoom:50%;" />

- 바로 위의 부모가 아니더라도 상속 계층에서 상위 타입이라면 자동 타입 변환이 일어날 수 있다. 다음 그림을 보면서 이해해보자.

<img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20191229161002649.png" alt="image-20191229161002649" style="zoom:50%;" />

- D객체는 B와 A 타입으로 자동 타입 변환이 될 수 있고, E객체는 C와 A 타입으로 자동 타입 변환이 될 수 있다. 그러나 D객체는 C타입으로 변환될 수 없고, 마찬가지로 E 객체는 B 타입으로 변환될 수 없다. 이유는 상속 관계가 아니기 때문이다. 
- **부모 타입으로 자동 타입 변환된 이후에는 부모 클래스에 선언된 필드와 메소드만 접근이 가능하다.** 비록 변수는 자식 객체를 참조하지만 변수로 접근 가능한 멤버는 부모 클래스 멤버로만 한정된다. 그러나 예외가 있는데, 메소드가 자식 클래스에서 오버라이딩되었다면 자식 클래스의 메소드가 대신 호출된다.  

<br>

## 7-7-2. 필드의 다형성

- **다형성이란 동일한 타입을 사용하지만 다양한 결과가 나오는 성질을 말한다.** 

- 주로 필드의 값을 다양화함으로써 실행 결과가 다르게 나오도록 구현하는데, 필드의 타입은 변함이 없지만, **실행 도중에 어떤 객체를 필드로 저장하느냐에 따라 실행 결과가 달라질 수 있다.** 이것이 필드의 다형성이다. 

  ```java
  class Car{
    Tire frontLeftTire = new Tire();
    Tire frontRightTire = new Tire();
    Tire backLeftTire = new Tire();
    Tire backRightTire = new Tire();
    
    void run(){...}
  }
  ```

  - 위의 코드에서 Car 클래스는 4개의 Tire 필드를 가지고 있다. Car 클래스로부터 Car 객체를 생성하면 4개의 Tire 필드에 각각 하나씩 Tire 객체가 들어가게 된다. 이때 Tire를 교체할 필요성이 생기면 다음과 같은 코드를 사용해서 교체할 수 있다.

  ```java
  Car myCar = new Car();
  myCar.frontRightTire = new HankookTire();
  myCar.backLeftTire = new KumhoTire();
  myCar.run();
  ```

  - Tire 클래스 타입인 frontRightTire 와 backLeftTire는 원래 Tire 객체가 저장되어야 하지만, Tire의 자식 객체가 저장되어도 문제가 없다. 왜냐하면 **자식 타입은 부모 타입으로 자동 타입 변환이 되기 때문이다.**
  - frontRightTire와 backLeftTire에 Tire 자식 객체가 저장되어도 Car 객체는 Tire 클래스에 선언된 필드와 메소드만 사용하므로 전혀 문제가 되지 않는다. 

<br>

## 7-7-3. 하나의 배열로 객체 관리

- 앞의 예제에서 Car 클래스에 4개의 타이어 객체를 4개의 필드로 각각 저장했다. 우리는 동일한 타입의 값들은 배열로 관리하는 것이 유리하다는 것을 알고 있다. 그렇다면 타이어 객체들도 타이어 배열로 관리하는 것이 코드를 깔끔하게 만들어줄 것이다.

  ```java
  class Car{
    Tire frontLeftTire = new Tire("앞왼쪽", 6);
    Tire frontRightTire = new Tire("앞오른쪽", 2);
    Tire backLeftTire = new Tire("뒤왼쪽", 3);
    Tire backRightTire = new Tire("뒤오른쪽", 4);
  }
  
  //-----------------------------------------------
  class Car {
    Tire[] tires = {
      new Tire("앞왼쪽", 6),
      new Tire("앞오른쪽", 2),
      new Tire("뒤왼쪽", 3),
      new Tire("뒤오른쪽", 4)
    };
  } 
  ```

  - 배열로 관리를 하게된다면 인덱스로 표현할수 있으므로 대입이나 제어문에서 활용하기 매우 쉽다. 예를 들어 인덱스1을 이용해서 앞오른쪽 타이어를 KumhoTire로 교체하기 위해 다음과 같이 작성할 수 있다.

  ```java
  tires[1] = new KumhoTire("앞오른쪽", 13);
  ```

  - tires 배열의 각 항목은 Tire 타입이므로 자식 객체인 KumhoTire를 대입하면 자동 타입 변환이 발생하기 때문에 아무런 문제가 없다. 

<br>

## 7-7-4. 매개 변수의 다형성

- 자동 타입 변환은 필드의 값을 대입할 때에도 발생하지만, **주로 메소드를 호출할 때 많이 발생**한다.

- 메소드를 호출할 때에는 매개 변수의 타입과 동일한 매개값을 지정하는 것이 정석이지만, **매개값을 다양화하기 위해 매개 변수에 자식 타입 객체를 지정할 수도 있다.**

- ex) Driver 클래스에는 drive() 메소드가 정의되어 있는데 Vehicle 타입의 매개 변수가 선언되어 있다.

  ```java
  class Driver{
    void drive(Vehicle vehicle){
      vehicle.run();
    }
  }
  ```

  - drive 메소드를 정상적으로 호출한다면 다음과 같을 것이다.

  ```java
  Driver driver = new Driver();
  Vehicle vehicle = new Vehicle();
  driver.drive(vehicle);
  ```

- 만약 Vehicle의 자식 클래스인 Bus 객체를 drive() 메소드의 매개값으로 넘겨준다면 어떻게 될까?

<img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200105204103996.png" alt="image-20200105204103996" style="zoom:70%;"/>

<img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200105204518212.png" alt="image-20200105204518212" style="zoom:70%;" />

- drive() 메소드는 Vehicle 타입을 매개 변수로 선언했지만, Vehicle을 상속받는 Bus 객체가 매개값으로 사용되면 자동 타입 변환이 발생한다.

  ```java
  Vehicle vehicle = bus;   //자동타입변환
  ```

- 우리는 여기서 매우 중요한 것을 하나 알게 되었다. 매개 변수의 타입이 클래스일 경우, 해당 클래스의 객체뿐만 아니라 **자식 객체까지도 매개값으로 사용**할 수 있다는 것이다.

<br>

## 7-7-5. 강제 타입 변환(Casting)

- 강제 타입 변환(Casting)은 **부모 타입을 자식 타입으로 변환하는 것을 말한다.** 그렇다고 모든 부모 타입을 자식 클래스 타입으로 강제 변환할 수 있는 것은 아니다.
- 자식 타입이 부모 타입으로 자동 변환한 후, 다시 자식 타입으로 변환할 때 강제 타입 변환을 사용할 수 있다. 

<img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200105204930028.png" alt="image-20200105204930028" style="zoom:50%;" />

- 자식 타입이 부모 타입으로 자동 변환하면, 부모 타입에 선언된 필드와 메소드만 사용 가능하다는 제약 사항이 따른다. 
- 만약 자식 타입에 선언된 필드와 메소드를 꼭 사용해야 한다면 강제 타입 변환을 해서 다시 자식 타입으로 변환한 다음 자식 타입의 필드와 메소드를 사용하면 된다. 

<br>

## 7-7-6. 객체 타입 확인(instanceof)

- 강제 타입 변환은 자식 타입이 부모 타입으로 변환되어 있는 상태에서만 가능하기 때문에 다음과 같이 부모 타입의 변수가 부모 객체를 참조할 경우 자식 타입으로 변환할 수 없다.

  ```java
  Parent parent = new Parent();
  Child child = (Child) parent; // 강제 타입 변환을 할 수 없다.
  ```

- 어떤 객체가 어떤 클래스의 인스턴스인지 확인하려면 **instanceof 연산자**를 사용할 수 있다. 

- instanceof 연산자의 **좌항은 객체**가 오고, **우항은 타입**이 오는데, 좌항의 객체가 우항의 타입으로 객체가 생성되었다면 true를 산출하고 그렇지 않으면 false를 산출한다. 

<img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200105205515754.png" alt="image-20200105205515754" style="zoom:80%;" />

- 메소드 내에서 강제 타입 변환이 필요할 경우 반드시 매개값이 어떤 객체인지 instanceof 연산자로 확인하고 안전하게 강제 타입 변환을 해야한다. <img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200105210201552.png" alt="image-20200105210201552" style="zoom:70%;" />

<br>

# 7-8. 추상 클래스

## 7-8-1. 추상 클래스의 개념

- 사전적 의미로 추상(abstract)은 **실체 간에 공통되는 특성을 추출한 것을 말한다.**
- 클래스에서도 추상 클래스가 존재한다. 객체를 직접 생성할 수 있는 클래스를 **실체 클래스**라고 한다면 이 클래스들의 공통적인 특성을 추출해서 선언한 클래스를 **추상 클래스**라고 한다. 
- 추상 클래스와 실체 클래스는 **상속의 관계**를 가지고 있다. 추상 클래스가 부모이고 실체 클래스가 자식으로 구현되어 실체 클래스는 추상 클래스의 모든 특성을 물려받고, 추가적인 특성(필드, 메소드)을 가질 수 있다. 

<img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200105210526465.png" alt="image-20200105210526465" style="zoom:70%;" />

- 추상 클래스는 실체 클래스의 공통되는 필드와 메소드를 추출해서 만들었기 때문에 객체를 직접 생성해서 사용할 수 없다. 다시 말해서 **추상 클래스**는 **new 연산자를 사용해서 인스턴스를 생성시키지 못한다.**

  ```java
  Animal animal = new Animal();    // <- x 
  ```

- 추상 클래스는 새로운 실체 클래스를 만들기 위해 부모 클래스로만 사용된다. 

- 코드로 설명하면 추상 클래스는 extends 뒤에만 올 수 있는 클래스이다. 

  ```java
  class Ant extends Animal{...}
  ```

<br>

## 7-8-2. 추상 클래스의 용도

- 실체 클래스들의 공통적인 특성(필드, 메소드)을 뽑아내어 추상 클래스로 만드는 다음 두 가지 이유를 살펴보자.

#### 첫 번째, 실체 클래스들의 공통된 필드와 메소드의 이름을 통일할 목적

- 실체 클래스를 설계하는 사람이 여러 사람일 경우, 실체 클래스마다 필드와 메소드가 제각기 다른 이름을 가질 수 있다.  동일한 데이터와 기능임에도 불구하고 이름이 다르다 보니, 객체마다 사용 방법이 달라진다. 

#### 두 번째, 실체 클래스를 작성할 때 시간을 절약

- 공통적인 필드와 메소드는 추상 클래스에 모두 선언해 두고, 실체 클래스마다 다른 점만 실체 클래스에 선언하게 되면 시간을 절약할 수 있다. 

<br>

## 7-8-3. 추상 클래스 선언

- 추상 클래스를 선언할 때에는 클래스 선언에 **abstract**키워드를 붙여야 한다. 
- abstract를 붙이게 되면 **new 연산자를 이용해서 객체를 만들지 못하고**, **상속을 통해 자식 클래스만 만들 수 있다.**

<img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200105211410641.png" alt="image-20200105211410641" style="zoom:70%;" />

<br>

## 7-8-4. 추상 메소드와 오버라이딩

- 추상 클래스는 **실체 클래스가 공통적으로 가져야 할 필드와 메소드들을 정의해 놓은 추상적인 클래스**이므로 실체 클래스의 멤버(필드, 메소드)를 **통일화**하는데 목적이 있다. 

- 모든 실체들이 가지고 있는 메소드의 실행 내용이 동일하다면 추상 클래스에 메소드를 작성하는 것이 좋을 것이다. 

- 하지만 메소드의 선언만 통일화하고, 실행 내용은 실체 클래스마다 달라야 하는 경우가 있다.

-  예를 들어 모든 동물은 소리를 내기 때문에 Animal 추상 클래스에 sound()라는 메소드를 정의했다고 하자. 그렇다면 어떤 소리를 내도록 해야 하는데, **이것은 실체에서 직접 작성해야 될 부분임을 알게 된다.** 왜냐하면 동물은 다양한 소리를 내기 때문에 이것을 추상 클래스에서 통일적으로 작성할 수 없기 때문이다. 그렇다고 해서 sound() 메소드를 실체에서 작성하도록 하면 sound() 메소드를 잊어버리고 작성하지 않을 수도 있기 때문에 동물은 소리를 낸다는 것에 위배된다. 

- 이런 경우를 위해서 추상 클래스는 **추상 메소드**를 선언할 수 있다.

- 추상 메소드는 추상 클래스에서만 선언할 수 있는데, 메소드의 선언부만 있고 메소드 실행 내용인 중괄호{}가 없는 메소드를 말한다. 

- 추상 클래스를 설계할 때, 하위 클래스가 반드시 실행 내요을 채우도록 강요하고 싶은 메소드가 있을 경우, 해당 메소드를 추상 메소드로 선언하면 된다. 자식 클래스는 반드시 추상 메소드를 재정의(오버라이딩) 해서 실행 내용을 작성해야 한다. 

  ```java
  [public | protected] abstract 리턴타입 메소드명(매개변수, ...);
  ```

  - 일반 메소드 선언과의 차이점은 abstract 키워드가 붙어 있고 메소드 중괄호 { }가 없다. 다음은 Animal 클래스를 추상 클래스로 선언하고 sound() 메소드를 추상 메소드로 선언한 것이다.

  ```java
  public abstract class Animal{
    public abstract void sound();
  }
  ```

  <img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200105214935001.png" alt="image-20200105214935001" style="zoom:70%;" />

<img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200105214945664.png" alt="image-20200105214945664" style="zoom:70%;" />







































