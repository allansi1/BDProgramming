package f.dataBinary;

public class Employee  {
	 
	   public static final int version = 1;
	
	   private String name;
	   private String address;
	   private transient int SSN;  //transient have no effect here. Just a "comment" the field must not be saved. 
	   private int number;
	   
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getSSN() {
		return SSN;
	}
	public void setSSN(int sSN) {
		SSN = sSN;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	   	   
}
