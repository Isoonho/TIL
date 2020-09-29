# chapter16. 스트림과 병렬 처리

## 16-1. 스트림 소개

- **스트림(Stream)**은 자바 8부터 추가된 컬렉션 (배열 포함)의 저장 요소를 하나씩 참조해서 람다식(함수적-스타일(functional-style)) 으로 처리할 수 있도록 해주는 **반복자**이다. 



<br>

### 16-1-1. 반복자 스트림

- 자바 7이전까지는 List\<String> 컬렉션에서 요소를 순차적으로 처리하기 위해 Iterator 반복자를 다음과 같이 사용해왔다.

  ```java
  List<String> list = Arrays.asList("홍길동", "신용권", "감자바");
  Iterator<String> iterator = list.iterator();
  while(iterator.hasNext()){
    String name = iterator.next();
    System.out.println(name);
  }
  ```

  - 위 코드를 Stream을 사용해서 변경하면 다음과 같다.

  ```java
  List<String> list = Arrays.asList("홍길동", "신용권", "감자바");
  Stream<String> stream = list.stream();
  stream.forEach(name -> System.out.println(name));
  ```

- 컬렉션 (java.util.Collection)의 stream( ) 메소드로 스트림 객체를 얻고 나서 stream.forEach(name->System.out.println(name)); 메소드를 통해 컬렉션의 요소를 하나씩 콘솔에 출력한다. 

- forEach( ) 메소드는 다음과 같이 Consumer 함수적 인터페이스 타입의 매개값을 가지므로 컬렉션의 요소를 소비할 코드를 람다식으로 기술할 수 있다.

  ```java
  void forEach(Conumer<T> action)
  ```

- Iterator를 사용한 코드와 Stream을 사용한 코드를 비교해보면 Stream을 사용하는 것이 훨씬 단순해 보인다.