package bit.java39.test.step7;

public class MyArrayList {
	ObjectBin start;

	public void add(Object obj) {
		ObjectBin bin = new ObjectBin();
		bin.value = obj;
		
		if (start == null) {
			start = bin;
		} else {
			ObjectBin temp = start;
			while (temp.nextBin != null) {
				temp = temp.nextBin;
			}
			temp.nextBin = bin;
		}
	}
	
	public Object get(int index) {
		int cursor = 0;
		ObjectBin temp = start;
		
		while (temp != null) {
			if (index == cursor) {
				return temp.value;
			}
			cursor++;
			temp = temp.nextBin;
		}
		
		return null;
	}
	
	public Object remove(int index) {
		int cursor = 0;
		ObjectBin temp = start;
		ObjectBin prevBin = null;
		while (temp != null) {
			if (index == cursor) {
				prevBin.nextBin = temp.nextBin;
				return temp.value;
			}
			cursor++;
			prevBin = temp;
			temp = temp.nextBin;
		}
		
		return null;
	}
	
	public int size() {
		int cursor = 0;
		ObjectBin temp = start;
		
		while (temp != null) {
			cursor++;
			temp = temp.nextBin;
		}
		
		return cursor;
	}
	

}
