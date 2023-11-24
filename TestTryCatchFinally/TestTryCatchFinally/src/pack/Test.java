package pack;

public class Test {

	public static void f(int i) {
		try {
			System.out.println("Point 0");
			if (i==1) {return ;} 
			if (i==2) { throw new RuntimeException("i==2");}
			System.out.println("Point 1");
		}
		catch (Exception e) {
			System.out.println("Point 2 - catch");
		}
		finally {
			System.out.println("Point 3 - finally");
		}
	}
	
	public static void main(String[] args) {
		System.out.println("with i=0");
		f(0);
		System.out.println();
		System.out.println("with i=1");
		f(1);
		System.out.println();
		System.out.println("with i=2");
		f(2);
		System.out.println();
	}

}
