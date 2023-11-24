package etudiant;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Etudiant {
	
	private int id;
    private String prenom;
    private String nom;
    private double noteMiSession;
    private double noteProjet;
    private double noteExamenFinal;
    private double moyenne;
    private String status;
   
    
    // construtor
    
    public Etudiant() {
       
    	setId(id);       
        setPrenom(prenom);
        setNom(nom);
        setNoteMiSession(noteMiSession);
        setNoteProjet(noteProjet);
        setNoteExamenFinal(noteExamenFinal);
        this.moyenne = calculerMoyenne();
        this.status = calculerSituation();
    }
    
    public Etudiant(int id, String prenom, String nom, double noteMiSession, double noteProjet, double noteExamenFinal,
    		double Moyenne, String status) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        setNoteMiSession(noteMiSession);
        setNoteProjet(noteProjet);
        setNoteExamenFinal(noteExamenFinal);
        this.moyenne = calculerMoyenne();
        this.status = calculerSituation();
    }
    
               
   /* public Etudiant(int id, String prenom, String nom, double noteMiSession, double noteProjet,
		double noteExamenFinal) {
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.noteMiSession = noteMiSession;
		this.noteProjet = noteProjet;
		this.noteExamenFinal = noteExamenFinal;
		
	}*/
    
    private void updateMoyenneAndStatus() {
        moyenne = 0.3 * noteMiSession + 0.3 * noteProjet + 0.4 * noteExamenFinal;
        if (moyenne >= 60.0) {
            status = "Approuvé";
        } else {
            status = "Échoué";
        }
    }


	
	public double calculerMoyenne() {
		double moy = 0.3*noteMiSession + 0.3*noteProjet + 0.4*noteExamenFinal;
		return moy;
	}
	
	public String calculerSituation() {
        if (moyenne >= 60.0) {
            return "Approuvé";
            }
        else {
            return "Échoué";
        }
    }
	
	
	//public List<Etudiant> getListEtudiant() {
		//return ListEtudiants;
	//}
	
	
    // getters e setters
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		if(isValidId(id)) {
			this.id = id;
			
		}
		else {
			System.out.println("Id invalide");
		}
				
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getNoteMiSession() {
		return noteMiSession;
	}

	public void setNoteMiSession(double noteMiSession) {
		
		try {
			if(noteMiSession>=0 && noteMiSession<=100) {
				this.noteMiSession = noteMiSession;
				 updateMoyenneAndStatus();
			}
			else {
				throw new IllegalArgumentException("Note invalide");
			}
		}catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
			System.out.println(e.getMessage());
	    }
		
	}

	public double getNoteProjet() {
		return noteProjet;
	}

	public void setNoteProjet(double noteProjet) {
		
		try {
			if(noteProjet>=0 && noteProjet<=100) {
				this.noteProjet = noteProjet;
				 updateMoyenneAndStatus();
			}
			else {
				throw new IllegalArgumentException("Note invalide");
			}
		}catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
			System.out.println(e.getMessage());
	    }
		
	}

	public double getNoteExamenFinal() {
		return noteExamenFinal;
	}

	public void setNoteExamenFinal(double noteExamenFinal) {
		
		try {
			if(noteExamenFinal>=0 && noteExamenFinal<=100) {
				this.noteExamenFinal = noteExamenFinal;
				 updateMoyenneAndStatus();
			}
			else {
				throw new IllegalArgumentException("Note invalide");
			}
		}catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
			System.out.println(e.getMessage());
	    }
		
	}

	public double getMoyenne() {
		return moyenne;
	}

	public void setMoyenne(double moyenne) {
		this.moyenne = moyenne;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
    public String toString() {
        return id + " - " + prenom + " " + nom + " - Moyenne: " + moyenne + " - Situation: " + status;
    }
	
	public static boolean isValidId(int id) {
			try {
				if (String.valueOf(id).length() != 4) {
					
					JOptionPane.showMessageDialog(null, "L'id doit avoir exactemente 4 chiffres");
					return false;
					
			}
				
				for (Etudiant etudiant : ControleEtudiant.le) {
					if (etudiant.getId() == id) {
						JOptionPane.showMessageDialog(null, "Il déjà existe un étudiant avec cet ID");
						return false;
				}
			}
			return true;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erreur: " + e.getMessage());
				JOptionPane.showMessageDialog(null, e.getMessage());
				return false;
			}
		}
	
	

}
