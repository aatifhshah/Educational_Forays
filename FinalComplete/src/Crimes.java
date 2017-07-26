
public class Crimes{

	private String crime;
	private int num;
	
	public void setCrime(String crime, int num){
		this.crime = crime;
		this.num = num;
	}
	public String getCrime(){
		return crime;
	}
	public int getnum(){
		return num;
	}
	public int compareTo(int i) {
		
		if(this.getnum() > i){
			return 1;
		}
		else
			return -1;

	}
	
}