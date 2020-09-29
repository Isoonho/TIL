# chapter15. 컬렉션 프레임워크

## 15-1. 컬렉션 프레임워크 소개

- 애플리케이션을 개발하다 보면 다수의 객체를 저장해 두고 필요할 때마다 꺼내서 사용하는 경우가 많다.

- 이런 상황에서 배열은 쉽게 생성하고 사용할 수 있지만, 저장할 수 있는 객체 수가 배열을 생성할 때 결정되기 때문에 **불특정 다수의 객체를 저장하기에는 문제가 있다.**

- 자바는 배열의 이러한 문제점을 해결하고, 널리 알려져 있는 자료구조(Data Structure)를 바탕으로 객체들을 효율적으로 추가, 삭제, 검색할 수 있도록 java.util 패키지에 컬렉션과 관련된 인터페이스와 클래스들을 포함시켜 놓았다.

- 이들을 총챙해서 **컬렉션 프레임워크(Collection Framework)**라고 부른다.

- 컬렉션이란 사전적 의미로 요소를 수집해서 저장하는 것을 말하는데, **자바 컬렉션은 객체를 수집해서 저장하는 역할**을 한다. **프레임워크란 사용 방법을 미리 정해 놓은 라이브러리**를 말한다.

  ![image-20200213212746502](/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200213212746502.png)

![image-20200213212811552](/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200213212811552.png)



<br>

## 15-2. List 컬렉션

- **List 컬렉션은 객체를 일렬로 늘어놓은 구조**를 가지고 있다.

- 객체를 인덱스로 관리하기 때문에 객체를 저장하면 **자동 인덱스가 부여**되고 인덱스로 객체를 검색, 삭제할 수 있는 기능을 제공한다.

- List 컬렉션은 객체의 번지를 참조한다. 또한 동일한 객체를 중복 저장할 수 있는데, 이 경우 동일한 번지가 참조된다. 

- null도 저장이 가능한데, 이 경우 해당 인덱스는 개체를 참조하지 않는다.

  <img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200213213255301.png" alt="image-20200213213255301" style="zoom:67%;" />

- List 컬렉션에는 ArrayList, Vector, LinkedList 등이 있는데, 다음은 List 컬렉션에서 공통적으로 사용 가능한 List 인터페이스의 메소드들이다. 

- 인덱스로 객체를 관리하기 때문에 **인덱스를 매개값으로 갖는 메소드가 많다.**

  ![image-20200213213413710](/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200213213413710.png)



- 다음은 List 컬렉션에 저장되는 구체적인 타입을 String으로 정해놓고, 추가, 삽입, 찾기 그리고 삭제하는 방법을 보여준다.

  ```java
  List<String> list = ... ;
  list.add("홍길동");            //맨끝에 객체 추가
  list.add(1, "신용권");         //지정된 인덱스에 객체 삽입
  String str = list.get(1);    //인덱스로 객체 찾기
  list.remove(0);              //인덱스로 객체 삭제
  list.remove("신용권");         //객체 삭제
  ```

  - 만약 전체 객체를 대상으로 하나씩 반복해서 저장된 객체를 얻고 싶다면 다음과 같이 for 문을 사용할 수 있다. 

  ```java
  List<String> list = ... ;
  for(int i=0; i<list.size(); i++){
    String str = list.get(i);
  }
  ```

  - 인덱스 번호가 필요 없다면 향상된 for문을 이용하는 것이 더욱 편리하다.

  ```java
  for(String str : list){
    
  }
  ```



<br>

### 15-2-1. ArrayList

- ArrayList는 List 인터페이스의 구현 클래스로, ArrayList에 객체를 추가하면 **객체가 인덱스로 관리**된다. 

- 일반 배열과 ArrayList는 인덱스로 객체를 관리한다는 점에서는 유사하지만, 큰 차이점을 가지고 있다. 배열은 생성할 때 크기가 고정되고 사용 중에 크기를 변경할 수 없지만, ArrayList는 **저장 용량(capacity)을 초과한 객체들이 들어오면 자동적으로 저장 용량이 늘어난다는 것이다.**

  <img src="/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200213215740418.png" alt="image-20200213215740418" style="zoom:67%;" />

- ArrayList를 생성하기 위해서는 저장할 객체 타입을 타입 파라미터로 표기하고 기본 생성자를 호출하면 된다. 

  ```java
  List<String> list = new ArrayList<String>();
  ```

  - 기본 생성자로 ArrayList 객체를 생성하면 내부에 10개의 객체를 저장할 수 있는 초기 용량을 가지게 된다. 저장되는 객체 수가 늘어나면 **용량이 자동적으로 증가**하지만, 처음부터 용량을 크게 잡고 싶다면 **용량의 크기를 매개값으로 받는 생성자를 이용**하면 된다.

  ```java
  List<String> list = new ArrayList<String>(30);
  ```



- ArrayList에 객체를 추가하면 인덱스 0부터 차례대로 저장된다. ArrayList에서 특정 인덱스의 객체를 제거하면 바로 뒤 인덱스부터 마지막 인덱스까지 모두 앞으로 1씩 당겨진다. 마찬가지로 특정 인덱스에 객체를 삽입하면 해당 인덱스부터 마지막 인덱스까지 모두 1씩 밀려난다. 

  ![image-20200213220845410](/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200213220845410.png)



- ArrayList를 생성하고 런타임 시 필요에 의해 객체들을 추가하는 것이 일반적이지만, **고정된 객체들로 구성된 List를 생성할 때도 있다.**

  ```java
  List<T> list = Arrays.asList(T...a);
  ```



<br>

### 15-2-2. Vector

- Vector는 ArrayList와 **동일한 내부 구조**를 가지고 있다. 

- Vector를 생성하기 위해서는 저장할 객체 타입을 타입 파라미터로 표기하고 기본 생성자를 호출하면 된다.

  ```java
  List<E> list = new Vector<E>();
  ```

- ArrayList와 다른 점은 **Vector는 동기화된(synchronized) 메소드로 구성**되어 있기 때문에 멀티 스레드가 동시에 이 메소드들을 실행할 수 없고, **하나의 스레드가 실행을 완료해야만 다른 스레드를 실행**할 수 있다.

- 그래서 멀티 스레드 환경에서 안전하게 객체를 추가, 삭제할 수 있다. 이것을 **스레드가 안전(Thread Safe)하다**라고 말한다.

![image-20200215151706030](/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200215151706030.png)



<br>

### 15-2-3. LinkedList

- LinkedList는 List 구현 클래스이므로 ArrayList와 사용 방법은 똑같지만 내부 구조는 완전 다르다. 
- ArrayList는 내부 배열에 객체를 저장해서 인덱스로 관리하지만, **LinkedList는 인접 참조를 링크해서 체인처럼 관리**한다.

![image-20200215152820174](/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200215152820174.png)



- LinkdeList에서 특정 인덱스의 객체를 제거하면 앞뒤 링크만 변경되고 나머지 링크는 변경되지 않는다. 
- **빈번한 객체 삭제와 삽입이 일어나는 곳에서는 ArrayList보다는 LinkedList가 좋은 성능을 발휘**한다.



![image-20200215153018566](/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200215153018566.png)



- LinkedList를 생성하기 위해서는 저장할 객체 타입을 타입 파라미터(E)에 표기하고 기본 생성자를 호출하면 된다. 

- LinkedList가 처음 생성될 때에는 어떠한 링크도 만들어지지 않기 때문에 내부는 비어 있다고 보면 된다.

  ```java
  List<E> list = new LinkedList<E>();
  ```



- 순차적으로 추가/삭제 하는 경우는 ArrayList가 빠르지만, 중간에 추가 또는 삭제할 경우는 앞뒤 링크 정보만 변경하면 되는 LinkedList가 더 빠르다. 

- ArrayList는 뒤쪽 인덱스들을 모두 1씩 증가 또는 감소시키는 시간이 필요하므로 처리 속도가 느리다. 

  | 구분       | 순차적으로 추가 / 삭제 | 중간에 추가 / 삭제 | 검색   |
  | ---------- | ---------------------- | ------------------ | ------ |
  | ArrayList  | 빠르다                 | 느리다             | 빠르다 |
  | LinkdeList | 느리다                 | 빠르다             | 느리다 |



<br>

## 15-3. Set 컬렉션

- List 컬렉션은 저장 순서를 유지하지만, **Set 컬렉션은 저장 순서가 유지되지 않는다.** 또한 객체를 중복해서 저장할 수 없고, 하나의 null만 저장할 수 있다. 

  ![image-20200215172734500](/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200215172734500.png)

- Set 컬렉션에는 HashSet, LinkedList, TreeSet 등이 있는데, 다은은 Set 컬렉션에서 공통적으로 사용 가능한 set 인터페이스의 메소드들이다. 

- 인덱스로 관리하지 않기 때문에 **인덱스를 매개값으로 갖는 메소드가 없다.**

  ![image-20200215172852855](/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200215172852855.png)

- Set 인터페이스는 제네릭 타입이다. 구체적인 타입은 구현 객체를 생성할 때 결정된다.

  ```java
  Set<String> set = ... ;
  set.add("홍길동");          //객체 추가
  set.add("신용권");
  set.remover("홍길동");      //객체 삭제
  ```



- Set 컬렉션은 인덱스로 객체를 검색해서 가져오는 메소드가 없다. 대신, **전체 객체를 대상으로 한 번씩 반복해서 가져오는 반복자(Iterator)를 제공**한다. 

  ```java
  Set<String> set = ... ;
  Iterator<String> iterator = set.iterator();
  ```



| 리턴 타입 | 메소드명    | 설명                                                         |
| --------- | ----------- | ------------------------------------------------------------ |
| boolean   | hashNext( ) | 가져올 객체가 있으면 true를 리턴하고 없으면 false를 리턴한다. |
| E         | next( )     | 컬렉션에서 하나의 객체를 가져온다.                           |
| void      | remove( )   | Set 컬렉션에서 객체를 제거한다.                              |

- Iterator에서 하나의 객체를 가져올 때는 **next( ) 메소드**를 사용한다. next( ) 메소드를 사용하기 전에 먼저 가져올 객체가 있는지 확인하는 것이 좋다.

- **hashNext( ) 메소드**는 가져올 객체가 있으면 ture를 리턴하고, 더 이상 가져올 객체가 없으면 false를 리턴한다. 따라서 true가 리턴될 때 next( ) 메소드를 사용해야 한다. 

  ```java
  Set<String> set = ... ;
  Iterator<String> iterator = set.iterator();
  while(iterator.hasNext()){
    //String 객체 하나를 가져옴
    String str = itreator.next();
  }
  ```

  - Iterator를 사용하지 않더라도 향상된 for문을 이용해서 전체 객체를 대상으로 반복할 수 있다.

  ```java
  Set<String> set = ... ;
  for(String str : set){
    //저장된 객체 수만큼 루핑한다.
  }
  ```

- Set 컬렉션에서 Iterator의 next( ) 메소드로 가져온 객체를 제거하고 싶다면 remove( ) 메소드를 호출하면 된다. 

- **Iteratord의 메소드이지만, 실제 Set 컬렉션에서 객체가 제거됨을 알아야 한다.**

  ```java
  while(iterator.hasNext()){
    String str = iterator.next();
    if(str.equals("홍길동")){
      iterator.remove();
    }
  }
  ```



<br>

### 15-3-1. HashSet

- HashSet은 Set 인터페이스의 구현 클래스이다. HashSet을 생성하기 위해서는 다음과 같이 **기본 생성자를 호출**하면 된다.

  ```java
  Set<E> set = new HashSet<E>();
  ```

  - 타입 파라미터 E에는 컬렉션에 저장할 객체 타입을 지정하면 된다. 

  ```java
  Set<String> set = new HashSet<String>();
  ```

- HashSet은 **객체들을 순서 없이 저장**하고 **동일한 객체는 중복 저장하지 않는다.** 

  ![image-20200215175744246](/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200215175744246.png)

- 문자열을 HashSet에 저장할 경우, 같은 문자열을 갖는 String 객체는 동등한 객체로 간주되고 다른 문자열을 갖는 String 객체는 다른 객체로 간주되는데, 그 이유는 String 클래스가 hashCode( )와 equals( ) 메소드를 재정의해서 같은 문자열일 경우 hashCode( )의 리턴값을 같게, equals( )의 리턴값은 true가 나오도록 했기 때문이다.



<br>

## 15-4. Map 컬렉션

- Map 컬렉션은 키(key)와 값(value)으로 구성된 Entry 객체를 저장하는 구조를 가지고 있다. 

- 여기서 키와 값은 모두 객체이다. **키는 중복 저장될 수 없지만 값은 중복 저장될 수 있다.** 만약 기존에 저장된 키와 동일한 키로 값을 저장하면 기존의 값은 없어지고 새로운 값으로 대치된다. 

  ![image-20200215211421436](/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200215211421436.png)

- Map 컬렉션에는 HashMap, HashTable, LinkedHashMap, Properties, TreeMap 등이 있다. 

- 다음은 Map 컬렉션에서 공통적으로 사용 가능한 Map 인터페이스의 메소드들이다. 키로 객체들을 관리하기 때문에 **키를 매개값으로 갖는 메소드가 많다.**

  ![image-20200215211547255](/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200215211547255.png)

- **구체적인 타입은 구현 객체를 생성할 때 결정된다.**

  ```java
  Map<String, Integer> map = ... ;
  map.put("홍길동", 30);            //객체 추가
  int score = map.get("홍길동");    //객체 찾기
  map.remove("홍길동");             //객체 삭제
  ```

- 키를 알고 있다면 get( ) 메소드로 간단하게 객체를 찾아오면 되지만, 저장된 전체 객체를 대상으로 하나씩 얻고 싶을 경우에는 두 가지 방법을 사용할 수 있다.

  - 첫 번째는 keySet( ) 메소드로 모든 키를 Set 컬렉션으로 얻은 다음, 반복자를 통해 키를 하나씩 얻고 get( ) 메소드를 통해 값을 얻으면 된다.

  ```java
  Map<K, V> map = ... ;
  Set<K> keySet = map.keySet();
  Iterator<K> keyIterator = keySet.iterator();
  while(keyIterator.hasNext()){
    K key = keyIterator.next();
    V value = map.get(key);
  }
  ```

  - 두 번째 방법은 entrySet( ) 메소드로 모든 Map.Entry를 Set 컬렉션으로 얻은 다음, 반복자를 통해 Map.Entry를 하나씩 얻고 getKey( )와 getValue( ) 메소드를 이용해 키와 값을 얻으면 된다. 

  ```java
  Set<Map.Entry<K, V>> entrySet = map.entrySet();
  Iterator<Map.Entry<K, V>> entryIterator = entrySet.Iterator();
  while(entryIterator.hasNext()){
    Map.Entry<k, V> entry = entryIterator.next();
    K key = entry.getKey();
    V value = entry.getValue();
  }
  ```



<br>

### 15-4-1. HashMap

- HashMap은 Map 인터페이스를 구현한 대표적인 Map 컬렉션이다. 

- HashMap의 키로 사용할 객체는 **hashCode( )와 equals( ) 메소드를 재정의해서 동등 객체가 될 조건을 정해야 한다.** 

- 동등 객체, 즉 동일한 키가 될 조건은 hashCode( )의 리턴값이 같아야 하고, equals( ) 메소드가 true를 리턴해야 한다. 

  ![image-20200215213701129](/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200215213701129.png)

- 주로 키 타입은 String을 많이 사용하는데, String은 문자열이 같을 경우 동등 객체가 될 수 있도록 hashCode( )와 equals( ) 메소드가 재정의되어 있다.

- HashMap을 생성하기 위해서는 키 타입과 값 타입을 파라미터로 주고 **기본 생성자를 호출**하면 된다.

  ```java
  Map<K, V> map = new HashMap<K, V>(); //K가 키 타입, V가 값 타입
  ```

  - 키와 값의 타입은 기본 타입(byte, short, int float, double, boolean, char)을 사용할 수 없고, **클래스 및 인터페이스 타입만 가능**하다.

  ```java
  Map<String, Integer> map = new HashMap<String, Integer>();
  ```



<br>

### 15-4-2. Hashtable

- Hashtable은 HashMap과 동일한 내부 구조를 가지고 있다. 

- Hashtable도 **키로 사용할 객체는 hashCode( )와 equals( ) 메소드를 재정의해서 동등 객체가 될 조건을 정해야 한다.**

- HashMap과의 차이점은 **Hashtable은 동기화된(synchronized) 메소드로 구성**되어 있기 때문에 멀티 스레드가 동시에 이 메소드들을 실행할 수 없고, **하나의 스레드가 실행을 완료해야만 다른 스레드를 실행할 수 있다.**

  ![image-20200217160605289](/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200217160605289.png)

- Hashtable의 생성 방법은 HashMap과 크게 다르지 않다. 키 타입과 값 타입을 지정하고 기본 생성자를 호출하면 된다.

  ![image-20200217160644448](/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200217160644448.png)

- 키로 String 타입을 사용하고, 값으로 Integer 타입을 사용하는 Hashtable은 다음과 같이 생성할 수 있다.

  ```java
  Map<String, Integer> map = new Hashtable<String, Integer>();
  ```



<br>

### 15-4-3. Properties











<br>

## 15-5. 검색 기능을 강화시킨 컬렉션

- 컬렉션 프레임워크는 검색 기능을 강화시킨 TreeSet과 TreeMap을 제공하고 있다. 이름에서 알 수 있듯이 TreeSet은 Set 컬렉션이고, TreeMap은 Map 컬렉션이다.
- 이 컬렉션들은 **이진 트리(binary tree)를 이용해서 계층적 구조(Tree 구조)를 가지면서 객체를 저장**한다.

<br>

### 15-5-1. 이진 트리 구조

- 이진 트리(binary tree)는 여러 개의 노드(node)가 트리 형태로 연결된 구조로, 루트 노드라고 블리는 하나의 노드에서부터 시작해서 각 노드에 최대 2개의 노드를 연결할 수 있는 구조를 가지고 있다. 

- 하나의 부모 노드는 최대 두 개의 자식 노드와 연결될 수 있다. 

- 첫 번째로 저장되는 값은 루트 노드가 되고, 두 번째 값은 루트 노드부터 시작해서 값의 크기를 비교하면서 트리를 따라 내려간다. 작은 값은 왼쪽에, 큰 값은 오른쪽에 저장한다. 

  ![image-20200217223133720](/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200217223133720.png)

- 이진 트리가 범위 검색을 쉽게할 수 있는 이유는 그림에서 보는 것과 같이 값들이 정렬되어 있어 그룹핑이 쉽기 때문이다.



<br>

### 15-5-2. TreeSet

- TreeSet은 이진 트리를 기반으로한 Set 컬렉션이다. 

- 하나의 노드는 **노드값인 value**와 **왼쪽과 오른쪽 자식 노드를 참조하기 위한 두 개의 변수**로 구성된다.

  ![image-20200217223317980](/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200217223317980.png)

- TreeSet을 생성하기 위해서는 저장할 객체 타입을 파라미터로 표기하고 기본 생성자를 호출하면 된다.

  ```java
  TreeSet<E> treeSet = new TreeSet<E>();
  ```

- 다음은 TreeSet이 가지고 있는 검색 관련 메소드들이다.

  | 리턴 타입 | 메소드       | 설명                                                         |
  | --------- | ------------ | ------------------------------------------------------------ |
  | E         | first( )     | 제일 낮은 객체를 리턴                                        |
  | E         | last( )      | 제일 높은 객체를 리턴                                        |
  | E         | lower(E e)   | 주어진 객체보다 바로 아래 객체를 리턴                        |
  | E         | higher(E e)  | 주어진 객체보다 바로 위 객체를 리턴                          |
  | E         | floor(E e)   | 주어진 객체와 동등한 객체가 있으면 리턴, 만약 없다면 주어진 객체의 바로 아래의 객체를 리턴 |
  | E         | ceiling(E e) | 주어진 객체와 동등한 객체가 있으면 리턴, 만약 없다면 주어진 객체의 바로 위의 객체를 리턴 |
  | E         | pollFirst( ) | 제일 낮은 객체를 꺼내오고 컬렉션에서 제거함                  |
  | E         | pollLast( )  | 제일 높은 객체를 꺼내오고 컬렉션에서 제거함                  |



- 다음은 TreeSet이 가지고 있는 정렬과 관련된 메소드들이다.

  | 리턴 타입         | 메소드                | 설명                                    |
  | ----------------- | --------------------- | --------------------------------------- |
  | Iterator< E >     | descendingIterator( ) | 내림차순으로 정렬된 Iterator를 리턴     |
  | NavigableSet< E > | descendingSet( )      | 내림차순으로 정렬된 NavigableSet을 반환 |

  - NavigableSet은 TreeSet과 마찬가지로 검색 메소드들을 제공하고, 정렬 순서를 바꾸는 descendingSet( )메소드도 제공한다. 
  - 오름차순으로 정렬하고 싶다면 다음과 같이 **descendingSet( ) 메소드를 두 번 호출**하면 된다.
  
  ```java
  NavigableSet<E> descendingSet = treeSet.descendingSet();
  NavigableSet<E> ascendingSet = descendingSet.descendingSet();
  ```
  
- TreeSet 메소드가 가지고 있는 범위 검색 메소드 중에서 subSet( ) 메소드의 사용 방법을 살펴보기로 하자.

  - subSet( ) 메소드는 네 개의 매개 변수가 있는데, 시작 객체와 끝 객체, 그리고 이 객체들을 포함할지 여부의 boolean 값을 받는다. 

<br>

### 15-5-3. TreeMap

- TreeSet과의 차이점은 **키와 값이 저장된 Map.Entry를 저장**한다는 점이다.

- TreeMap에 객체를 저장하면 자동으로 정렬되는데, 기본적으로 부모 키값과 비교해서 키 값이 낮은 것은 왼쪽 자식 노드에, 키 값이 높은 것은 오른쪽 자식 노드에 Map.Entry 객체를 저장한다. 

  ![image-20200220150907080](/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200220150907080.png)

- TreeMap을 생성하기 위해서는 **키로 저장할 객체 타입과 값으로 저장할 객체 타입을 타입 파라미터로 주고 기본 생성자를 호출**하면 된다.

  ```java
  TreeMap<K, V> treeMap = new TreeMpa<K, V>();
  ```

  - 키로 String 타입을 사용하고 값으로 Integer 타입을 사용하는 TreeMap은 다음과 같이 생성할 수 있다.

  ```java
  TreeMap<String, Integer> treeMap = new TreeMap<>();
  ```



<br>

### 15-5-4. Comparable과 Comparator

- **TreeSet의 객체와 TreeMap의 키는 저장과 동시에 자동 오름차순으로 정렬**되는데, 숫자(Integer, Double) 타입일 경우에는 값으로 정렬하고, 문자열(String) 타입일 경우에는 유니코드로 정렬한다.

- 사용자 정의 클래스도 Comparable을 구현한다면 자동 정렬이 가능하다. Comparable에는 compareTo( ) 메소드가 정의되어 있기 때문에 사용자 정의 클래스에서는 이 메소드를 오버라이딩하여 다음과 같이 리턴 값을 만들어 내야 한다.

  | 리턴 타입 | 메소드         | 설명                                                         |
  | --------- | -------------- | ------------------------------------------------------------ |
  | int       | compareTo(T o) | 주어친 객체와 같으면 0을 리턴, 주어진 객체보다 적으면 음수를 리턴, 주어진 객체보다 크면 양수를 리턴 |









<br>

## 15-6. LIFO와 FIFO 컬렉션

- 후입선출(LIFO : Last In FIrst Out)은 나중에 넣은 객체가 먼저 빠져나가는 자료구조를 말한다.
- 선입선출(FIFO : First In First Out)은 먼저 넣은 객체가 먼저 빠져나가는 구조를 말한다.
- 컬렉션 프레임워크에서는 LIFO 자료구조를 제공하는 **스택(Stack)**클래스와 FIFO 자료구조를 제공하는 **큐(Queue) 인터페이스를 제공**하고 있다.

<br>

### 15-6-1. Stack

- 다음은 Stack 클래스의 주요 메소드들이다.

  ![image-20200220162630915](/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200220162630915.png)



- Stack 객체를 생성하기 위해서는 저장할 객체 타입을 파라미터로 표기하고 기본 생성자를 호출하면 된다.

  ![image-20200220162712898](/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200220162712898.png)



<br>

### 15-6-2. Queue

- Queue 인터페이스는 FIFO 자료구조에서 사용되는 메소드를 정의하고 있다. 

  ![image-20200220163929243](/Users/hwangsunho/Library/Application Support/typora-user-images/image-20200220163929243.png)



- Queue 인터페이스를 구현한 대표적인 클래스는 LinkedList이다. LinkedList는 List 인터페이스를 구현했기 때문에 List 컬렉션이기도 하다.

- 다음 코드는 LinkedList 객체를 Queue 인터페이스 타입으로 변환한 것이다.

  ```java
  Queue<E> queue = new LinkedList<E>();
  ```



<br>

## 15-7. 동기화된 컬렉션

- 컬렉션 프레임워크의 대부분의 클래스들은 싱글 스레드 환경에서 사용할 수 있도록 설계되었다. 그렇기 때문에 여러 스레드가 동시에 컬렉션에 접근한다면 의도하지 않게 요소가 변경될 수 있는 불안전한 상태가 된다. 
- Vector와 HashTable은 동기화된(synchronized) 메소드로 구성되어 있기 때문에 멀티 스레드 환경에서 안전하게 요소를 처리할 수 있지만, ArrayList, HashSet, HashMap은 동기화된 메소드로 구성되어 있지 않아 멀티 스레드 환경에서 안전하지 않다.

























































































