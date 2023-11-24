package pack;


public class Student{
	private int id;
	private String nom;
	private Integer note;
	
	public Student (int id, String nom, int note) {
		this.id = id;
		this.nom = nom;
		this.note = note;
	}
	
	public Student (int id, String nom) {
		this.id = id;
		this.nom = nom;
		
	}
	public int getId() {
	return this.id;
	}
	
	public String getNom() {
		return this.nom;
	}
	public int getNote() {
		return this.note;
	}
	
	public void setNote(int note) {
		if (note < 0) {
			this.note = null;
			
		}
		else {
			this.note = note;
		}
		
	}
// toString padrão do sistema foi trocado por esse personalizado:	
	@Override
	public String toString() {
		return "[Student: id = " + id + ", nom = " + nom + ", note = " + note + "]";
	}
	
	
	
}
