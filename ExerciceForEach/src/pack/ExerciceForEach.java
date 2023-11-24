package pack;

public class ExerciceForEach {

	public static void main(String[] args) {
	float [] t = {2,4,8,16,32};
	for (float u:t) {System.out.print(u+" ");}
	System.out.println();
	
	System.out.println("Et maintenant:");
	for (float u:t) {u=0;}
	for (float u:t) {System.out.print(u+" ");}
	System.out.println();
	}

}
