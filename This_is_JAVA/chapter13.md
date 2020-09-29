# chapter13. 제네릭

## 13-1. 왜 제네릭을 사용해야 하는가?

- Java5부터 제네릭 타입이 새로 추가되었는데, 제네릭 타입을 이용함으로써 잘못된 타입이 사용될 수 있는 문제를 컴파일 과정에서 제거할 수 있게 되었다.

- 제네릭은 **클래스와 인터페이스, 그리고 메소드를 정의할 때 타입(type)을 파라미터(parameter)로 사용**할 수 있도록 한다.

- 제네릭을 사용하는 코드는 비제네릭 코드에 비해 다음과 같은 **이점**을 가지고 있다.

  - **컴파일 시 강한 타입 체크를 할 수 있다.** : 자바 컴파일러는 코드에서 잘못 사용된 타입 때문에 발생하는 문제점을 제거하기 위해 제네릭 코드에 대한 강한 타입 체크를 한다. 실행 시 타입 에러가 나는 것보다는 컴파일 시에 미리 타입을 강하게 체크해서 **에러를 사전에 방지하는 것이 좋다.**
  - **타입 변환(casting)을 제거한다.** : 비제네릭 코드는 불필요한 타입 변환을 하기 때문에 프로그램 성능에 악영향을 미친다. 다음 코드를 보면 List에 문자열 요소를 저장했지만, 요소를 찾아올 때는 반드시 String으로 타입 변환을 해야 한다. 

  ```java
  List list = new ArrayList();
  list.add("hello");
  String str = (String) list.get(0); //타입 변환을 해야 한다. 
  ```

  - 하지만 다음과 같이 제네릭 코드로 수정하면 List에 저장되는 요소를 String 타입으로 국한하기 때문에 요소를 찾아올 때 타입 변환을 할 필요가 없어 프로그램 성능이 향상된다.

  ```java
  List<String> list = new ArrayList<String>();
  list.add("hello");
  String str = list.get(0);
  ```

<br>

## 13-2. 제네릭 타입(class< T >, interface< T > )

- 제네릭 타입은 **타입을 파라미터로 가지는 클래스와 인터페이스**를 말한다.
- 제네릭 타입은 클래스 또는 인터페이스 이름 뒤에 < > 부호가 붙고, 사이에 타입 파라미터가 위치한다. 

```java
public class 클래스명<T> {...}
public interface 인터페이스명<T> {...}
```

- 타입 파라미터는 변수명과 동일한 규칙에 따라 작성할 수 있지만, 일반적으로 대문자 알파벳 한 글자로 표현한다. 
- 제네릭 타입을 실제 코드에서 사용하려면 타입 파라미터에 **구체적인 타입을 지정**해야 한다. 
- 타입 파라미터를 사용하는 이유에 대하여 알아보자.

```java
public class Box{
  private Object object;
  public void set(Object object){
    this.object = object;
  }
  public Object get(){
    return object;
  }
}
```

- Box 클래스의 필드 타입이 Object인데, Object 타입으로 선언한 이유는 필드에 모든 종류의 객체를 저장하고 싶어서이다. **Object 클래스는 모든 자바 클래스의 최상위 부모 클래스이다.** 따라서 자식 객체는 부모 타입에 대입할 수 있다는 성질 때문에 모든 자바 객체는 Object 타입으로 자동 타입 변환되어 저장된다. 

  ```java
  Object object = 자바의 모든 객체;
  ```

- set( ) 메소드는 매개 변수 타입으로 Object를 사용함으로써 매개값으로 자바의 **모든 객체를 받을 수 있게 했고**, 받은 매개값을 Object 필드에 저장시킨다. 반대로 get( ) 메소드는 Object 필드에 저장된 객체를 Object 타입으로 리턴한다. 

- 만약 필드에 저장된 원래 타입의 객체를 얻으려면 다음과 같이 강제 타입 변환을 해야 한다. 

  ```java
  Box box = new Box();
  box.set("hello"); //String 타입을 Object 타입으로 자동 타입 변환해서 저장
  String str = (String) box.get(); //Object 타입을 String 타입으로 강제 타입 변환해서 얻음
  ```

<br>

- 이와 같이 Object 타입을 사용하면 모든 종류의 자바 객체를 저장할 수 있다는 장점이 있지만, 저장할 때 타입 변환이 발생하고, 읽어올 때에도 타입 변환이 발생한다. 

- 이러한 타입 변환이 빈번해지면 전체 프로그램 성능에 좋지 못한 결과를 가져올 수 있다. 

- 다음은 제네릭을 이용해서 Box 클래스를 수정한 것이다.

  ```java
  public class Box<T> {
    private T t;
    public T get(){
      return t;
    }
    public void set(T t){
      this.t = t;
    }
  }
  ```

  - 타입 파라미터 T를 사용해서 Object 타입을 모두 T로 대체했다. T는 Box클래스로 객체를 생성할 때 구체적인 타입으로 변경된다. 

  ```java
  Box<String> box = new Box<String>();
  ```

  - 타입 파라미터 T는 String 타입으로 변경되어 Box 클래스의 내부는 다음과 같이 자동으로 재구성 된다.

  ```java
  public class Box<String>{
    private Stirng t;
    public void set(String t){
      this.t = t;
    }
    public String get(){
      return t;
    }
  }
  ```

  - 필드 타입이 String으로 변경되었고, set( ) 메소드도 String 타입만 매개값으로 받을 수 있게 변경 되었다. 그리고    get( ) 메소드 역시 String 타입으로 리턴하도록 변경되었다. 
  - 그래서 **다음 코드를 보면 저장할 때와 읽어올 때 모두 타입 변환이 발생하지 않는다.**

  ```java
  Box<String> box = new Box<String>();
  box.set("hello");
  String str = box.get();
  ```

- 이와 같이 제네릭은 클래스를 설계할 때 구체적인 타입을 명시하지 않고, 타입 파라미터로 대체했다가 실제 클래스가 사용될 때 구체적인 타입을 지정함으로써 **타입 변환을 최소화** 시킨다.

<br>

## 13-3. 멀티 타입 파라미터(class<K, V, ...>, interface<K, V, ...>)

- 제네릭 타입은 두 개 이상의 멀티 타입 파라미터를 사용할 수 있는데, 이 경우 각 타입 파라미터를 콤마로 구분한다. 

  ```java
  public class Product<K, M> {
      private K kind;
      private M model;
  
      public K getKind(){
          return this.kind;
      }
      public M getModel(){
          return this.model;
      }
  
      public void setKind(K kind){
          this.kind = kind;
      }
      public void setModel(M model){
          this.model = model;
      }
  }
  ```

  ```java
  public class ProductExample {
      public static void main(String[] args) {
          Product<Tv, String> product1 = new Product<Tv, String>();
          product1.setKind(new Tv());
          product1.setModel("스마트 TV");
          Tv tv = product1.getKind();
          String tvModel = product1.getModel();
  
          Product<Car, String> product2 = new Product<Car, String>();
          product2.setKind(new Car());
          product2.setModel("디젤");
          Car car = product2.getKind();
          String carModel = product2.getModel();
  
  
          System.out.println(tv);
          System.out.println(tvModel);
          System.out.println();
          System.out.println(car);
          System.out.println(carModel);
      }
  }
  ```

<br>

## 13-4. 제네릭 메소드(<T, R> R method(T t))

- 제네릭 메소드는 매개 타입과 리턴 타입으로 타입 파라미터를 갖는 메소드를 말한다. 

- 제네릭 메소드를 선언하는 방법은 리턴 타입 앞에 < > 기호를 추가하고 타입 파라미터를 기술한 다음, 리턴 타입과 매개 타입으로 타입 파라미터를 사용하면 된다. 

  ```java
  public <타입파라미터, ...> 리턴타입 메소드명(매개변수, ...){...}
  ```

- 다음 boxing( ) 제네릭 메소드는 < > 기호 안에 타입 파라미터 T를 기술한 뒤, 매개 변수 타입으로 T를 사용했고, 리턴 타입으로 제네릭 타입 Box< T >를 사용했다.

  ```java
  public <T> Box<T> boxing(T t){ ... }
  ```

- 제네릭 메소드는 두 가지 방식으로 호출할 수 있다. 코드에서 **타입 파라미터의 구체적인 타입을 명시적으로 지정**해도 되고, **컴파일러가 매개값의 타입을 보고 구체적인 타입을 추정**하도록 할 수도 있다.

  ```java
  리턴타입 변수 = <구체적타입> 메소드명(매개값); //명시적으로 구체적 타입을 지정
  리턴타입 변수 = 메소드명(매개값);           //매개값을 보고 구체적 타입을 추정
  ```

  - 다음 코드는 boxing( ) 메소드를 호출하는 코드이다.

  ```java
  Box<Integer> box = <Integer>boxing(100);
  Box<Integer> box = boxing(100);
  ```

-----------------------

```java
public class Util2 {
    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2){
        boolean keyCompare = p1.getKey().equals(p2.getKey());
        boolean valueCompare = p1.getValue().equals(p2.getValue());
        return keyCompare && valueCompare;
    }
}
```

```java
public class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value){
        this.key = key;
        this.value = value;
    }

    public void setKey(K key){
        this.key = key;
    }
    public void setValue(V value){
        this.value = value;
    }

    public K getKey(){
        return key;
    }
    public V getValue(){
        return value;
    }
}
```

```java
public class CompareMethodExample {
    public static void main(String[] args) {
        Pair<Integer, String> p1 = new Pair<>(1, "사과");
        Pair<Integer, String> p2 = new Pair<>(1, "사과");
        boolean result1 = Util2.<Integer, String>compare(p1, p2);
        if(result1){
            System.out.println("논리적으로 동등한 객체입니다.");
        }else
            System.out.println("논리적으로 동등하지 않는 객체입니다.");

        Pair<String, String> p3 = new Pair<>("user1", "홍길동");
        Pair<String, String> p4 = new Pair<>("user2", "홍길동");
        boolean result2 = Util2.<String, String>compare(p3, p4);
        if(result2){
            System.out.println("논리적으로 동등한 객체입니다.");
        }else
            System.out.println("논리적으로 동등하지 않는 객체입니다.");
    }
}



//실행 결과
//논리적으로 동등한 객체입니다.
//논리적으로 동등하지 않는 객체입니다.
```



<br>

## 13-5. 제한된 타입 파라미터(<T extends 최상위타입)

- 타입 파라미터에 지정되는 **구체적인 타입을 제한할 필요가 종종 있다.**

- 제한된 타입 파라미터를 선언하려면 **타입 파라미터 뒤에 extends 키워드를 붙이고 상위 타입을 명시**하면 된다.

- 상위 타입은 클래스뿐만 아니라 인터페이스도 가능하다. **인터페이스라고 해서 implements를 사용하지 않는다.**

  ```java
  public <T extends 상위타입> 리턴타입 메소드(매개변수, ...) {...}
  ```

- 타입 파라미터에 지정되는 구체적인 타입은 상위 타입이거나 상위 타입의 하위 또는 구현 클래스만 가능하다. 주의할 점은 메소드의 중괄호 { } 안에서 **타입 파라미터 변수로 사용 가능한 것은 상위 타입의 멤버(필드, 메소드)로 제한**된다. 하위 타입에만 있는 필드와 메소드는 사용할 수 없다. 

- 다음은 숫자 타입만 구체적인 타입으로 갖는 **제네릭 메소드 compare( ) 이다.** 두 개의 숫자 타입을 매개값으로 받아 **차이를 리턴**한다.

  ```java
  public <T extends Number> int compare(T t1, T t2){
    double v1 = t1.doubleValue();  //Number의 doubleValue() 메소드 사용
    double v2 = t2.doubleValue();  //Number의 doubleValue() 메소드 사용
    return Double.compare(v1, v2);
  }
  ```

  - doubleValue( ) 메소드는 Number 클래스에 정의되어 있는 메소드로, 숫자를 double 타입으로 변환한다.
  - Double.compare( ) 메소드는 첫 번째 매개값이 작으면 -1을, 같으면 0을, 크면 1을 리턴한다.



<br>

## 13-6. 와일드카드 타입(<?>, <? extends ...>, <? super>)

- 코드에서 " ? " 를 일반적으로 와일드카드(wildcard)라고 부른다. 
- 제네릭 타입을 매개값이나 리턴 타입으로 사용할 때 구체적인 타입 대신에 와일드카드를 다음과 같이 세 가지 형태로 사용할 수 있다.
  - **제네릭타입<?>** : Unbounded Wildcards(제한 없음)
    - 타입 파라미터를 대치하는 구체적인 타입으로 모든 클래스나 인터페이스 타입이 올 수 있다.
  - **제네릭타입<? extends 상위타입>** : Upper Bounded Wildcards(상위 클래스 제한)
    - 타입 파라미터를 대치하는 구체적인 타입으로 상위 타입이나 하위 타입만 올 수 있다.
  - **제네릭타입<? super 하위타입>** : Lower Bounded Wildcards(하위 클래스 제한)
    - 타입 파라미터를 대치하는 구체적인 타입으로 하위 타입이나 상위 타입이 올 수 있다.



<br>

## 13-7. 제네릭 타입의 상속과 구현

- 제네릭 타입도 다른 타입과 마찬가지로 **부모 클래스가 될 수 있다.** 다음은 Product<T, M> 제네릭 타입을 상속해서 ChildProduct<T, M> 타입을 정의한다.

  ```java
  public class ChildProduct<T, M> extends Product<T, M> {...}
  ```

  - **자식 제네릭 타입은 추가적으로 타입 파라미터를 가질 수 있다.** 다음은 세 가지 타입 파라미터를 가진 자식 제네릭 타입을 선언한 것이다.

  ```java
  pulbic class ChildProduct<T, M, C> extends Product<T, M> {...}
  ```

  





























