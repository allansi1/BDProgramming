package pack;

public class ExerciceArrayAssign {

	public static void main(String[] args) {
		int [] a= {1,2,3};
		int [] b= {4,5};
		b=a;
		System.out.println("lenght of b = " +b.length);
		for (int i=0;i<b.length;++i) {
		System.out.println("b["+i+ "]" +b[i]);
		}
		a[0]=10;
		System.out.println("a[0]="+a[0]);
		for (int i=0;i<b.length;++i) {
			System.out.println("b["+i+"]= "+b[i]);
		}
	}

}
