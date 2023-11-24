package etudiant;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ControleEtudiant {
	
	public static ArrayList<Etudiant> le = new ArrayList<Etudiant>();
	
	
	public static boolean sauvegarde(Etudiant e) {
		if(e != null) {
			le.add(e);
			System.out.println(le.size());
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null, "Veillez remplir correctemente tous les champs");
			return false;
		}
	}
	
	public static ArrayList <Etudiant> returnList(){
		return le;
	}
	
	public static void addEtudiant(Etudiant e) {
		le.add(e);
    }
    
    public static void removeEtudiant(Etudiant e) {
    	le.remove(e);
    }
    
    public static void clearList() {
    	le.clear();
    }
   
    
    

}
