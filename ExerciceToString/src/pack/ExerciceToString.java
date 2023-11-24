package pack;


public class ExerciceToString {
	
	
	
	public static void main(String[] args) {
		Student s1 = new Student(1,"Juliana",70);
			System.out.println(s1.getId());
			System.out.println(s1.getNom());
			System.out.println(s1.getNote());
			s1.setNote(80);
			System.out.println(s1.getNote());
			
			System.out.println(s1);
			Student s2 = new Student(3,"Hernani");
			System.out.println(s2);
			
			
			
		}
	
}



