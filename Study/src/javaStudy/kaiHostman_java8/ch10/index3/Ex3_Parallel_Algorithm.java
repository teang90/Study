package javaStudy.kaiHostman_java8.ch10.index3;

// 병렬 알고리즘
public class Ex3_Parallel_Algorithm {
	public static void main(String[] args) {
		
	}

	/** 병렬 스트림
	 * 스트림 라이브러리는 거대한 병렬 스트림의 연산을 자동으로 병렬화한다.
	 * 
	 * parallelStreamㅇ 메소드는 병렬 스트림을 돌려준다. 병렬 스트림은 여러 세그먼트로 나뉜다.
	 * 그리고 각 세그먼트에서 필터링과 카운팅을 수행하고 결과들을 결합한다.
	 */
	
	/**
	 * 병렬 배열 연산
	 * Arrays 클래스는 다수의 병렬화된 연산을 지원한다.
	 * 앞서 살펴본 병렬 스트림 연산과 같이 병렬 배열 연산은 배열을 여러 부분으로 나눠 병렬로 처리한 후 결과를 결합한다.
	 * 
	 * 정적 메소드 Arrays.parallelSetAll은 전달받은 함수에서 계산한 값으로 배열을 채운다.
	 * 이 메소드가 전달 받은 함수는 요소 인덱스를 받고 해당 위치에서 계산한다.
	 * 
	 * Arrays.parallelSetAll(values, i -> i%10);  // 0,1,2,3,4,5,6,7,8,9,0,1,2,3... 으로 채움
	 * 
	 * parallelSort 메소드는 기본타입 값이나 객체의 배열을 정렬할 수 있다.
	 * Arryas.parallelSort(words, Camparator.comparing(String::length));
	 * 
	 * 정수로 구성된 배열의 합계 계산
	 * long sum = IntStream.of(values).parallel().sum();
	 * 
	 * 특정 디렉토리 아래에 특정 문자가 몇번 나타나는지 횟수의 총합
	 * Stream<Path> paths = Files.walk(pathOfRoot);
	 * paths.parallel().mapToInt(return p에서 단어가 나타난 횟수).sum();
	 */
	
	/** 스레드 안전 자료구조
	 * 여러 스레드에서 동시에 큐, 해시테이블의 자료구조를 수정하면 자료구조의 내부가 손상되기 쉽다.
	 * 예로서, 어떤 스레드에서 새로운 요소를 삽입한다. 그리고, 이 스레드가 링크를 수정하는 도중에 
	 * 선점되어 또다른 스레드가 같은 위치에서 순회를 시작한다고하자.
	 * 이때 두번째 스레드에서 유효하지 않은 링크를 따라가다가는 잘못된 결과를 도출할 수 있다.
	 * 
	 *  잠금을 이용해서 한 시점에 한 스레드에서만 자료구조에 접근 할 수 있게 하고, 다른 스레드에서는 블로킹하도록 보장할 수 있다.
	 *  -> 이 방법은 비효율적이다.
	 *  대신 java.util.concurrent 패키지의 컬렉션을 활용하자.
	 *  
	 *  ★★★★★ ★★★★★ ★★★★★
	 *  ConcurrentModificationException <- java.util 패키지에 들어있는 컬렉션의 반복자는 해당 반복자가 생성된 후, 컬렉션이 수정되면 해당 예외를 던진다.
	 *  
	 *  1) 병행 해시맵 
	 *  ConcurrentHashMap은 스레드가 안전한 연산을 할 수 있게 해주는 해시맵이다. 
	 *  이는 상당수의 병행리더(concurrent reader)와 일정 개수의 병행 라이터(concurrent writer)를 지원한다.
	 *  
	 *  카운트를 업데이트하는 psuedo code이며, 스레드에 안전하지 않은 코드다.
	 *  ConcurrentHashMap<String, Long> map = new ConcurrenthashMap<>();
	 *  ...
	 *  Long oldVal = map.get(word);
	 *  Long newVal = oldVal == null?1:oldVal+1;
	 *  map.put(word,newVal); // 오류! -> oldVal를 교체하지 않을 수도 있다.
	 *  -> 또 다른 스레드에서 동시에 정확히 같은 카운트를 업데이트할 수 있기 때문이다.
	 *  
	 *  해결책> 값을 안전하게 업데이트하려면 compute()를 이용하자.
	 *  compute()를 호출할 때는 키와 새 값을 계산하는 함수를 전달해야한다. 해당 함수는 키와 해당 키에 연관된 값(없을때는 null)을 받고, 새 값을 계산한다.
	 *  
	 *  예> map.compute(word, (k,v)->v==null?1:v+1);
	 *  compute는 원자적이다. 따라서 계산을 수행하는 도중에는 다른 어떤 스레드에서도 해당 맵의 엔트리를 변경할 수 없다.
	 *  
	 *  computeIfPresent, computeIfAbsent라는 변형도 있음 -> 각각 기존의 값이 있거나 없을때만 새로운 값을 계산한다.
	 *  +) putIfAbset도 원자적 계산을한다. -> map.putIfAbsent(word, 0L);
	 *  키가 처음 추가될때 무너가 특별한 작업을 해야할떄가 있다.
	 *  merge 메소드를 이용하면 이런 상황에 편리하다. 이 메소드는 키가 아직 없을때 사용할 초기값을 파라미터로 받는다.
	 *  키가 있을 때는 지정한 함수를 호출해서 기존값과 초기값을 결합한다.
	 *  
	 *  map.merge(word, 1L, (existingValue, newValue)-> existingValue + newValue); 
	 *  또는
	 *  map.merger(word, 1L, Long::sum);
	 *  
	 *  이때 주의할 점은 compute와 merge에 전달한 함수는 계산을 빠르게 완료해야하며, 해당 맵을 변경하려는 시도는 하지 말아야 한다.
	 *  
	 *  벌크연산은 ConcurrentHashMap을 검색, 변형, 방문하는데 사용한다. search, reduce, forEach로 시작하는 연산을 참고할것
	 *  
	 *   
	 *  
	 *  
	 *  
	 * 
	 */
	
}
