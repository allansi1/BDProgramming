
public class ExerciceAndOr {

	public static void main(String[] args) {
		double x =0;
		if ((x!=0)&&((1/x)>0.25)){
			System.out.println("OK");
			
		}
		else {
			System.out.println("Not Ok");
		}
		
		//other option to do the same
		if (x!=0) {
			if ((1/x)>0.25) {
				System.out.println("OK");
			}
			else {
				System.out.println("Not OK");
			}
		}else {
			System.out.println("Not OK");
						
			
		}
		
		// or now
		
		if ((x==0)||((1/x)<=0.25)){
			System.out.println("Not OK");
			
		}
		else {
			System.out.println("Ok");
		}
		
				
		}
	
	}

	

