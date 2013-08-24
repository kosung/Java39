package bit.java39.test;

public class TeamMaker {
	static int[] studentList = new int[20];
	static int[] absentList = {
		11, 19
	};
	
	static int cursor = 0;
	
	public static void main(String[] args) {
		int selectedNo = -1;
		while (cursor < 20) {
			selectedNo = (int)(Math.random() * 20 + 1);
			if (isExist(selectedNo)) {
				continue;
			} else {
				studentList[cursor] = selectedNo;
				cursor++;
			}
		}
		
		printTeam();
	}
	
	public static boolean isExist(int no) {
		for (int i = 0; i < cursor; i++) {
			if (studentList[i] == no) {
				return true;
			}
		}
		
		return false;
	}
	
	public static void printTeam() {
		for (int i = 0; i < cursor; i += 2) {
			System.out.print(getLabel(studentList[i]));
			System.out.print(",");
			System.out.println(getLabel(studentList[i+1]));
		}
	}
	
	public static String getLabel(int no) {
		for (int i = 0; i < absentList.length; i++) {
			if (absentList[i] == no) {
				return "X";
			}
		}
		
		return Integer.toString(no);
	}

}
