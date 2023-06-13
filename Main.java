package hw02;
/**
 * This class implements all of the methods from the PPMImage class through an
 * organized text menu which reads an image and outputs an image copy in 
 * grayscale, sepia, or negative. The menu also contains a logout option which
 * exits the menu and ends the program.
 * <br />
 * 
 * @author Roger Roldan, 400986323, CS 2013- 01/02
 * 
 * 
 */
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		System.out.println("------------------------------------");
		System.out.println("Please enter your desired file path: ");
		System.out.println("------------------------------------");
		
		/**
		 * Scanner take input of the image's file name and uses it as a
		 * parameter to invoke the PPM Image contructor.
		 */
		Scanner input = new Scanner(System.in);
	    String image = input.next();
		PPMImage obj = new PPMImage(image);
		
		
		while(true) {
			System.out.println("Enter 1 for image insertion");
			System.out.println("Enter 2 to change image to grayscale");
			System.out.println("Enter 3 to change image to sepia");
			System.out.println("Enter 4 to change image to negative");
			System.out.println("Enter 5 to logout");
			int choice = input.nextInt();
			
			switch(choice) {
			
			case 1:
				System.out.println("Enter file name: ");
				input = new Scanner(System.in);
				image = input.next();
				obj = new PPMImage(image);
				break;
				
			case 2:
				obj.grayScale();
				obj.writeImage(image);
				break;
			
			case 3:
				obj.sepia();
				obj.writeImage(image);
				break;
			
			case 4:
				obj.negative();
				obj.writeImage(image);
				break;
			
			case 5:
				System.out.println("Logged out");
				System.exit(0);
			
			default: System.out.println("Please enter a correct input.");
			
			}
		}
		
	}

}
package hw02;
/**
 * This class implements all of the methods from the PPMImage class through an
 * organized text menu which reads an image and outputs an image copy in 
 * grayscale, sepia, or negative. The menu also contains a logout option which
 * exits the menu and ends the program.
 * <br />
 * 
 * @author Roger Roldan, 400986323, CS 2013- 01/02
 * 
 * 
 */
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		System.out.println("------------------------------------");
		System.out.println("Please enter your desired file path: ");
		System.out.println("------------------------------------");
		
		/**
		 * Scanner take input of the image's file name and uses it as a
		 * parameter to invoke the PPM Image contructor.
		 */
		Scanner input = new Scanner(System.in);
	    String image = input.next();
		PPMImage obj = new PPMImage(image);
		
		
		while(true) {
			System.out.println("Enter 1 for image insertion");
			System.out.println("Enter 2 to change image to grayscale");
			System.out.println("Enter 3 to change image to sepia");
			System.out.println("Enter 4 to change image to negative");
			System.out.println("Enter 5 to logout");
			int choice = input.nextInt();
			
			switch(choice) {
			
			case 1:
				System.out.println("Enter file name: ");
				input = new Scanner(System.in);
				image = input.next();
				obj = new PPMImage(image);
				break;
				
			case 2:
				obj.grayScale();
				obj.writeImage(image);
				break;
			
			case 3:
				obj.sepia();
				obj.writeImage(image);
				break;
			
			case 4:
				obj.negative();
				obj.writeImage(image);
				break;
			
			case 5:
				System.out.println("Logged out");
				System.exit(0);
			
			default: System.out.println("Please enter a correct input.");
			
			}
		}
		
	}

}
