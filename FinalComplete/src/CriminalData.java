import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import com.itextpdf.text.Image;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.Scanner;

public class CriminalData {

private static final boolean recoverFromImageError = false;
Scanner scan = new Scanner(System.in);
Stack<String> reverseQueue = new Stack<String>();
PriorityQueue queue = new PriorityQueue();
Crimes newCrime = new Crimes();
ChainedData PDFLocation[] = new ChainedData[10];
ChainedData Links = new ChainedData();
private String name = ""; 
private String age = "";
private String gender = "";
private String dateofbirth = "";
private String height = "";
private String weight = "";
private String hair = "";
private String eyes = "";

private String PDFLink = "";
private String image = "";

public CriminalData(){
for(int k = 0; k < PDFLocation.length; k++){
PDFLocation[k] = new ChainedData();
}
}
private void setName(){	
System.out.println("Enter Criminal's name: ");
name = scan.nextLine();
}
private void setAge() {
System.out.println("Age: ");
age = scan.nextLine();
}

private void setGender() {
System.out.println("Gender: ");
gender = scan.nextLine();
}
private void setCrimes() {
System.out.println("Enter End to finish crimes:");
String text = "";
int intensity;
while(!text.equals("End")){
text = scan.nextLine();
if(!text.equals("End")){
intensity = Integer.parseInt(scan.nextLine());
newCrime = new Crimes();
newCrime.setCrime(text, intensity);
queue.push(newCrime);
}
}
   }

private void setHeight() {
System.out.print("Height: ");
height = scan.nextLine();
}
private void setWeight() {
System.out.print("Weight: ");
weight = scan.nextLine();
}
private void setHair() {
System.out.print("Hair color: ");
hair = scan.nextLine();
}	
private void setEyes() {
System.out.print("Eye color: ");
eyes = scan.nextLine();
}
private void popQueue() {
while(queue.getMax() != null){
String myList = queue.pop();
reverseQueue.push(myList);	
}
}
public void Add(){
setName(); 
setAge();
setGender();
setCrimes();  
setHeight();
setWeight();
setHair();
setEyes();
popQueue();
}
public void test(){
setName();
}
    public void buildPDF(){
   
String FILE = "C:/Users/Public/Database/" + this.name + ".pdf";
Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,Font.BOLD);
Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.NORMAL, BaseColor.BLUE);
Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,Font.BOLD);
Font small = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.NORMAL);
   
    try {
    Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream(FILE));
    document.open();
    
    
    addMetaData(document);

   
Paragraph preface = new Paragraph();
image = "C:/Users/Public/Database/"+this.name+".jpg";


Image i = Image.getInstance(image, recoverFromImageError);
document.add(i);
addEmptyLine(preface, 2);
preface.add(new Paragraph(this.name, catFont));
addEmptyLine(preface, 1);

preface.add(new Paragraph("Age: " + this.age, small));
preface.add(new Paragraph("Gender: " + this.gender, small));
preface.add(new Paragraph("Crimes (listed in order of severity of offense): ", small));
while(reverseQueue.entries() != -1){
preface.add(new Paragraph(reverseQueue.pop(), blueFont));
}
preface.add(new Paragraph("Height: " + this.height, small));
preface.add(new Paragraph("Weight: " + this.weight, small));
preface.add(new Paragraph("Eye Color: " + this.eyes, small));
document.add(preface);
// Start a new page
document.newPage();    	
   
    document.close();
     
    if (Desktop.isDesktopSupported()) {
   	   try {
   	       File myFile = new File("C:/Users/Public/Database/" + this.name + ".pdf");
   	       this.PDFLink = "C:/Users/Public/Database/" + this.name + ".pdf"; 
   	       SavePDFLocation();
   	       Desktop.getDesktop().open(myFile);
   	   } catch (IOException ex) {
   	   ex.printStackTrace();
   	   // no application registered for PDFs
   	   }
   	}
    } catch (Exception e) {
     e.printStackTrace();
   }
 }
   
 private static void addMetaData(Document document) {
   document.addTitle("My first PDF");
   document.addSubject("Using iText");
   document.addKeywords("Java, PDF, iText");
 }
 private static void addEmptyLine(Paragraph paragraph, int number) {
   for (int i = 0; i < number; i++) {
     paragraph.add(new Paragraph(" "));
   }
 }

public void SavePDFLocation(){
PDFLocation[Links.HashFunc(name)].add(name, PDFLink);
}
public void retrieve(){
 
 System.out.println("Enter Criminal's First Name: ");
 String name = scan.nextLine();
 
 String s = PDFLocation[Links.HashFunc(name)].getFile(name);
 System.out.println(s);
 
 	if (Desktop.isDesktopSupported()) {
   	   try {
   	       File myFile = new File(s);

   	       Desktop.getDesktop().open(myFile);
   	   } catch (IOException ex) {
   	   ex.printStackTrace();
   	   // no application registered for PDFs
   	   }
   	}	 
}
 
public void retrieveHC(){
 
 System.out.println("Enter Criminal's First Name: ");
 String check = scan.nextLine();
 

 	if (Desktop.isDesktopSupported()) {
   	   try {
   	       File myFile = new File("C:/Users/Public/Database/" + check + ".pdf");
   	       Desktop.getDesktop().open(myFile);
   	   } catch (IOException ex) {
   	   ex.printStackTrace();
   	   // no application registered for PDFs
   	   }
   	}
 
 
 
 }
}