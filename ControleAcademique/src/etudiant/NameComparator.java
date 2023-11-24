package etudiant;

import java.util.*;

public class NameComparator implements Comparator<Etudiant>{

	
	NameComparator(){
		
	}
	public int compare(Etudiant e1, Etudiant e2) {
		
		//comparer les noms
		int r = e1.getNom().compareTo(e2.getNom());
		if(r==0) {
		//Si les noms sont égaux, on compare les prenoms
			r = e1.getPrenom().compareTo(e2.getPrenom());
		}
		//Si les prenoms sont égaux, on compare les ids
		if(r==0) {
			r = e1.getId()- e2.getId();
		}
		return r;
		
	}

}
