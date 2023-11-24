package etudiant;

import java.util.Comparator;

public class IdComparator implements Comparator<Etudiant>{
	public IdComparator() {
		
	}
	public int compare(Etudiant e1, Etudiant e2) {
		int r;
		r = e1.getId()- e2.getId();
		return r;
	}

}
