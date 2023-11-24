package pack;

public class ExerciceOverload {

	public static int succ(int i) {
		return i+1;
	}
	
	public static char succ (char i) {
		return (char)(i+1);
	}
	
	public static void main(String[] args) {
		
		System.out.println(succ(5));
		System.out.println(succ('d'));
		
	}

}
