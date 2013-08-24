package bit.java39.test.step7;

public class ListTest {

	public static void main(String[] args) {
		MyArrayList list = new MyArrayList();
		list.add("홍길동");
		list.add("임꺽정");
		list.add(new Integer(20));
		
		System.out.println(list.size());
		print(list);
		
		list.remove(1);
		System.out.println(list.size());
		print(list);
	}
	
	public static void print(MyArrayList list) {
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

}
