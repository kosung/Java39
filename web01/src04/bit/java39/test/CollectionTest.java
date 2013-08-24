package bit.java39.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;

public class CollectionTest {

	public static void main(String[] args) {
		System.out.println("ArrayList:___________________________");
		test01();
		
		System.out.println("HashSet:___________________________");
		test02();

		System.out.println("HashMap:___________________________");
		test03();
		
		System.out.println("Hashtable:___________________________");
		test04();
		
		System.out.println("Iterator:___________________________");
		test05();
	}
	
	public static void test01() {
		/* - 순서대로 객체를 관리 
		 * - null 값을 가질 수 있다.
		 * - 중복된 값을 가질 수 있다.
		 */
		String s1 = "홍길동";
		String s2 = "임꺽정";

		ArrayList<String> list = new ArrayList<String>();
		list.add(s1);
		list.add(null);
		list.add(s2);
		list.add(null);
		list.add(s1);
		
		for (String item : list) {
			System.out.println(item);
		}
		
		System.out.println(list);
	}
	
	public static void test02() {
		/* - 순서에 상관없이 hashcode로 값을 보관한다.
		 * - null 값을 가질 수 있다.
		 * - 값이 중복되지 않는다.
		 */
		String s1 = "홍길동";
		String s2 = "임꺽정";
		
		HashSet<String> set = new HashSet<String>();
		set.add(s1);
		set.add(null);
		set.add(s2);
		set.add(null);
		set.add(s1);
		
		for(String item : set) {
			System.out.println(item);
		}
	}
	
	public static void test03() {
		/*
		 * 키를 이용하여 값을 저장
		 * - key 객체는 반드시 hashCode()와 equals()를 재정의한 객체여야 한다.
		 * - 키와 값으로 null을 사용할 수 있다.
		 * - 값의 중복 허용.
		 * - 순서를 지키지 않는다.
		 * - 데이터 조회 동기화 안됨.
		 *   => 데이터를 읽은 중에 데이터 추가 시, 조회 시작 후에 추가된 것은 조회 못함.
		 * - Hashtable에 비해 속도가 빠르다.
		 */
		
		String s1 = "홍길동";
		String s2 = "임꺽정";
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("100", s1);
		map.put("200", null);
		map.put("300", s2);
		map.put("400", s1);
		map.put(null, "일지매");
		
		for(String item : map.values()) {
			System.out.println(item);
		}
	}
	
	public static void test04() {
		/*
		 * 키를 이용하여 값을 저장
		 * - key 객체는 반드시 hashCode()와 equals()를 재정의한 객체여야 한다.
		 * - 키와 값으로 null 사용 불가!
		 * - 값의 중복 허용.
		 * - 순서를 지키지 않는다.
		 * - 데이터 조회 동기화 됨.
		 *   => 데이터를 읽은 중에 데이터 추가 시, 추가된 데이터도 조회할 수 있음.
		 */
		Hashtable<String,String> table = new Hashtable<String,String>();
		table.put("100", "홍길동");
		//table.put("200", null);
		table.put("300", "임꺽정");
		table.put("400", "홍길동");
		//table.put(null, "일지매");
		
		for(String item : table.values()) {
			System.out.println(item);
		}
	}
	
	public static void test05() {
		/* Iterator 패턴 
		 * - 데이터를 직접 꺼내는 것이 아니라.
		 * - 데이터 꺼내는 역할을 분리하여 정의
		 * - 데이터를 꺼내는 방식이 달라도 => 꺼내는 쪽에서는 동일한 메서드를 사용한다. 
		 */
		
		class MyList {
			int[] list = {10, 5, 7, 3, 1, 6};
			
			public Iterator iterator() {
				//return new AscIterator(this.list);
				return new DescIterator(this.list);
			}
		}
		
		
		
		MyList box = new MyList();
		
		//방법1. 직접 목록에 접근해서 꺼낸다.
		/*
		for(int i = 0; i < box.list.length; i += 2) {
			System.out.println(box.list[i]);
		}
		*/
		
		//방법2. 꺼내는 전문가에게 맡긴다.
		Iterator 꺼내는전문가 = box.iterator();
		while(꺼내는전문가.hasNext()) {
			System.out.println(꺼내는전문가.next());
		}
	}

}

interface Iterator {
	boolean hasNext();
	Object next();
}

class AscIterator implements Iterator {
	int[] list;
	int cursor = 0;
	
	public AscIterator(int[] list) {
		this.list = list;
	}
	
	public boolean hasNext() {
		if (list != null && cursor < list.length) {
			return true;
		} else {
			return false;
		}
	}
	
	public Object next() {
		Integer result = new Integer(list[cursor]);
		cursor++;
		return result;
	}
}

class DescIterator implements Iterator {
	int[] list;
	int cursor = 0;
	
	public DescIterator(int[] list) {
		this.list = list;
		cursor = this.list.length - 1;
	}
	
	public boolean hasNext() {
		if (list != null && cursor >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public Object next() {
		Integer result = new Integer(list[cursor]);
		cursor--;
		return result;
	}
}
































