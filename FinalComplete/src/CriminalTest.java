import java.util.Scanner;

public class CriminalTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CriminalData data = new CriminalData();
		
		boolean Running = true;
		String option = "";
		String user = "";
		Scanner scan = new Scanner(System.in);
		
		
		
		
		
		System.out.println("Choose a numbered option:");
		System.out.println("'Add Profile (1)', 'Retrieve (2)', or 'End (3)' or 'RetrieveHC (4)'");
		
		while(Running){
			
			option = scan.nextLine();
			switch(option){
				case "1":
					System.out.println("Adding profile, please provide prompted data.");
					data.Add();
					data.buildPDF();
					break;
				case "2":
					System.out.println("Retrieve");
					data.retrieve();
					break;
				case "3":
					System.out.println("End");
					Running = false;
					break;
				case "4":
					data.retrieveHC();
					break;
				default:
					System.out.println("Error, Try Again");
					break;
			}
			
									
		
		}
	}
}