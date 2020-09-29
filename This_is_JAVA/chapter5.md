# Chpater5. 참조 타입

# 5-1. 데이터 타입 분류

- 자바의 데이터 타입에는 크게 **기본 타입(원시 타입:primitive type)** 과 **참조 타입(reference type) ** 으로 분류된다.
- 기본 타입이란 정수, 실수, 문자, 논리 리터럴을 저장하는 타입을 말한다.
- **참조 타입이란 객체(Object)의 번지를 참조하는 타입으로 배열, 열거, 클래스, 인터페이스 타입을 말한다.**

![자바 참조타입에 대한 이미지 검색결과](https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F21321F34586F19FD07)

- 기본 타입인 byte, char, short, int, long, float, double, boolean을 이용해서 선언된 변수는 실제 값을 변수 안에 저장하지만, **참조 타입인 배열, 열거, 클래스, 인터페이스를 이용해서 선언된 변수는 메모리의 번지를 값으로 갖는다.** 번지를 통해 객체를 참조한다는 뜻에서 참조 타입이라고 부른다.

- 예를 들어 int와 double로 선언된 변수 age와 price가 있고, String 클래스로 선언된 name과 hobby가 다음과 같이 선언되어 있다고 가정해보자.



```java
int age = 25;
double price = 100.5;

String name = "신용권";
String hobby = "독서";
```

<img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20191017152833465.png" alt="image-20191017152833465" style="zoom:50%;" />

- - int와 double 변수인 age와 price는 직접 값을 저장하고 있지만, String 클래스 변수인 name과 hobby는 힙 영역의 String 객체 주소 값을 가지고 있다. 



# 5-2. 메모리 사용 영역

- 다음 사진은 JVM이 사용하는 메모리 영역에 대한 사진이다. java.exe로 JVM이 시작되면 JVM은 운영체제에서 할당받은 메모리 영역(Runtime Data Area)을 다음과 같이 세부 영역으로 구분해서 사용한다.

<img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20191017154102580.png" alt="image-20191017154102580" style="zoom:50%;" />

### 5-2-1. 메소드(Method)영역

- 메소드 영역에는 코드에서 사용되는 클래스(~.class)들을 클래스 로더로 읽어 클래스별로 런타임 상수풀(runtim constant pool), 필드(field)데이터, 메소드(method)데이터, 메소드 코드, 생성자(constructor) 코드 등을 분류해서 저장한다. 
- 메소드 영역은 JVM이 시작할 때 생성되고 모든 스레드가 공유하는 영역이다.

### 5-2-2. 힙(Heap) 영역

- 힙 영역은 **객체와 배열이 생성되는 영역**이다. 힙 영역에 생성된 객체와 배열은 **JVM 스택 영역의 변수나 다른 객체의 필드에서 참조한다.** 
- 참조하는 변수나 필드가 없다면 의미 없는 객체가 되기 때문에 이것을 쓰레기로 취급하고 JVM은 쓰레기 수집기(Garbage-Collector) 를 실행시켜 쓰레기 객체를 힙 영역에서 자동으로 제거한다.

### 5-2-3. JVM 스택(Stack) 영역

- JVM 스택 영역은 각 스레드마다 하나씩 존재하며 스레드가 시작될 때 할당된다. 추가적으로 스레드를 생성하지 않았다면 main 스레드만 존재하므로 JVM 스택도 하나이다. 
- JVM 스택은 메소드를 호출할 때마다 프레임(Frame)을 추가(push)하고 메소드가 종료되면 해당 프레임을 제거(pop)하는 동작을 수행한다. 
  - 프레임 내부에는 로컬 변수 스택이 있는데, 기본 타입 변수와 참조 타입 변수가 추가(push)되거나 제거(pop)된다. **변수가 이 영역에 생성되는 시점은 초기화가 될 때, 즉 최초로 변수에 값이 저장될 때**이다. 
  - **변수는 선언된 블록 안에서만 스택에 존재하고 블록을 벗어나면 스택에서 제거된다.**

## 5-3. 참조 변수의 ==, != 연산

- 기본 타입 변수의 ==, != 연산은 변수의 값이 같은지, 아닌지를 조사하지만 **참조 타입 변수들 간의 ==, != 연산은 동일한 객체를 참조하는지, 다른 객체를 참조하는지 알아볼 때 사용된다.**
- 참조 타입 변수의 값은 힙 영역의 객체 주소이므로 결국 **주소 값을 비교하는 것이 된다.**

<img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20191017155822497.png" alt="image-20191017155822497" style="zoom:50%;" />

```java
refVar1 == refVar2;   //결과 : false
refVar1 != refVar2;   //결과 : true
```

```java
refVar2 == refVar3;   //결과 : true
refVar2 != refVar3;   //결과 : false
```



## 5-4. null과 NullPointerException

- 참조 타입 변수는 힙 영역의 객체를 참조하지 않는다는 뜻으로 **null 값을 가질 수 있다.** null 값도 초기값으로 사용할 수 있기 대문에 null로 초기화된 참조 변수는 **스택 영역에 생성된다.**

<img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20191017160310911.png" alt="image-20191017160310911" style="zoom:50%;" />

- 참조 타입 변수가 null 값을 가지는지 확인하려면 ==, != 연산을 수행하면 된다.

```java
refVar1 == refVar2;  //결과 : false
refVar1 != refVar2;  //결과 : true

refVar2 == null;     //결과 : true
refVar2 != null;     //결과 : false
```

- **참조 타입 변수가 null을 가지고 있을 경우, 참조 타입 변수는 사용할 수 없다.** 참조 타입 변수를 사용하는 것은 곧 객체를 
- 사용하는 것을 의미하는데, 참조할 객체가 없으므로 사용할 수가 없는 것이다.

```java
int[] intArray = null;
intArray[0] = 10;        //NullPointerException
```



## 5-5. String 타입

- 자바는 문자열을 String 변수에 저장하고, String 변수를 우선 선언해야한다..  String 변수에 문자열을 저장하려면 큰 따옴표로 감싼 문자열 리터럴을 대입하면 된다.

  ```java
  String name;
  name = "신용권";
  String hobby = "자바";
  ```

<img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20191017161848962.png" alt="image-20191017161848962" style="zoom:50%;" />

- 문자열을 String 변수에 저장한다는 말은 틀린 표현이다. 문자열이 직접 변수에 저장되는 것이 아니라, 문자열은 String 객체로 생성되고 변수는 String 객체를 참조한다.
- 위 그림을 보면 name변수와 hobby변수는 스택 영역에 생성되고, 문자열 리터럴인 "신용권"과 "자바"는 힙 영역에 String 객체로 생성된다. 그리고 **name변수와 hobby변수에는 String 객체의 주소 값이 저장된다.** 

---------------------------------------------------------------------------------------------------------------------------------------------------------

<img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20191017162237239.png" alt="image-20191017162237239" style="zoom:50%;" />

- 자바는 문자열 리터럴이 동일하다면 String객체를 공유하도록 되어 있다. 다음과 같이 name1과 name2 변수가 동일한 문자열 리터럴인 "신용권"을 참조할 경우 name1과 name2는 동일한 String 객체를 참조하게 된다.

```java
String name1 = "신용권";
String name2 = "신용권";
```

-----------------------------------------------------------------------------------------------------------------------------------------------------------

<img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20191017164945380.png" alt="image-20191017164945380" style="zoom:50%;" />

- 일반적으로 변수에 문자열을 저장할 경우에는 문자열 리터럴을 사용하지만, new 연산자를 사용해서 직접 String 객체를 생성시킬 수도 있다.
- **new 연산자는 힙 영역에 새로운 객체를 만들 때 사용하는 연산자로 객체 생성 연산자라고도 한다.**

```java
String name1 = new String("신용권");
String name2 = new String("신용권");
```

- 이 경우 name1과 name2는 서로 다른 String 객체를 참조한다.

-----------------------------------------------------------------------------------------------------------------------------------------------------------

- ==, != 연산은 문자열 리터럴로 생성하느냐 new 연산자로 생성하느냐에 따라 비교 연산자의 결과가 달라질 수 있다. ==연산자는 변수에 저장된 객체 번지가 동일한지를 검사하기 때문이다.

  ```java
  String name1 = "신민철";
  String name2 = "신민철";
  String name3 = new String("신민철");
  ```

- name1과 name2는 동일한 문자열 리터럴로 생성된 객체를 참조하기 때문에 name1==name2의 결과는 true가 나온다. 그러나 name3은 new 연산자로 String 객체를 별도로 생성했기 때문에 name1==name3는 false가 나온다. 

- 동일한 String 객체이건 다른 String객체이건 상관없이 **문자열만을 비교할 때에는 String 객체의 equals() 메소드를 사용해야 한다.** 

  ```java
  boolean result = str1.equals(str2);
  ```

----------------------------------------------------------------------------------------------------------------------------------------------------------

- String 변수는 참조 타입이므로 초기값으로 null을 대입할 수 있다. null은 String 변수가 참조하는 String 객체가 없다는 뜻이다. 

  ```java
  String hobby = null;
  ```

- 다음 코드처럼 hobby 변수가 String 객체를 참조하였으나, null을 대입함으로써 더 이상 String객체를 참조하지 않도록 할 수도 있다.

  ```java
  String hobby = "여행";
  hobby = null;
  ```

- 그렇다면 참조를 잃은 String 객체는 어떻게 될까? JVM은 참조되지 않은 객체를 쓰레기 객체로 취급하고 쓰레기 수집기를 구동시켜 메모리에서 **자동 제거한다.**

## 5-6. 배열 타입

### 5-6-1. 배열이란?

- 변수는 한 개의 데이터만 저장할 수 있다. 따라서 저장해야 할 데이터의 수가 많아지면 그만큼 많은 변수가 필요하다. 
- 같은 타입의 많은 양의 데이터를 다루기 효율적인 방법은 배열을 사용하는 것이다. 
- 배열은 같은 타입의 데이터를 연속된 공간에 나열시키고, 각 데이터에 인덱스(index)를 부여해 놓은 자료구조이다. 

<img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20191017171635171.png" alt="image-20191017171635171" style="zoom:50%;" />

- 배열은 같은 타입의 데이터만 저장할 수 있다. 한 번 생성된 배열은 길이를 늘리거나 줄일 수 없다.  배열의 길이를 수정하기 위해선 새로운 배열을 생성하고, 기존 배열 항목을 새 배열로 복사해야 한다. 

### 5-6-2. 배열 선언

- 배열을 사용하기 위해서는 우선 배열 변수를 선언해야 한다. 배열 변수 선언은 다음과 같이 두 가지 형태로 작성할 수 있다.

  ```java
  타입[] 변수;  //자바에서 보통 사용하는 형태.
  ----------
  타입 변수[];
  ```

- **배열 변수는 참조 변수에 속한다.** 배열도 객체이므로 힙 영역에 생성되고 배열 변수는 힙 영역의 배열 객체를 참조하게 된다. 참조할 배열 객체가 없다면 배열 변수는 null 값으로 초기화될 수 있다.

  ```java
  타입[] 변수 = null;
  ```

  - 만약 배열 변수가 null 값을 가진 상태에서 변수[인덱스]로 값을 읽거나 저장하게 되면 NullPointerException이 발생한다. 

### 5-6-3. 값 목록으로 배열 생성

- 배열 항목에 저장될 값의 목록이 있다면, 다음과 같이 간단하게 배열 객체를 만들 수 있다.

  ```java
  데이터타입[] 변수 = {값0, 값1, 값2, 값3, ...}
  ```

  <img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20191017172400649.png" alt="image-20191017172400649" style="zoom:50%;" />

- 중괄호 { }는 주어진 값들을 항목으로 가지는 배열 객체를 힙에 생성하고, 배열 객체의 번지를 리턴한다.

  ```java
  String[] names = {"신용권", "홍길동", "감자바"};
  ```

  - 이렇게 생성된 배열에서 "신용권"은 names[0], "홍길동"은 names[1], "감자바"는 names[2]로 읽을 수 있다.

  - names[1]의 "홍길동"을 "홍삼원"으로 바꾸고 싶다면 다음과 같이 대입 연산자를 사용하면 된다.

    ```java
    names[1] = "홍삼원";
    ```

- 값의 목록으로 배열 객체를 생성할 때 주의할 점이 있는데, **배열 변수를 이미 선언한 후에 다른 실행문에서 중괄호를 사용한 배열 생성은 허용되지 않는다.**

  ```java
  타입[] 변수;
  변수 = {값0, 값1, 값2, 값3, ...};   //컴파일 에러
  ```

  ```java
  변수 = new 타입[]{값0, 값1, 값2, 값3, ...};
  ```

  - 위의 new 연산자에 대한 예를 들면 배열 names를 다음과 같이 생성할 수 있다.

  ```java
  String[] names = null;
  names = new Stringp[]{"신용권", "홍길동", "감자바"};
  ```

- 메소드의 매개값이 배열일 경우에도 마찬가지이다. 아래와 같이 매개 변수로 int[ ] 배열이 선언된 add()메소드가 있을 경우, 값 목록으로 배열을 생성함과 동시에 add() 메소드의 매개값으로 사용하고자 할 때는 반드시 new 연산자를 사용해야 한다.

  ```java
  int add(int[] scores){...}
  ----------------------------------
  int result = add({95, 85, 90});  //컴파일 에러
  int result = add(new int[] {95, 85, 90});
  ```

### 5-6-4. new 연산자로 배열 생성

- 값의 목록을 가지고 있지 않지만, 향후 값들을 저장할 배열을 미리 만들고 싶다면 new 연산자로 다음과 같이 배열 객체를 생성시킬 수 있다.

  ```java
  타입[] 변수 = new 타입[길이];
  ```

- **길이는 배열이 저장할 수 있는 값의 수를 말한다.** new 연산자로 배열을 생성할 경우에는 이미 배열 변수가 선언된 후에도 가능한다.

  ```java
  타입[] 변수 = null;
  변수 = new 타입[길이];
  ```

- 다음은 길이 5인 int[ ] 배열을 생성한다.

  ```java
  int[] intArray = new int[5];
  ```

  - 자바는 intArray[0]~intArray[4]까지 값이 저장될 수 있는 공간을 확보하고, 배열의 생성 번지를 리턴한다. 리턴된 번지는 intArray 변수에 저장된다. 각각의 항목 크기는 다음과 같이 int 타입의 크기인 4byte 이다.

    <img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20191017224325976.png" alt="image-20191017224325976" style="zoom:50%;" />

- new 연산자로 배열을 처음 생성할 경우 배열은 자동적으로 기본값으로 초기화된다. 학생 30명의 점수를 저장할 배열을 다음과 같이 생성한다고 가정해보자.

  ```java
  int[] scores = new int[30];
  ```

  - scores 배열은 int 타입 배열이므로 다음과 같이 scores[0]~scores[29]까지 모두 기본값 0으로 초기화된다.

- 만약 String 배열을 생성했다면 names[0]에서 names[29]까지 모두 null 값으로 초기화 된다.

- 다음은 타입별로 배열의 초기값을 보여준다.

<img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20191017224642842.png" alt="image-20191017224642842" style="zoom:50%;" />

- 배열이 생성되고 나서 새로운 값을 저장하려면 대입 연산자를 사용하면 된다.

  ```java
  변수[인덱스] = 값;
  ```

  - 예를 들어 배열 scores의 0,1,2 인덱스에 각각 83, 90, 75를 저장하는 코드는 다음과 같다.

    ```java
    int[] scores = new int[3];
    scores[0] = 83;
    scores[1] = 90;
    scores[2] = 75;
    ```

### 5-6-5. 배열 길이

- 배열의 길이란 배열에 저장할 수 있는 전체 항목 수를 말한다. 

- 코드에서 배열의 길이를 얻으려면 다음과 같이 **배열 객체의 length 필드를 읽으면 된다.** 

  ```java
  배열변수.length;
  ```

  ```java
  int[] intArray = {10, 20, 30};
  int num = intArray.length;
  //result = 3
  ```

  - **length 필드는 읽기 전용 필드이기 때문에 값을 바꿀 수가 없다**. 그래서 다음과 같이 작성하면 안된다.

  ```java
  intArray.length = 10;   // 잘못된 코드
  ```

### 5-6-6. 커맨드 라인 입력

- 우리는 이제 프로그램 실행을 위해 main() 메소드가 필요하다는 것을 알고 있다. 하지만 main() 메소드의 매개값인           String[ ] args 가 왜 필요한 것인지 궁금하였을 것이다. 이제 이 궁금증을 풀어보자.

  ```java
  public static void main(String[] args){...}
  ```

- "java 클래스" 로 프로그램을 실행하려면 JVM은 길이가 0인 String 배열을 먼저 생성하고 main() 메소드를 호출할 때 매개값으로 전달한다.
- 만약 다음과 같이 "java 클래스" 뒤에 공백으로 구분된 문자열 목록을 주고 실행하면, 문자열 목록으로 구성된 String[ ] 배열이 생성되고 main() 메소드를 호출할 때 매개값으로 전달된다.

<img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20191018002738787.png" alt="image-20191018002738787" style="zoom:50%;" />

- main() 메소드는 String[ ] args 매개 변수를 통해서 커맨드 라인에서 입력된 데이터의 수(배열의 길이)와 입력된 데이터(배열의 항목 값)를 알 수 있게 된다.

- **ex)** "java Foo" 이런 명령으로, Foo.class 라는 자바프로그램을 실행할 때 **java Foo ABC DEFG HIJK** 와 같이 뒤쪽에 문자열을 붙여서 실행 했다면

  ABC // DEFG // HIJK  이런 3개의 옵션들이 args[ ] 라는 배열에 자동으로 들어가고, 프로그래머는 이 배열에서 값을 꺼내서 사용하면 됩니다.

- args = Argument(인수, 매개 변수, 파라미터) 라는 뜻.

### 5-6-7. 다차원 배열

- 지금까지 살펴본 배열은 값 목록으로 구성된 1차원 배열이다. 이와는 달리 값들이 행과 열로서 구성된 배열을 2차원 배열이라고 한다. 

- 2차원 배열은 수학의 행렬을 떠올리면 되는데, 가로 인덱스와 세로 인덱스를 사용한다. 

- 예를 들어 2(행) X 3(열) 행렬을 만들기 위해 다음과 같은 코드를 사용한다.

  ```java
  int[][] scores = new int[2][3];
  ```

  <img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20191018011030532.png" alt="image-20191018011030532" style="zoom:50%;" />

<img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20191018011041317.png" alt="image-20191018011041317" style="zoom:50%;" />

- 생성 원리는 수학 행렬과는 근본적으로 다르지만 사용 방식은 행렬과 동일하다. scores[0] [1]  은 배열 B의 인덱스 1값을 뜻한다. 수학 행렬에서는 (0,1)값이라고 볼 수 있다.

- 자바는 일차원 배열이 서로 연결된 구조로 다차원 배열을 구현하기 때문에 수학 행렬 구조가 아닌 계단식 구조를 가질 수 있다.

  ```java
  int[][] scores = new int[2][];
  scores[0] = new int[2];
  scores[1] = new int[3];
  ```

  - 위의 경우 배열 항목의 수를 조사해보면 다음과 같다.

  ```java
  scores.length // 2(1번 라인)
  scores[0].length. // 2(2번 라인)
  scofes[1].length  // 3(3번 라인)
  ```

- 이런 형태의 배열에서 주의할 점은 정확한 배열의 길이를 알고 인덱스를 사용해야 한다. scores[0] [2]는 ArrayIndexOutOfBoundsException을 발생시킨다. 이유는 2번라인 객체의 마지막 인덱스는 1이기 때문이다.
- scores[1] [2]는 scores[2] 로 나타낸다.

### 5-6-8. 객체를 참조하는 배열

- 기본 타입(byte, char, short, int, long, float, double, boolean) 배열은 각 항목에 직접 값을 갖고 있지만, **참조 타입(클래스, 인터페이스) 배열은 각 항목에 객체의 번지를 가지고 있다.** 

- 예를 들어 String은 클래스 타입이므로 String[ ] 배열은 각 항목에 문자열이 아니라 String 객체의 주소를 가지고 있다. 즉, **String 객체를 참조하게 된다.**

  ```java
  String[] strArray = new String[3];
  strArray[0] = "Java";
  strArray[1] = "C++";
  strArray[2] = "C#";
  ```

  - 위코드는 배열 변수 strArray를 선언하고3개의 문자열을 참조하는 배열을 생성한다. 그림으로 표현하면 다음과 같다.

  <img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20191018135306080.png" alt="image-20191018135306080" style="zoom:50%;" />

- 따라서 String[ ] 배열의 항목도 결국 String 변수와 동일하게 취급되어야 한다. 예를 들어 **String[ ] 배열 항목 간에 문자열을 비교하기 위해서는 == 연산자 대신  equals() 메소드를 사용해야 한다.** 왜냐하면 ==는 객체의 번지 비교이기 때문에 문자열 비교에 사용할 수 없다.

  ```java
  String[] strArray = new String[3];
  strArray[0] = "Java";
  strArray[1] = "Java";
  strArray[2] = new String("Java");
  
  System.out.println( strArray[0] == strArray[1]);  //true(같은 객체를 참조)
  System.out.println( strArray[0] == strArray[2]);  //flase(다른 객체를 참조)
  System.out.println( strArray[0].equals(strArray[2])); //true(문자열이 같음)
  ```

  - strArray[0]과 strArray[1] 배열 항목을 == 연산하면 결과는 true 가 나온다. 이유는 동일한 String객체를 참조하기 때문이다. 반면에 String 객체를 new 연산자로 생성하면, 무조건 새로운 String 객체가 생성되기 때문에 strArray[0]과 strArray[2] 배열 항목을 == 연산하면 결과는 false 가 나온다. 

### 5-6-9. 배열 복사

- 배열은 한 번 생성하면 크기를 변경할 수 없기 때문에 더 많은 저장 공간이 필요하다면 보다 큰 배열을 새로 만들고 이전 배열로부터 항목 값들을 복사해야 한다. 

- **배열 간의 항목 값들을 복사하려면 for문을 사용하거나 System.arraycopy() 메소드를 사용하면 된다.**

- 배열 복사를 하게 되면 복사되지 않은 항목은 int[ ] 배열의 기본 초기값 0이 그대로 유지된다.

  ```java
  Systsm.arraycopy(원본 배열, 원본 배열의 시작 인덱스, 새 배열, 새 배열의 시작 인덱스, 복사 할 개수)
  ex) System.arraycopy(arr1, 0, arr2, 0, arr1.length);
  ```

- 참조 타입 배열일 경우, 배열 복사가 되면 복사되는 값이 객체의 번지이므로 새 배열의 항목은 이전 배열의 항목이 참조하는 객체와 동일하다. 이것을 **얕은 복사(shallow copy)**라고 한다.
- 반대로 **깊은 복사(deep copy)**는 참조하는 객체도 별도로 생성하는 것을 말한다. 



<br>

### 5-6-10. 향상된 for문

- **향상된 for문은 반복 실행을 하기 위해 카운터 변수와 증감식을 사용하지 않는다.** 배열 및 컬렉션 항목의 개수만큼 반복하고, 자동적으로 for문을 빠져나간다. 

<img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20191018141423270.png" alt="image-20191018141423270" style="zoom:50%;" />

- for문의 괄호( )에는 배열에서 꺼낸 항목을 저장할 변수 선언과 콜론(:) 그리고 배열을 나란히 작성한다. 
  - for문이 처음 실행될 때 **1-배열**에서 가져올 첫 번째 값이 존재하는지 평가한다. 가져올 값이 존재하면 해당 값을 **2-변수**에 저장한다. 그리고 **3-실행문**을 실행한다. 
  - 블록 내부의 실행문이 모두 실행되면 다시 루프를 돌아 1-배열에서 가져올 다음 값이 존재하는지 평가한다. 만약 다음 항목이 존재하면 2-3-1 순서로 다시 진행하고, 가져올 다음 항목이 없으면 for문이 종료된다. 
  - 따라서 for문의 반복 횟수는 배열의 항목 수가 된다.

## 5-7. 열거 타입

- **한정된 값만을 갖는 데이터 타입이 열거 타입이다.** 열거 타입은 몇 개의 열거 상수 주에서 하나의 상수를 저장하는 데이터 타입이다.
- 예를 들어 요일에 대한 데이터는 월, 화, 수, 목, 금, 토, 일이라는 일곱 개의 값만을 갖고, 계절에 대한 데이터는 봄, 여름, 가을, 겨울이라는 네 개의 값만을 가진다. 

### 5-7-1. 열거 타입 선언

-  열거 타입을 선언하기 위해서는 먼저 열거 타입의 이름을 정하고 열거 타입 이름으로 소스 파일(.java)을 생성해야 한다. 

- 열거 타입 이름은 관례적으로 첫 문자를 대문자로 하고 나머지는 소문자로 구성한다. 만약 여러 단어로 구성된 이름이라면 단어 첫 문자는 대문자로 하는것이 관례이다. 

  ```java
  Week.java
  MemberGrade.java
  ProductKind.java
  ```

- ##### 소스 파일의 내용으로는 다음과 같이 열거 타입 선언이 온다. public enum키워드는 열거 타입을 선언하기 위한 키워드이다.  반드시 소문자로 작성해야 한다. 

  ```java
  public enum 열거타입이름{...};
  ```

  

- 열거 타입을 선언했다면 이제는 열거 상수를 선언하면 된다. 열거 상수는 열거 타입의 값으로 사용되는데, **관례적으로 열거 상수는 모두 대문자로 작성한다.**

  ```java
  public enum Week{MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, ...}
  ```

  - 만약 열거 상수가 여러 단어로 구성될 경우에는 단어 사이를 밑줄( _ )로 연결하는 것이 관례이다.

    ```
    public enum LoginResult{LOGIN_SUCCESS, LOGIN_FAILED}
    ```

###   5-7-2. 열거 타입 변수

- 열거 타입도 하나의 데이터 타입이므로 변수를 선언하고 사용해야 한다. 

  ```java
  열거타입 변수;
  ```

  - 예를 들어 열거 타입 Week로 변수를 선언하면 다음과 같다. 

    ```java
    Week today;
    Week reservationDay;
    ```

- 열거 타입 변수를 선언했다면 다음과 같이 열거 상수를 저장할 수 있다. 열거 상수는 단독으로 사용할 수는 없고 반드시 열거타입.열거상수로 사용된다.

  ```java
  열거타입 변수 = 열거타입.열거상수;
  ```

  - 예를 들어 today 열거 변수에 열거 상수인 SUNDAY를 저장하면 다음과 같다.

    ```java
    Week today = Week.SUMDAY;
    ```

- 열거 타입 변수는 null 값을 저장할 수 있는데 열거 타입도 참조 타입이기 때문이다.

  ```java
  Week birthday = null;
  ```

- 참조 타입 변수는 객체를 참조하는 변수라고 알고 있는데, 그렇다면 열거 상수는 객체일까? 그렇다. 열거 상수는 열거 객체로 생성된다. 열거 타입 Week의 경우 MONDAY부터 SUNDAY까지의 열거 상수는 다음과 같이 총 7개의 Week 객체로 생성된다. 그리고 메소드 영역에 생성된 열거 상수가 해당 Week 객체를 각각 참조하게 된다.

### 5-7-3. 열거 객체의 메소드

- 열거 객체는 열거 상수의 문자열을 내부 데이터로 가지고 잇다. 아래 그림은 열거 객체가 가지는 데이터 및 메소드들을 보여준다.

  <img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20191018143620277.png" alt="image-20191018143620277" style="zoom:50%;" /><img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20191018143723469.png" alt="image-20191018143723469" style="zoom:50%;" />

#### name() 메소드

- **name() 메소드는 열거 객체가 가지고 있는 문자열을 리턴한다.** 

  ```java
  Week today = Week.SUNDAY;
  String name = today.name();
  ```

  - 위 코드는 today가 참조하는 열거 객체에서 name() 메소드를 호출하여 문자열을 얻어낸다. name()메소드는 열거 객체 내부의 문자열인 "SUNDAY"를 리턴하고 name 변수에 저장한다.

#### ordinal() 메소드

- **ordinal() 메소드는 전체 열거 객체 중 몇 번째 열거 객체인지 알려준다.** 열거 객체의 순번은 열거 타입을 정의할 때 주어진 순번을 말하는데, **0번부터 시작한다.** 

  ```java
  Week today = Week.SUNDAY;
  int ordinal = today.ordinal();
  ```

#### compareTo() 메소드

- **compareTo() 메소드는 매개값으로 주어진 열거 객체를 기준으로 전후로 몇 번째 위치하는지를 비교한다.**

  ```java
  Week day1 = Week.MONDAY;
  Week day2 = Week.WEDNESDAY;
  int ersult1 = day1.compareTo(day2);  //-2
  int ersult1 = day2.compareTo(day1);  // 2 
  ```

  - 위 코드를 예로 들면, 3번 라인에서 day2를 기준으로 day1의 상대적 위치를 리턴한다. 즉 ( ) 안의 객체를 기준으로 비교가 진행된다.

#### valueOf() 메소드

- valueOf() 메소드는 매개값으로 주어지는 문자열과 동일한 문자열을 가지는 열거 객체를 리턴한다. 

- 이 메소드는 외부로부터 문자열을 입력받아 열거 객체로 변환할 때 유용하게 사용할 수 있다.

  ```java
  Week weekDay = Week.valueOf("SATURDAY");
  ```

#### values() 메소드

- values() 메소드는 열거 타입의 모든 열거 객체들을 배열로 만들어 리턴한다.

  ```java
  Week[] days = Week.values();
  for(Week day : days){
    System.out.println(day);
  
  ```





```java
int[] oldIntArray = {1, 2, 3};
int[] newIntArray = new int[5];

for(int i=0; i<oldIntArray.length; i++){
  newIntArray[i] = oldIntArray[i];
}

for(int i=0; i<newIntArray.length; i++){
  System.out.print(newIntArray[i]+",");
}

//실행 결과 : 1, 2, 3, 0, 0
```

